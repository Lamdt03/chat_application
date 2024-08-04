package server.service;

import server.Handler;
import server.respositories.UserFriend.UserFriendListRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static server.Server.handlers;

public class ListFriendService {
    private static final ListFriendService listFriendService = new ListFriendService();
    private final String type = "ViewListFriend";

    UserFriendListRepository userFriendListRepository = UserFriendListRepository.getInstance();
    private ListFriendService(){

    }
    public static ListFriendService getInstance(){
        return listFriendService;
    }

    public String response(String username) throws SQLException {
        int flag = 0;
        String response = "Type: " + type + ";" + "listFriend: ";
        List<String> friendList = new ArrayList<>(ViewListFriend(username));
        for (String friend : friendList){
            for(Handler handler : handlers){
                if(friend.equals(handler.getUsername())){
                    flag = 1;
                    break;
                }
            }
            response = response + friend+"("+flag+")"+",";
            flag = 0;

        }
        if (response.endsWith(",")) {
            response = response.substring(0, response.length() - 1);
        }
       return response;
    }

    private List<String> ViewListFriend(String username) throws SQLException {
        return userFriendListRepository.getFriendList(username);
    }

    public void addFriend(String username, String friendName) throws SQLException{
        userFriendListRepository.addFriend(username,friendName);
        userFriendListRepository.addFriend(friendName,username);
    }
}
