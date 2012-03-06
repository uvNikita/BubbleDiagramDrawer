package gui;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JTable;

import model.BubbleTableModel;

/**
 * @author Nikita Uvarov
 */
public class DelRowAction extends AbstractAction {
    private JTable table;

    /**
     * Create and initialize DelRowAction using given table.
     * @param table
     *        Table to add row in.
     */
    public DelRowAction(final JTable table) {
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
        if (selectedIndex == -1) {
            return;
        }
        model.delRow(selectedIndex);
    }

}
