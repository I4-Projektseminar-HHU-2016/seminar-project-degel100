package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ResourceBundle;

/**
 * Created by dennisgelfart on 06.09.16.
 */
public class DeleteController implements Initializable {

    Connection conection;
    DatabaseAdminController daController;

    @FXML
    private TextField deletedPlayer;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    //Löschen-Button
    public void deletePlayer (ActionEvent event) {


        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conection = DriverManager.getConnection("jdbc:sqlite:olympics.db");
            conection.setAutoCommit(false);
            stmt = conection.createStatement();

            String sql = "DELETE FROM Athletes WHERE Name = \'" + deletedPlayer.getText() + "\';";
            stmt.executeUpdate(sql);

            stmt.close();
            conection.commit();
            conection.close();
            ((Node)(event.getSource())).getScene().getWindow().hide();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
}
