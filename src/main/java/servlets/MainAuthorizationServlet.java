package servlets;

import biz.UserFacade;
import entity.UserDTO;
import tools.TokenChecker;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.security.GeneralSecurityException;

@WebServlet(name = "MainServlet")
public class MainAuthorizationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("get method received ");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder idTokenstringBuffer = new StringBuilder();
        System.out.println("post method has been arrived");
        BufferedReader bufferedReader = req.getReader();
        String str;
        while ((str = bufferedReader.readLine()) != null) {
            idTokenstringBuffer.append(str);
        }
        signIn(idTokenstringBuffer.toString());
//            System.out.println("post body : "+idTokenstringBuffer.toString());
//            TokenChecker tokenChecker=new TokenChecker();
//            try {
//             UserDTO userDTO= tokenChecker.tokenExtraction(idTokenstringBuffer.toString());
//                UserFacade userFacade = new UserFacade();
//                System.out.println("check before insert");
//                userFacade.checkUser(userDTO);
//                System.out.println("after check before insert");
//                System.out.println("");
//                System.out.println("before insert");
//                userFacade.insertHuman(userDTO);
//                System.out.println("after insert");
//
//            } catch (GeneralSecurityException e) {
//                e.printStackTrace();
//            }

    }

    void signIn(String idTokenstringBufferString) {
        System.out.println("post body : " + idTokenstringBufferString);
        TokenChecker tokenChecker = new TokenChecker();
        try {
            UserDTO userDTO = tokenChecker.tokenExtraction(idTokenstringBufferString);
            UserFacade userFacade = new UserFacade();
            System.out.println("check before insert");
            boolean userAvailable = userFacade.checkUser(userDTO);
            System.out.println("after check before insert");
            System.out.println("");
            System.out.println("before insert");
            if (!userAvailable) {
                System.out.println("before insert");
                userFacade.insertHuman(userDTO);
                System.out.println("after insert");
            }
        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
        }
    }
}
