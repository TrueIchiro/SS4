package Beispiel01.Functionalities.Components.Implementation;

import Beispiel01.Functionalities.Components.Component;
import Beispiel01.Functionalities.DataList.DataList;
import Beispiel01.Functionalities.DataTypes.Implementation.StorageData;

import java.util.Random;

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
                dataList.put(new StorageData(random.nextInt(storageSize), this.storageSize));
                //System.out.println("Put something on");
                Thread.sleep(1000);
            }
        }catch (InterruptedException e){

        }
    }
}
