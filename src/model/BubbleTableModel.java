package model;

import javax.swing.table.AbstractTableModel;

/**
 * @author Nikita Uvarov 
 * Table model to represent bubble diagram data.
 */
public class BubbleTableModel extends AbstractTableModel {
    /**
     * Generated serialVersionUID.
     */
    private static final long serialVersionUID = 2511445944820000327L;
    private DocumentHolder holder;

    /**
     * Create BubbleTableModel using specified data hollder.
     * @param holder
     */
    public BubbleTableModel(final DocumentHolder holder) {
        this.holder = holder;
    }

    /*
     * (non-Javadoc)
     * @see javax.swing.table.TableModel#getColumnCount()
     */
    @Override
    public final int getColumnCount() {
        return Bubble.NUMBER_OF_PROPERTIES;
    }

    /*
     * (non-Javadoc)
     * @see javax.swing.table.TableModel#getRowCount()
     */
    @Override
    public final int getRowCount() {
        return holder.getCurrentDocument().size();
    }

    /*
     * (non-Javadoc)
     * @see javax.swing.table.TableModel#getValueAt(int, int)
     */
    @Override
    public final Object getValueAt(final int rowIndex, final int columnIndex) {
        Bubble bubble = holder.getCurrentDocument().get(rowIndex);
        switch (columnIndex) {
        case 0:
            return bubble.getX();
        case 1:
            return bubble.getY();
        case 2:
            return bubble.getRadius();
        default:
            throw new IllegalArgumentException("Too many columns");
        }
    }

    /*
     * (non-Javadoc)
     * @see javax.swing.table.TableModel#getColumnName(int)
     */
    @Override
    public final String getColumnName(final int column) {
        switch (column) {
        case 0:
            return "X";
        case 1:
            return "Y";
        case 2:
            return "Radius";
        default:
            throw new IllegalArgumentException("Too many columns");
        }
    }
}
