package sample;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by dennisgelfart on 16.08.16.
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





