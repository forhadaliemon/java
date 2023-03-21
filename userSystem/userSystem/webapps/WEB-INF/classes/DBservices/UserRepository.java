package DBservices;

import Models.User;
import Models.UserType;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import static DBservices.DatabasesConnection.getConnection;

public class UserRepository {
    public static int RegisterUser(User p) {
        int status = 0;
        try (Connection con = getConnection()) {
            var ps = con.prepareStatement("INSERT INTO Users (username, password, email, userType) values(?,?,?,?)");
            ps.setString(1, p.getUsername());
            ps.setString(2, p.getPassword());
            ps.setString(3, p.getEmail());
            ps.setString(4, p.getUserType().toString());
            ps.executeUpdate();
            status = 1;
            return status;
        } catch (Exception e) {
            return status;
        }
    }

    public static int LoginUser(String username, String password) {
        int status = 0;
        try (Connection con = getConnection()) {
            var ps = con.prepareStatement("SELECT * FROM Users WHERE username = ? AND password = ?");
            ps.setString(1, username);
            ps.setString(2, password);
            var rs = ps.executeQuery();
            if (rs.next()) {
                status = 1;
            }
            return status;
        } catch (Exception e) {
            return status;
        }
    }

    //get user type by username



}
