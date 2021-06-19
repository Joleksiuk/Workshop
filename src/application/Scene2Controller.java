package application;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Scene2Controller implements Initializable {

    @FXML
    Label nameLabel;

    @FXML
    Label nameLabel2;

    @FXML
    Label recievedOrder;

    public void displayShelfSize(int shelfSize){
        nameLabel.setText("Shelf size : " + shelfSize);
    }

    public void displayNumberOfOrders(int numberOfOrders){
        nameLabel2.setText("Number of orders : "+numberOfOrders);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Platform.runLater(()->{

            Shelf shelf = null;
            try {
                shelf = new Shelf(5,recievedOrder);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Receiver reciever = new Receiver(1,120,shelf);
            Fixer[] fixers = new Fixer[3];

            for( int i=1; i<4;i++){
                fixers[i-1]=new Fixer(i,40, shelf);
            }

            reciever.start();
            for( int i=1; i<4;i++){
                fixers[i-1].start();
            }
            try{
                reciever.join();
            }catch (InterruptedException e){}

            for( int i=1; i<4;i++){
                try{
                    fixers[i-1].join();
                }catch (InterruptedException e){}
            }
        });
    }
}
