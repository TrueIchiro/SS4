package Beispiel01.Functionalities.Components.Implementation;

import Beispiel01.Functionalities.Components.Component;
import Beispiel01.Functionalities.DataList.DataList;
import Beispiel01.Functionalities.DataTypes.Implementation.ByteData;
import Beispiel01.Functionalities.DataTypes.Implementation.TemperatureData;

import java.util.Random;

public class ByteComponent extends Component {

    private DataList dataList;

    public ByteComponent(DataList dataList) {
        this.dataList = dataList;
    }

    @Override
    public void run() {
        Random random = new Random();
        try {
            //beautiful endless loop
            while (true){
                //adds data to the List
                dataList.put(new ByteData(
                        random.nextInt(10000),
                        random.nextInt(10000),
                        random.nextInt(500)));
                Thread.sleep(1000);
            }
        }catch (InterruptedException e){

        }
    }

}
