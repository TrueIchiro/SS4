package Beispiel01.Functionalities.DataTypes.Implementation;

import Beispiel01.Functionalities.DataTypes.ProvidedData;

public class UtilisationData implements ProvidedData {

    private int processData;
    private int allData;

    public UtilisationData(int processData, int allData) {
        this.processData = processData;
        this.allData = allData;
    }

    @Override
    public void describe() {
        System.out.println("    Current process utilisation: " + this.processData + "%");
        System.out.println("    Current overall utilisation: " + this.allData + "%");
    }

    @Override
    public int getFirstData() {
        return this.processData;
    }

    @Override
    public int getSecondData() {
        return this.allData;
    }

    @Override
    public int getThirdData() {
        return 0;
    }

}
