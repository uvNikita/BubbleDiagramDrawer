package test;

import csv.CSVParseException;
import csv.CSVProcessor;

public class ParseThread extends Thread {

    private Double[][] result;
    private CSVProcessor csvproc;

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
