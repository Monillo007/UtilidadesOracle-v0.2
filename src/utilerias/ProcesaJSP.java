/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package utilerias;

import interfacebd.InterfaceBD;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;
import superclases.Combo;

/**
 *
 * @author zeus
 */
class ProcesaJSP {

    String tabla = null;
    boolean incluyeFecha = false;
    String[] campos;
    String[] tipos;
    int[] tamanos;
    ResultSetMetaData rsmd;
    ResultSet resultado;
    String[] tiposString = new String[]{"VARCHAR","VARCHAR2","NVARCHAR",
                                        "NVARCHAR2","CHAR","NCHAR",
                                        "CHARACTER","CHARACTER VARYING"};
    String[] tiposBigDecimal = new String[]{"NUMBER","INTEGER","DECIMAL",
                                          "INT","SMALLINT","LONG","FLOAT"};
    String[] tiposDate = new String[]{"DATE","TIME","TIMESTAMP"};
    String[] tiposFile = new String[]{"LONG RAW","BLOB","CLOB","NCLOB","BFILE","RAW"};

    public ProcesaJSP(String tabla, ArrayList<String> camposA) {
        this.tabla = tabla;
        this.campos = camposA.toArray(new String[camposA.size()]);
        System.out.println("tabla: "+tabla);
        doAll();
    }

    private void doAll() {
        String consulta = "SELECT * FROM "+tabla;
        try
        {
            Statement SentenciaSQL =
                InterfaceBD.getConexion().createStatement(
                                                          ResultSet.TYPE_FORWARD_ONLY,
                                                          ResultSet.CONCUR_READ_ONLY);

            resultado = SentenciaSQL.executeQuery(consulta);
            rsmd = resultado.getMetaData();
            setCampos();
            setTipos();
            setTamanos();
            createFile();
        }catch(Exception e){
            //e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al leer la tabla '"+tabla+"'.\n"+e.getMessage());
        }
    }

