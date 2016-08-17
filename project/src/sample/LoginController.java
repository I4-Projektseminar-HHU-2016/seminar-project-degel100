package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.omg.CORBA.StringValueHelper;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by dennisgelfart on 13.08.16.
 */
public class LoginController implements Initializable {

    public SQLConnection sqlconnection;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;



    //Login Button
    public void Login (ActionEvent event){

        if (String.valueOf(usernameField.getText()).equals("gast") && String.valueOf(passwordField.getText()).equals("gast"))
        {
            try {
                Stage primaryStage = new Stage();
                FXMLLoader loader = new FXMLLoader();
                Pane root = loader.load(getClass().getResource("DatabaseAdmin.fxml").openStream());
                Scene scene = new Scene(root);

                primaryStage.setTitle("Olympische Spieler 2016 - Datenbank");
                primaryStage.setScene(scene);
                primaryStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    //Gast Button
    public void Guest (ActionEvent event){

        try {
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = loader.load(getClass().getResource("DatabaseGuest.fxml").openStream());
            Scene scene = new Scene(root);

            primaryStage.setTitle("Olympische Spieler 2016 - Datenbank");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
