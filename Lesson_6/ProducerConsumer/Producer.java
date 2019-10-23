package Lesson_6;

import java.util.concurrent.BlockingQueue;

public class Producer extends Thread {

    private String[] books = {"Демидович: Математический анализ","Гарри Поттер","Сказка о потеряном времени",
                              "Введение в микроэкономику","Преступление и наказание","Мастер и Маргарита",
                              "Вишнёвый сад", "Хроники Нарнии","Война и мир", "Java: пособие для чайников"};
    private String[] covers = {"Мягкая обложка","Твёрдый переплет","Флипбук","Подарочное издание"};
    private String[] colours = {"Новая","Идеальное","Хорошее","Удовлетворительное","Плохое","*Развалилась в руках*"};

    public final static int maxBooksNumber = 5;
    private BlockingQueue<Book> queue;
    private boolean exit = true;

    public Producer(BlockingQueue<Book> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        synchronized (queue){
            while (exit){
                if(queue.size()< maxBooksNumber) {
                    BookBuilder builder = new BookBuilderImpl();
                    Book newBook = builder.title(books[(int) (Math.random() * books.length)])
                            .bookCover(covers[(int) (Math.random() * covers.length)])
                            .bookCoverColour(colours[(int) (Math.random() * colours.length)])
                            .pages((int) (Math.random() * 1000))
                            .year((int) (Math.random() * 120 + 1900))
                            .build();
                    try {
                        queue.put(newBook);
                        System.out.println("На полке появилась новая книга: " + newBook);
                        queue.wait(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();}
                } else{
                    System.out.println("На полке закончилось место:(");
                    try {
                        queue.wait(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("Работа потока завершена");
        }
    }

    @Override
    public void interrupt() {
        exit = false;
    }
}
