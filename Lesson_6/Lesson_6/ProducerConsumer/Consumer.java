package Lesson_6;

import java.util.concurrent.BlockingQueue;

public class Consumer extends Thread{
    private BlockingQueue<Book> queue;
    private boolean exit = true;
    public Consumer(BlockingQueue<Book> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        synchronized (queue){
            while (exit){
                if(queue.size()>0){
                    try {
                        Book consumedData = queue.take();
                        System.out.println("Кто-то взял почитать с полки книгу: " + consumedData);
                        queue.wait((int) ( Math.random() * 1000 + 100));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else{
                    try {
                        System.out.println("К сожалению, полка пуста:(");
                        queue.wait(500);
                    } catch (InterruptedException e) {
                        exit = false;
                    }
                }
            }
        }
    }
    @Override
    public void interrupt() {
        exit = false;
    }
}
