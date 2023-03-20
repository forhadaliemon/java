package lms.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class UserRepository {
    private final Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public UserRepository() throws NamingException, SQLException {
        Context context = new InitialContext();
        DataSource dataSource = (DataSource)context.lookup("java:comp/env/jdbc/lms");
        this.connection = dataSource.getConnection();
        this.preparedStatement = null;
        this.resultSet = null;
    }

    public boolean login(String email, String password) throws SQLException {
        String sql = "select id, email, password from users where email = ? and password = ?";
        this.preparedStatement = this.connection.prepareStatement(sql);
        this.preparedStatement.setString(1, email);
        this.preparedStatement.setString(2, password);
        this.resultSet = this.preparedStatement.executeQuery();
        boolean res = this.resultSet.next();
        this.close();
        return res;
    }

    public boolean register(String email, String password) throws SQLException {
        String sql = "insert into users set email = ?, password = ?, is_enabled = 1";
        this.preparedStatement = this.connection.prepareStatement(sql);
        this.preparedStatement.setString(1, email);
        this.preparedStatement.setString(2, password);
        boolean res = this.preparedStatement.execute();
        this.close();
        return res;
    }

    public boolean changePassword(String email, String currentPassword, String newPassword) throws SQLException {
        String sql = "update users set password = ? where email = ? and password = ?";
        this.preparedStatement = this.connection.prepareStatement(sql);
        this.preparedStatement.setString(1, newPassword);
        this.preparedStatement.setString(2, email);
        this.preparedStatement.setString(3, currentPassword);
        boolean res = this.preparedStatement.execute();
        this.close();
        return res;
    }

    private void close() throws SQLException {
        this.preparedStatement.close();
        this.connection.close();
    }
}
