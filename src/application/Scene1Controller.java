package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Scene1Controller {

    @FXML
    TextField numberOfOrdersTextField;
    @FXML
    TextField shelfSizeTextField;

    @FXML
    TextField test;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void startSimulation(ActionEvent event) throws IOException {

        //variables from user input
        int shelfSize = Integer.parseInt(shelfSizeTextField.getText());
        int numberOfOrders = Integer.parseInt(numberOfOrdersTextField.getText());

        //changing Scene 1 to Scene 2
        FXMLLoader  loader = new FXMLLoader(getClass().getResource("Scene2.fxml"));
        root = loader.load();

        Scene2Controller scene2Controller  = loader.getController();
        scene2Controller.displayShelfSize(shelfSize);
        scene2Controller.displayNumberOfOrders(numberOfOrders);

        //displaying the scene2
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
