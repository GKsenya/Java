package Lesson_6;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Book> queue = new LinkedBlockingDeque<>();
        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);
        producer.start();
        consumer.start();
        try{
            producer.join(5000);
        }catch (Exception e){}
        producer.interrupt();
        consumer.interrupt();
    }
}
