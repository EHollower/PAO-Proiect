package Serivice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;

public class CSVWriter {
    private static CSVWriter instance;
    private BufferedWriter csvWriter;

    private CSVWriter() {
        try {
            csvWriter = new BufferedWriter(new FileWriter("log/actions.csv", true));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static synchronized CSVWriter getInstance() {
        if (instance == null) {
            instance = new CSVWriter();
        }
        return instance;
    }

    private String getFormattedDateTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return now.format(formatter);
    }

    public void exportToCSV(String data) {
        try {
            csvWriter.write(data);
            csvWriter.write(",");
            csvWriter.write(this.getFormattedDateTime());
            csvWriter.write("\n");
            csvWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


