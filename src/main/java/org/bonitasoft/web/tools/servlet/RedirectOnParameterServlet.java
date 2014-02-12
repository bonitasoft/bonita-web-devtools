package org.bonitasoft.web.tools.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Utility servlet that redirect an url to the given url specified in a query string parameter
 * 
 * Web.xml should have init-parameter named 'redirectParam' wich specify query string parameter containing url to be redirected
 * 
 * @author Colin PUY
 */
public class RedirectOnParameterServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String redirectParam = getServletConfig().getInitParameter("redirectParam");
        if (redirectParam != null) {
            String parameter = req.getParameter(redirectParam);
            if (parameter !=null) {
                resp.sendRedirect("/" + parameter);
            }
        }
    }
}
