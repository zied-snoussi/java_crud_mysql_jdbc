package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DataSource {
    private Connection connection;
    private static DataSource instance;
    private final String url = "jdbc:mysql://localhost:3306/db_test";
    private final String user="root";
    private final String password="";

    private DataSource() {
        try {
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connection established");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Connection closed");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static DataSource getInstance(){
        if(instance==null){
            instance = new DataSource();
        }
        return instance;
    }
}