/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package superclases;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.EventObject;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellRenderer;
import javax.swing.ComboBoxEditor;
import javax.swing.JList;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.EventListenerList;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.table.TableCellEditor;

public class ComboBoxRenderer extends JComboBox implements TableCellRenderer {

    public ComboBoxRenderer() {
        super();
        addItem("Normal");
        addItem("Usuario");
        addItem("Catalogo");
        addItem("Estado");
        addItem("Municipio");
        addItem("Colonia");
        addItem("Calle");
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
            boolean hasFocus, int row, int column) {

        if (hasFocus) {
            table.editCellAt(row, column);
            JComboBox combo = (JComboBox) table.getColumnModel().getColumn(4).getCellEditor();
            ComboBoxEditor editor = (ComboBoxEditor) combo.getEditor();
            JTextField tf = (JTextField) editor.getEditorComponent();
            tf.requestFocusInWindow();
            tf.selectAll();
        }
        if (isSelected) {
            setForeground(table.getSelectionForeground());
            super.setBackground(table.getSelectionBackground());
        } else {
            setForeground(table.getForeground());
            setBackground(table.getBackground());
        }

        setSelectedItem(value);
        return this;
    }
}

class MyComboUI extends BasicComboBoxUI {

    public JList getList() {
        return listBox;
    }
}
