package gui;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JTable;

import model.BubbleTableModel;

/**
 * @author Nikita Uvarov
 */
public class DelRowAction extends AbstractAction {
    /**
     * Generated serialVersionUID.
     */
    private static final long serialVersionUID = -8057615610942019718L;
    /**
     * Table to delete row from.
     */
    private final JTable table;

    /**
     * Create and initialize DelRowAction using given table.
     * @param table
     *        Table to delete row from.
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
    public final void actionPerformed(final ActionEvent e) {
        final BubbleTableModel model = (BubbleTableModel) this.table
                .getModel();
        final int selectedIndex = this.table.getSelectedRow();
        if (selectedIndex == -1) {
            return;
        }
        model.delRow(selectedIndex);
    }

}
