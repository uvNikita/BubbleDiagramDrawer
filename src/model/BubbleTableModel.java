package model;

import java.util.List;

import javax.swing.event.TableModelEvent;
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
    public final Object getValueAt(final int rowIndex,
            final int columnIndex) {
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

    /*
     * (non-Javadoc)
     * @see javax.swing.table.AbstractTableModel#isCellEditable(int, int)
     */
    @Override
    public final boolean isCellEditable(final int row, final int col) {
        return true;
    }

    /*
     * (non-Javadoc)
     * @see javax.swing.table.AbstractTableModel#setValueAt(java.lang.Object,
     * int, int)
     */
    @Override
    public final void setValueAt(final Object aValue, final int rowIndex,
            final int columnIndex) {
        Bubble bubble = holder.getCurrentDocument().get(rowIndex);
        double dValue = Double.parseDouble((String) aValue);
        switch (columnIndex) {
        case 0:
            bubble.setX(dValue);
            break;
        case 1:
            bubble.setY(dValue);
            break;
        case 2:
            bubble.setRadius(dValue);
            break;
        default:
            throw new IllegalArgumentException("Too many columns");
        }
        holder.setWasChanged(true);
        this.fireTableChanged(new TableModelEvent(this));
    }

    /**
     * Add row.
     * @param after
     *        an index of row after which new must be added.
     */
    public final void addRow(final int after) {
        holder.getCurrentDocument().add(after + 1, new Bubble(0, 0, 0));
        holder.setWasChanged(true);
        this.fireTableStructureChanged();
    }

    /**
     * Delete row.
     * @param index an index of row which must be deleted.
     */
    public final void delRow(final int index) {
        holder.getCurrentDocument().remove(index);
        holder.setWasChanged(true);
        fireTableStructureChanged();
    }
}
