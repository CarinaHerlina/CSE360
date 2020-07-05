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
                    // If value is not a float it is ignored.
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
        } catch (IOException e) {
            e.printStackTrace();
        }

        sortDataArray();
    }

    public static void addFloatToDataArray(float newFloat) {
        dataArray.add(newFloat);

        sortDataArray();
    }

    public static int getDataArraySize(){
        return dataArray.size();
    }

    private static void sortDataArray(){
        Collections.sort(dataArray);

        for (Float dataPoint: dataArray) {
            System.out.println(dataPoint);
        }
        System.out.println("\n Total Count: " + dataArray.size());
    }

    private static float getMean() {
        float sum = 0f, mean;
        for (float i: dataArray) {
            sum += i;
        }
        mean = sum / dataArray.size();
        return mean;
    }

    public static float getMedian(){
        float median;

        // If the size of the array is even, median is calculated by
        // taking the sum of the middle two indices and dividing by 2
        if(dataArray.size()%2 == 0) {
             median = (dataArray.get(dataArray.size()/2) + dataArray.get(dataArray.size()/2 + 1))/2;
        } else {
            median = dataArray.get(dataArray.size()/2 + 1);
        }
        return median;
    }


}
