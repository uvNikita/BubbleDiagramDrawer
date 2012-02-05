package csv;

import java.io.File;
import java.io.IOException;

public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
        CSVProcessor csvp = new CSVProcessor(",");
        try {
            csvp.loadFromFile(new File("/tmp/1.csv"));
            File f = new File("/tmp/2.csv");
            csvp.saveToFile(f);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
