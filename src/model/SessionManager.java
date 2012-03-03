package model;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author nikita
 */
public class SessionManager implements DocumentHolder {
    private Logger log = Logger.getLogger(SessionManager.class.getName());
    private List<Bubble> currentDocument;

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

    public final void setCurrentDocument(final double[][] doc) {
        this.currentDocument.clear();
        for (final double[] ds : doc) {
            this.currentDocument.add(new Bubble(ds[0], ds[1], ds[2]));
        }
        log.info(String.format("Current document: %s", this.currentDocument));
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
