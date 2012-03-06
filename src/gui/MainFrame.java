package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import model.BubbleTableModel;
import model.DocumentHolder;
import model.SessionManager;
import bubbleDrawer.BubblePanel;

/**
 * @author Nikita Uvarov Main window to work with bubble diagram.
 */
public class MainFrame extends JFrame implements TableModelListener {
    /**
     * Default window height.
     */
    private static final int DEFAULT_HEIGHT = 768;
    /**
     * Default window width.
     */
    private static final int DEFAULT_WIDTH = 1024;
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
     * Panel to draw diagram on.
     */
    private final BubblePanel bubblePanel;

    /**
     * Current manager of data.
     */
    private final DocumentHolder sessionManager;

    /**
     * Table containing all bubble diagram data.
     */
    private final JTable table;

    /**
     * Initialize and configure mainframe.
     */
    public MainFrame() {
        super("Bubble diagram drawer");

        this.sessionManager = new SessionManager();
        this.bubblePanel = new BubblePanel(this.sessionManager);

        final BubbleTableModel tableModel = new BubbleTableModel(
                this.sessionManager);

        this.table = new JTable(tableModel);
        final Dimension d = this.table.getPreferredSize();
        d.width = 150;
        this.table.setPreferredScrollableViewportSize(d);
        tableModel.addTableModelListener(this);

        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(new JScrollPane(this.table),
                BorderLayout.WEST);
        this.getContentPane().add(this.bubblePanel);
        this.createMenu();

        this.setSize(new Dimension(MainFrame.DEFAULT_WIDTH,
                MainFrame.DEFAULT_HEIGHT));
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new CloseAction(this, this.sessionManager));
    }

    /**
     * Create menu of MainFrame.
     */
    protected final void createMenu() {
        final JMenu file = new JMenu("File");
        file.setMnemonic('F');

        final JMenuItem openItem = new JMenuItem("Open");
        openItem.setMnemonic('o');
        file.add(openItem);
        openItem.addActionListener(new OpenFileAction(this,
                this.sessionManager));

        final JMenuItem createItem = new JMenuItem("Create");
        createItem.setMnemonic('c');
        file.add(createItem);
        createItem.addActionListener(new CreateAction(this,
                this.sessionManager));

        final JMenuItem saveAsItem = new JMenuItem("Save as...");
        saveAsItem.setMnemonic('s');
        file.add(saveAsItem);
        saveAsItem.addActionListener(new SaveFileAsAction(this,
                this.sessionManager));

        final JMenuItem exportItem = new JMenuItem("Export...");
        exportItem.setMnemonic('e');
        file.add(exportItem);
        exportItem.addActionListener(new ExportAction(this,
                this.bubblePanel));

        final JMenu edit = new JMenu("Edit");
        file.setMnemonic('E');

        final JMenuItem addRowItem = new JMenuItem("Add row");
        addRowItem.setMnemonic('a');
        edit.add(addRowItem);
        addRowItem.addActionListener(new AddRowAction(this.table));

        final JMenuItem delRowItem = new JMenuItem("Del row");
        delRowItem.setMnemonic('d');
        edit.add(delRowItem);
        delRowItem.addActionListener(new DelRowAction(this.table));

        final JMenuBar bar = new JMenuBar();
        this.setJMenuBar(bar);
        bar.add(file);
        bar.add(edit);
    }

    /*
     * (non-Javadoc)
     * @seejavax.swing.event.TableModelListener#tableChanged(javax.swing.event.
     * TableModelEvent)
     */
    @Override
    public final void tableChanged(final TableModelEvent e) {
        this.repaint();
    }
}
