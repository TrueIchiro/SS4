package Beispiel01.Functionalities.Buffer;

import Beispiel01.Functionalities.Message.Message;

public interface MessageBuffer {

    void add(Message message);

    Message get();

    void delete(Message message);

}
