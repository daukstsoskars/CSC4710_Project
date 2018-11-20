package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import actions.ReviewAction;

@WebServlet("/ReviewServ")
public class ReviewServ extends HttpServlet {
    private static final long serialVersionUID = 1L;


    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doPost(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub

        ReviewAction reviewservice = new ReviewAction();
        try {
            request.setAttribute("ReviewList", reviewservice.Reviewfindall());
        }

        catch (InstantiationException | IllegalAccessException
                | ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            List<Object> li = reviewservice.Reviewfindall();
            for(int i = 0; i < li.size();i++){
                System.out.println(li.get(i).toString());
            }

        } catch (InstantiationException | IllegalAccessException
                | ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        request.getRequestDispatcher("/jsps/show_overview.jsp").forward(request, response);
    }


}
