package server.service;
public class SendFriendRequestService {

    private static final SendFriendRequestService sendFriendRequest = new SendFriendRequestService();
    private SendFriendRequestService(){
    }
    public static SendFriendRequestService getInstance(){
        return sendFriendRequest;
    }

    public String SendFriendRequest(String sender, String receiver, String currentTime ){
        // todo
        return "A";
    }
}
