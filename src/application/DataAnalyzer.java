package application;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

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

    public static boolean deleteFloatFromDataArray(float floatToDelete){
        return dataArray.remove(floatToDelete);
    }

    public static int getDataArraySize(){
        return dataArray.size();
    }

    private static void sortDataArray(){
        Collections.sort(dataArray);
    }

    // reverseDataArray always set array to the reverse of sorted array
    private static void reverseDataArray(){
        Collections.sort(dataArray);
        Collections.reverse(dataArray);
    }

    public static float getMean() {
        float sum = 0f, mean;
        for (float i: dataArray) {
            sum += i;
        }
        mean = sum / dataArray.size();
        return mean;
    }

    public static float getMedian(){
        float median;

        if (dataArray.size() == 1) return dataArray.get(0);

        // If the size of the array is even, median is calculated by
        // taking the sum of the middle two indices and dividing by 2
        if(dataArray.size()%2 == 0) {
             median = (dataArray.get(dataArray.size()/2 - 1) + dataArray.get(dataArray.size()/2))/2;
        } else {
            median = dataArray.get(dataArray.size()/2);
        }
        return median;
    }

    public static float[] getSortedDataArray(){
        sortDataArray();
        float[] floatDataArray = new float[dataArray.size()];
        int index = 0;
        for(Float i: dataArray){
            floatDataArray[index++] = i;
        }
        return floatDataArray;
    }

    public static float[] getReversedDataArray() {
        reverseDataArray();
        float[] floatDataArray = new float[dataArray.size()];
        int index = 0;
        for(Float i: dataArray){
            floatDataArray[index++] = i;
        }
        return floatDataArray;
    }

    public static void resetDataArray(){
        dataArray.clear();
    }

    //TODO Add Method to find top 3 occurring values

    public static Object[] getTopThreeOccuring()
    {
        ArrayList<Float> top3Array = new ArrayList<>();

        for(int i = 0; i < 3; i++)
        {
            top3Array.add(null);
        }

        float[] inputArray = new float[dataArray.size()];
        for (int i = 0; i < inputArray.length; i++) {
            inputArray[i] = dataArray.get(i);
        }

        //Creating HashMap object with elements as keys and their occurrences as values

        HashMap<Float, Integer> elementCountMap = new HashMap<>();

        //Inserting all the elements of inputArray into elementCountMap

        for (int i = 0; i < dataArray.size(); i++)
        {
            float currentElement = inputArray[i];
            if (elementCountMap.containsKey(currentElement))
            {
                //If an element is present, incrementing its count by 1

                int freq = elementCountMap.get(currentElement);
                freq++;
                elementCountMap.put(currentElement, freq);
            }
            else
            {
                //If an element is not present, put that element with 1 as its value
                elementCountMap.put(currentElement, 1);
            }
        }

        //Iterating through elementCountMap to get the most frequent element and its frequency

        Set<Entry<Float, Integer>> entrySet = elementCountMap.entrySet();
        for(int i = 0; i < 3; i++)
        {

            int max_count = 0;
            float element = -1.0f;
            for (Entry<Float, Integer> entry : entrySet)
            {
                if(max_count < entry.getValue()) {

                    element = entry.getKey();
                    max_count = entry.getValue();

                }
            }

            // Only return non-unique numbers
            if(elementCountMap.get(element) > 1) {
                top3Array.set(i, element);
                elementCountMap.remove(element);
            }

            entrySet = elementCountMap.entrySet();
        }

        return top3Array.toArray();
    }

    //TODO Add Method to find all values above Percentile
    public static float[] getPercentile(double percentile)
    {
        float[] inPercentile = new float[0];
        percentile = percentile / 100;
        float minToBeat = (float) (getMax() * percentile);

        int count = 0;
        for(int i = 0; i < dataArray.size(); i++)
        {
            if (dataArray.get(i) > minToBeat)
            {
                count++;
            }
        }

        if(count > 0)
        {
            inPercentile = new float[count];

            count = 0;

            for(int i = 0; i < dataArray.size(); i++)
            {
                if (dataArray.get(i) > minToBeat)
                {
                    inPercentile[count] = dataArray.get(i);
                    count++;
                }
            }
        }

        return inPercentile;
    }

    public static float getMax()
    {
        float max = -Float.MAX_VALUE;
        for(int i = 0; i < dataArray.size(); i++)
        {
            if(dataArray.get(i) > max)
            {
                max = dataArray.get(i);
            }
        }

        return max;
    }

    //TODO Add method to find the average of all values above Percentile.
    public static float getAverageAbovePercentile(double percentile)
    {
        float averageAbove = 0;
        float[] temp = getPercentile(percentile);
        for(int i = 0; i < temp.length; i++)
        {
            averageAbove += temp[i];
        }

        averageAbove = averageAbove / temp.length;

        return averageAbove;
    }

}
