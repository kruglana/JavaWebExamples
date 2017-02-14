package main;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.AllRequestsServlet;

import java.util.logging.Logger;

/**
 * Created by kruglana on 06.02.2017.
 */
public class Main {
    private static Logger log = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) throws Exception {
        AllRequestsServlet allRequestsServlet = new AllRequestsServlet();

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(allRequestsServlet), "/mirror");

        Server server = new Server(8080);
        server.setHandler(context);

        server.start();
        log.info("Server started");
        server.join();
    }
}
