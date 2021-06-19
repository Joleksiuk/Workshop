package application;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;


public class SceneController {

    @FXML
    Label orderNumber;
    @FXML
    TextField shelfSize;
    @FXML
    TextField ordersNum;

    public void startSimulation(ActionEvent event) {

        //variables from user input
        int size = Integer.parseInt(shelfSize.getText());
        int numberOfOrders = Integer.parseInt(ordersNum.getText());

        Platform.runLater(() -> {
            for (int i = 0; i < 10; i++) {
                orderNumber.setText(Integer.toString(i));
            }
        });

        Platform.runLater(() -> {
            Shelf shelf = null;
            try {
                shelf = new Shelf(5, orderNumber);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Receiver reciever = new Receiver(1, 120, shelf);
            Fixer[] fixers = new Fixer[3];

            for (int i = 1; i < 4; i++) {
                fixers[i - 1] = new Fixer(i, 40, shelf);
            }

            reciever.start();
            for (int i = 1; i < 4; i++) {
                fixers[i - 1].start();
            }
            try {
                reciever.join();
            } catch (InterruptedException e) {
            }

            for (int i = 1; i < 4; i++) {
                try {
                    fixers[i - 1].join();
                } catch (InterruptedException e) {
                }
            }
        });
    }


    // public void initialize(URL url, ResourceBundle resourceBundle) {}


}
