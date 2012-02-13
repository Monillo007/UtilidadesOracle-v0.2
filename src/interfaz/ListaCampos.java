/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ListaCampos.java
 *
 * Created on 24/07/2009, 02:09:09 PM
 */
package interfaz;

import java.util.ArrayList;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import manejadores.mnjListaCampos;
import superclases.Combo;
import superclases.ComboBoxEditor;
import superclases.ComboBoxRenderer;
import superclases.ComboEditor;
import superclases.FormatoTabla;
import utilerias.Util;
import utilerias.VariablesGlobales;

/**
 *
 * @author zeus
 */
public class ListaCampos extends javax.swing.JFrame {

    mnjListaCampos manejador;

    /** Creates new form ListaCampos */
    public ListaCampos(mnjListaCampos manejador) {
        initComponents();
        this.manejador = manejador;
        Util.setAnchoColumnas(tablaCampos, 7, 7, 7, 14, 17, 16, 16, 16);
        //FormatoTabla f = new FormatoTabla();
        //tablaCampos.setDefaultRenderer(String.class, f);
        TableColumn c = tablaCampos.getColumnModel().getColumn(3);        
        JComboBox comboEditor = new JComboBox();
        comboEditor.addItem("Normal");
        comboEditor.addItem("Usuario");
        comboEditor.addItem("Persona");
        comboEditor.addItem("Catalogo");
        comboEditor.addItem("Estado");
        comboEditor.addItem("Municipio");
        comboEditor.addItem("Calle");
        comboEditor.addItem("Colonia");
        //c.setCellEditor(new ComboEditor());
        c.setCellEditor(new DefaultCellEditor(comboEditor));
        for (int i = 0; i < tablaCampos.getModel().getRowCount(); i++) {
            tablaCampos.getModel().setValueAt("Normal", i, 3);
        }
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private ArrayList getCamposSeleccionados() {
        ArrayList<String> camposOcultos = new ArrayList<String>();
        for (int i = 0; i < tablaCampos.getRowCount(); i++) {
            if (((Boolean) tablaCampos.getValueAt(i, 0)) == true) {
                camposOcultos.add((String) tablaCampos.getValueAt(i, 4));
            }
        }
        return camposOcultos;
    }

    private ArrayList getCombos() {
        ArrayList<Combo> combos = new ArrayList<Combo>();
        for (int i = 0; i < tablaCampos.getRowCount(); i++) {
            if (((Boolean) tablaCampos.getValueAt(i, 1)) == true) {
                combos.add(new Combo(
                        (String) tablaCampos.getValueAt(i, 4),
                        (String) tablaCampos.getValueAt(i, 5),
                        (String) tablaCampos.getValueAt(i, 6),
                        (String) tablaCampos.getValueAt(i, 7)));
            }
        }
        return combos;
    }

    private ArrayList getRequeridos() {
        ArrayList<String> requeridos = new ArrayList<String>();
        for (int i = 0; i < tablaCampos.getRowCount(); i++) {
            if (((Boolean) tablaCampos.getValueAt(i, 2)) == true) {
                requeridos.add((String) tablaCampos.getValueAt(i, 4));
            }
        }
        return requeridos;
    }

    private ArrayList getSelUsuario() {
        ArrayList<String> selUsuario = new ArrayList<String>();
        for (int i = 0; i < tablaCampos.getRowCount(); i++) {
            if (((String)tablaCampos.getValueAt(i, 3)).equalsIgnoreCase("Usuario")) {
                selUsuario.add((String) tablaCampos.getValueAt(i, 4));
            }
        }
        return selUsuario;
    }
    
    private ArrayList getSelPersona() {
        ArrayList<String> selPersona = new ArrayList<String>();
        for (int i = 0; i < tablaCampos.getRowCount(); i++) {
            if (((String)tablaCampos.getValueAt(i, 3)).equalsIgnoreCase("Persona")) {
                selPersona.add((String) tablaCampos.getValueAt(i, 4));
            }
        }
        return selPersona;
    }

    private ArrayList getCatalogos() {
        ArrayList<String> cat = new ArrayList<String>();
        for (int i = 0; i < tablaCampos.getRowCount(); i++) {
            if (((String)tablaCampos.getValueAt(i, 3)).equalsIgnoreCase("Catalogo")) {
                cat.add((String) tablaCampos.getValueAt(i, 4));
            }
        }
        return cat;
    }

    private ArrayList getEstados() {
        ArrayList<String> edo = new ArrayList<String>();
        for (int i = 0; i < tablaCampos.getRowCount(); i++) {
            if (((String)tablaCampos.getValueAt(i, 3)).equalsIgnoreCase("Estado")) {
                edo.add((String) tablaCampos.getValueAt(i, 4));
            }
        }
        return edo;
    }

    private ArrayList getMunicipios() {
        ArrayList<String> mpio = new ArrayList<String>();
        for (int i = 0; i < tablaCampos.getRowCount(); i++) {
            if (((String)tablaCampos.getValueAt(i, 3)).equalsIgnoreCase("Municipio")) {
                mpio.add((String) tablaCampos.getValueAt(i, 4));
            }
        }
        return mpio;
    }
    private ArrayList getCalles() {
        ArrayList<String> calle = new ArrayList<String>();
        for (int i = 0; i < tablaCampos.getRowCount(); i++) {
            if (((String)tablaCampos.getValueAt(i, 3)).equalsIgnoreCase("Calle")) {
                calle.add((String) tablaCampos.getValueAt(i, 4));
            }
        }
        return calle;
    }
    private ArrayList getColonias() {
        ArrayList<String> col = new ArrayList<String>();
        for (int i = 0; i < tablaCampos.getRowCount(); i++) {
            if (((String)tablaCampos.getValueAt(i, 3)).equalsIgnoreCase("Colonia")) {
                col.add((String) tablaCampos.getValueAt(i, 4));
            }
        }
        return col;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaCampos = new javax.swing.JTable();
        btSiguiente = new javax.swing.JButton();
        btRegresar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Define los campos"));

        tablaCampos.setModel(new superclases.ModeloLista(mnjListaCampos.getDatosTabla(),new String[]{"Oculto","Combo","Requerido","Tipo","Campo","Tabla (combo)","Campo ID (combo)","Campo Etiqueta(combo)"},"campos"));
        jScrollPane1.setViewportView(tablaCampos);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 707, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                .addContainerGap())
        );

