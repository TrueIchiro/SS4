package Beispiel01.Functionalities.Profiler.Implementation;

import Beispiel01.Functionalities.DataList.DataList;
import Beispiel01.Functionalities.DataTypes.Implementation.StorageData;
import Beispiel01.Functionalities.DataTypes.ProvidedData;
import Beispiel01.Functionalities.Profiler.Profiler;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class StorageProfiler extends Profiler {

    private DataList dataList;
    private DataList storedData;

    private int minData = 1000;
    private int maxData = 0;
    private int avgData = 0;

    public StorageProfiler(DataList dataList) {
        super(dataList, new DataList());
    }

    @Override
    public String toString() {
        return "StorageProfiler";
    }

    private void warn() {
        System.out.println("----- TOO MUCH DATA IS BEING USED -----");
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
                if (dataList.get().getFirstData() < minData) {
                    minData = dataList.get().getFirstData();
                } else if (dataList.get().getFirstData() > maxData) {
                    maxData = dataList.get().getFirstData();
                }

                avgData = (avgData + dataList.get().getFirstData()) / 2;

                //too much data is being used
                if (dataList.get().getFirstData() * 0.80 > dataList.get().getSecondData()) {
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
            Thread.sleep((waitTimeMinutes * 60000));

            System.out.println("##########");
            System.out.println("StorageProfiler Statistics");
            System.out.println("    Minimal occupied amount of GB: " + minData);
            System.out.println("    Maximal occupied amount of GB: " + maxData);
            System.out.println("    Average occupied amount of GB: " + avgData);
            System.out.println("##########");
        } catch (InterruptedException e) {

        }
    }

    public void writeStatistic(String filename, int periodicityInMinutes) {
        while (true) {
            try {
                //waits a certain amount of time before being reactivated
                Thread.sleep(periodicityInMinutes * 60000);
                //gets the current data
                ProvidedData data = storedData.get();

                FileWriter writer = new FileWriter(filename);
                //what will be written in the file
                String toWrite = data.getFirstData() + ";" + data.getSecondData();
                writer.write(toWrite);
                writer.close();
            } catch (InterruptedException e) {

            } catch (IOException e) {

            }
        }
    }

}
