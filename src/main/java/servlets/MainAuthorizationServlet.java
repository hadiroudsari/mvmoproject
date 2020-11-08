package servlets;

import biz.UserFacade;
import entity.UserDTO;
import tools.TokenChecker;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.GeneralSecurityException;

@WebServlet(name = "MainServlet")
public class MainAuthorizationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("get method received ");
//        String page = "/SignInServlet";
//        ServletContext context;
//        context = getServletContext();
//        RequestDispatcher rd = context.getRequestDispatcher(page);
//        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("recived post request in mainservlet");
        /**
         * request Forwarding to the other servlet to make
         */

        String page = "/SignInServlet";
        ServletContext context;
        context = getServletContext();
        RequestDispatcher rd = context.getRequestDispatcher(page);
        rd.forward(req, resp);

//-------------------------------------------------------------------
//        StringBuilder idTokenstringBuffer = new StringBuilder();
//        System.out.println("post method has been arrived");
//        BufferedReader bufferedReader = req.getReader();
//        String str;
//        while ((str = bufferedReader.readLine()) != null) {
//            idTokenstringBuffer.append(str);
//        }
//        signIn(idTokenstringBuffer.toString());

    }

//    void signIn(String idTokenStringBufferString) {
//        System.out.println("post body : " + idTokenStringBufferString);
//        TokenChecker tokenChecker = new TokenChecker();
//        try {
//            UserDTO userDTO = tokenChecker.tokenExtraction(idTokenStringBufferString);
//            UserFacade userFacade = new UserFacade();
//            boolean userAvailable = userFacade.checkUser(userDTO);
//            System.out.println("");
//            if (!userAvailable) {
//                userFacade.insertHuman(userDTO);
//            }
//        } catch (GeneralSecurityException | IOException e) {
//            e.printStackTrace();
//        }
//    }
}
