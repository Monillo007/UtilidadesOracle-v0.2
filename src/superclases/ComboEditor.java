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
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.EventListenerList;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author lnavarrora
 */
public class ComboEditor extends JComboBox implements TableCellEditor, CaretListener {

    protected EventListenerList listenerList = new EventListenerList();
    protected ChangeEvent changeEvent = new ChangeEvent(this);
    private MyComboUI comboUi = new MyComboUI();
    private JTextField tf;
    Object newValue;

    public ComboEditor() {
        super();
        setUI(comboUi);
        // comboUi.get
        addItem("Normal");
        addItem("Usuario");
        addItem("Catalogo");
        addItem("Estado");
        addItem("Municipio");
        addItem("Colonia");
        addItem("Calle");
        setEditable(true);
        tf = (JTextField) getEditor().getEditorComponent();
        tf.addCaretListener(this);

        tf.addKeyListener(new KeyAdapter() {

            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == e.VK_ENTER) {
                    Object value = comboUi.getList().getSelectedValue();
                    tf.transferFocus();
                    hidePopup();
                    System.out.println("enter pressed, selected value, when enter pressed: " + value);
                    fireEditingStopped();
                }
            }
        });

        addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent event) {
                // fireEditingStopped();
            }
        });
    }

    public void addCellEditorListener(CellEditorListener listener) {
        listenerList.add(CellEditorListener.class, listener);
    }

    public void removeCellEditorListener(CellEditorListener listener) {
        listenerList.remove(CellEditorListener.class, listener);
    }

    protected void fireEditingStopped() {
        System.out.println("fireEditingStopped called ");

        CellEditorListener listener;
        Object[] listeners = listenerList.getListenerList();
        for (int i = 0; i < listeners.length; i++) {
            if (listeners[i] == CellEditorListener.class) {
                listener = (CellEditorListener) listeners[i + 1];
                listener.editingStopped(changeEvent);
            }
        }
    }

    protected void fireEditingCanceled() {
        CellEditorListener listener;
        Object[] listeners = listenerList.getListenerList();
        for (int i = 0; i < listeners.length; i++) {
            //if (listeners == CellEditorListener.class) {
            if (listeners[i] == CellEditorListener.class) {
                listener = (CellEditorListener) listeners[i + 1];
                listener.editingCanceled(changeEvent);
            }
        }
    }

    public void cancelCellEditing() {
        System.out.println("cancelCellEditing called ");
        fireEditingCanceled();
    }

    public boolean stopCellEditing() {
        System.out.println("stopCellEditing called ");
        fireEditingStopped();
        return true;
    }

    public boolean isCellEditable(EventObject event) {
        return true;
    }

    public boolean shouldSelectCell(EventObject event) {
        return true;
    }

    public Object getCellEditorValue() {
        System.out.println("getCellEditorValue called returning vlaue: " + newValue);
        tf.setText(newValue.toString());
        return newValue;
        // return super.getSelectedItem();
    }

    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected,
            int row, int column) {

        return this;
    }

    public void caretUpdate(CaretEvent e) {
        if (!isPopupVisible() && tf.isShowing() && tf.hasFocus()) {
            showPopup();
        }

        JTextField tf = (JTextField) e.getSource();
        String text = tf.getText().toLowerCase();

        int index = -1;
        for (int i = 0; i < super.getItemCount(); i++) {
            String item = ((String) getItemAt(i)).toLowerCase();
            if (item.startsWith(text)) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            comboUi.getList().setSelectedIndex(index);
        } else {
            comboUi.getList().clearSelection();
        }

        newValue = comboUi.getList().getSelectedValue();
        System.out.println("new value set to: " + newValue);
    }
}
