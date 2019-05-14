package Beispiel01.Test;

import Beispiel01.ServerProfiling.Component.Component;
import Beispiel01.ServerProfiling.Component.Implementation.StorageComponent;
import Beispiel01.ServerProfiling.DataList;

public class ServerProfilingTest {

    public static void main(String[] args) {

    }

    public static void test01() {
        DataList list = new DataList();
        Component Storage = new StorageComponent(200, list);
        new Thread(Storage).start();
    }

}
