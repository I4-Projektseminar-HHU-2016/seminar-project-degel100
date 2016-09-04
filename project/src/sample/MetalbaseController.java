package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

/**
 * Created by dennisgelfart on 01.09.16.
 */
public class MetalbaseController implements Initializable{

    Connection conection;

    //Tabelle "Land"
    @FXML
    TableView<TableMetal> tableMetal;
    @FXML
    TableColumn<TableMetal, Integer> id;
    @FXML
    TableColumn<TableMetal, String> criteria;
    @FXML
    TableColumn<TableMetal, String> metal;

    @FXML
    private RadioButton countryButton;

    @FXML
    private RadioButton sportButton;

    @FXML
    private RadioButton athleteButton;

    private int number = 1;


    //Tabelle bei Auswahl "Land"
    public void fillTableMetalLand() throws SQLException {
        Statement stmt;
        try {
            Class.forName("org.sqlite.JDBC");
            conection = DriverManager.getConnection("jdbc:sqlite:olympics.db");
            conection.setAutoCommit(false);
            stmt = conection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM CountryMetal");
            countryButton.setSelected(true);
            sportButton.setSelected(false);
            athleteButton.setSelected(false);
            criteria.setText("Land");
            number = 1;

            //Tabelle anlegen
            final ObservableList<TableMetal> data = FXCollections.observableArrayList(
            );

            //Tabelle füllen
            while (rs.next()) {

                data.add(new TableMetal(number++, rs.getString("Land"), rs.getString("Medaillen")));
            }

            tableMetal.setItems(data);

            rs.close();
            stmt.close();
            conection.close();


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    //Tabelle bei Auswahl "Sportart"
    public void fillTableMetalSport() throws SQLException {
        Statement stmt;
        try {
            Class.forName("org.sqlite.JDBC");
            conection = DriverManager.getConnection("jdbc:sqlite:olympics.db");
            conection.setAutoCommit(false);
            stmt = conection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM SportMetal");
            countryButton.setSelected(false);
            sportButton.setSelected(true);
            athleteButton.setSelected(false);
            criteria.setText("Sportart");
            number = 1;

            //Tabelle anlegen
            final ObservableList<TableMetal> data = FXCollections.observableArrayList(
            );

            //Tabelle füllen
            while (rs.next()) {

                data.add(new TableMetal(number++, rs.getString("Sportart"), rs.getString("Medaillen")));
            }

            tableMetal.setItems(data);

            rs.close();
            stmt.close();
            conection.close();


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    //Tabelle bei Auswahl "Sportler"
    public void fillTableMetalAthlete() throws SQLException {
        Statement stmt;
        try {
            Class.forName("org.sqlite.JDBC");
            conection = DriverManager.getConnection("jdbc:sqlite:olympics.db");
            conection.setAutoCommit(false);
            stmt = conection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM AthleteMetal");
            countryButton.setSelected(false);
            sportButton.setSelected(false);
            athleteButton.setSelected(true);
            criteria.setText("Sportler");
            number = 1;

            //Tabelle anlegen
            final ObservableList<TableMetal> data = FXCollections.observableArrayList(
            );

            //Tabelle füllen
            while (rs.next()) {

                data.add(new TableMetal(number++, rs.getString("Name"), rs.getString("Medaillen")));
            }

            tableMetal.setItems(data);

            rs.close();
            stmt.close();
            conection.close();


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        id.setCellValueFactory(new PropertyValueFactory<TableMetal, Integer>("ROWID"));
        criteria.setCellValueFactory(new PropertyValueFactory<TableMetal, String>("CRITERIA"));
        metal.setCellValueFactory(new PropertyValueFactory<TableMetal, String>("METAL"));

        try {
            fillTableMetalLand();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Zurück-Button
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


    //Country-Button
    public void sortCountry (ActionEvent event) {

        try {
            fillTableMetalLand();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Sporart-Button
    public void sortSport (ActionEvent event) {

        try {
            fillTableMetalSport();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Sportler-Button
    public void sortAthlete (ActionEvent event) {

        try {
            fillTableMetalAthlete();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}



