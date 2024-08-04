package server.respositories.UserFriend;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static server.database.database.connection;

public class UserFriendListRepository {

    private final String readQuery = "SELECT * FROM \"Friend\" where username = ?";
    private String createQuery = "INSERT INTO \"Friend\" (username, friend_name) VALUES (?, ?)";
    private static final UserFriendListRepository userFriendListRepository;

    static{
        try{
            userFriendListRepository = new UserFriendListRepository();
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public static UserFriendListRepository getInstance(){
        return userFriendListRepository;
    }

    private UserFriendListRepository() throws SQLException{

    }

    public List<String> getFriendList(String username) throws SQLException {
        PreparedStatement readStatement = connection.prepareStatement(readQuery);
        readStatement.setString(1,username);
        ResultSet resultSet = readStatement.executeQuery();
        List<String> FriendList = new ArrayList<>();
        while (resultSet.next()){
            FriendList.add(resultSet.getString("friend_name"));
        }
        readStatement.close();
        return FriendList;
    }
    public void addFriend(String username, String friendName) throws SQLException {
        PreparedStatement createStatement = connection.prepareStatement(createQuery);
        createStatement.setString(1,username);
        createStatement.setString(2,friendName);
        createStatement.executeUpdate();
        createStatement.close();
    }
}
