package com.ditya;

import java.io.*;
import java.util.*;

public class Menu1 {
    private final List<List<String>> data;
    private final String csvFileName;
    private final String txtFileName;

    /**
     * Constructor
     * @param csvFileName to collect the csv file
     * @param txtFileName to collect the txt file
     */
    public Menu1(String csvFileName, String txtFileName) {
        this.csvFileName = csvFileName;
        this.txtFileName = txtFileName;
        this.data = new ArrayList<>();
    }

    /**
     * Method to read csv
     * @throws IOException for error handling
     */
    public void readCsv() throws IOException {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(csvFileName));
            String line = reader.readLine();
            while (line != null) {
                String[] values = line.split(",");
                List<String> row = new ArrayList<>(Arrays.asList(values));
                data.add(row);
                line = reader.readLine();
            }
            reader.close();
        } catch (FileNotFoundException e){
            System.out.println(e);
        }
    }

    public Map<String, Integer> calculateFrequency() {
        Map<String, Integer> frequencyMap = new HashMap<>();
        for (List<String> row : data) {
            for (String value : row) {
                if (frequencyMap.containsKey(value)) {
                    frequencyMap.put(value + "  ", frequencyMap.get("   " + value + "\n") + 1);
                } else {
                    frequencyMap.put(value + "      ", 1);
                }
            }
        }
        return frequencyMap;
    }

    /**
     * Method to calculate mode
     * @return mode
     */
    public String calculateMode() {
        Map<String, Integer> freqMap = new HashMap<>();
        for (List<String> row : data) {
            for (String value : row) {
                if (freqMap.containsKey(value)) {
                    freqMap.put(value, freqMap.get(value) + 1);
                } else {
                    freqMap.put(value, 1);
                }
            }
        }
        String mode = null;
        int maxFreq = 0;
        for (Map.Entry<String, Integer> entry : freqMap.entrySet()) {
            if (entry.getValue() > maxFreq) {
                mode = entry.getKey();
                maxFreq = entry.getValue();
            }
        }
        return mode;
    }

    /**
     * Method to write the result to destination file
     * @throws IOException for error handling
     */
    public void writeModeToFile() throws IOException {
        String mode = calculateMode();
        String freq = String.valueOf(calculateFrequency());
        BufferedWriter writer = new BufferedWriter(new FileWriter(txtFileName));
        writer.write("Berikut hasil pengolahan nilai :");
        writer.newLine();
        writer.newLine();
        writer.write("Modus : " + mode);
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
        String pathMenu1 = "/home/summer/Documents/temp/directory/output_mode.txt";

        Menu1 csvProcessor = new Menu1(pathCsv, pathMenu1);
        csvProcessor.readCsv();
        csvProcessor.calculateFrequency();
        csvProcessor.writeModeToFile();

        return true;
    }
}