    public void setCampos() throws SQLException{
        campos = new String[rsmd.getColumnCount()];
        for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                String campo = rsmd.getColumnName(i);
                if(i==1)VariablesGlobales.setIdTabla(campo);
                campos[i-1] = campo;
        }
    }

    public void setTamanos() throws SQLException{
        tamanos = new int[rsmd.getColumnCount()];
        for (int i = 1; i <= rsmd.getColumnCount(); i++) {
            int tamano = rsmd.getPrecision(i);
            tamanos[i-1] = tamano;
        }
    }

    public void setTipos() throws SQLException{
        tipos = new String[rsmd.getColumnCount()];
        for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                String tipo = rsmd.getColumnTypeName(i);
                for (String tip : tiposString) {
                    if(tipo.equalsIgnoreCase(tip)){
                        tipos[i-1] = "String";
                    }
                }
                for (String tip : tiposBigDecimal) {
                    if(tipo.equalsIgnoreCase(tip)){
                        tipos[i-1] = "BigDecimal";
                    }
                }
                for (String tip : tiposDate) {
                    if(tipo.equalsIgnoreCase(tip)){
                        tipos[i-1] = "Date";
                        incluyeFecha = true;
                    }
                }
                for (String tip : tiposFile) {
                    if(tipo.equalsIgnoreCase(tip)){
                        tipos[i-1] = "File";
                    }
                }
        }
    }

    private void createFile() throws IOException {
        File jspFile = new File(VariablesGlobales.getRutaDestino()+"\\frm"+VariablesGlobales.getNombreArchivo()+".jsp");
        if(jspFile.exists()){
            jspFile.delete();
        }

        jspFile.createNewFile();
        FileWriter fichero = new FileWriter(jspFile);
        PrintWriter pw = new PrintWriter(fichero);

        /***** CABECERA *****/
        pw.println("<%@ page contentType=\"text/html; charset=ISO-8859-1\" language=\"java\" import=\""+VariablesGlobales.getImportString()+"\" errorPage=\"\"%>");
        pw.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");
        pw.println("<% ");
        pw.println("        if (session.getAttribute(\"idUserSession\") == null) {");
        pw.println("            response.sendRedirect(\"/login.jsp?mensaje=No Existe Sesion Activa\");");
        pw.println("        }");
        pw.println("        String idSesion = session.getId();");
        pw.println("        String idUser = (String) session.getAttribute(\"idUserSession\");");
        pw.println("        int sistema = 0; //MODIFICAR");
        pw.println("        int modulo = 0; //MODIFICAR");
        pw.println("        String actoresAcceso = \"*1*2*3*4*5*6*\";");
        pw.println("");
        pw.println("        Usuario usuarioLog = new Usuario();");
        pw.println("        int verifica = usuarioLog.verificarSistema(idUser, idSesion, sistema, modulo, actoresAcceso);");
        pw.println("        int actor = ControlUsuarios.getTipoActor(idUser, sistema, modulo);");
        pw.println("");
        pw.println("        if (verifica == 2) {");
        pw.println("            session.invalidate();");
        pw.println("            response.sendRedirect(\"/login.jsp?mensaje=No Existe Sesion Activa\");");
        pw.println("        }");
        pw.println("");
        pw.println("        if (verifica == 1) {");
        pw.println("%> ");
        pw.println("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
        pw.println("<head>");
        pw.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\" />");
        pw.println("<title>"+VariablesGlobales.getNombreArchivo()+"</title>");

        /***** DEFINICION DE ESTILOS *****/
        
        pw.println("<jsp:include page=\"/Modulos/jsp_part/includes_v2.jsp?p=.gritter."+(incluyeFecha ? "calendar.":"")+(VariablesGlobales.getSelUsuario().size() > 0 ? "ui.":"")+"\"></jsp:include>");
        pw.println("<script type='text/javascript' src='../Javascript/js"+VariablesGlobales.getNombreArchivo()+".js'></script>");

        /***** INICIALIZACION VARIABLES JAVA *****/
        pw.println("<%");        
        pw.println("String accion = \"C\";");
        pw.println("String "+campos[0]+" = \"\";");

        //objeto de la clase
        pw.println(tabla+" "+tabla.toLowerCase()+" = null;");

        pw.println("     InterfaceBDPool bd = new InterfaceBDPool();");
        pw.println("     bd.setEsquema(\""+VariablesGlobales.getEsquema()+"\");");
        pw.println("     bd.bdConexion();");
        pw.println("     InterfaceRegistrosBDPool registros = new InterfaceRegistrosBDPool();");
        pw.println("     registros.setConexion(bd.getConexion());");

        pw.println("if(request.getParameter(\""+campos[0]+"\")!=null && !request.getParameter(\""+campos[0]+"\").equals(\"\")){");
        pw.println("     "+campos[0]+" = request.getParameter(\""+campos[0]+"\");");
        pw.println("     accion = \"M\";");
        pw.println("     "+tabla.toLowerCase()+" = ("+tabla+")registros.getRegistro(\""+VariablesGlobales.getPaqClases()+"."+tabla+"\", \""+campos[0]+" = \"+"+campos[0]+");");
        pw.println("     request.setAttribute(\"reg\", "+tabla.toLowerCase()+");");
        int i = 0;

        pw.println("");
        pw.println("}");
        pw.println("%>");//FIN DE INICIALIZACION DE VARIABLES JAVA

        pw.println("");
        pw.println("<jsp:useBean id=\"reg\" scope=\"request\" class=\""+VariablesGlobales.getPaqClases()+"."+tabla+"\" type=\""+VariablesGlobales.getPaqClases()+"."+tabla+"\"></jsp:useBean>");
        pw.println("</head>");
        pw.println("");

        /***** CUERPO DEL DOCUMENTO *****/
        pw.println("<body>");
        //pw.println("<table align=\"center\" border=\"1px\" bordercolor=\"#000033\" width=\"95%\" style=\"background-color:#d9e0f0;\"><tr><td>");
        pw.println("<div class=\"contenedor\">");
        pw.println("<form method=\"POST\" id=\""+VariablesGlobales.getNombreFormulario()+"\" action='GUARDAR REGISTRO'>");
        pw.println("     <table id=\"tblDatos\" align=\"center\" class='AzulTabla'>");

        pw.println("          <colgroup style='width:33%'></colgroup> ");
        pw.println("          <colgroup style='width:33%'></colgroup> ");
        pw.println("          <colgroup style='width:33%'></colgroup> ");
        pw.println("          <thead>");
        pw.println("          <tr>");
        pw.println("               <th colspan=\"3\" align=\"center\" class='Titulo'>");
        pw.println("                    "+VariablesGlobales.getTituloTabla());
        pw.println("               </th>");
        pw.println("          </tr>");
        pw.println("          </thead>");
        pw.println("          <tbody>");

        int fila = 0;
        double totalFilas = (VariablesGlobales.getTotalCamposVisibles() - VariablesGlobales.getCamposOcultos().size()) /3;
        int totalFilasInt = 0;
        int indiceNombres = 0;

        if(totalFilas > (int)totalFilas){
            totalFilasInt = (int)totalFilas + 1;
        }else{
            totalFilasInt = (int)totalFilas;
        }
        ArrayList<String> camposFila = new ArrayList<String>();
        ArrayList<String> requeridoFila = new ArrayList<String>();
        ArrayList<String> tamanoFila = new ArrayList<String>();
        ArrayList<String> tipoFila = new ArrayList<String>();

        for (int j = 0; j < campos.length; j++) {
            String campo = campos[j];
            String requerido = "";
            String reqAst = "";

            if(!VariablesGlobales.getCamposOcultos().contains(campo)){
                indiceNombres++;
                camposFila.add(campo);
                //se verifica si el campo es requerido
                if(VariablesGlobales.getRequeridos().contains(campo)){
                    requerido = " required";
                    reqAst = "* ";
                }

                requeridoFila.add(requerido);
                tamanoFila.add(""+tamanos[j]);
                tipoFila.add(tipos[j]);

                if(indiceNombres%3 == 1){
                    pw.println("          <tr>");
                }

                pw.println("               <td class='NombreDeCampo'>"+reqAst+campo+"</td>");

                if(indiceNombres%3 == 0 || fila == totalFilasInt){
                    pw.println("          </tr>");

                    pw.println("          <tr>");
                    int indiceDatos = 0;
                    for (String c : camposFila) {
                        boolean isCombo = false;
                        boolean isSelUsuario = false;
                        boolean isSelPersona = false;
                        boolean isCatalogo = false;
                        boolean isEstado = false;
                        boolean isMunicipio = false;
                        boolean isColonia = false;
                        boolean isCalle = false;
                        Combo combo = null;
                        String ajaxEdoMpio = "";
                        String ids = "";

                        if(VariablesGlobales.getCatalogos().contains(c)){
                            isCatalogo = true;
                        }
                        if(VariablesGlobales.getSelUsuario().contains(campo)){
                            isSelUsuario = true;
                        }
                        if(VariablesGlobales.getSelPersona().contains(campo)){
                            isSelPersona = true;
                        }
                        if(VariablesGlobales.getEstados().contains(c)){
                            isEstado = true;

                            if(!VariablesGlobales.getMunicipios().isEmpty()){
                                ajaxEdoMpio = " ajaxestado";
                                ids= " idmunicipio = '"+VariablesGlobales.getMunicipios().get(0)+"'";
                            }
                        }
                        if(VariablesGlobales.getMunicipios().contains(c)){
                            isMunicipio = true;

                            if(!VariablesGlobales.getColonias().isEmpty() || !VariablesGlobales.getCalles().isEmpty()){
                                ajaxEdoMpio = " ajaxmunicipio";
                                if(!VariablesGlobales.getColonias().isEmpty()){
                                    ids= " idcolonia = '"+VariablesGlobales.getColonias().get(0)+"'";
                                }
                                if(!VariablesGlobales.getCalles().isEmpty()){
                                    ids= ids.concat(" idcalle = '"+VariablesGlobales.getCalles().get(0)+"'");
                                }
                            }
                        }
                        if(VariablesGlobales.getCalles().contains(c)){
                            isCalle = true;
                        }
                        if(VariablesGlobales.getColonias().contains(c)){
                            isColonia = true;
                        }

                        for (Iterator it = VariablesGlobales.getCombos().iterator(); it.hasNext();) {
                            Combo com = (Combo) it.next();
                            if(c.equals(com.toString())){
                                isCombo = true;
                                combo = com;
                            }
                        }

                        if(isCombo){
                            pw.println("               <td>");
                            pw.println("                   <%");
                            pw.println("                        out.println(UtilHTML.getComboRegistros(\""+combo.getTabla().trim()+"\", \"ESTATUSREGISTRO <> 99\", \""+combo.getEtiqueta().trim()+"\", \""+combo.getId().trim()+"\", \""+combo.getEtiqueta().trim()+"\", \"\"+reg.get"+c+"(), \"class='Combo"+requeridoFila.get(indiceDatos)+ajaxEdoMpio+"' "+ids+" id='"+c+"' name='"+c+"' \"));");
                            pw.println("                   %>");
                            if(isCatalogo){
                                pw.println("                   <img class='catadmon' comboid='"+combo.getId().trim()+"' e='<%=Utilerias.Encrypter.encrypt(\"DBCATALOGO\")%>' t='<%=Utilerias.Encrypter.encrypt(\"Catalogo."+combo.getTabla().trim()+"\")%>' src='/Imagenes/png/addSmall.png' /> <!--MODIFICAR EN CASO DE SER NECESARIO-->");
                            }
                            pw.println("               </td>");
                        }else if(tipoFila.get(indiceDatos).equals("String")){
                            if(isSelUsuario){
                                pw.println("               <td><input type=\"text\" name=\""+c+"\" id=\""+c+"\" class=\"CuadroTextoSL"+requeridoFila.get(indiceDatos)+" width90\" value=\"${reg."+c+"}\" maxlength='"+tamanoFila.get(indiceDatos)+"' readonly='readonly'/>");
                                pw.println("                   <img border='0' src='/Imagenes/icons/buscarUsuario.png' alt='Buscar' class='selecusuario' rfcid='"+c+"' nombreid=''/>");
                            }if(isSelPersona){
                                pw.println("               <td><input type=\"text\" name=\""+c+"\" id=\""+c+"\" class=\"CuadroTextoSL"+requeridoFila.get(indiceDatos)+" width90\" value=\"${reg."+c+"}\" maxlength='"+tamanoFila.get(indiceDatos)+"' readonly='readonly'/>");
                                pw.println("                   <img border='0' src='/Imagenes/icons/buscarPersona.png' alt='Buscar' class='selecpersona' rfcid='"+c+"' nombreid='' personaid=''/>");
                            }else{
                                pw.println("               <td><input type=\"text\" name=\""+c+"\" id=\""+c+"\" class=\"CuadroTexto"+requeridoFila.get(indiceDatos)+" width90\" value=\"${reg."+c+"}\" maxlength='"+tamanoFila.get(indiceDatos)+"' />");
                            }
                            pw.println("               </td>");
                        }else if(tipoFila.get(indiceDatos).equals("BigDecimal")){
                            pw.println("               <td><input type=\"text\" name=\""+c+"\" id=\""+c+"\" class=\"CuadroTexto digits "+requeridoFila.get(indiceDatos)+" width90\" maxlength='5' value=\"${reg."+c+"}\"/></td>");
                        }else if(tipoFila.get(indiceDatos).equals("Date")){
                            pw.println("               <td>");
                            pw.println("                    <input type=\"text\" name=\""+c+"\" id=\""+c+"\" value=\"<%=Util.convierteFechaMostrar(reg.get"+c+"(),true)%>\" class=\"CuadroTexto "+requeridoFila.get(indiceDatos)+"\" />");
                            pw.println("                    <img src=\"/Imagenes/icons/calendario.png\" alt=\"CALENDARIO\" id=\"boton_"+c+"\" class=\"imgCalendario calendario\" align=\"bottom\" title=\"SELECCION DE FECHA\" />");
                            pw.println("               </td>");
                        }
                        indiceDatos++;
                    }
                    pw.println("          </tr>");
                    fila ++;
                    camposFila.clear();
                    requeridoFila.clear();
                    tamanoFila.clear();
                    tipoFila.clear();
                }                
            }
        }

        pw.println("          </tbody>");
        pw.println("     </table>");
        pw.println("<div align=\"center\">");
        for(Iterator it = VariablesGlobales.getCamposOcultos().iterator(); it.hasNext();){
            String campo = (String)it.next();
            if(!campo.equals("USUARIOCREACION") && !campo.equals("USUARIOMODIFICACION") && !campo.equals("FECHACREACION") && !campo.equals("FECHAMODIFICACION")){
                pw.println("          <input type=\"hidden\" id=\""+campo+"\" name=\""+campo+"\" value=\"${reg."+campo+"}\"/>");
            }
        }
        pw.println("          <input type=\"hidden\" id=\"accion\" name=\"accion\" value=\"<%=accion%>\"/>");
        pw.println("          <input type=\"hidden\" id=\"tablaBD\" name=\"tablaBD\" value=\""+VariablesGlobales.getPaqClases()+"."+tabla+"\"/>");

        pw.println("          <input type=\"hidden\" id=\"pagRespuesta\" name=\"pagRespuesta\" value=\"/Web/.../Formularios/frm"+VariablesGlobales.getNombreArchivo()+".jsp\"/>");
        pw.println("          <input type=\"hidden\" id=\"condicion\" name=\"condicion\" value=\""+campos[0]+"= <%="+campos[0]+"%>\"/><!--MODIFICAR ESTO-->");
        pw.println("          <input type=\"hidden\" id=\"autoInc\" name=\"autoInc\" value=\"true\"/>");
        pw.println("          <input type=\"hidden\" id=\"ESQUEMA\" name=\"ESQUEMA\" value=\""+VariablesGlobales.getEsquema()+"\"/>");
        pw.println("          <input type=\"hidden\" id=\"validaGrid\" name=\"validaGrid\" value=\"0\"/>");
        pw.println("          <div class='divBotones'>");
        pw.println("            <a href=\"/GUARDAR\" class='positive' id='btnGuardar'><img src='/Imagenes/icons/guardar.png' alt='GUARDAR'/>Guardar</a>");
        pw.println("            <% if("+tabla.toLowerCase()+" != null){ %>");
        pw.println("                &nbsp;&nbsp;<a href='/CANCELAR' class='negative' id='btnCancelar'><img src='/Imagenes/icons/cancelar.png' alt='CANCELAR'/>Cancelar</a>");
        pw.println("            <% } %>");
        pw.println("          </div>");
        pw.println("</div>");
        pw.println("</form>");
        pw.println("</div>");
        //pw.println("</td></tr></table>");

        /******************** GRID DE MODIFICACION *************************/

        pw.println("<%");
        pw.println("    int cont = bd.bdConreg(\""+tabla+"\",\"ESTATUSREGISTRO <> 99\");");
        pw.println("    if(cont>0){");
        pw.println("%>");

        pw.println("<br/>");
        pw.println("<div align='center' id='gridDatos' name='gridDatos' class='width100'>");
        pw.println("     <form name='modificar' id='modificar' method='POST' action='MODIFICAR'>");
        pw.println("          <% out.println(UtilHTML.generarGrid(\""+tabla+"\",");
        String camposGrid = "";
        for (int j = 0; j < campos.length-5; j++) {
            String campo = campos[j];
            if(j==0)camposGrid += campo;
            if(j!=0)camposGrid += ","+campo;
        }
        pw.println("             \""+camposGrid+"\",");
        pw.println("             \""+camposGrid+"\",");
        pw.println("             \"NOT ESTATUSREGISTRO = 99\",");
        pw.println("             \""+campos[0]+"\",");
        pw.println("             \"style='width:80%' class='gradient-style'\",");
        pw.println("             null,");
        pw.println("             \"\",");
        pw.println("             null,");
        pw.println("             \"\",");
        pw.println("             true,");
        pw.println("             \""+campos[0]+"\",");
        pw.println("             \""+campos[0]+"\",");
        pw.println("             \""+VariablesGlobales.getEsquema()+"\"));");
        pw.println("%>");
        pw.println("          <div class='divBotones'>");
        pw.println("            <a id='btnModificar' href='/MODIFICAR'><img src='/Imagenes/icons/modificar.png' alt='MODIFICAR'/>Modificar</a>");
        pw.println("            &nbsp;&nbsp;<a id='btnEliminar' href='/ELIMINAR' class='negative'><img src='/Imagenes/icons/eliminar.png' alt='ELIMINAR'/>Eliminar</a>");
        pw.println("          </div>");
        pw.println("</form></div>");


        pw.println("<% ");
        pw.println("      } //fin de if cont>0 ");
        pw.println("%>");

        pw.println("</body>");
        pw.println("</html>");
        pw.println("<% bd.bdCierra();");
        pw.println("           } else {");
        pw.println("        if (verifica == 0) {");
        pw.println("           out.print(\"<meta http-equiv='refresh' content='0; url=/logo.jsp'>\");");
        pw.println("        }");
        pw.println("     }");
        pw.println("%>");


        fichero.close();
        new ProcedimientosJS();
    }

}
