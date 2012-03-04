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

public class MainFrame extends JFrame {
    private final SessionManager sessionManager;
    private int defaultWidth = 1024;
    private int defaultHeight = 768;

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

        this.sessionManager = new SessionManager();
        JTable table = new JTable(
                new BubbleTableModel(this.sessionManager));
//        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        Dimension d = table.getPreferredSize();
        d.width = 150;
        table.setPreferredScrollableViewportSize(d);
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(new JScrollPane(table), BorderLayout.WEST);
        this.getContentPane().add(new BubblePanel(this.sessionManager));
        this.createMenu();

        this.setSize(new Dimension(this.defaultWidth, this.defaultHeight));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    protected void createMenu() {
        final JMenu file = new JMenu("File");
        file.setMnemonic('F');

        final JMenuItem openItem = new JMenuItem("Open");
        openItem.setMnemonic('O');
        file.add(openItem);
        openItem.addActionListener(new OpenFileAction(this,
                this.sessionManager));

        JMenuItem saveAsItem = new JMenuItem("Save as...");
        saveAsItem.setMnemonic('o');
        file.add(saveAsItem);
        saveAsItem.addActionListener(new SaveFileAsAction(this,
                this.sessionManager));

        final JMenuBar bar = new JMenuBar();
        setJMenuBar(bar);
        bar.add(file);
    }

}
