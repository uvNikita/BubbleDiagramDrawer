package model;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author Nikita Uvarov 
 * Class can manage data of bubble diagram.
 */
public class SessionManager implements DocumentHolder {
    /**
     * Logger to use for debug.
     */
    private Logger log = Logger.getLogger(SessionManager.class.getName());
    /**
     * Current document represented like List.
     */
    private List<Bubble> currentDocument;

    /**
     * Create and initialize {@link SessionManager}.
     */
    public SessionManager() {
        this.currentDocument = new ArrayList<Bubble>();
    }

    /*
     * (non-Javadoc)
     * @see model.DocumentHolder#getcurrentDocument()
     */
    @Override
    public final List<Bubble> getCurrentDocument() {
        return this.currentDocument;
    }

    /**
     * Set current document using data from array.
     * @param doc
     *        Table to use data from.
     */
    public final void setCurrentDocumentAsArray(final double[][] doc) {
        this.currentDocument.clear();
        for (final double[] ds : doc) {
            this.currentDocument.add(new Bubble(ds[0], ds[1], ds[2]));
        }
        log.info(String.format("Current document: %s", this.currentDocument));
    }

    /**
     * Get current document represented by array.
     * @return Table containing all document data.
     */
    public final double[][] getCurrentDocumentAsArray() {
        double[][] doc = new double[this.currentDocument.size()][Bubble.NUMBER_OF_PROPERTIES];
        int i = 0;
        for (Bubble bubble : this.currentDocument) {
            doc[i][0] = bubble.getX();
            doc[i][1] = bubble.getY();
            doc[i][2] = bubble.getRadius();
            i++;
        }
        return doc;
    }

    /*
     * (non-Javadoc)
     * @see model.DocumentHolder#setcurrentDocument(java.util.ArrayList)
     */
    @Override
    public final void setCurrentDocument(final List<Bubble> doc) {
        this.currentDocument = doc;
    }
}
