package model;

import java.util.ArrayList;

/**
 * @author nikita
 */
public class SessionManager implements DocumentHolder {
    private ArrayList<Bubble> currentDocument;

    /*
     * (non-Javadoc)
     * @see model.DocumentHolder#getcurrentDocument()
     */
    @Override
    public final ArrayList<Bubble> getCurrentDocument() {
        return this.currentDocument;
    }

    /*
     * (non-Javadoc)
     * @see model.DocumentHolder#setcurrentDocument(java.util.ArrayList)
     */
    @Override
    public final void setCurrentDocument(final ArrayList<Bubble> document) {
        this.currentDocument = document;
    }

}
