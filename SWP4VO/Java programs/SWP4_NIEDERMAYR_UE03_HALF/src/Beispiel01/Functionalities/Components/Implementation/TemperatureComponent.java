package Beispiel01.Functionalities.Components.Implementation;

import Beispiel01.Functionalities.Components.Component;
import Beispiel01.Functionalities.DataList.DataList;
import Beispiel01.Functionalities.DataTypes.Implementation.StorageData;
import Beispiel01.Functionalities.DataTypes.Implementation.TemperatureData;

import java.util.Random;

public class TemperatureComponent extends Component {

    private DataList dataList;

    public TemperatureComponent(DataList dataList) {
        this.dataList = dataList;
    }

    @Override
    public void run() {
        Random random = new Random();
        try {
            //beautiful endless loop
            while (true){
                //adds data to the List
                dataList.put(new TemperatureData(random.nextInt(50) + 30));
                //System.out.println("Put something on");
                Thread.sleep(1000);
            }
        }catch (InterruptedException e){

        }
    }
}
