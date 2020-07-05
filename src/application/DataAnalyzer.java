package application;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class DataAnalyzer {

    private static ArrayList<Float> dataArray = new ArrayList<>();

    public static void addFileDataToArray(File file){
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            while((st = br.readLine()) != null){
                try {
                    float newFloat = Float.parseFloat(st);
                    dataArray.add(newFloat);
                } catch (NumberFormatException e) {
                    continue;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
        } catch (IOException e) {
            e.printStackTrace();
        }

        sortDataArray();

        for (Float dataPoint: dataArray) {
            System.out.println(dataPoint);
        }
        System.out.println("\n Total Count: " + dataArray.size());
    }

    private static void sortDataArray(){
        Collections.sort(dataArray);
    }

}
