package Beispiel01.ServerProfiling;

import java.util.ArrayList;
import java.util.List;

public class DataList {

    //maskSize of the List
    private int maxSize;
    //List with the Data
    private List<ProvidedData> dataList = new ArrayList<>();

    //constructor
    public DataList() {
        this.maxSize = 10;
    }

    public DataList(int maxSize) {
        this.maxSize = maxSize;
    }

    //functionalities
    //add some data
    public synchronized void put ( ProvidedData data){
        while(dataList.size() == maxSize){
            try{
                wait();
            }catch (InterruptedException e){

            }
        }
        dataList.add(0,data);
        notifyAll();
    }

    //gets some data
    public synchronized ProvidedData get(){
        while(dataList.size() < 1) {
            try {
                //waits 1 second for new task
                wait(1000);
            }catch(InterruptedException e){

            }
        }
        //taskList is empty
        if(dataList.size() == 0){
            return null;
        }else{
            //returns the data at the first position
            return dataList.get(0);
        }
    }

    //delete the data
    public synchronized void delete(ProvidedData data){
        dataList.remove(data);
        notifyAll();
    }

}
