package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Created by dennisgelfart on 16.08.16.
 */
public class AddPlayerController implements Initializable {

    Connection conection;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private TextField nameField;
    @FXML
    private TextField birthField;
    @FXML
    private TextField sportField;
    @FXML
    private TextField countryField;
    @FXML
    private TextField metalField;


    //Spieler hinzufügen Methode
    public void addtoDatabase(String name, String birth, String sport, String country, String metal) throws SQLException {

        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conection = DriverManager.getConnection("jdbc:sqlite:olympics.db");
            conection.setAutoCommit(false);

            stmt = conection.createStatement();

            String sql = "INSERT INTO Athletes (Name,Geburtstag,Sportart,Land,Medaillen)" +
                    "VALUES (\'"+name+"\', \'"+birth+"\', \'"+sport+"\', \'"+country+"\', \'"+metal+"\');";
            stmt.executeUpdate(sql);

            stmt.close();
            conection.commit();
            conection.close();

            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = loader.load(getClass().getResource("DatabaseAdmin.fxml").openStream());
            Scene scene = new Scene(root);

            primaryStage.setTitle("Olympische Spieler 2016 - Datenbank");
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    //Hinzufügen Button
    public void addAthlete(ActionEvent event) {

        try {
            addtoDatabase(nameField.getText(), birthField.getText(), sportField.getText(), countryField.getText(), metalField.getText());
            ((Node)(event.getSource())).getScene().getWindow().hide();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void goBack(ActionEvent event) {

        try {
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = loader.load(getClass().getResource("DatabaseAdmin.fxml").openStream());
            Scene scene = new Scene(root);

            primaryStage.setTitle("Olympische Spieler 2016 - Datenbank");
            primaryStage.setScene(scene);
            primaryStage.show();
            ((Node)(event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
