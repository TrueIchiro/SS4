package Beispiel01.Functionalities.Profiler.Implementation;

import Beispiel01.Functionalities.DataList.DataList;
import Beispiel01.Functionalities.DataTypes.ProvidedData;
import Beispiel01.Functionalities.Profiler.Profiler;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

//takes data from the list and makes it look cute
public class TemperatureProfiler extends Profiler {

    private DataList dataList;
    //stores all data from the beginning
    private DataList storedData;
    private int threshold;
    private String name = "TemperatureProfiler";

    //stats
    private int minTemp = 1000;
    private int maxTemp = 0;
    private int avgTemp = 0;

    public TemperatureProfiler(DataList dataList, int threshold) {
        super(dataList, new DataList());
        this.threshold = threshold;
    }

    public TemperatureProfiler(DataList dataList, int threshold, String name) {
        super(dataList, new DataList());
        this.threshold = threshold;
        this.name = name;
    }

    private void warn() {
        System.out.println("----- TEMPERATURE IS CURRENTLY TOO HIGH -----");
    }

    @Override
    public void run() {
        Random r = new Random();
        //get the storedData List
        storedData = super.getStoredList();
        //get the dataList
        dataList = super.getDataList();

        while( dataList.get() != null){

            try {
                if (dataList.get().getFirstData() < minTemp) {
                    minTemp = dataList.get().getFirstData();
                } else if (dataList.get().getFirstData() > maxTemp) {
                    maxTemp = dataList.get().getFirstData();
                }

                avgTemp = (avgTemp + dataList.get().getFirstData()) / 2;

                if (this.dataList.get().getFirstData() >= threshold) {
                    warn();
                }

                //uses the run method of the Profiler
                super.run();

                Thread.sleep(r.nextInt(1000));
            } catch (InterruptedException e) {

            }
        }
    }

    //statistic will be printed on the console after a certain amount of time
    //periodically
    public void showStatistic(int waitTimeMinutes) {
        try {
            Thread.sleep((waitTimeMinutes * 60000));

            System.out.println("##########");
            System.out.println("TemperatureProfiler Statistics");
            System.out.println("    Minimal temperature: " + minTemp);
            System.out.println("    Maximal temperature: " + maxTemp);
            System.out.println("    Average temperature: " + avgTemp);
            System.out.println("##########");
        } catch (InterruptedException e) {

        }
    }

    public void writeStatistic(int waitTimeMinutes, String filename) {
        try {
            //first time writing to the file
            FileWriter head = new FileWriter(filename);
            //writing the header
            String headWrite = "Temperature;MinTemp;MaxTemp;AvgTemp" + "\n";
            head.write(headWrite);
            head.close();

            while (true) {
                //sleep for a 100 years and awake to the Fire Nation attacking
                Thread.sleep(waitTimeMinutes * 60000);

                //for writing to the file
                //it will be a csv file!
                FileWriter writer = new FileWriter(filename, true);
                String toWrite = this.storedData.get().getFirstData()
                        + ";" + this.minTemp
                        + ";" + this.maxTemp
                        + ";" + this.avgTemp
                        + "\n";
                writer.write(toWrite);
                writer.close();
            }
        } catch (IOException e) {

        } catch (InterruptedException e) {

        }
    }

    //to name it
    @Override
    public String toString() {
        return this.name;
    }

}
