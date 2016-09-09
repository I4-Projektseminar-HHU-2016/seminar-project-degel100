package sample;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TablePosition;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by dennisgelfart on 06.09.16.
 */
public class ProfileController implements Initializable{

    @FXML
    public Label nameLabel;
    @FXML
    public Label birthLabel;
    @FXML
    public Label countryLabel;
    @FXML
    public Label sportLabel;
    @FXML
    public Label metalLabel;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void fillProfile (String name, String birth, String country, String sport, String metal){

        nameLabel.setText(name);
        birthLabel.setText(birth);
        countryLabel.setText(country);
        sportLabel.setText(sport);
        metalLabel.setText(metal);
    }
}
