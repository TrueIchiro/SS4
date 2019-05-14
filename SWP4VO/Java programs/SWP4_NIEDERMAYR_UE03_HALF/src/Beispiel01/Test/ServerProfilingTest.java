package Beispiel01.Test;

import Beispiel01.Functionalities.Components.Component;
import Beispiel01.Functionalities.Components.Implementation.*;
import Beispiel01.Functionalities.DataList.DataList;
import Beispiel01.Functionalities.Profiler.Implementation.*;

public class ServerProfilingTest {

    //TODO: Profilers write to file! CSV
    //TODO: Make diagrams in Excel
    //TODO: Write document!

    public static void main(String[] args) {
        test05();
    }

    //Using all Components und Profiler
    public static void test01() {
        DataList d0 = new DataList();
        Component Storage = new StorageComponent(200, d0);
        new Thread(Storage).start();
        StorageProfiler StorageProfiler = new StorageProfiler(d0);
        Thread t0 = new Thread(StorageProfiler);
        t0.start();

        DataList d1 = new DataList();
        Component Network = new NetworkComponent(d1);
        new Thread(Network).start();
        NetworkProfiler NetworkProfiler = new NetworkProfiler(d1);
        Thread t1 = new Thread(NetworkProfiler);
        t1.start();

        DataList d2 = new DataList();
        Component Temperature = new TemperatureComponent(d2);
        new Thread(Temperature).start();
        TemperatureProfiler TemperatureProfiler = new TemperatureProfiler(d2, 60);
        Thread t2 = new Thread(TemperatureProfiler);
        t2.start();

        DataList d3 = new DataList();
        Component Utilisation = new UtilisationComponent(d3);
        new Thread(Utilisation).start();
        UtilisationProfiler UtilisationProfiler = new UtilisationProfiler(d3);
        Thread t3 = new Thread(UtilisationProfiler);
        t3.start();

        DataList d4 = new DataList();
        Component Byte = new ByteComponent(d4);
        new Thread(Byte).start();
        ByteProfiler ByteProfiler = new ByteProfiler(d4);
        Thread t4 = new Thread(ByteProfiler);
        t4.start();
    }

    //test with one component for showStatistic
    public static void test02() {
        DataList d0 = new DataList();
        Component Storage = new StorageComponent(200, d0);
        new Thread(Storage).start();
        StorageProfiler StorageProfiler = new StorageProfiler(d0);
        Thread t0 = new Thread(StorageProfiler);
        t0.start();

        StorageProfiler.showStatistic(1);
    }

    //test for writeStatistic
    public static void test03() {
        DataList d0 = new DataList();
        Component Storage = new StorageComponent(200, d0);
        new Thread(Storage).start();
        StorageProfiler StorageProfiler = new StorageProfiler(d0);
        Thread t0 = new Thread(StorageProfiler);
        t0.start();

        StorageProfiler.writeStatistic(1, "output.csv");
    }

    public static void test04() {
        DataList d2 = new DataList();
        Component Temperature = new TemperatureComponent(d2);
        new Thread(Temperature).start();
        TemperatureProfiler TemperatureProfiler = new TemperatureProfiler(d2, 60);
        Thread t2 = new Thread(TemperatureProfiler);
        t2.start();
    }

    public static void test05() {
        DataList d2 = new DataList();
        Component Temperature = new TemperatureComponent(d2);
        new Thread(Temperature).start();
        TemperatureProfiler TemperatureProfiler = new TemperatureProfiler(d2, 80, "First");
        TemperatureProfiler TemperatureProfiler2 = new TemperatureProfiler(d2, 80, "Second");
        Thread t2 = new Thread(TemperatureProfiler);
        Thread t22 = new Thread(TemperatureProfiler2);
        t2.start();
        t22.start();
    }

}
