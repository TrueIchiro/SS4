package Beispiel01.Functionalities.Profiler.Implementation;

import Beispiel01.Functionalities.DataList.DataList;
import Beispiel01.Functionalities.DataTypes.ProvidedData;
import Beispiel01.Functionalities.Profiler.Profiler;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class NetworkProfiler extends Profiler {

    private DataList dataList;
    private DataList storedData;
    private String name = "NetworkProfiler";

    private int minAccepted;
    private int maxAccepted;
    private int avgAccepted;

    private int minSent;
    private int maxSent;
    private int avgSent;

    public NetworkProfiler(DataList dataList) {
        super(dataList, new DataList());
    }

    public NetworkProfiler(DataList dataList, String name) {
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
                if (dataList.get().getFirstData() < minAccepted) {
                    minAccepted = dataList.get().getFirstData();
                } else if (dataList.get().getFirstData() > maxAccepted) {
                    maxAccepted = dataList.get().getFirstData();
                }

                if (dataList.get().getSecondData() < minSent) {
                    minSent = dataList.get().getSecondData();
                } else if (dataList.get().getSecondData() > maxSent) {
                    maxSent = dataList.get().getSecondData();
                }

                avgAccepted = (avgAccepted + dataList.get().getFirstData()) / 2;
                avgSent = (avgSent + dataList.get().getSecondData()) / 2;

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
            System.out.println("NetworkProfiler Statistics");
            System.out.println("    Minimal accepted amount of Bytes: " + minAccepted);
            System.out.println("    Maximal accepted amount of Bytes: " + maxAccepted);
            System.out.println("    Average accepted amount of Bytes: " + avgAccepted);
            System.out.println("----------");

            System.out.println("    Minimal sent amount of Bytes: " + minSent);
            System.out.println("    Maximal sent amount of Bytes: " + maxSent);
            System.out.println("    Average sent amount of Bytes: " + avgSent);
            System.out.println("##########");
        } catch (InterruptedException e) {

        }
    }

    public void writeStatistic(int waitTimeMinutes, String filename) {
        try {
            //first time writing to the file
            FileWriter head = new FileWriter(filename);
            //writing the header
            String headWrite = "AcceptedBytes;SentBytes" + ";"
                    + "MinAccepted;MaxAccepted;AvgAccepted" + ";"
                    + "MinSent;MaxSent;AvgSent"
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
                        + ";" + this.minAccepted
                        + ";" + this.maxAccepted
                        + ";" + this.avgAccepted
                        + ";" + this.minSent
                        + ";" + this.maxSent
                        + ";" + this.avgSent
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
