package model;

import java.util.List;

public interface DocumentHolder {
    List<Bubble> getCurrentDocument();

    void setCurrentDocument(List<Bubble> doc);

    /**
     * Get current value of wasChanged.
     * @return the wasChanged
     */
    boolean wasChanged();

    /**
     * Set new value of wasChanged.
     * @param wasChanged
     *        the wasChanged to set
     */
    void setWasChanged(final boolean wasChanged);

    /**
     * Set current document using data from array.
     * @param doc
     *        Table to use data from.
     */
    public void setCurrentDocumentAsArray(final double[][] doc);

    /**
     * Get current document represented by array.
     * @return Table containing all document data.
     */
    public double[][] getCurrentDocumentAsArray();
}
