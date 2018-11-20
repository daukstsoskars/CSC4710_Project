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
//            String drop5 = "DROP TABLE IF EXISTS writePaper;";
//            PreparedStatement dropWritePaperTable = connect.prepareStatement(drop5);
//            dropWritePaperTable.executeUpdate();

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
                    + "affiliation VARCHAR(100),"
                    + "PRIMARY KEY(email));";
            PreparedStatement createTableAuthor = connect.prepareStatement(sql4);
            createTableAuthor.executeUpdate();
            //tuples
            insertIntoAuthor("AUTHOR_EMAIL1", "Author1","ENG");
            insertIntoAuthor("AUTHOR_EMAIL2", "Author2","ENG");
            insertIntoAuthor("AUTHOR_EMAIL3", "Author3","CSC");
            insertIntoAuthor("AUTHOR_EMAIL4", "Author4","CSC");




            //create writePaper
//            String sql5 = "CREATE TABLE writePaper"
//                    + "(paperid INT,"
//                    + "email VARCHAR(50),"
//                    + "ordersignificance INT,"
//                    + "PRIMARY KEY(paperid, email),"
//                    + "FOREIGN KEY (paperid) REFERENCES paper(paperid)ON DELETE CASCADE ON UPDATE CASCADE,"
//                    + "FOREIGN KEY (email) REFERENCES author(email)ON DELETE CASCADE ON UPDATE CASCADE);";
//            PreparedStatement createTableWritePaper = connect.prepareStatement(sql5);
//            createTableWritePaper.executeUpdate();
//            //tuples
//            insertIntoWritePaper(9, "mike7@gmail.com", 2);
//            insertIntoWritePaper(6, "mike6@gmail.com", 1);
//            insertIntoWritePaper(7, "mike8@gmail.com", 8);
//            insertIntoWritePaper(8, "mike9@gmail.com", 7);
//            insertIntoWritePaper(9, "mike10@gmail.com", 1);
//            insertIntoWritePaper(1, "mike10@gmail.com", 1);

            //create pcmember
            String sql6 = "CREATE TABLE pcmember"
                    + "(email VARCHAR(50),"
                    + "name VARCHAR(20),"
                    + "assigned INT,"
                    + "UNIQUE(email));";
            PreparedStatement createTablePcmember = connect.prepareStatement(sql6);
            createTablePcmember.executeUpdate();
            //tuples
            insertIntoPcMember("PC_EMAIL1","PC1",5);
            insertIntoPcMember("PC_EMAIL2","PC2",0);
            insertIntoPcMember("PC_EMAIL3","PC3",0);
            insertIntoPcMember("PC_EMAIL4","PC4",0);
            insertIntoPcMember("PC_EMAIL5","PC5",0);
            insertIntoPcMember("PC_EMAIL6","PC6",0);
            insertIntoPcMember("PC_EMAIL7","PC7",0);
            insertIntoPcMember("PC_EMAIL8","PC8",0);


            //create review
            String sql7 = "CREATE TABLE review"
                    + "(reportid INT AUTO_INCREMENT,"
                    + "sdate DATE,"
                    + "comment VARCHAR(250),"
                    + "recommendation CHAR(1),"
                    + "paperid INT,"
                    + "email VARCHAR(100),"
                    + "PRIMARY KEY(reportid),"
                    + "UNIQUE(paperid, email))";
//                    + "FOREIGN KEY (paperid) REFERENCES paper(paperid)ON DELETE CASCADE ON UPDATE CASCADE,"
//                    + "FOREIGN KEY (email) REFERENCES pcmember(email)ON DELETE CASCADE ON UPDATE CASCADE);";
            PreparedStatement createTableReview = connect.prepareStatement(sql7);
            createTableReview.executeUpdate();
            //tuples
            insertIntoReview("2018-06-01", "like it", "y", 1, "PC_EMAIL1");
            insertIntoReview("2018-06-02", "like it", "y", 2, "PC_EMAIL1");
            insertIntoReview("2018-06-02", "like it", "y", 3, "PC_EMAIL1");
            insertIntoReview("2018-06-03", "like it", "n", 4, "PC_EMAIL1");
            insertIntoReview("2018-06-03", "like it", "n", 5, "PC_EMAIL1");
            insertIntoReview("2018-06-04", "like it", "y", 4, "PC_EMAIL4");
            insertIntoReview("2018-06-05", "like it", "n", 5, "PC_EMAIL5");
            insertIntoReview("2018-06-05", "like it", "n", 5, "PC_EMAIL6");
            insertIntoReview("2018-06-06", "like it", "n", 2, "PC_EMAIL2");
            insertIntoReview("2018-06-06", "like it", "n", 6, "PC_EMAIL6");
            insertIntoReview("2018-06-07", "like it", "n", 7, "PC_EMAIL7");
            insertIntoReview("2018-06-08", "like it", "y", 8, "PC_EMAIL8");
            insertIntoReview("2018-06-08", "like it", "y", 8, "PC_EMAIL3");
            insertIntoReview("2018-06-09", "like it", "y", 9, "PC_EMAIL4");

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
    public void insertIntoAuthor(String email, String firstname, String affiliation ) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
        try {
            String sql = "INSERT INTO author(email, firstname, affiliation) VALUES(?,?,?)";
            PreparedStatement pstm = MakeDbConnection.getConnection().prepareStatement(sql);
            pstm.setString(1,email);
            pstm.setString(2,firstname);
            pstm.setString(3,affiliation);
            pstm.executeUpdate();

        }catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void insertIntoPcMember(String email, String name, int numberOfPapersAssigned) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
        try {
            String sql = "INSERT INTO pcmember(email, name, assigned) VALUES(?,?,?)";
            PreparedStatement pstm = MakeDbConnection.getConnection().prepareStatement(sql);
            pstm.setString(1,email);
            pstm.setString(2,name);
            pstm.setInt(3,numberOfPapersAssigned);
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

//    public void insertIntoWritePaper(int paperid, String email, int ordersignificance) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
//        try {
//            String sql = "INSERT INTO writepaper(paperid, email, ordersignificance) VALUES(?,?,?)";
//            PreparedStatement pstm = MakeDbConnection.getConnection().prepareStatement(sql);
//            pstm.setInt(1, paperid);
//            pstm.setString(2, email);
//            pstm.setInt(3, ordersignificance);
//            pstm.executeUpdate();
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }

}

