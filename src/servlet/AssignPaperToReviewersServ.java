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
        String insertSuccess;
        boolean success = true;
        if(request.getParameter("entries")!= null){
            Review review = new Review();

            //Reviewer 1
            review.setSdate(java.sql.Date.valueOf("2018-07-24"));
            review.setEmail(request.getParameter("email1"));
            review.setPaperid(Integer.parseInt(request.getParameter("paperid")));
            review.setComment("I like it");
            review.setRecommendation("y");
            insertSuccess = dao.InsertReview(review);
            appropriateResponse(request, response, insertSuccess, "author1");

            //Reviewer 2
            if(success) {
                    review.setEmail(request.getParameter("email2"));
                    insertSuccess = dao.InsertReview(review);
                    success = appropriateResponse(request, response, insertSuccess, "author2");
            }

            //Reviewer 3
            if(success) {
                    review.setEmail(request.getParameter("email3"));
                    insertSuccess = dao.InsertReview(review);
                    appropriateResponse(request, response, insertSuccess,"author3");
            }
            RequestDispatcher test = request.getRequestDispatcher("ReviewServ");
            test.forward(request, response);
        }
        else{
            RequestDispatcher test = request.getRequestDispatcher("ReviewServ");
            test.forward(request, response);
        }
    }

    private boolean appropriateResponse(HttpServletRequest request, HttpServletResponse response, String insertSuccess, String author) throws ServletException, IOException {
        if(insertSuccess.equals("papers")){
            request.setAttribute(author, "Below PC member already has 5 papers assigned");
            request.getRequestDispatcher("/jsps/post_login.jsp").forward(request, response);
            return false;
        }
        else if(insertSuccess.equals("paperid")){
            request.setAttribute(author, "Below PC member already has this paper assigned to him");
            request.getRequestDispatcher("/jsps/post_login.jsp").forward(request, response);
            return false;
        }
        else if(insertSuccess.equals("paper_overflow")){
            request.setAttribute("overflow", "This paper has already been assigned three times");
            request.getRequestDispatcher("/jsps/post_login.jsp").forward(request, response);
            return false;
        }
        else {
            return true;
        }
    }

}
