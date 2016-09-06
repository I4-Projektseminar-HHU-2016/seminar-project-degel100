package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by dennisgelfart on 06.09.16.
 */
public class ProfileController implements Initializable{


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private Label nameLabel;
    @FXML
    private Label birthLabel;
    @FXML
    private Label countryLabel;
    @FXML
    private Label sportLabel;
    @FXML
    private Label metalLabel;
}
