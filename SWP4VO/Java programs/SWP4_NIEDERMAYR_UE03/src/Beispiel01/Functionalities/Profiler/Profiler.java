package Beispiel01.Functionalities.Profiler;

import Beispiel01.Functionalities.DataList.DataList;
import Beispiel01.Functionalities.DataTypes.ProvidedData;

import java.util.Random;

public abstract class Profiler implements Runnable {

    private DataList dataList;
    private DataList storedData;

    public Profiler(DataList dataList, DataList storedData) {
        this.dataList = dataList;
        this.storedData = storedData;
    }

    @Override
    public void run() {
        ProvidedData data;

        if ((data = dataList.get()) != null){

            dataList.delete(data);
            // int result = task.execute();
            System.out.println(this.toString());
            data.describe();
            //add the data for future reference
            storedData.put(data);
        }
    }

    @Override
    public String toString() {
        return "Profiler";
    }

    protected DataList getStoredList() {
        return this.storedData;
    }

    protected DataList getDataList() {
        return this.dataList;
    }
}
