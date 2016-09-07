package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
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

    @FXML
    private Button searchCountryButton;

    @FXML
    private Button searchSportButton;

    @FXML
    private Button searchAthleteButton;


    @FXML
    private TextField searchField;

    @FXML
    private Label Entry;

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

            searchSportButton.setVisible(false);
            searchAthleteButton.setVisible(false);
            Entry.setText("");

            searchCountryButton.setVisible(true);
            searchField.setText("Land...");

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

            searchCountryButton.setVisible(false);
            searchAthleteButton.setVisible(false);
            Entry.setText("");

            searchSportButton.setVisible(true);
            searchField.setText("Sportart...");

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

            searchCountryButton.setVisible(false);
            searchSportButton.setVisible(false);
            Entry.setText("");

            searchAthleteButton.setVisible(true);
            searchField.setText("Sportler...");

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

        ((Node)(event.getSource())).getScene().getWindow().hide();
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


    //Suchfunktion-Auswahl "Land"
    public void issearchCountry(String entry) throws SQLException {

        Statement stmt = null;
        int row = 0;
        try {

            Class.forName("org.sqlite.JDBC");
            conection = DriverManager.getConnection("jdbc:sqlite:olympics.db");
            conection.setAutoCommit(false);
            stmt = conection.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM CountryMetal");
            while ( rs.next() ) {

                row++;
                if (rs.getString("Land").equals(entry)) {
                    Entry.setText(entry + ", Nr." + String.valueOf(row));
                    break;

                } else {
                    Entry.setText("Land nicht enthalten!");
                }

            }
            rs.close();
            stmt.close();
            conection.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }


    //Suche-Button "Land"
    public void searchCountry (ActionEvent event) {

        try {
            issearchCountry(searchField.getText());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //Suchfunktion-Auswahl "Sportart"
    public void issearchSport(String entry) throws SQLException {

        Statement stmt = null;
        int row = 0;
        try {

            Class.forName("org.sqlite.JDBC");
            conection = DriverManager.getConnection("jdbc:sqlite:olympics.db");
            conection.setAutoCommit(false);
            stmt = conection.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM SportMetal");
            while ( rs.next() ) {

                row++;
                if (rs.getString("Sportart").equals(entry)) {
                    Entry.setText(entry + ", Nr." + String.valueOf(row));
                    break;

                } else {
                    Entry.setText("Sportart nicht enthalten!");
                }

            }
            rs.close();
            stmt.close();
            conection.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }


    //Suche-Button "Sportart"
    public void searchSport (ActionEvent event) {

        try {
            issearchSport(searchField.getText());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //Suchfunktion-Auswahl "Sportler"
    public void issearchAthlete(String entry) throws SQLException {

        Statement stmt = null;
        int row = 0;
        try {

            Class.forName("org.sqlite.JDBC");
            conection = DriverManager.getConnection("jdbc:sqlite:olympics.db");
            conection.setAutoCommit(false);
            stmt = conection.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM AthleteMetal");
            while ( rs.next() ) {

                row++;
                if (rs.getString("Name").equals(entry)) {
                    Entry.setText(entry + ", Nr." + String.valueOf(row));
                    break;

                } else {
                    Entry.setText("Sportler nicht enthalten!");
                }

            }
            rs.close();
            stmt.close();
            conection.close();
        } catch ( Exception e ) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage() );
        }
    }


    //Suche-Button "Sportler"
    public void searchAthlete (ActionEvent event) {

        try {
            issearchAthlete(searchField.getText());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}



