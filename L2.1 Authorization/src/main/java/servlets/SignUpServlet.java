package servlets;

import accounts.AccountService;
import accounts.UserProfile;
import main.Main;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * Created by kruglana on 08.02.2017.
 */
public class SignUpServlet  extends HttpServlet {
    private final AccountService accountService;
    private static Logger log = Logger.getLogger(Main.class.getName());

    public SignUpServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    //get logged user profile
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {

        /*String sessionId = request.getSession().getId();

        UserProfile profile = accountService.getUserBySessionId(sessionId);

        if (profile == null) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        } else {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("456");
            response.setStatus(HttpServletResponse.SC_OK);
        }*/
        System.out.println("DoGet");
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
            String login = request.getParameter("login");
            String pass = request.getParameter("password");

            if (login == null ) {
                response.setContentType("text/html;charset=utf-8");
                System.out.println("SC_BAD_REQUEST");
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }

            UserProfile profile = new UserProfile(login);
            accountService.addNewUser(profile);
            accountService.addSession(request.getSession().getId(), profile);
            System.out.println("SC_OK");
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doDelete(HttpServletRequest request,
                            HttpServletResponse response) throws ServletException, IOException {
        String sessionId = request.getSession().getId();
        UserProfile profile = accountService.getUserBySessionId(sessionId);
        if (profile == null) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        } else {
            accountService.deleteSession(sessionId);
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("Goodbye!");
            response.setStatus(HttpServletResponse.SC_OK);
        }

    }
}
