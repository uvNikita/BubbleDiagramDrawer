/**
 * 
 */
package gui;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import csv.CSVProcessor;

import model.SessionManager;

/**
 * @author Nikita Uvarov
 * Action to save file as csv.
 */
public class SaveFileAsAction extends AbstractAction {
    /**
     * Generated serialVersionUID.
     */
    private static final long serialVersionUID = 3369141372489805599L;
    /**
     * Holder of data to save.
     */
    SessionManager sessionManager;
    /**
     * Parent frame.
     */
    JFrame frame;

    /**
     * Create SaveFileAsAction using specified parent frame and data holder.
     * @param frame
     *        Parent frame.
     * @param sessionManager
     *        Holder of data to save.
     */
    public SaveFileAsAction(JFrame frame, SessionManager sessionManager) {
        this.frame = frame;
        this.sessionManager = sessionManager;
    }

    /*
     * (non-Javadoc)
     * @see
     * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(final ActionEvent e) {
        final JFileChooser chooser = new JFileChooser(".");
        final int returnVal = chooser.showSaveDialog(frame);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            final File file = chooser.getSelectedFile();
            final CSVProcessor csvProc = new CSVProcessor(" ");
            try {
                csvProc.write(file, sessionManager.getCurrentDocumentAsArray());
            } catch (IOException e1) {
                JOptionPane.showMessageDialog(this.frame,
                        "Error while accessing file.");
            }
        }
    }

}
