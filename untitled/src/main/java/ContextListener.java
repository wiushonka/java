import org.apache.commons.dbcp2.BasicDataSource;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.SQLException;

public class ContextListener implements ServletContextListener {

    private static final String URL =  "jdbc:mysql://localhost:3306/accountsTable";

    private static final String DRIVER = "com.mysql.jdbc.Driver";

    private static final String USER =  "root";

    private static final String PASS =  "Rootroot1!";

    private BasicDataSource basicDataSource;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName(DRIVER);
        basicDataSource.setUrl(URL);
        basicDataSource.setUsername(USER);
        basicDataSource.setPassword(PASS);

        Accounts accounts = new Accounts(basicDataSource);
        ServletContext context = sce.getServletContext();
        context.setAttribute("accounts", accounts);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            basicDataSource.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