        btSiguiente.setFont(new java.awt.Font("Tahoma", 0, 10));
        btSiguiente.setText("Siguiente>");
        btSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSiguienteActionPerformed(evt);
            }
        });

        btRegresar.setFont(new java.awt.Font("Tahoma", 0, 10));
        btRegresar.setText("<Regresar");
        btRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRegresarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btRegresar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 559, Short.MAX_VALUE)
                .addComponent(btSiguiente)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btSiguiente)
                    .addComponent(btRegresar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSiguienteActionPerformed
        VariablesGlobales.setCamposOcultos(getCamposSeleccionados());
        VariablesGlobales.setCombos(getCombos());
        VariablesGlobales.setRequeridos(getRequeridos());
        VariablesGlobales.setSelUsuario(getSelUsuario());
        VariablesGlobales.setSelPersona(getSelPersona());
        VariablesGlobales.setCatalogos(getCatalogos());
        VariablesGlobales.setEstados(getEstados());
        VariablesGlobales.setMunicipios(getMunicipios());
        VariablesGlobales.setColonias(getColonias());
        VariablesGlobales.setCalles(getCalles());
        this.dispose();
        manejador.siguiente();
    }//GEN-LAST:event_btSiguienteActionPerformed

    private void btRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRegresarActionPerformed
        this.dispose();
        new SeleccionProceso(manejador.getBd());
    }//GEN-LAST:event_btRegresarActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btRegresar;
    private javax.swing.JButton btSiguiente;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaCampos;
    // End of variables declaration//GEN-END:variables
}
