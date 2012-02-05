package test;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import csv.CSVProcessor;

public class TestIO {
    private TestIO() {
    }

    /**
     * @param args
     * @throws IOException
     */
    public static void main(final String[] args) throws IOException {
        File ser = new File("serialized.dat");
        CSVProcessor csvproc = new CSVProcessor(",");
        if (ser.exists()) {
            try {
                csvproc.deserialize(ser);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            Scanner in = new Scanner(System.in);
            String path;
            File csvfile;
            final int numOfTries = 3;
            for (int i = 0; i < numOfTries; i++) {
                try {
                    path = in.nextLine();
                    csvfile = new File(path);
                    csvproc.loadFromFile(csvfile);
                    break;
                } catch (IOException e) {
                    if (i >= numOfTries) {
                        throw e;
                    }
                    System.out
                            .println("No such file or something else is wrong.");
                }
            }
        }
        System.out.println(csvproc.getRows());
        csvproc.serialize(ser);
    }

}
