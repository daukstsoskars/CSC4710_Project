
package database;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import user.*;

public class DB_Query {

    //create connection with database
    Connection connect = MakeDbConnection.getConnection();

    public boolean findByUsername(String uname) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        try {
            String qstring = "SELECT * FROM tb_user WHERE username=?";
            PreparedStatement prs = connect.prepareStatement(qstring);
            prs.setString(1,uname);
            ResultSet results = prs.executeQuery();
            if(!results.next()){
                return false;
            }
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public List<Object> Authorfindall() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        List<Object> list = new ArrayList<>();
        try {

            String sql = "select * from author";
            PreparedStatement preparestatement = connect.prepareStatement(sql);
            ResultSet resultSet = preparestatement.executeQuery();

            while (resultSet.next()) {
                Author author = new Author();
                author.setEmail(resultSet.getString("email"));
                author.setFirstname(resultSet.getString("firstname"));
                author.setLastname(resultSet.getString("lastname"));
                author.setAffiliation(resultSet.getString("affiliation"));
                list.add(author);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public List<Object> Reviewfindall() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        List<Object> list = new ArrayList<>();
        try {

            String sql = "select * from review";
            PreparedStatement preparestatement = connect.prepareStatement(sql);
            ResultSet resultSet = preparestatement.executeQuery();

            while (resultSet.next()) {
                Review review = new Review();
                review.setComment(resultSet.getString("comment"));
                review.setEmail(resultSet.getString("email"));
                review.setReportid(resultSet.getInt("reportid"));
                review.setRecommendation(resultSet.getString("recommendation"));
                review.setPaperid(resultSet.getInt("paperid"));
                review.setSdate(resultSet.getDate("sdate"));
                list.add(review);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public String InsertReview(Review review) {
        try {
            Connection connection = MakeDbConnection.getConnection();

            //get number of times this paper has been assigned (Assignment says cannot be more that 3)
            int numberOfTimesAssigned = numberOfTimesPaperHasBeenAssigned(connection, review);
            //If a paper can still be assigned proceed
            if(numberOfTimesAssigned < 3) {

                //get number of assigned papers to to the PC member (Assignment says cannot be more than 5)
                int assignedPapers = getNumberOfPapersAssignedToPcMember(connection, review);

                //If PC member has less than 5 papers assigned
                if (assignedPapers < 5) {
                    //Check if PC member already has this paper assigned to him
                    boolean isAlreadyAssigned = isThisPaperAlreadyAssignedToPcMemeber(connection, review);

                    //If paper is not already assigned
                    if (!isAlreadyAssigned) {
                        //proceed assigning paper to this author for review
                        String sql = "insert into review(sdate,comment,recommendation,paperid,email) values (?, ?, ?, ?, ?)";
                        PreparedStatement preparestatement = connection.prepareStatement(sql);


                        // Parameters start with 1

                        preparestatement.setDate(1, review.getSdate());
                        preparestatement.setString(2, review.getComment());
                        preparestatement.setString(3, review.getRecommendation());
                        preparestatement.setInt(4, review.getPaperid());
                        preparestatement.setString(5, review.getEmail());
                        preparestatement.executeUpdate();
                    } else {
                        return "paperid";
                    }
                } else {
                    //if PC memeber already has 5 papers assigned
                    return "papers";
                }
            }
            else{
                return "paper_overflow_"+review.getEmail();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "good";
    }

    private boolean isThisPaperAlreadyAssignedToPcMemeber(Connection connection, Review review) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT paperid FROM review WHERE email=?");
        statement.setString(1, review.getEmail());
        ResultSet result = statement.executeQuery();
        while(result.next()){
            if(result.getInt("paperid") == review.getPaperid()){
                return true;
            }
        }
        return false;
    }

    private int getNumberOfPapersAssignedToPcMember(Connection connection, Review review) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT assigned FROM pcmember WHERE email= ?");
        statement.setString(1, review.getEmail());
        ResultSet result = statement.executeQuery();
        if(result.next()){
            return result.getInt("assigned");
        }
        return -1;
    }

    private int numberOfTimesPaperHasBeenAssigned(Connection connection, Review review) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT paperid FROM review WHERE paperid= ?");
        statement.setInt(1, review.getPaperid());
        ResultSet result = statement.executeQuery();
        int rowCount = 0;
        while(result.next()){
            rowCount++;
        }
        return rowCount;
    }
}