
package database;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import user.*;

public class DB_Query {
    /**
     * get the search result with username
     *
     * @param username
     * @return
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */


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

    /**
     * get data from author
     */
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
    /**
     * Insert for Review
     *
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public void InsertReview(Review review) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        try {
            
            

            String sql = "insert into review(sdate,comment,recommendation,paperid,email) values (?, ?, ?, ?, ?)";
            PreparedStatement preparestatement = connect.prepareStatement(sql);


            // Parameters start with 1

            preparestatement.setDate(1, review.getSdate());
            preparestatement.setString(2, review.getComment());
            preparestatement.setString(3, review.getRecommendation());
            preparestatement.setInt(4, review.getPaperid());
            preparestatement.setString(5, review.getEmail());
            preparestatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}