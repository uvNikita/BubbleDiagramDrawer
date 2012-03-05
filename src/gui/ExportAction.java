/**
 * 
 */
package gui;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import bubbleDrawer.BubblePanel;

/**
 * @author nikita
 */
public class ExportAction extends AbstractAction {
    private JFrame frame;
    private BubblePanel panel;

    public ExportAction(JFrame frame, BubblePanel panel) {
        this.frame = frame;
        this.panel = panel;
    }

    /*
     * (non-Javadoc)
     * @see
     * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(final ActionEvent e) {
        final JFileChooser chooser = new JFileChooser(".");
        FileNameExtensionFilter jpgFilter = new FileNameExtensionFilter(
                "JPEG", "jpeg", "jpg");
        chooser.setFileFilter(jpgFilter);
        while (true) {
            final int returnVal = chooser.showSaveDialog(this.frame);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                final File file = chooser.getSelectedFile();
                if (file.exists()) {
                    int react = JOptionPane
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
                Image img = this.panel.getImage();
                try {
                    ImageIO.write((BufferedImage) img, "jpg", file);
                } catch (IOException e1) {
                    JOptionPane.showMessageDialog(this.frame,
                            "Error while accessing file.");
                }
            }
            return;
        }
    }
}
