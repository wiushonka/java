import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginPage extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/loginPage.html").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account account = new Account(req.getParameter("username"), req.getParameter("password"));

        Accounts accounts = (Accounts) req.getServletContext().getAttribute("accounts");
        accounts.addNewAccount(account);

        resp.sendRedirect(req.getContextPath() + "/homepage");
    }
}
