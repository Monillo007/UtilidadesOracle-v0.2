/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utilerias;

import java.io.IOException;
import java.sql.ResultSetMetaData;
import interfacebd.InterfaceBD;
import interfacebd.InterfaceRegistrosBD;
import interfaz.CrearBitacoras;
import interfaz.CrearClases;
import interfaz.CrearSecuencias;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import oracle.jdbc.OracleDatabaseMetaData;
import superclases.DatoTabla;

/**
 *
 * @author Ing. Luis Manuel Navarro R.
 * Coordinación de Desarrollo de Sistemas
 * Direccion de Análisis y Tecnologías de Información
 * 
 */
public class Procedimientos implements Runnable {

    ArrayList<String> data;
    String proc = "clase";
    String scriptsFileString = "";

    public InterfaceBD getBd() {
        return bd;
    }

    public void setBd(InterfaceBD bd) {
        this.bd = bd;
    }
    InterfaceBD bd;

    public String getProc() {
        return proc;
    }

    public void setProc(String proc) {
        this.proc = proc;
    }

    public Procedimientos(ArrayList<String> data) {
        this.data = data;
    }

    public void run() {

        if (proc.equals("clase")) {
            int porcentaje = 0;
            CrearClases.pbBarra.setMaximum(100);
            CrearClases.pbBarra.setMinimum(0);
            CrearClases.pbBarra.setVisible(true);
            CrearClases.pbBarra.setValue(porcentaje);
            for (String tablaActual : data) {
                new ProcesaTabla(tablaActual);
                porcentaje += 100 / data.size();
                CrearClases.pbBarra.setValue(porcentaje);
            }
            CrearClases.pbBarra.setValue(100);
        } else if (proc.equals("jsp")) {
            Iterator it = VariablesGlobales.getTablas().iterator();
            if (it.hasNext()) {
                new ProcesaJSP((String) it.next(), data);
            }
        } else if (proc.equals("secuencias")) {
            int porcentaje = 0;

            CrearSecuencias.pbBarra.setMaximum(100);
            CrearSecuencias.pbBarra.setMinimum(0);
            CrearSecuencias.pbBarra.setVisible(true);
            CrearSecuencias.pbBarra.setValue(porcentaje);
            for (String tablaActual : data) {
                crearSecuencia(tablaActual);
                porcentaje += 100 / data.size();
                CrearSecuencias.pbBarra.setValue(porcentaje);
            }

            if (VariablesGlobales.getRutaDestino() != null && !VariablesGlobales.getRutaDestino().trim().equals("")) {
                crearArchivo("secuencias");
            }

            CrearSecuencias.pbBarra.setValue(100);        
        } else if (proc.equals("bitacoras")) {
            int porcentaje = 0;

            CrearBitacoras.pbBarra.setMaximum(100);
            CrearBitacoras.pbBarra.setMinimum(0);
            CrearBitacoras.pbBarra.setVisible(true);
            CrearBitacoras.pbBarra.setValue(porcentaje);

            for (String tablaActual : data) {
                crearBitacora(tablaActual);
                crearSecuencia("BIT_"+tablaActual,true);
                crearTriggerBitacora(tablaActual);
                porcentaje += 100 / data.size();
                CrearBitacoras.pbBarra.setValue(porcentaje);
            }

            if (VariablesGlobales.getRutaDestino() != null && !VariablesGlobales.getRutaDestino().trim().equals("")) {
                crearArchivo("bitacoras");
            }

            CrearBitacoras.pbBarra.setValue(100);
        }

        if (proc.equals("clases") || proc.equals("jsp") || ((proc.equals("secuencias") || proc.equals("bitacoras")) && VariablesGlobales.getRutaDestino() != null && !VariablesGlobales.getRutaDestino().trim().equals(""))) {
            int resp = JOptionPane.showConfirmDialog(null, "Los archivos fueron creados exitosamente.\n¿Deseas abrir la carpeta para ver los archivos?", "Proceso finalizado", JOptionPane.YES_NO_OPTION);
            if (resp == JOptionPane.YES_OPTION) {
                Process pr;
                String comando = "explorer " + VariablesGlobales.getRutaDestino().replace("\\\\", "\\");
                try {
                    pr = Runtime.getRuntime().exec(comando);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        } else if ( (proc.equals("secuencias")  || proc.equals("bitacoras")) && (VariablesGlobales.getRutaDestino() == null || VariablesGlobales.getRutaDestino().trim().equals(""))) {
            JOptionPane.showMessageDialog(null, "Se han creado las secuencias.", "Proceso terminado.", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void crearBitacora(String tablaActual) {        
        ArrayList<DatoTabla> datos = getDatosTabla(tablaActual);
        String scriptTablaBitacora = "CREATE TABLE BIT_"+tablaActual+"(";       

        int x = 0;
        for(DatoTabla d : datos){                        
            scriptTablaBitacora = scriptTablaBitacora.concat(d.getNombre()+" "+d.getTipo());
            if(!d.getTipo().equals("DATE")){
                scriptTablaBitacora = scriptTablaBitacora.concat("("+d.getTamanio());
                if(d.getTipo().equals("NUMBER")){
                    if(d.getPrecision() > 0)
                    scriptTablaBitacora = scriptTablaBitacora.concat(","+d.getPrecision());
                }
                scriptTablaBitacora = scriptTablaBitacora.concat(")");
            }
            scriptTablaBitacora = scriptTablaBitacora.concat(" "+d.getIsNull()+",");

            x++;
        }

        scriptTablaBitacora = scriptTablaBitacora.concat(" IDBIT_"+tablaActual+" NUMBER NOT NULL,"+
                                                         " MOVIMIENTO VARCHAR2(1) NOT NULL,"+
                                                         " REALIZADOEL DATE DEFAULT SYSTIMESTAMP NOT NULL,"+
                                                         " REALIZADODESDE VARCHAR2(15) NOT NULL,"+
                                                         " CONSTRAINT PK_BIT_"+tablaActual+" PRIMARY KEY (IDBIT_"+tablaActual+")"+
                                                         ")");
        scriptsFileString = scriptsFileString.concat("\n --DROP TABLE BIT_"+tablaActual+" \n "+scriptTablaBitacora);
        bd.bdEjesql(scriptTablaBitacora);
    }

    private void crearTriggerBitacora(String tabla){
        ArrayList<DatoTabla> datos = getDatosTabla(tabla);
        String scriptTrigger = "CREATE OR REPLACE TRIGGER TRG_BIT_"+tabla+" ";
        scriptTrigger = scriptTrigger.concat("AFTER  INSERT OR  UPDATE ON "+tabla+
                                             " REFERENCING OLD AS \"OLD\" NEW AS \"NEW\""+
                                             " FOR EACH ROW "+
                                             " DECLARE "+
                                             " MOVIMIENTO VARCHAR2(1):='C'; "+
                                             " IP VARCHAR2(15); "+
                                             " BEGIN "+
                                                " SELECT SYS_CONTEXT( 'USERENV', 'IP_ADDRESS') INTO IP FROM DUAL; "+
                                                " IF UPDATING THEN "+
                                                    " MOVIMIENTO := 'M';"+
                                                    " IF :OLD.ESTATUSREGISTRO<> 99 AND :NEW.ESTATUSREGISTRO = 99 THEN "+
                                                        " MOVIMIENTO := 'B'; "+
                                                    " END IF; "+
                                                    " IF :NEW.ESTATUSREGISTRO<>99 AND :OLD.ESTATUSREGISTRO = 99 THEN "+
                                                        " MOVIMIENTO := 'A'; "+
                                                    " END	IF; "+
                                                " END IF; "+
                                            " INSERT INTO BIT_"+tabla+" ("
                                            );
        for(DatoTabla d : datos){
            scriptTrigger = scriptTrigger.concat(" "+d.getNombre()+",");
        }

        scriptTrigger = scriptTrigger.concat(" IDBIT_"+tabla+","+
                                             " MOVIMIENTO,"+
                                             " REALIZADOEL,"+
                                             " REALIZADODESDE"+
                                             " )"+
                                             " VALUES"+
                                             "(");
        for(DatoTabla d : datos){
            scriptTrigger = scriptTrigger.concat(" :NEW."+d.getNombre()+",");
        }

        scriptTrigger = scriptTrigger.concat(" BIT_"+tabla+"_SEC.NEXTVAL,"+
                                            " MOVIMIENTO,"+
                                            " SYSTIMESTAMP,"+
                                            " IP "+
                                            "); "+
                                            "END;");
        System.out.println("Trigger: "+scriptTrigger);
        scriptsFileString += "\n" + scriptTrigger + ";";
        bd.bdEjesql(scriptTrigger);
    }

    private void crearSecuencia(String tablaActual,boolean esBitacora) {
        String borrar = "DROP SEQUENCE " + tablaActual + "_SEC";
        String script = "CREATE SEQUENCE " + tablaActual + "_SEC INCREMENT BY 1 START WITH *** NOMAXVALUE MINVALUE 1 NOCYCLE NOCACHE ORDER";

        if(esBitacora){
            script = script.replace("***", "0");
            script = script.replace("MINVALUE 1", "MINVALUE 0");
        }else{
            if (VariablesGlobales.getTipoConsecutivo().equals("IniciaEn")) {
                script = script.replace("***", "" + VariablesGlobales.getNumConsecutivo());
            } else if (VariablesGlobales.getTipoConsecutivo().equals("OnDeFlai")) {
                InterfaceRegistrosBD registros = new InterfaceRegistrosBD();
                int siguiente = registros.getSiguienteSecuencia(tablaActual, getIDTabla(tablaActual), "1=1");
                siguiente += new Integer(VariablesGlobales.getNumConsecutivo());
                script = script.replace("***", "" + siguiente);
            }
        }

        scriptsFileString += "\n" + borrar + ";";
        scriptsFileString += "\n" + script + ";";
        bd.bdEjesql(borrar);
        bd.bdEjesql(script);
    }

    private void crearSecuencia(String tablaActual) {
        crearSecuencia(tablaActual,false);
    }

    private String getIDTabla(String tabla) {
        ResultSetMetaData rsmd;
        String id = null;
        Statement SentenciaSQL = null;
        String consulta = "SELECT * FROM " + tabla + " WHERE ROWNUM <= 2";
        ResultSet resultado = null;
        try {
            SentenciaSQL =
                    InterfaceBD.getConexion().createStatement(
                    ResultSet.TYPE_FORWARD_ONLY,
                    ResultSet.CONCUR_READ_ONLY);

            resultado = SentenciaSQL.executeQuery(consulta);
            rsmd = resultado.getMetaData();
            id = rsmd.getColumnName(1);
        } catch (Exception e) {
            System.out.println("No se pudo verificar el id de la tabla " + tabla);
        } finally {
            try {
                resultado.close();
                SentenciaSQL.close();
            } catch (SQLException ex) {
            }
        }
        return id;
    }

    private void crearArchivo(String nombre) {

        File scriptFile = null;
        PrintWriter pw = null;
        scriptFile = new File(VariablesGlobales.getRutaDestino() + "\\"+nombre+"_SQL.txt");
        if (scriptFile.exists()) {
            scriptFile.delete();
        }
        try {
            scriptFile.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(Procedimientos.class.getName()).log(Level.SEVERE, null, ex);
        }
        FileWriter fichero = null;
        try {
            fichero = new FileWriter(scriptFile);
        } catch (IOException ex) {
            Logger.getLogger(Procedimientos.class.getName()).log(Level.SEVERE, null, ex);
        }
        pw = new PrintWriter(fichero);
        pw.println(scriptsFileString);
        try {
            fichero.close();
        } catch (IOException ex) {
            Logger.getLogger(Procedimientos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<DatoTabla> getDatosTabla(String tabla){
        ArrayList<DatoTabla> datos = new ArrayList<DatoTabla>();
        OracleDatabaseMetaData dbmd;
        ResultSet wrkResultSet = null;
        ResultSetMetaData md = null;

        try{
            Statement SentenciaSQL = InterfaceBD.getConexion().createStatement(ResultSet.TYPE_FORWARD_ONLY,
                                    ResultSet.CONCUR_READ_ONLY);

            wrkResultSet = SentenciaSQL.executeQuery("SELECT * FROM "+tabla+" WHERE 1 <> 1");
            md = wrkResultSet.getMetaData();
        } catch (SQLException e) {
            System.out.println(e);
        }
        try {
            for (int i = 0; i < md.getColumnCount(); i++) {
                DatoTabla dt = new DatoTabla();
                dt.setNombre(md.getColumnName(i+1));
                dt.setTipo(md.getColumnTypeName(i+1));
                dt.setTamanio(md.getColumnDisplaySize(i+1));
                dt.setPrecision(md.getScale(i+1));
                if(md.isNullable(i+1) == ResultSetMetaData.columnNullable){
                    dt.setIsNull("NULL");
                }else{
                    dt.setIsNull("NOT NULL");
                }
                datos.add(dt);
                //System.out.println("Dato: "+dt);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Procedimientos.class.getName()).log(Level.SEVERE, null, ex);
        }

        return datos;
    }
}
