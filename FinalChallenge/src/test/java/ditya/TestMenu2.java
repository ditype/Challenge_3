package ditya;

import java.io.*;
import java.util.*;

public class TestMenu2 {
    private final List<List<String>> data;
    private final String csvFileName;
    private final String txtFileName;

    /**
     * Constructor
     * @param csvFileName to collect the csv file
     * @param txtFileName to collect the txt file
     */
    public TestMenu2(String csvFileName, String txtFileName) {
        this.csvFileName = csvFileName;
        this.txtFileName = txtFileName;
        this.data = new ArrayList<>();
    }

    /**
     * Method to read csv
     * @throws IOException for error handling
     */
    public void readCsv() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(csvFileName));
        String line = reader.readLine();
        while (line != null) {
            String[] values = line.split(",");
            List<String> row = new ArrayList<>(Arrays.asList(values));
            data.add(row);
            line = reader.readLine();
        }
        reader.close();
    }

    /**
     * Method to calculate mode from data
     * @return mode value as String
     */
    public String calculateMode() {
        Map<String, Integer> frequency = new HashMap<>();
        for (List<String> row : data) {
            for (String value : row) {
                if (frequency.containsKey(value)) {
                    frequency.put(value, frequency.get(value) + 1);
                } else {
                    frequency.put(value, 1);
                }
            }
        }

        int maxFrequency = 0;
        String mode = "";
        for (Map.Entry<String, Integer> entry : frequency.entrySet()) {
            if (entry.getValue() > maxFrequency) {
                maxFrequency = entry.getValue();
                mode = entry.getKey();
            }
        }

        return mode;
    }

    /**
     * Method to calculate average from data
     * @return average value as double
     */
    public double calculateAverage() {
        double sum = 0;
        int count = 0;
        for (List<String> row : data) {
            for (String value : row) {
                sum += Double.parseDouble(value);
                count++;
            }
        }
        return sum / count;
    }

    /**
     * Method to calculate median from data
     * @return median value as double
     */
    public double calculateMedian() {
        List<Double> values = new ArrayList<>();
        for (List<String> row : data) {
            for (String value : row) {
                values.add(Double.parseDouble(value));
            }
        }
        Collections.sort(values);
        int size = values.size();
        if (size % 2 == 0) {
            int mid = size / 2;
            return (values.get(mid - 1) + values.get(mid)) / 2;
        } else {
            return values.get(size / 2);
        }
    }

    /**
     * Method to write the result to destination file
     * @throws IOException for error handling
     */
    public void writeResultToFile() throws IOException {
        String mode = calculateMode();
        double average = calculateAverage();
        double median = calculateMedian();
        BufferedWriter writer = new BufferedWriter(new FileWriter(txtFileName));
        writer.write("Berikut hasil pengolahan nilai :");
        writer.newLine();
        writer.newLine();
        writer.write("Berikut hail sebaran data nilai");
        writer.newLine();
        writer.write("Modus      : " + mode);
        writer.newLine();
        writer.write("Average   : " + average);
        writer.newLine();
        writer.write("Median    : " + median);
        writer.close();
    }

    /**
     * Create object from this class, call the created method and fill it's arguments/params
     *
     * @return
     * @throws IOException for error handling
     */
    public boolean call() throws IOException {
        String pathCsv = "/home/summer/Documents/temp/directory/data_sekolah.csv";
        String pathMenu2 = "/home/summer/Documents/temp/directory/generated_data_sekolah.csv";

        TestMenu2 menu2 = new TestMenu2(pathCsv, pathMenu2);
        menu2.readCsv();
        menu2.writeResultToFile();

        return true;
    }
}