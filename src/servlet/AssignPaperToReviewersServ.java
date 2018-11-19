package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.DB_Query;
import user.Review;

/**
 * Servlet implementation class AssignPaperToReviewersServ
 */
@WebServlet("/AssignPaperToReviewersServ")
public class AssignPaperToReviewersServ extends HttpServlet {
    private static final long serialVersionUID = 1L;
    DB_Query dao = new DB_Query();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AssignPaperToReviewersServ() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doPost(request,response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        if(request.getParameter("sbtrv")!= null){
            Review review = new Review();

            System.out.println("here");
            //first reviewer
            review.setSdate(java.sql.Date.valueOf("2018-07-24"));
            review.setEmail(request.getParameter("email1"));
            review.setPaperid(Integer.parseInt(request.getParameter("paperid")));
            review.setComment("I like it");
            review.setRecommendation("y");
            try {
                dao.InsertReview(review);
            } catch (InstantiationException | IllegalAccessException
                    | ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            //second reviewer
            review.setEmail(request.getParameter("email2"));
            try {
                dao.InsertReview(review);
            } catch (InstantiationException | IllegalAccessException
                    | ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            //third reviewer
            review.setEmail(request.getParameter("email3"));

            try {
                dao.InsertReview(review);
            } catch (InstantiationException | IllegalAccessException
                    | ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            RequestDispatcher test = request.getRequestDispatcher("ReviewServ");
            test.forward(request, response);
        }
        else{
            RequestDispatcher test = request.getRequestDispatcher("ReviewServ");
            test.forward(request, response);
        }
    }

}
