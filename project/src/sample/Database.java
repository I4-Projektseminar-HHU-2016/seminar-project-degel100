package sample;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by dennisgelfart on 16.08.16.
 */


/*  Folgenden Code zur Herstellung der Verbindung mit der Datenbank habe ich dem Video-Tutorial
    von ProgrammingKnowledge entnommen. Link: https://www.youtube.com/watch?v=nTm1rzhHOc8&index=2&list=PLS1QulWo1RIbqOzdu1jShN0xah41S6lLP
*/
public class Database {

    public static Connection Connector(){
        try {

            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:olympics.db");
            return conn;

        } catch (Exception e) {

            return null;

        }
    }
}





