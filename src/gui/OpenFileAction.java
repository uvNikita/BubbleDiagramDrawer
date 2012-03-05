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
import javax.swing.SwingUtilities;

import model.SessionManager;
import csv.CSVParseException;
import csv.CSVProcessor;

class OpenFileAction extends AbstractAction {
    /**
     * 
     */
    private static final long serialVersionUID = 1247486436377126288L;
    private final JFrame frame;
    private final SessionManager sessionManager;

    public OpenFileAction(final JFrame frame,
            final SessionManager sessionManager) {
        super("Open...");
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
        final int returnVal = chooser.showOpenDialog(frame);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            final File file = chooser.getSelectedFile();
            final CSVProcessor csvProc = new CSVProcessor(" ");
            try {
                this.sessionManager.setCurrentDocumentAsArray(csvProc.read(file));
                SwingUtilities.updateComponentTreeUI(this.frame);
            } catch (final CSVParseException e1) {
                JOptionPane.showMessageDialog(this.frame,
                        "Error while parsing");
            } catch (final IOException e1) {
                JOptionPane.showMessageDialog(this.frame,
                        "Error while accessing file.");
            }
        }
    }

}