package gui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.DocumentHolder;
import csv.CSVProcessor;

/**
 * @author nikita
 */
public class CloseAction extends WindowAdapter {
    /**
     * Parent frame.
     */
    private final JFrame frame;
    /**
     * Document container to watch changes in.
     */
    private final DocumentHolder holder;

    /**
     * Creates CloseAction using specified parent frame and data holder.
     * @param frame
     *        Parent frame.
     * @param holder
     *        Container of data to check changes in.
     */
    public CloseAction(final JFrame frame, final DocumentHolder holder) {
        this.frame = frame;
        this.holder = holder;
    }

    /*
     * (non-Javadoc)
     * @see
     * java.awt.event.WindowAdapter#windowClosing(java.awt.event.WindowEvent)
     */
    @Override
    public final void windowClosing(final WindowEvent e) {
        if (!this.holder.wasChanged()) {
            System.exit(0);
        }
        int react = JOptionPane.showConfirmDialog(this.frame,
                "The diagramhas not been saved. Save changes now?",
                "Closing diagram without saving",
                JOptionPane.YES_NO_CANCEL_OPTION);
        if (react == JOptionPane.CANCEL_OPTION) {
            return;
        } else if (react == JOptionPane.NO_OPTION) {
            System.exit(0);
        }
        final JFileChooser chooser = new JFileChooser(".");
        while (true) {
            final int returnVal = chooser.showSaveDialog(this.frame);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                final File file = chooser.getSelectedFile();
                if (file.exists()) {
                    react = JOptionPane
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
                    csvProc.write(file, this.holder
                            .getCurrentDocumentAsArray());
                    System.exit(0);
                } catch (final IOException e1) {
                    JOptionPane.showMessageDialog(this.frame,
                            "Error while accessing file.");
                }
            }
            return;
        }
    }
}
