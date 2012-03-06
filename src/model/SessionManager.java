package model;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author Nikita Uvarov Class can manage data of bubble diagram.
 */
public class SessionManager implements DocumentHolder {
    /**
     * Current document represented like List.
     */
    private List<Bubble> currentDocument;

    /**
     * Logger to use for debug.
     */
    private final Logger log = Logger.getLogger(SessionManager.class
            .getName());

    /**
     * Flag determines whether data was changed.
     */
    private boolean wasChanged = false;

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

    /*
     * (non-Javadoc)
     * @see model.DocumentHolder#getCurrentDocumentAsArray()
     */
    public final double[][] getCurrentDocumentAsArray() {
        final double[][] doc = new double[this.currentDocument.size()][Bubble.NUMBER_OF_PROPERTIES];
        int i = 0;
        for (final Bubble bubble : this.currentDocument) {
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

    /*
     * (non-Javadoc)
     * @see model.DocumentHolder#setCurrentDocumentAsArray(double[][])
     */
    public final void setCurrentDocumentAsArray(final double[][] doc) {
        this.currentDocument.clear();
        for (final double[] ds : doc) {
            this.currentDocument.add(new Bubble(ds[0], ds[1], ds[2]));
        }
        this.log.info(String.format("Current document: %s",
                this.currentDocument));
    }

    /*
     * (non-Javadoc)
     * @see model.DocumentHolder#setWasChanged(boolean)
     */
    public final void setWasChanged(final boolean wasChanged) {
        this.wasChanged = wasChanged;
    }

    /*
     * (non-Javadoc)
     * @see model.DocumentHolder#isWasChanged()
     */
    public final boolean wasChanged() {
        return this.wasChanged;
    }
}
