import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class HomePage extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.getRequestDispatcher("/homePage.html").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String pass = req.getParameter("password");
        String user = req.getParameter("username");
        Accounts accounts = (Accounts) req.getServletContext().getAttribute("accounts");
        if(accounts == null) {
            resp.sendRedirect(req.getContextPath() + "homepage/login");
        } else if(!accounts.isPasswordValid(user,pass)) {

            // todo -> create "do you want to create and account page . . ."

            resp.sendRedirect(req.getContextPath() + "/homepage/login");
        }else {
            req.getRequestDispatcher("/Secret.html").forward(req,resp);
        }
    }

}
