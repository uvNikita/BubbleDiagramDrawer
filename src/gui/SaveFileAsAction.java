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

import model.DocumentHolder;
import csv.CSVProcessor;

/**
 * @author Nikita Uvarov Action to save file as csv.
 */
public class SaveFileAsAction extends AbstractAction {
    /**
     * Generated serialVersionUID.
     */
    private static final long serialVersionUID = 3369141372489805599L;
    /**
     * Parent frame.
     */
    JFrame frame;
    /**
     * Holder of data to save.
     */
    DocumentHolder sessionManager;

    /**
     * Create SaveFileAsAction using specified parent frame and data holder.
     * @param frame
     *        Parent frame.
     * @param sessionManager
     *        Holder of data to save.
     */
    public SaveFileAsAction(final JFrame frame,
            final DocumentHolder sessionManager) {
        this.frame = frame;
        this.sessionManager = sessionManager;
    }

    /*
     * (non-Javadoc)
     * @see
     * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public final void actionPerformed(final ActionEvent e) {
        final JFileChooser chooser = new JFileChooser(".");
        while (true) {
            final int returnVal = chooser.showSaveDialog(this.frame);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                final File file = chooser.getSelectedFile();
                if (file.exists()) {
                    final int react = JOptionPane
                            .showConfirmDialog(
                                    this.frame,
                                    String
                                            .format(
                                                    "The file '%s' already exists. Do you want to overwrite it?",
                                                    file.getAbsolutePath()));
                    if (react == JOptionPane.CANCEL_OPTION) {
                        return;
                    } else if (react == JOptionPane.NO_OPTION) {
                        continue;
                    }
                }
                final CSVProcessor csvProc = new CSVProcessor(" ");
                try {
                    csvProc.write(file, this.sessionManager
                            .getCurrentDocumentAsArray());
                    this.sessionManager.setWasChanged(false);
                } catch (final IOException e1) {
                    JOptionPane.showMessageDialog(this.frame,
                            "Error while accessing file.");
                }
            }
            return;
        }
    }

}
