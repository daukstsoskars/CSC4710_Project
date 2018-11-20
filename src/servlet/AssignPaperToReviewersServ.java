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

@WebServlet("/AssignPaperToReviewersServ")
public class AssignPaperToReviewersServ extends HttpServlet {
    private static final long serialVersionUID = 1L;
    DB_Query dao = new DB_Query();

    public AssignPaperToReviewersServ() {
        super();
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
        String insertSuccessCommand;
        boolean success = true;
        if(request.getParameter("entries")!= null){
            Review review = new Review();

            //Reviewer 1
            review.setSdate(java.sql.Date.valueOf("2018-07-24"));
            review.setEmail(request.getParameter("email1"));
            review.setPaperid(Integer.parseInt(request.getParameter("paperid")));
            review.setComment("I like it");
            review.setRecommendation("y");

            insertSuccessCommand = dao.InsertReview(review);
            success = appropriateResponse(request, response, insertSuccessCommand, "author1",review.getEmail());

            //Reviewer 2
            if(success && request.getParameter("email2") != "") {
                    review.setEmail(request.getParameter("email2"));
                    insertSuccessCommand = dao.InsertReview(review);
                    success = appropriateResponse(request, response, insertSuccessCommand, "author2",review.getEmail());
            }

            //Reviewer 3
            if(success && request.getParameter("email3") != "") {
                    review.setEmail(request.getParameter("email3"));
                    insertSuccessCommand = dao.InsertReview(review);
                    appropriateResponse(request, response, insertSuccessCommand,"author3",review.getEmail());
            }
//            if(success) {
//                RequestDispatcher test = request.getRequestDispatcher("ReviewServ");
//                test.forward(request, response);
//            }
        }
        else{
            RequestDispatcher test = request.getRequestDispatcher("ReviewServ");
            test.forward(request, response);
        }
    }

    private boolean appropriateResponse(HttpServletRequest request, HttpServletResponse response, String insertSuccess, String author, String email) throws ServletException, IOException {
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
        else if(insertSuccess.contains("paper_overflow")){
            request.setAttribute("overflow", "This paper could not be assigned to: "+email+" (has been assigned 3 times)");
            request.getRequestDispatcher("/jsps/post_login.jsp").forward(request, response);
            return false;
        }
        else {
            request.setAttribute("updated", "Review Table has been updated");
            request.getRequestDispatcher("/jsps/post_login.jsp").forward(request, response);
            return true;
        }
    }

}
