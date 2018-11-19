package database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MakeDbConnection {
    static Connection connect;
    public static void InitDB() throws IOException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();

            String URL = "jdbc:mysql://localhost:3306/sampledb";
            String USER = "john";
            String PASS = "pass1234";
            connect = DriverManager.getConnection(URL, USER, PASS);

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){
        return connect;
    }

}
