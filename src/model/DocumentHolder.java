package model;

import java.util.List;

/**
 * @author Nikita Uvarov Bubble diagram data holder.
 */
public interface DocumentHolder {
    List<Bubble> getCurrentDocument();

    /**
     * Get current document represented by array.
     * @return Table containing all document data.
     */
    double[][] getCurrentDocumentAsArray();

    /**
     * Set current document using List.
     * @param doc
     *        List to take data from.
     */
    void setCurrentDocument(List<Bubble> doc);

    /**
     * Set current document using data from array.
     * @param doc
     *        Table to use data from.
     */
    void setCurrentDocumentAsArray(final double[][] doc);

    /**
     * Set new value of wasChanged.
     * @param wasChanged
     *        the wasChanged to set
     */
    void setWasChanged(final boolean wasChanged);

    /**
     * Get current value of wasChanged.
     * @return the wasChanged
     */
    boolean wasChanged();
}
