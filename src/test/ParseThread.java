package test;

import csv.CSVParseException;
import csv.CSVProcessor;

/**
 * @author nikita
 * Thread for parsing csv files.
 */
public class ParseThread extends Thread {

    /**
     * Result of parse.
     */
    private Double[][] result;
    /**
     * CSVProcessor used to parse.
     */
    private CSVProcessor csvproc;

    /**
     * Creates ParseThread with specific CSVProcessor.
     * @param csvproc
     * Which {@link CSVProcessor} use to parse values.
     */
    public ParseThread(CSVProcessor csvproc) {
        this.csvproc = csvproc;
    }

    @Override
    public final void run() {
        try {
            this.result = this.csvproc.parse();
        } catch (CSVParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get current value of result.
     * @return the result
     */
    public final Double[][] getResult() {
        return result;
    }

}
