package application;

import java.util.Random;

public class Receiver extends Thread{

    Random rand = new Random();
    int id;
    int repetitions;
    volatile Shelf shelf;

    public Receiver(int id, int repetitions, Shelf shelf) {

        this.id = id;
        this.repetitions = repetitions;
        this.shelf = shelf;
    }

    void threadOwnthings(){
        try {
            Thread.sleep(rand.nextInt(10)+1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void recieve() {

        int count = 0;
        while (count < repetitions) {

            threadOwnthings();
            shelf.addOrder(count);
            count++;
        }
    }

    public void run() {
        recieve();
    }
}
