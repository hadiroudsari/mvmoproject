package servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "Dispatcher")
public class Dispatcher extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /**
         * @// TODO: 3/4/21 here is secuity constraint that I shoudl take care
         */
        System.out.println("in dispatcher ");
        HttpSession session=request.getSession(false);
        if(session==null){
            System.out.println("there is no session remain from new request");
        }else if(session!=null){
            System.out.println("session is still valid the name is"+session.getAttribute("name"));
            String page = "/secondPage.jsp";
            ServletContext context = getServletContext();
            RequestDispatcher rd = context.getRequestDispatcher(page);
            rd.forward(request, response);
        }
        
//        response.setContentType("text/html;charset=UTF-8");
//        PrintWriter out = response.getWriter();
//        out.println("<!DOCTYPE html>");
//        out.println("<html>");
//
//        // ------ include target.html content -----------
//        String page = "/target.html";
//        ServletContext context = getServletContext();
//        RequestDispatcher rd = context.getRequestDispatcher(page);
//        rd.include(request, response);
//        // ----------------------------
//
//        out.println("</html>");
//        out.close();

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);    }
}
