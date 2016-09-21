package sample;

import java.sql.*;

/**
 * Created by dennisgelfart on 16.08.16.
 */

/*  Folgenden Code zur Herstellung der Verbindung mit der Datenbank habe ich dem Video-Tutorial
    von ProgrammingKnowledge entnommen. Link: https://www.youtube.com/watch?v=nTm1rzhHOc8&index=2&list=PLS1QulWo1RIbqOzdu1jShN0xah41S6lLP
 */

public class SQLConnection {

    Connection conection;

    public SQLConnection(){

        conection = Database.Connector();
        if (conection == null) System.exit(1);

    }
}

