package Beispiel01.Functionalities.Profiler.Implementation;

import Beispiel01.Functionalities.DataList.DataList;
import Beispiel01.Functionalities.Profiler.Profiler;

import java.util.Random;

public class ByteProfiler extends Profiler {

    private DataList dataList;
    private DataList storedData;

    private int minWritten;
    private int maxWritten;
    private int avgWritten;

    private int minRead;
    private int maxRead;
    private int avgRead;

    private int minAccesses;
    private int maxAccesses;
    private int avgAccesses;

    public ByteProfiler(DataList dataList) {
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
                if (dataList.get().getFirstData() < minWritten) {
                    minWritten = dataList.get().getFirstData();
                } else if (dataList.get().getFirstData() > maxWritten) {
                    maxWritten = dataList.get().getFirstData();
                }

                if (dataList.get().getSecondData() < minRead) {
                    minRead = dataList.get().getSecondData();
                } else if (dataList.get().getSecondData() > maxRead) {
                    maxRead = dataList.get().getSecondData();
                }

                if (dataList.get().getThirdData() < minAccesses) {
                    minAccesses = dataList.get().getThirdData();
                } else if (dataList.get().getThirdData() > maxAccesses) {
                    maxAccesses = dataList.get().getThirdData();
                }

                avgWritten = (avgWritten + dataList.get().getFirstData()) / 2;
                avgRead = (avgRead + dataList.get().getSecondData()) / 2;
                avgAccesses = (avgAccesses + dataList.get().getThirdData()) / 2;

                super.run();

                Thread.sleep(r.nextInt(1000));
            } catch (InterruptedException e) {

            }
        }
    }

    public void showStatistic(int waitTimeMinutes) {
        try {
            Thread.sleep((waitTimeMinutes * 1000));

            System.out.println("ByteProfiler Statistics");
            System.out.println("    Minimal written amount of Bytes: " + minWritten);
            System.out.println("    Maximal written amount of Bytes: " + maxWritten);
            System.out.println("    Average written amount of Bytes: " + avgWritten);
            System.out.println("----------");

            System.out.println("    Minimal read amount of Bytes: " + minRead);
            System.out.println("    Maximal read amount of Bytes: " + maxRead);
            System.out.println("    Average read amount of Bytes: " + avgRead);
            System.out.println("----------");

            System.out.println("    Minimal accesses amount of Bytes: " + minAccesses);
            System.out.println("    Maximal accesses amount of Bytes: " + maxAccesses);
            System.out.println("    Average accesses amount of Bytes: " + avgAccesses);
            System.out.println("----------");
        } catch (InterruptedException e) {

        }
    }

    @Override
    public String toString() {
        return "ByteProfiler";
    }

}
