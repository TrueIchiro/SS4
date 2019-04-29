package Beispiel_01;

public interface Observable {

    //attach Observer
    void registerObserver(Observer observer);

    //detach Observer
    void unregisterObserver(Observer observer);

    //notify
    void notifyObservers(Object o) throws Exception;

}
