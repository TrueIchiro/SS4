package Beispiel01.Functionalities.Message.Implementation;

import Beispiel01.Functionalities.Buffer.MessageBuffer;
import Beispiel01.Functionalities.Message.Message;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MessageBufferImplementation implements MessageBuffer {

    private int maxSize;
    //buffer for the messages
    private List<Message> buffer = Collections.synchronizedList(new ArrayList<>());

    //constructor
    public MessageBufferImplementation(int maxSize) {
        this.maxSize = maxSize;
    }

    @Override
    public synchronized void add(Message message) {
        System.out.println("MessageBuffer adding Message: " + message);
        //buffer is full
        while (buffer.size() == maxSize) {
            try {
                wait();
            } catch(InterruptedException e) {

            }
        }

        buffer.add(message);
        notifyAll();
    }

    @Override
    public synchronized Message get() {
        while (buffer.size() < 1) {
            try {
                wait(200);
            } catch (InterruptedException e) {

            }
        }

        return buffer.get(0);
    }

    @Override
    public synchronized void delete(Message message) {
        buffer.remove(message);
        notifyAll();
    }

}
