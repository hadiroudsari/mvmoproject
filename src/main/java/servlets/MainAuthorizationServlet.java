package servlets;

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
public class MainAuthorizationServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("get method received ");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            StringBuilder idTokenstringBuffer=new StringBuilder();
            System.out.println("post method has been arrived");
            BufferedReader bufferedReader=req.getReader();
            String str;
            while( (str = bufferedReader.readLine())!= null    ){
                idTokenstringBuffer.append(str);
            }
            System.out.println("post body : "+idTokenstringBuffer.toString());
            TokenChecker tokenChecker=new TokenChecker();
            try {
                tokenChecker.tokenExtraction(idTokenstringBuffer.toString());
            } catch (GeneralSecurityException e) {
                e.printStackTrace();
            }

        }
}
