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
    private final String delimiter;
    /**
     * Container that aggregates values from csv file.
     */
    private List<String> rows = new ArrayList<String>();

    /**
     * @param delimeter
     *        Delimiter used to separate values in csv file.
     */
    public CSVProcessor(final String delimeter) {
        this.delimiter = delimeter;
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
    public final void deserialize(final File file) throws IOException,
            ClassNotFoundException {
        final ObjectInputStream is = new ObjectInputStream(new FileInputStream(
                file));
        this.rows = (List<String>) is.readObject();
        is.close();
    }

    /**
     * Get current value of rows.
     * @return the rows
     */
    public final List<String> getRows() {
        return rows;
    }

    /**
     * Read csv lines from file.
     * @param file
     *        File to read lines from.
     * @throws IOException
     *         if have problems with File loading.
     */
    public final void loadFromFile(final File file) throws IOException {
        final BufferedReader br = new BufferedReader(new FileReader(file));
        while (br.ready()) {
            this.rows.add(br.readLine());
        }
        br.close();
    }

    public Double[][] parse() throws CSVParseException {
        final int height = this.rows.size();
        final int width = this.rows.get(0).split(this.delimiter).length;
        final Double[][] table = new Double[height][width];
        for (int i = 0; i < height; i++) {
            final String[] row = this.rows.get(i).split(this.delimiter);
            for (int j = 0; j < width; j++) {
                try {
                    if (row.length != width)
                        throw new CSVParseException(
                                "Length of rows doesn't match", i);
                    table[i][j] = new Double(row[j]);
                } catch (final Exception e) {
                    throw new CSVParseException(e.getMessage(), i);
                }
            }
        }
        return table;

    }

    /**
     * Write csv lines to file.
     * @param file
     *        File to write lines into.
     * @throws IOException
     *         if have problems with saving to File.
     */
    public final void saveToFile(final File file) throws IOException {
        final BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        for (final String row : this.rows) {
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
        final ObjectOutputStream os = new ObjectOutputStream(
                new FileOutputStream(file));
        os.writeObject(this.rows);
        os.close();
    }

    /**
     * Set new value of rows.
     * @param rows
     *        the rows to set
     */
    public final void setRows(final List<String> rows) {
        this.rows = rows;
    }
}
