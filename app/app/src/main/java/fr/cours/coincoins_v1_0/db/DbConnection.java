package fr.cours.coincoins_v1_0.db;

import java.sql.Connection;
import java.sql.DriverManager;
import org.apache.log4j.Logger;

public class DbConnection {
    private static final Logger log = Logger.getLogger(DbConnection.class);

    private static final String DB_URL = "jdbc:mysql://localhost/";

    private static final String DB_USER = "root";

    private static final String DB_NAME = "coincoin";

    private static final String DB_PASSWORD = "root";

    private static Connection connection;

    private DbConnection() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL+DB_NAME, DB_USER, DB_PASSWORD);
        } catch (Exception e) {
            log.error("Impossible de se connecter a la bdd . Message " + e.getMessage());
        }

    }

    public static synchronized Connection getInstance() {
        if(connection != null) {
            return connection;
        }else {
            new DbConnection();
            return connection;
        }
    }


}