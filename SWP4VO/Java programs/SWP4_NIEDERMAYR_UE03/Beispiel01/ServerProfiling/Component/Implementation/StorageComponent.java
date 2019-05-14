package Beispiel01.ServerProfiling.Component.Implementation;

import Beispiel01.ServerProfiling.Component.Component;
import Beispiel01.ServerProfiling.DataList;

import java.util.Random;

//provides storage data
public class StorageComponent extends Component {
    //storageSize in GB
    private int storageSize;
    //the list with the data
    //will also be used with the fitting Profiler
    private DataList dataList;

    //constructors
    public StorageComponent(int storageSize, DataList dataList) {
        this.storageSize = storageSize;
        this.dataList = dataList;
    }

    //from Component -> Runnable
    @Override
    public void run() {
        Random random = new Random();
        try {
            //beautiful endless loop
            while (true){
                //adds data to the List
                dataList.put(new StorageData(random.nextInt(storageSize)));
                System.out.println("Put something on");
                Thread.sleep(1000);
            }
        }catch (InterruptedException e){

        }
    }
}
