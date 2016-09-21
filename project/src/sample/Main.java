package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/*  Folgenden Basis-Code, welchen ich jeweils in den meisten Unterklassen für die Erstellung einer JavaFX-Applikation
    verwendet habe, habe ich dem Video-Tutorial von ProgrammingKnowledge entnommen.
    Link: https://www.youtube.com/watch?v=nTm1rzhHOc8&index=2&list=PLS1QulWo1RIbqOzdu1jShN0xah41S6lLP
 */

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Startpage.fxml"));
        primaryStage.setTitle("Olympische Spiele Rio 2016");
        primaryStage.setScene(new Scene(root, 600, 341));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
