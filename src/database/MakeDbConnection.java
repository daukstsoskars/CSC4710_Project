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

            /*
            |-------------------------------------------------------------------------------------------------
            |Modify below parameters per your sql server properties
            |    1) You can either specify your root USER and PASS
            |+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            |                                               OR
            |+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            |    2) Add a user specified below to your sql server and give permissions to access sampledb
            |        To add a user on UNIX machine follow these steps
            |        1) Log in as the root user through command line
            |            mysql -u root -p
            |        2) Type mysql password and hit enter
            |        3) To create a database user type the following command replacing username and password
            |            GRANT ALL PRIVILEGES ON *.* TO 'username'@'localhost' IDENTIFIED BY 'password';
            |            Ex: GRANT ALL PRIVILEGES ON *.* TO 'john'@'localhost' IDENTIFIED BY 'pass1234';
            |-------------------------------------------------------------------------------------------------
             */
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
