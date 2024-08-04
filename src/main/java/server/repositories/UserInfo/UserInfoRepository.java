package server.respositories.UserInfo;


import java.sql.*;
import java.util.HashMap;

import static server.database.database.connection;


public class UserInfoRepository {
    private final String readQuery = "SELECT * FROM \"User\"";
    private final String createQuery = "INSERT INTO \"User\" (username, password) VALUES (?, ?)";
    private static final UserInfoRepository userInfoRepository;

    static {
        try {
            userInfoRepository = new UserInfoRepository();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public boolean createUser(String username, String password) throws SQLException {
        PreparedStatement createStatement = connection.prepareStatement(createQuery);
        createStatement.setString(1,username);
        createStatement.setString(2,password);
        createStatement.executeUpdate();
        createStatement.close();
        return true;
    }


    public HashMap<String,String> getUserList() throws SQLException {
        PreparedStatement readStatement = connection.prepareStatement(readQuery);
        ResultSet resultSet = readStatement.executeQuery();
        HashMap<String,String> userAccounts = new HashMap<>();
        while (resultSet.next()) {
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            userAccounts.put(username,password);
        }
        readStatement.close();
        return userAccounts;
    }


    public static UserInfoRepository getInstance(){
        return userInfoRepository;
    }
    private UserInfoRepository() throws SQLException {
    }


}
