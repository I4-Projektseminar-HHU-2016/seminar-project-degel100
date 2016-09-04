package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;


/**
 * Created by dennisgelfart on 16.08.16.
 */
public class DatabaseAdminController implements Initializable {

    Connection conection;


    @FXML
    TableView<TablePlayer> tablePlayer;
    @FXML
    TableColumn<TablePlayer, Integer> id;
    @FXML
    TableColumn<TablePlayer, String> name;
    @FXML
    TableColumn<TablePlayer, String> birth;
    @FXML
    TableColumn<TablePlayer, String> sport;
    @FXML
    TableColumn<TablePlayer, String> country;
    @FXML
    TableColumn<TablePlayer, String> metal;

    @FXML
    private TextField searchField;
    @FXML
    private Label noEntry;

    private int number = 1;



    //Tabelle befüllen
    public void fillTablePlayer() throws SQLException {
        Statement stmt;
        try {
            Class.forName("org.sqlite.JDBC");
            conection = DriverManager.getConnection("jdbc:sqlite:olympics.db");
            conection.setAutoCommit(false);
            stmt = conection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Athletes");

            //Tabelle anlegen
            final ObservableList<TablePlayer> data = FXCollections.observableArrayList(
            );

            //Tabelle füllen
            while (rs.next()) {

                data.add(new TablePlayer(number++, rs.getString("Name"), rs.getString("Geburtstag"), rs.getString("Sportart"), rs.getString("Land"), rs.getString("Medaillen")));
            }

            tablePlayer.setItems(data);

            rs.close();
            stmt.close();
            conection.close();


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        id.setCellValueFactory(new PropertyValueFactory<TablePlayer, Integer>("ROWID"));
        name.setCellValueFactory(new PropertyValueFactory<TablePlayer, String>("NAME"));
        birth.setCellValueFactory(new PropertyValueFactory<TablePlayer, String>("BIRTH"));
        sport.setCellValueFactory(new PropertyValueFactory<TablePlayer, String>("SPORT"));
        country.setCellValueFactory(new PropertyValueFactory<TablePlayer, String>("COUNTRY"));
        metal.setCellValueFactory(new PropertyValueFactory<TablePlayer, String>("METAL"));

        try {
            fillTablePlayer();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


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
            ((Node)(event.getSource())).getScene().getWindow().hide();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //Startseite Button
    public void startpage (ActionEvent event){

        try {
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = loader.load(getClass().getResource("Startpage.fxml").openStream());
            Scene scene = new Scene(root);

            primaryStage.setTitle("Olympische Spiele Rio 2016");
            primaryStage.setScene(scene);
            primaryStage.show();
            ((Node)(event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //Suchfunktion
    public void issearchAthlete(String entry) throws SQLException {

        Statement stmt = null;
        try {

            Class.forName("org.sqlite.JDBC");
            conection = DriverManager.getConnection("jdbc:sqlite:olympics.db");
            conection.setAutoCommit(false);
            stmt = conection.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM Athletes");
            while ( rs.next() ) {

                if (rs.getString("Name").equals(entry)) {

                    Stage primaryStage = new Stage();
                    FXMLLoader loader = new FXMLLoader();
                    Pane root = loader.load(getClass().getResource("DatabaseAdmin.fxml").openStream());
                    Scene scene = new Scene(root);

                    primaryStage.setTitle("Olympische Spieler 2016 - Datenbank");
                    primaryStage.setScene(scene);
                    primaryStage.show();

                } else {

                    noEntry.setText("Sportler nicht enthalten!");
                }

            }
            rs.close();
            stmt.close();
            conection.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }


    //Suche-Button
    public void searchAthlete (ActionEvent event) {

        try {
            issearchAthlete(searchField.getText());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //Medaillenspiegel-Button
    public void showStatistic (ActionEvent event) {

        try {
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = loader.load(getClass().getResource("Metalbase.fxml").openStream());
            Scene scene = new Scene(root);

            primaryStage.setTitle("Olympische Spiele 2016 - Medaillenspiegel");
            primaryStage.setScene(scene);
            primaryStage.show();

            ((Node)(event.getSource())).getScene().getWindow().hide();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
