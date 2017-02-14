package servlets;

import accounts.AccountService;
import dbService.dataSets.UsersDataSet;
import main.Main;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * Created by kruglana on 10.02.2017.
 */
public class SignInServlet extends HttpServlet {
    private final AccountService accountService;
    private static Logger log = Logger.getLogger(Main.class.getName());

    public SignInServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        System.out.println("SignInServlet : DoGet");
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String pass = request.getParameter("password");

        if (login == null || pass == null) {
            response.setContentType("text/html;charset=utf-8");
            System.out.println("SignIn : SC_BAD_REQUEST , login ==0");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        UsersDataSet profile = accountService.getUserByLogin(login);
        System.out.println(profile);

        if (profile == null) {
            response.setContentType("text/html;charset=utf-8");
            System.out.println("SignIn : SC_UNAUTHORIZED");
            response.getWriter().println("Unauthorized");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        if(profile.getPassword().equals(pass))
        {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println(String.format("Authorized: %s", login));
            response.setStatus(HttpServletResponse.SC_OK);
        }
        else
        {
            response.setContentType("text/html;charset=utf-8");
            System.out.println("SignIn : SC_CONFLICT");
            response.setStatus(HttpServletResponse.SC_CONFLICT);
        }
    }
}
