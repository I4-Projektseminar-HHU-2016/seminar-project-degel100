package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Created by dennisgelfart on 13.08.16.
 */
public class DatabaseGuestController implements Initializable{

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

    @FXML
    private Button profileButton;
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

        profileButton.setVisible(false);

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

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //Suchfunktion
    public void issearchAthlete(String entry) throws SQLException {

        Statement stmt = null;
        int row = 0;
        try {

            Class.forName("org.sqlite.JDBC");
            conection = DriverManager.getConnection("jdbc:sqlite:olympics.db");
            conection.setAutoCommit(false);
            stmt = conection.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM Athletes");
            while ( rs.next() ) {

                row++;
                if (rs.getString("Name").equals(entry)) {
                    noEntry.setText(entry + ", Nr." + String.valueOf(row));
                    profileButton.setVisible(true);
                    break;

                } else {
                    noEntry.setText("Sportler nicht enthalten!");
                    profileButton.setVisible(false);
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


    //Profil anlegen
    public void isCreateProfile(String entry) throws SQLException {

        Statement stmt = null;
        try {

            Class.forName("org.sqlite.JDBC");
            conection = DriverManager.getConnection("jdbc:sqlite:olympics.db");
            conection.setAutoCommit(false);
            stmt = conection.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM Athletes");
            while ( rs.next() ) {
                if (rs.getString("Name").equals(entry)) {
                    try {
                        Stage primaryStage = new Stage();
                        FXMLLoader loader = new FXMLLoader();
                        Pane root = loader.load(getClass().getResource("Profile.fxml").openStream());
                        ProfileController pController = loader.getController();
                        pController.fillProfile(rs.getString("Name"), rs.getString("Geburtstag"), rs.getString("Land"), rs.getString("Sportart"), rs.getString("Medaillen"));
                        Scene scene = new Scene(root);

                        primaryStage.setTitle(entry+" - Profil");
                        primaryStage.setScene(scene);
                        primaryStage.show();
                        break;

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } else {
                }
            }
            rs.close();
            stmt.close();
            conection.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }


    //Profil anzeigen-Button
    public void showPlayer (ActionEvent event) {

        try {
            isCreateProfile(searchField.getText());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
