package sample;

import java.sql.*;

/**
 * Created by dennisgelfart on 16.08.16.
 */
public class SQLConnection {

    Connection conection;

    public SQLConnection(){

        conection = Database.Connector();
        if (conection == null) System.exit(1);

    }
}

