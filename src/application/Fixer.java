package application;

import java.util.Random;

public class Fixer extends Thread{

    int id;
    static int repetitions;
    volatile Shelf shelf;
    Random rand = new Random();

    public Fixer(int id, int repetitions, Shelf shelf) {

        this.id = id;
        this.repetitions = repetitions;
        this.shelf = shelf;
    }

    void threadOwnthings(){
        try {
            Thread.sleep(rand.nextInt(1000)+1000);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void run() {
        fix();
    }

    public void fix() {

        int counter = 0;

        while (counter < repetitions) {

            threadOwnthings();
            Order order = shelf.getOrder(id);
            counter++;

        }
    }
}
