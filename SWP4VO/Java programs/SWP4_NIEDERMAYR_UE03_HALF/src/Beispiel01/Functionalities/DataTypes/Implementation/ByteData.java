package Beispiel01.Functionalities.DataTypes.Implementation;

import Beispiel01.Functionalities.DataTypes.ProvidedData;

public class ByteData implements ProvidedData {

    private int writtenBytes;
    private int readBytes;
    private int accesses;

    public ByteData(int writtenBytes, int readBytes, int accesses) {
        this.writtenBytes = writtenBytes;
        this.readBytes = readBytes;
        this.accesses = accesses;
    }

    @Override
    public void describe() {
        System.out.println("    Current written per process: " + this.writtenBytes + " Byte");
        System.out.println("    Current read per process: " + this.readBytes + " Byte");
        System.out.println("    Current accesses: " + this.accesses + " Accesses");
    }

    @Override
    public int getFirstData() {
        return this.writtenBytes;
    }

    //exists thanks to OTHER data types
    @Override
    public int getSecondData() {
        return this.readBytes;
    }

    @Override
    public int getThirdData() {
        return this.accesses;
    }

}
