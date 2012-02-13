/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package superclases;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;

/**
 *
 * @author lnavarrora
 */
public class ComboBoxEditor extends DefaultCellEditor {
    public ComboBoxEditor(String[] items) {
        super(new JComboBox(items));
    }
}
