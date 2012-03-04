package bubbleDrawer;

import java.awt.Dimension;
import java.awt.HeadlessException;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;

import model.SessionManager;
import csv.CSVParseException;
import csv.CSVProcessor;

public class MainFrame extends JFrame {

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
        Logger root = Logger.getLogger("");
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
        final SessionManager sm = new SessionManager();
        final CSVProcessor cp = new CSVProcessor(" ");
        sm.setCurrentDocument(cp.read(new File("/tmp/1")));
        this.getContentPane().add(new BubblePanel(sm));
        this.setSize(new Dimension(420, 420));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
