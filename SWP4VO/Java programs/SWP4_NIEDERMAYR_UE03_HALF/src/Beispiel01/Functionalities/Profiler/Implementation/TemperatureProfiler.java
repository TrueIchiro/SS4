package Beispiel01.Functionalities.Profiler.Implementation;

import Beispiel01.Functionalities.DataList.DataList;
import Beispiel01.Functionalities.DataTypes.ProvidedData;
import Beispiel01.Functionalities.Profiler.Profiler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

//takes data from the list and makes it look cute
public class TemperatureProfiler extends Profiler {

    private DataList dataList;
    //stores all data from the beginning
    private DataList storedData;

    //stats
    private int minTemp = 1000;
    private int maxTemp = 0;
    private int avgTemp = 0;

    public TemperatureProfiler(DataList dataList) {
        super(dataList, new DataList());
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

                if (maxTemp >= 60) {
                    warn();
                }

                super.run();

                Thread.sleep(r.nextInt(1000));
            } catch (InterruptedException e) {

            }
        }
    }

    public void showStatistic(int waitTimeMinutes) {
        try {
            Thread.sleep((waitTimeMinutes * 1000));

            System.out.println("TemperatureProfiler Statistics");
            System.out.println("    Minimal temperature: " + minTemp);
            System.out.println("    Maximal temperature: " + maxTemp);
            System.out.println("    Average temperature: " + avgTemp);
            System.out.println("----------");
        } catch (InterruptedException e) {

        }
    }

    //to name it
    @Override
    public String toString() {
        return "TemperatureProfiler";
    }

}
