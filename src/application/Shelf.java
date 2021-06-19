package application;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Shelf extends Thread{

    volatile Order orders[];
    volatile int enter;
    volatile int exit;
    volatile int counter;
    int size;

    Random rand = new Random();

    final Lock dostep;
    final Condition pelny;
    final Condition pusty;

    @FXML
    Label orderNumber;


    Shelf(int size, Label orderNumber) throws IOException {

        this.size = size;
        orders = new Order[size];
        enter = 0;
        exit = 0;
        counter = 0;
        dostep = new ReentrantLock();
        pelny = dostep.newCondition();
        pusty = dostep.newCondition();
        this.orderNumber = orderNumber;

    }

    public void addOrder(int count) {

        Order element = new Order(count);
        dostep.lock();
        try {
            if (counter == size) {
                try {
                    pelny.await();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            orders[enter] = element;
            enter = (enter + 1) % size;

            try {
                Thread.sleep(rand.nextInt(10)+1);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            System.out.println("Reciever: I recived order number : "+ element.id);

            Platform.runLater(()->{
                orderNumber.setText(Integer.toString(element.id));
            });

            counter = counter + 1;
            pusty.signal();

        } finally {
            dostep.unlock();
        }
    }

    public Order getOrder(int fixerID) {

        dostep.lock();
        Order element;

        try {
            if (counter == 0) {
                try {
                    pusty.await();

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            element = orders[exit];
            exit = (exit + 1) % size;

            try {
                Thread.sleep(rand.nextInt(1000)+1000);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            System.out.println("Fixer " + fixerID + ":  I fixed order number :" + element.id);

            counter = counter - 1;
            pelny.signal();

        } finally {
            dostep.unlock();
        }
        return element;
    }
}
