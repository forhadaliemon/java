package com.example.model;

public class UserDao {
    private String jdbcUrl;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;

    public UserDao() {
        jdbcUrl = "jdbc:mysql://localhost:3306/user_db";
        jdbcUsername = "root";
        jdbcPassword = "password";
    }

    protected void connect() throws SQLException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            jdbcConnection = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
        }
    }

    protected void disconnect() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }

    public boolean checkLogin(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        try {
            connect();
            PreparedStatement statement = jdbcConnection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                disconnect();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public User getUserByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        try {
            connect();
            PreparedStatement statement = jdbcConnection.prepareStatement(sql);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                return new User(id, username, password, email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                disconnect();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public boolean checkPassword(String username, String password) {
        User user = getUserByUsername(username);
        if (user != null) {
            return user.getPassword().equals(password);
        }
        return false;
    }

    public boolean createUser(User user) {
        String sql = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";
        try {
            connect();
            PreparedStatement statement = jdbcConnection.prepareStatement(sql);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            int rowsInserted = statement.executeUpdate();
            return rowsInserted
