package database;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet("/InitializeDB")
public class InitializeDB extends HttpServlet {

    public void createTable(Connection connect) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        try {

            //use database
            String sql = "use sampledb";
            PreparedStatement useDatabase = connect.prepareStatement(sql);
            useDatabase.executeUpdate();

            //drop writePaper
            String drop5 = "DROP TABLE IF EXISTS writePaper;";
            PreparedStatement dropWritePaperTable = connect.prepareStatement(drop5);
            dropWritePaperTable.executeUpdate();

            //drop author
            String drop4 = "DROP TABLE IF EXISTS author;";
            PreparedStatement dropAuthorTable = connect.prepareStatement(drop4);
            dropAuthorTable.executeUpdate();

            //drop review
            String drop7 = "DROP TABLE IF EXISTS review;";
            PreparedStatement dropReviewTable = connect.prepareStatement(drop7);
            dropReviewTable.executeUpdate();

            //drop pcMember
            String drop6 = "DROP TABLE IF EXISTS pcmember;";
            PreparedStatement dropPcmemberTable = connect.prepareStatement(drop6);
            dropPcmemberTable.executeUpdate();

            //drop paper
            String drop3 = "DROP TABLE IF EXISTS paper;";
            PreparedStatement dropPaperTable = connect.prepareStatement(drop3);
            dropPaperTable.executeUpdate();

            //drop accepted
            String drop8 = "DROP VIEW IF EXISTS accepted;";
            PreparedStatement dropAccepted = connect.prepareStatement(drop8);
            dropAccepted.executeUpdate();

            //create paper
            String sql3 = "CREATE TABLE paper"
                    + "(paperid INT AUTO_INCREMENT,"
                    + "title VARCHAR(50),"
                    + "abstract VARCHAR(250),"
                    + "pdf VARCHAR(100),"
                    + "PRIMARY KEY (paperid));";
            PreparedStatement createTables = connect.prepareStatement(sql3);
            createTables.executeUpdate();
            //tuples
            insertIntoPaper("database", "ch1","csc4710");
            insertIntoPaper("database", "ch2","csc4710");
            insertIntoPaper("database", "ch3","csc4710");
            insertIntoPaper("database", "ch4","csc4710");
            insertIntoPaper("database", "ch5","csc4710");
            insertIntoPaper("database", "ch6","csc4710");
            insertIntoPaper("database", "ch7","csc4710");
            insertIntoPaper("database", "ch8","csc4710");
            insertIntoPaper("database", "ch9","csc4710");
            insertIntoPaper("database", "ch10","csc4710");




            //create author
            String sql4 = "CREATE TABLE author"
                    + "(email VARCHAR(100),"
                    + "firstname VARCHAR(50),"
                    + "lastname VARCHAR(50),"
                    + "affiliation VARCHAR(100),"
                    + "PRIMARY KEY(email));";
            PreparedStatement createTableAuthor = connect.prepareStatement(sql4);
            createTableAuthor.executeUpdate();
            //tuples
            insertIntoAuthor("mike4@gmail.com", "mike4","diaz","eng");
            insertIntoAuthor("mike5@gmail.com", "mike5","fields","csc");
            insertIntoAuthor("mike6@gmail.com", "mike6","snay","eng");
            insertIntoAuthor("mike7@gmail.com", "mike7","zhang","eng");
            insertIntoAuthor("mike8@gmail.com", "mike8","bazi","csc");
            insertIntoAuthor("mike9@gmail.com", "mike9","nuzha","eng");
            insertIntoAuthor("mike10@gmail.com", "mike10","lu","csc");




            //create writePaper
            String sql5 = "CREATE TABLE writePaper"
                    + "(paperid INT,"
                    + "email VARCHAR(50),"
                    + "ordersignificance INT,"
                    + "PRIMARY KEY(paperid, email),"
                    + "FOREIGN KEY (paperid) REFERENCES paper(paperid)ON DELETE CASCADE ON UPDATE CASCADE,"
                    + "FOREIGN KEY (email) REFERENCES author(email)ON DELETE CASCADE ON UPDATE CASCADE);";
            PreparedStatement createTableWritePaper = connect.prepareStatement(sql5);
            createTableWritePaper.executeUpdate();
            //tuples
            insertIntoWritePaper(9, "mike7@gmail.com", 2);
            insertIntoWritePaper(6, "mike6@gmail.com", 1);
            insertIntoWritePaper(7, "mike8@gmail.com", 8);
            insertIntoWritePaper(8, "mike9@gmail.com", 7);
            insertIntoWritePaper(9, "mike10@gmail.com", 1);
            insertIntoWritePaper(1, "mike10@gmail.com", 1);

            //create pcmember
            String sql6 = "CREATE TABLE pcmember"
                    + "(memberid INT AUTO_INCREMENT,"
                    + "email VARCHAR(50),"
                    + "name VARCHAR(20),"
                    + "PRIMARY KEY (memberid),"
                    + "UNIQUE(email));";
            PreparedStatement createTablePcmember = connect.prepareStatement(sql6);
            createTablePcmember.executeUpdate();
            //tuples
            insertIntoPcMember("dave1@gmail.com","dave1");
            insertIntoPcMember("dave2@gmail.com","dave2");
            insertIntoPcMember("dave3@gmail.com","dave3");
            insertIntoPcMember("dave4@gmail.com","dave4");
            insertIntoPcMember("dave5@gmail.com","dave5");
            insertIntoPcMember("dave6@gmail.com","dave6");
            insertIntoPcMember("dave7@gmail.com","dave7");
            insertIntoPcMember("dave8@gmail.com","dave8");
            insertIntoPcMember("dave9@gmail.com","dave9");
            insertIntoPcMember("dave10@gmail.com","dave10");


            //create review
            String sql7 = "CREATE TABLE review"
                    + "(reportid INT AUTO_INCREMENT,"
                    + "sdate DATE,"
                    + "comment VARCHAR(250),"
                    + "recommendation CHAR(1),"
                    + "paperid INT,"
                    + "email VARCHAR(100),"
                    + "PRIMARY KEY(reportid),"
                    + "UNIQUE(paperid, email),"
                    + "FOREIGN KEY (paperid) REFERENCES paper(paperid)ON DELETE CASCADE ON UPDATE CASCADE,"
                    + "FOREIGN KEY (email) REFERENCES pcmember(email)ON DELETE CASCADE ON UPDATE CASCADE);";
            PreparedStatement createTableReview = connect.prepareStatement(sql7);
            createTableReview.executeUpdate();
            //tuples
            insertIntoReview("2018-06-01", "like it", "y", 1, "dave1@gmail.com");
            insertIntoReview("2018-06-02", "like it", "y", 2, "dave2@gmail.com");
            insertIntoReview("2018-06-02", "like it", "y", 2, "dave4@gmail.com");
            insertIntoReview("2018-06-03", "like it", "n", 2, "dave1@gmail.com");
            insertIntoReview("2018-06-03", "like it", "n", 2, "dave3@gmail.com");
            insertIntoReview("2018-06-03", "like it", "n", 3, "dave3@gmail.com");
            insertIntoReview("2018-06-04", "like it", "y", 4, "dave4@gmail.com");
            insertIntoReview("2018-06-05", "like it", "n", 5, "dave5@gmail.com");
            insertIntoReview("2018-06-05", "like it", "n", 5, "dave1@gmail.com");
            insertIntoReview("2018-06-06", "like it", "n", 5, "dave2@gmail.com");
            insertIntoReview("2018-06-06", "like it", "n", 6, "dave6@gmail.com");
            insertIntoReview("2018-06-07", "like it", "n", 7, "dave7@gmail.com");
            insertIntoReview("2018-06-08", "like it", "y", 8, "dave8@gmail.com");
            insertIntoReview("2018-06-08", "like it", "y", 8, "dave3@gmail.com");
            insertIntoReview("2018-06-09", "like it", "y", 9, "dave9@gmail.com");

            //create view accepted
            String view = "CREATE VIEW accepted AS "
                    +"SELECT * , count(recommendation) AS accept "
                    +"FROM review "
                    +"WHERE recommendation ='y' "
                    +"GROUP BY paperid";
            PreparedStatement createView = connect.prepareStatement(view);
            createView.executeUpdate();

        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertIntoPaper(String title, String Abstract, String pdf ) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
        try {
            String sql = "INSERT INTO paper(title, abstract, pdf) VALUES(?,?,?)";
            PreparedStatement pstm = MakeDbConnection.getConnection().prepareStatement(sql);
            pstm.setString(1,title);
            pstm.setString(2,Abstract);
            pstm.setString(3,pdf);
            pstm.executeUpdate();

        }catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void insertIntoAuthor(String email, String firstname,String lastname, String affiliation ) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
        try {
            String sql = "INSERT INTO author(email, firstname, lastname, affiliation) VALUES(?,?,?,?)";
            PreparedStatement pstm = MakeDbConnection.getConnection().prepareStatement(sql);
            pstm.setString(1,email);
            pstm.setString(2,firstname);
            pstm.setString(3,lastname);
            pstm.setString(4,affiliation);
            pstm.executeUpdate();

        }catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void insertIntoPcMember(String email, String name) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
        try {
            String sql = "INSERT INTO pcmember(email, name) VALUES(?,?)";
            PreparedStatement pstm = MakeDbConnection.getConnection().prepareStatement(sql);
            pstm.setString(1,email);
            pstm.setString(2,name);
            pstm.executeUpdate();

        }catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertIntoReview(String sdate, String comment, String recommendation, int paperid, String email) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
        try {
            String sql = "INSERT INTO review(sdate, comment, recommendation, paperid, email) VALUES(?,?,?,?,?)";
            PreparedStatement pstm = MakeDbConnection.getConnection().prepareStatement(sql);
            pstm.setDate(1, java.sql.Date.valueOf(sdate));
            pstm.setString(2, comment);
            pstm.setString(3, recommendation);
            pstm.setInt(4, paperid);
            pstm.setString(5, email);
            pstm.executeUpdate();

        }catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertIntoWritePaper(int paperid, String email, int ordersignificance) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        try {
            String sql = "INSERT INTO writepaper(paperid, email, ordersignificance) VALUES(?,?,?)";
            PreparedStatement pstm = MakeDbConnection.getConnection().prepareStatement(sql);
            pstm.setInt(1, paperid);
            pstm.setString(2, email);
            pstm.setInt(3, ordersignificance);
            pstm.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

