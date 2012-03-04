package csv;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
     * @param delimeter
     *        Delimiter used to separate values in csv file.
     */
    public CSVProcessor(final String delimeter) {
        this.delimiter = delimeter;
    }

    /**
     * Get current value of delimiter.
     * @return the delimiter
     */
    public final String getDelimiter() {
        return delimiter;
    }

    /**
     * Read csv lines from file.
     * @param file
     *        File to read lines from.
     * @throws IOException
     *         if have problems with File loading.
     * @throws CSVParseException
     *         if something wrong with parse stage. For example length of rows
     *         doesn't match or not all of values are double.
     * @return Double[][] table contained parsed values.
     */
    public final double[][] read(final File file) throws IOException,
            CSVParseException {
        final List<String> rows = new ArrayList<String>();
        final BufferedReader br = new BufferedReader(new FileReader(file));
        while (br.ready()) {
            rows.add(br.readLine());
        }
        br.close();

        final int height = rows.size();
        if (height == 0)
            return new double[0][0];
        final int width = rows.get(0).split(this.delimiter).length;
        final double[][] table = new double[height][width];
        for (int i = 0; i < height; i++) {
            final String[] row = rows.get(i).split(this.delimiter);
            for (int j = 0; j < width; j++) {

                if (row.length != width)
                    throw new CSVParseException(
                            "Length of rows doesn't match", i);
                try {
                    table[i][j] = Double.parseDouble(row[j]);
                } catch (final Exception e) {
                    throw new CSVParseException(e.getMessage(), i);
                }
            }
        }
        return table;
    }

    /**
     * Set new value of delimiter.
     * @param delimiter
     *        the delimiter to set
     */
    public final void setDelimiter(final String delimiter) {
        this.delimiter = delimiter;
    }

    /**
     * Write csv lines to file.
     * @param file
     *        File to write lines into.
     * @throws IOException
     *         if have problems with saving to File.
     */
    public final void write(final File file, final double[][] rows)
            throws IOException {
        final BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        for (int i = 0; i < rows.length; i++) {
            final double[] row = rows[i];
            for (int j = 0; j < row.length; j++) {
                final double d = row[j];
                bw.write(d + ((j < row.length - 1) ? this.delimiter : ""));
            }
            if (i < rows.length - 1) {
                bw.newLine();
            }
        }

        bw.close();
    }
}
