package Beispiel01.Functionalities.DataTypes.Implementation;

import Beispiel01.Functionalities.DataTypes.ProvidedData;

//current size of used and unused storage data
public class StorageData implements ProvidedData {

    int usedData;
    int allData;

    public StorageData(int usedData, int allData) {
        this.usedData = usedData;
        this.allData = allData;
    }

    @Override
    public void describe() {
        System.out.println("    Current used data: " + this.usedData + " GB");
        System.out.println("    Current available data: " + (this.allData - this.usedData) + " GB");
    }

    @Override
    public int getFirstData() {
        return this.usedData;
    }

    //exists because I'm too stupid to write better code
    @Override
    public int getSecondData() {
        return this.allData;
    }

    @Override
    public int getThirdData() {
        return 0;
    }
}
