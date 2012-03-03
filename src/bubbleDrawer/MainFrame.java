package bubbleDrawer;

import java.awt.Dimension;
import java.awt.HeadlessException;
import java.io.File;
import java.io.IOException;

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
        cp.loadFromFile(new File("/tmp/1"));
        sm.setCurrentDocument(cp.parse());
        this.getContentPane().add(new BubblePanel(sm));
        this.setSize(new Dimension(420, 420));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
