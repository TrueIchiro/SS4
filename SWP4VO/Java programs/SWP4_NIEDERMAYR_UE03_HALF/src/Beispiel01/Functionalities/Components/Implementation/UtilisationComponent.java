package Beispiel01.Functionalities.Components.Implementation;

import Beispiel01.Functionalities.Components.Component;
import Beispiel01.Functionalities.DataList.DataList;
import Beispiel01.Functionalities.DataTypes.Implementation.TemperatureData;
import Beispiel01.Functionalities.DataTypes.Implementation.UtilisationData;

import java.util.Random;

public class UtilisationComponent extends Component {

    private DataList dataList;

    public UtilisationComponent(DataList dataList) {
        this.dataList = dataList;
    }

    @Override
    public void run() {
        Random random = new Random();
        try {
            //extremely beautiful endless loop
            while (true){
                //adds data to the List
                dataList.put(new UtilisationData(random.nextInt(100), random.nextInt(100)));
                //System.out.println("Put something on");
                Thread.sleep(1000);
            }
        }catch (InterruptedException e){

        }
    }
}
