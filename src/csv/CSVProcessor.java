package csv;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
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
    /**
     * Delimiter used to separate values in csv.
     */
    private String delimiter;
    /**
     * Container that aggregates values from csv file.
     */
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

    /**
     * @param delimeter
     *        Delimiter used to separate values in csv file.
     */
    public CSVProcessor(final String delimeter) {
        this.delimiter = delimeter;
    }

    /**
     * Read csv lines from file.
     * @param file
     *        File to read lines from.
     * @throws IOException
     *         if have problems with File loading.
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
     *        File to write lines into.
     * @throws IOException
     *         if have problems with saving to File.
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
     *        File to serialize into.
     * @throws IOException
     *         if have problems with serializing or writhing to File.
     */
    public final void serialize(final File file) throws IOException {
        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(
                file));
        os.writeObject(this.rows);
        os.close();
    }

    /**
     * Deserialize String List from file.
     * @param file
     *        File to get Object from.
     * @throws IOException
     *         if have problems with deserializing or reading to File.
     * @throws ClassNotFoundException
     *         when there is no such class to serialize.
     */
    public final void deserialize(final File file)
            throws IOException, ClassNotFoundException {
        ObjectInputStream is = new ObjectInputStream(new FileInputStream(file));
        this.rows = (List<String>) is.readObject();
        is.close();
    }
}
