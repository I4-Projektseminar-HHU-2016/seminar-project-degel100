package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by dennisgelfart on 12.08.16.
 */
public class StartpageController implements Initializable {


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    //Olympische-Spieler Button
    public void Start (ActionEvent event){

        try {
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = loader.load(getClass().getResource("Login.fxml").openStream());
            Scene scene = new Scene(root);

            primaryStage.setTitle("Login");
            primaryStage.setScene(scene);
            primaryStage.show();
            ((javafx.scene.Node)(event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
