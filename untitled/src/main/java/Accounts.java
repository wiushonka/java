import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Accounts {

    private final BasicDataSource basicDataSource;
    public Accounts(BasicDataSource ds) {
        basicDataSource = ds;

    }

    public boolean isPasswordValid(String username, String inputpPassword) {
        try (Connection connection = basicDataSource.getConnection()) {
            PreparedStatement pq = connection.prepareStatement("SELECT password FROM users WHERE username = ?");
            pq.setString(1, username);
            ResultSet rs = pq.executeQuery();
            if(rs.next()) {
                String pass = rs.getString("password");
                return inputpPassword.equals(pass);
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public void addNewAccount(Account account) {
        try (Connection connection = basicDataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("insert into users (username, password) values(?,?);");
            ps.setString(1, account.getUsername());
            ps.setString(2, account.getPassword());
            ps.executeUpdate();
        } catch (SQLException ex){
            throw new RuntimeException(ex);
        }
    }

}
