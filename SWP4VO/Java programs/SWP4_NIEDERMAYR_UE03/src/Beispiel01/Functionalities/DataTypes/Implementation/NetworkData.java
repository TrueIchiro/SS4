package Beispiel01.Functionalities.DataTypes.Implementation;

import Beispiel01.Functionalities.DataTypes.ProvidedData;

//current accepted and sent data
public class NetworkData implements ProvidedData {

    int acceptedBytes;
    int sentBytes;

    public NetworkData(int acceptedBytes, int sentBytes) {
        this.acceptedBytes = acceptedBytes;
        this.sentBytes = sentBytes;
    }

    @Override
    public void describe() {
        System.out.println("    Current accepted data: " + this.acceptedBytes + " Byte");
        System.out.println("    Current sent data: " + this.sentBytes + " Byte");
    }

    @Override
    public int getFirstData() {
        return this.acceptedBytes;
    }

    @Override
    public int getSecondData() {
        return this.sentBytes;
    }

    @Override
    public int getThirdData() {
        return 0;
    }
}
