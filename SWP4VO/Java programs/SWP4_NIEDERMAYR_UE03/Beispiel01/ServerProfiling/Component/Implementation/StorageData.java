package Beispiel01.ServerProfiling.Component.Implementation;

import Beispiel01.ServerProfiling.ProvidedData;

public class StorageData implements ProvidedData {

    int usedData;

    public StorageData(int usedData) {
        this.usedData = usedData;
    }

}