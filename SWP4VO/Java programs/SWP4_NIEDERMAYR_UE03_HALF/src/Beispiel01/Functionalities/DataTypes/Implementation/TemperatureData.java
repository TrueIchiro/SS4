package Beispiel01.Functionalities.DataTypes.Implementation;

import Beispiel01.Functionalities.DataTypes.ProvidedData;

//current temperature of the CPU
public class TemperatureData implements ProvidedData {

    private int temperature;

    public TemperatureData(int temperature) {
        this.temperature = temperature;
    }

    @Override
    public void describe() {
        System.out.println("    Current CPU temperature: " + this.temperature + "°C");
    }

    @Override
    public int getFirstData() {
        return this.temperature;
    }

    //exists thanks to OTHER data types
    @Override
    public int getSecondData() {
        return 0;
    }

    @Override
    public int getThirdData() {
        return 0;
    }

}
