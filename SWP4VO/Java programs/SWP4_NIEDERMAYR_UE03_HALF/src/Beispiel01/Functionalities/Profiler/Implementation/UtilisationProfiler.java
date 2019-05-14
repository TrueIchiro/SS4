package Beispiel01.Functionalities.Profiler.Implementation;

import Beispiel01.Functionalities.DataList.DataList;
import Beispiel01.Functionalities.Profiler.Profiler;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class UtilisationProfiler extends Profiler {

    private DataList dataList;
    private DataList storedData;
    private String name = "UtilisationProfiler";

    private int minProcessed;
    private int maxProcessed;
    private int avgProcessed;

    private int minAll;
    private int maxAll;
    private int avgAll;

    public UtilisationProfiler(DataList dataList) {
        super(dataList, new DataList());
    }

    public UtilisationProfiler(DataList dataList, String name) {
        super(dataList, new DataList());
        this.name = name;
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
                if (dataList.get().getFirstData() < minProcessed) {
                    minProcessed = dataList.get().getFirstData();
                } else if (dataList.get().getFirstData() > maxProcessed) {
                    maxProcessed = dataList.get().getFirstData();
                }

                if (dataList.get().getSecondData() < minAll) {
                    minAll = dataList.get().getSecondData();
                } else if (dataList.get().getSecondData() > maxAll) {
                    maxAll = dataList.get().getSecondData();
                }

                avgProcessed = (avgProcessed + dataList.get().getSecondData()) / 2;
                avgAll = (avgAll + dataList.get().getSecondData()) / 2;

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
            System.out.println("UtilisationProfiler Statistics");
            System.out.println("    Minimal process utilisation: " + minProcessed);
            System.out.println("    Maximal process utilisation: " + maxProcessed);
            System.out.println("    Average process utilisation " + avgProcessed);
            System.out.println("----------");

            System.out.println("    Minimal system utilisation: " + minProcessed);
            System.out.println("    Maximal system utilisation: " + maxProcessed);
            System.out.println("    Average system utilisation: " + avgProcessed);
            System.out.println("##########");
        } catch (InterruptedException e) {

        }
    }

    public void writeStatistic(int waitTimeMinutes, String filename) {
        try {
            //first time writing to the file
            FileWriter head = new FileWriter(filename);
            //writing the header
            String headWrite = "ProcessPercentage;SystemPercentage" + ";"
                    + "MinProcess;MaxProcess;AvgProcess" + ";"
                    + "MinSystem;MaxSystem;AvgSystem"
                    + "\n";
            head.write(headWrite);
            head.close();

            while (true) {
                //sleep for a 100 years and awake to the Fire Nation attacking
                Thread.sleep(waitTimeMinutes * 60000);

                //for writing to the file
                //it will be a csv file!
                FileWriter writer = new FileWriter(filename, true);
                String toWrite = this.storedData.get().getFirstData()
                        + ";" + this.storedData.get().getSecondData()
                        + ";" + this.minProcessed
                        + ";" + this.maxProcessed
                        + ";" + this.avgProcessed
                        + ";" + this.minAll
                        + ";" + this.maxAll
                        + ";" + this.avgAll
                        + "\n";
                writer.write(toWrite);
                writer.close();
            }
        } catch (IOException e) {

        } catch (InterruptedException e) {

        }
    }

    @Override
    public String toString() {
        return this.name;
    }
}
