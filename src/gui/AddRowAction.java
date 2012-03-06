/**
 * 
 */
package gui;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JTable;

import model.BubbleTableModel;

/**
 * @author Nikita Uvarov
 */
public class AddRowAction extends AbstractAction {

    /**
     * Generated serialVersionUID.
     */
    private static final long serialVersionUID = 2753855479405231035L;
    private JTable table;

    /**
     * Create and initialize AddRowAction using given table.
     * @param table
     *        Table to add row in.
     */
    public AddRowAction(final JTable table) {
        this.table = table;
    }

    /*
     * (non-Javadoc)
     * @see
     * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(final ActionEvent e) {
        BubbleTableModel model = (BubbleTableModel) table.getModel();
        int selectedIndex = this.table.getSelectedRow();
        if(selectedIndex == -1) {
            selectedIndex = table.getRowCount() - 1;
        }
        model.addRow(selectedIndex);
    }

}
