package utils;

import java.sql.Connection; //het connection object
import java.sql.DriverManager; //om te verbinden met MySQL
import java.sql.SQLException; //Fouten controleren


//Database klasse
public class DatabaseVerbinding {
    //Adres van databse
    private static final String URL = "jdbc:mysql://localhost:3306/factuurrouter_db";
    private static final String USER = "root";//MySQL
    private static final String PASSWORD = "";//MySQL

    //methode om te verbinden
    public static Connection getConnection() {
        try {//voor als er iets misgaat
            return DriverManager.getConnection(URL, USER, PASSWORD); //maak verbinding met de databse
        } catch (SQLException e) { //meld de fout
            System.out.println("no connection: " + e.getMessage());
            return null;
        }
    }
}
