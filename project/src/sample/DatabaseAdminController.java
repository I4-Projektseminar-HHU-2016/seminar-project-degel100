package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.nio.channels.AcceptPendingException;
import java.util.ResourceBundle;

/**
 * Created by dennisgelfart on 16.08.16.
 */
public class DatabaseAdminController implements Initializable {


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private TextField searchField;


    //Spieler hinzufügen Button
    public void addPlayer (ActionEvent event){

        try {
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = loader.load(getClass().getResource("AddPlayer.fxml").openStream());
            Scene scene = new Scene(root);

            primaryStage.setTitle("Spieler hinzufügen");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
