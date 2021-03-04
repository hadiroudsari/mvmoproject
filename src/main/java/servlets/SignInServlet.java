package servlets;

import biz.UserFacade;
import entity.UserDTO;
import tools.TokenChecker;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.GeneralSecurityException;


@WebServlet(name = "SignInServlet")
public class SignInServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("process method signin servlet");
//        ============================================================
        /**
         * @// TODO: 3/3/21 here we need some security constraint for sessions 
         */
        HttpSession httpSession=request.getSession();
        StringBuilder idTokenstringBuffer = new StringBuilder();
        System.out.println("post method has been arrived");
        BufferedReader bufferedReader = request.getReader();
        String str;
        while ((str = bufferedReader.readLine()) != null) {
            idTokenstringBuffer.append(str);
        }
        UserDTO userDTOAfterCheck = signIn(idTokenstringBuffer.toString());


//        =============================================================
        response.setContentType("text/plain");
        response.setHeader("Cache-Control", "no-cache");
        PrintWriter out = response.getWriter();
        httpSession.setAttribute("name",userDTOAfterCheck.getName());
//        RequestDispatcher view = request.getRequestDispatcher("secondPage.jsp");
//        response.sendRedirect("secondPage.jsp");
        try {
            out.println("Hi "+userDTOAfterCheck.getName());
        } catch (Exception e) {
            System.out.println("error ");
            System.out.println(e.toString());
        } finally {
            out.close();
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("GET request in signin servlet");
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("post request in signin servlet");
        processRequest(request, response);
    }

    UserDTO signIn(String idTokenStringBufferString) {
        UserDTO userDTO=null;
        System.out.println("post body : " + idTokenStringBufferString);
        TokenChecker tokenChecker = new TokenChecker();
        try {
            userDTO = tokenChecker.tokenExtraction(idTokenStringBufferString);
            UserFacade userFacade = new UserFacade();
            boolean userAvailable = userFacade.checkUser(userDTO);
            System.out.println("");
            if (!userAvailable) {
                userFacade.insertHuman(userDTO);
            }
        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
        }
        return userDTO;
    }
}