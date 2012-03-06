package gui;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import model.Bubble;
import model.DocumentHolder;

/**
 * @author Nikita Uvarov Action to create new document.
 */
public class CreateAction extends AbstractAction {

    /**
     * Generated serialVersionUID.
     */
    private static final long serialVersionUID = -2738826447494703790L;
    /**
     * Parent frame.
     */
    private final JFrame frame;
    /**
     * Holder to create data in.
     */
    private final DocumentHolder holder;

    /**
     * Creates CreateAction using specified parent frame and data holder.
     * @param frame
     *        Parent frame.
     * @param holder
     *        Container of data to create in.
     */
    public CreateAction(final JFrame frame, final DocumentHolder holder) {
        this.frame = frame;
        this.holder = holder;
    }

    /*
     * (non-Javadoc)
     * @see
     * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public final void actionPerformed(final ActionEvent e) {
        this.holder.setCurrentDocument(new ArrayList<Bubble>());
        this.holder.setWasChanged(false);
        SwingUtilities.updateComponentTreeUI(this.frame);
    }

}
