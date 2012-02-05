package csv;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author nikita
 */
public class CSVProcessor {
    private String delimeter;
    private List<String> rows = new ArrayList<String>();

    /**
     * Get current value of rows.
     * @return the rows
     */
    public final List<String> getRows() {
        return rows;
    }

    /**
     * Set new value of rows.
     * @param rows
     *        the rows to set
     */
    public final void setRows(final List<String> rows) {
        this.rows = rows;
    }

    public CSVProcessor(String delimeter) {
        this.delimeter = delimeter;
    }

    /**
     * Read csv lines from file.
     * @param file
     *        File to read lines from.
     * @throws IOException
     */
    public final void loadFromFile(final File file) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        while (br.ready()) {
            this.rows.add(br.readLine());
        }
        br.close();
    }

    /**
     * Write csv lines to file.
     * @param file
     *          File to write lines into.
     * @throws IOException
     */
    public final void saveToFile(final File file) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        for (String row : this.rows) {
            bw.write(row);
            bw.newLine();
        }
        bw.close();
    }

    /**
     * Serialize String List of csv lines into file.
     * @param file
     *  File to serialize into.
     * @throws FileNotFoundException
     * @throws IOException
     */
    public final void serialize(final File file) throws FileNotFoundException,
            IOException {
        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(
                file));
        os.writeObject(this.rows);
        os.close();
    }

    /**
     * Deserialize String List from file.
     * @param file
     *  File to get Object from.
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public final void deserialize(final File file) throws FileNotFoundException,
            IOException, ClassNotFoundException {
        ObjectInputStream is = new ObjectInputStream(new FileInputStream(file));
        this.rows = (List<String>) is.readObject();
        is.close();
    }
}
