package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.TextField;


/**
 * Created by dennisgelfart on 16.08.16.
 */
public class AddPlayerController implements Initializable {
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
}
