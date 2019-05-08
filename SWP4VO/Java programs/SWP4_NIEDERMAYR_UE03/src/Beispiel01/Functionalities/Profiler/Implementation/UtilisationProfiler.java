package Beispiel01.Functionalities.Profiler.Implementation;

import Beispiel01.Functionalities.DataList.DataList;
import Beispiel01.Functionalities.Profiler.Profiler;

import java.util.Random;

public class UtilisationProfiler extends Profiler {

    private DataList dataList;
    private DataList storedData;

    private int minProcessed;
    private int maxProcessed;
    private int avgProcessed;

    private int minAll;
    private int maxAll;
    private int avgAll;

    public UtilisationProfiler(DataList dataList) {
        super(dataList, new DataList());
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

    @Override
    public String toString() {
        return "UtilisationProfiler";
    }
}
