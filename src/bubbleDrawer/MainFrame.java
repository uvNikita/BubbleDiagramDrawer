package bubbleDrawer;

import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import model.SessionManager;
import csv.CSVParseException;
import csv.CSVProcessor;

public class MainFrame extends JFrame {
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

        @Override
        public void actionPerformed(final ActionEvent e) {
            final JFileChooser chooser = new JFileChooser(".");
            final int returnVal = chooser.showOpenDialog(frame);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                final File file = chooser.getSelectedFile();
                final CSVProcessor csvProc = new CSVProcessor(" ");
                try {
                    this.sessionManager.setCurrentDocument(csvProc
                            .read(file));
                    this.frame.repaint();
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

    private final SessionManager sessionManager;

    /**
     * 
     */
    private static final long serialVersionUID = 7743212290439358675L;

    /**
     * @param args
     * @throws IOException
     * @throws CSVParseException
     */
    public static void main(final String[] args) throws IOException,
            CSVParseException {
        final Logger root = Logger.getLogger("");
        root.setLevel(Level.INFO);
        final JFrame jf = new MainFrame();
        jf.setVisible(true);
    }

    /**
     * @param title
     * @throws IOException
     * @throws CSVParseException
     * @throws HeadlessException
     */
    public MainFrame() throws IOException, CSVParseException {
        super("Bubble diagram drawer");

        final JMenu file = new JMenu("File");
        file.setMnemonic('F');
        final JMenuItem openItem = new JMenuItem("Open");
        openItem.setMnemonic('O');
        file.add(openItem);
        final JMenuBar bar = new JMenuBar();
        setJMenuBar(bar);
        bar.add(file);

        this.sessionManager = new SessionManager();
        this.getContentPane().add(
                new BubblePanel(this.sessionManager));
        openItem.addActionListener(new OpenFileAction(this,
                this.sessionManager));

        this.setSize(new Dimension(420, 420));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
