/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package utilerias;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lnavarrora
 */
public class ProcedimientosJS {

    public ProcedimientosJS() {
        try {
            crearJs();
        } catch (Exception ex) {
            Logger.getLogger(ProcedimientosJS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void crearJs() throws Exception{
        String rutaDestino = VariablesGlobales.getRutaDestino().replace("Formularios", "Javascript");
        File carpeta = new File(rutaDestino);

        if(!carpeta.exists()){
            carpeta.mkdir();
        }

        File jsFile = new File(rutaDestino+"\\js"+VariablesGlobales.getNombreArchivo()+".js");
        if(jsFile.exists()){
            jsFile.delete();
        }

        jsFile.createNewFile();
        FileWriter fichero = new FileWriter(jsFile);
        PrintWriter pw = new PrintWriter(fichero);

        pw.println("$(function(){");
        pw.println("    eliminarValoresSelect();");
        pw.println("");
        pw.println("    $(\"#btnCancelar\").click(function(){");
        pw.println("        $(\".divBotones\").html(\"<img src='/Imagenes/gif/circleBlue.gif' />\");");
        pw.println("        window.location = 'frm"+VariablesGlobales.getNombreArchivo()+".jsp';");
        pw.println("    });");
        pw.println("");
        pw.println("    $(\"#"+VariablesGlobales.getNombreFormulario()+"\").validate({");
        pw.println("        submitHandler: function(form) {");
        pw.println("                $(\".divBotones\").html(\"<img src='/Imagenes/gif/circleBlue.gif' />\");");
        pw.println("                form.action = \"/ManejadorBD\";");
        pw.println("                form.submit();");
        pw.println("        }");
        pw.println("    });");
        pw.println("");
        pw.println("    $(\"#btnGuardar\").click(function(){");
        pw.println("           $(\"#"+VariablesGlobales.getNombreFormulario()+"\").submit();");
        pw.println("    });");
        pw.println("");
        pw.println("    $(\"#btnEliminar\").click(function(){");
        pw.println("        if($(\"#modificar :checked\").length == 1){");
        pw.println("            if(confirm('ESTA SEGURO DE ELIMINAR EL REGISTRO?')){");
        pw.println("                var form = $(\"#"+VariablesGlobales.getNombreFormulario()+"\");");
        pw.println("                $(\"#accion\").val(\"B\");");
        pw.println("                $(\"#condicion\").val(\""+VariablesGlobales.getIdTabla()+"=\"+$(\"#modificar :checked\").val());");
        pw.println("                $(\".divBotones\").html(\"<img src='/Imagenes/gif/circleOrange.gif' />\");");
        pw.println("                form.attr('action','/ManejadorBD')[0].submit();");
        pw.println("            }");
        pw.println("        }else{");
        pw.println("             $.gritter.error(\"DEBES SELECCIONAR UN REGISTRO!\");");
        pw.println("        }");
        pw.println("    });");
        pw.println("");
        pw.println("    $(\"#btnModificar\").click(function(){");
        pw.println("        if($(\"#modificar :checked\").length == 1){");
        pw.println("           $(\".divBotones\").html(\"<img src='/Imagenes/gif/circleBlue.gif' />\");");
        pw.println("           $(\"#modificar\").attr('action','frm"+VariablesGlobales.getNombreArchivo()+".jsp').submit();");
        pw.println("        }else{");
        pw.println("             $.gritter.error(\"DEBES SELECCIONAR UN REGISTRO!\");");
        pw.println("        }");
        pw.println("    });");
        pw.println("");
        pw.println("});");
        pw.println("");
        pw.println("function cam(){}");

        fichero.close();
    }
}
