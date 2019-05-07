package Beispiel01.Functionalities.Components.Implementation;

import Beispiel01.Functionalities.Components.Component;
import Beispiel01.Functionalities.DataList.DataList;
import Beispiel01.Functionalities.DataTypes.Implementation.NetworkData;

import java.util.Random;

public class NetworkComponent extends Component {

    private DataList dataList;

    public NetworkComponent(DataList dataList) {
        this.dataList = dataList;
    }

    @Override
    public void run() {
        Random random = new Random();
        try {
            //beautiful endless loop
            while (true){
                //adds data to the List
                dataList.put(new NetworkData(random.nextInt(1000), random.nextInt(1000)));
                //System.out.println("Put something on");
                Thread.sleep(1000);
            }
        }catch (InterruptedException e){

        }
    }
}
