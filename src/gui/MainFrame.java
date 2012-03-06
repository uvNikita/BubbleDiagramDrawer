package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import model.BubbleTableModel;
import model.SessionManager;
import bubbleDrawer.BubblePanel;
import csv.CSVParseException;

/**
 * @author Nikita Uvarov 
 * Main window to work with bubble diagram.
 */
public class MainFrame extends JFrame {
    /**
     * Current manager of data.
     */
    private final SessionManager sessionManager;
    /**
     * Default window width.
     */
    private int defaultWidth = 1024;
    /**
     * Default window height.
     */
    private int defaultHeight = 768;
    /**
     * Panel to draw diagram on.
     */
    private BubblePanel bubblePanel;

    /**
     * Generated seralVersionUID.
     */
    private static final long serialVersionUID = 7743212290439358675L;

    /**
     * Crate, initialize Mainframe on start.
     * @param args
     *        Command line argument.
     */
    public static void main(final String[] args) {
        final Logger root = Logger.getLogger("");
        root.setLevel(Level.INFO);
        final JFrame jf = new MainFrame();
        jf.setVisible(true);
    }

    /**
     * Initialize and configure mainframe.
     */
    public MainFrame() {
        super("Bubble diagram drawer");

        this.sessionManager = new SessionManager();
        this.bubblePanel = new BubblePanel(this.sessionManager);

        JTable table = new JTable(new BubbleTableModel(this.sessionManager));
        Dimension d = table.getPreferredSize();
        d.width = 150;
        table.setPreferredScrollableViewportSize(d);
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(new JScrollPane(table), BorderLayout.WEST);
        this.getContentPane().add(bubblePanel);
        this.createMenu();

        this.setSize(new Dimension(this.defaultWidth, this.defaultHeight));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Create menu of MainFrame.
     */
    protected void createMenu() {
        final JMenu file = new JMenu("File");
        file.setMnemonic('F');

        final JMenuItem openItem = new JMenuItem("Open");
        openItem.setMnemonic('o');
        file.add(openItem);
        openItem.addActionListener(new OpenFileAction(this, this.sessionManager));

        JMenuItem saveAsItem = new JMenuItem("Save as...");
        saveAsItem.setMnemonic('s');
        file.add(saveAsItem);
        saveAsItem.addActionListener(new SaveFileAsAction(this,
                this.sessionManager));

        JMenuItem exportItem = new JMenuItem("Export...");
        exportItem.setMnemonic('e');
        file.add(exportItem);
        exportItem.addActionListener(new ExportAction(this, this.bubblePanel));

        final JMenuBar bar = new JMenuBar();
        setJMenuBar(bar);
        bar.add(file);
    }

}
