package server.service;

public class CheckOnlineService {
    private static final CheckOnlineService CHECK_ONLINE_SERVICE = new CheckOnlineService();
    private CheckOnlineService(){}
    public static CheckOnlineService getInstance(){
        return CHECK_ONLINE_SERVICE;
    }
    public String response(){
        return  "Type: logOut;Message: time out";
    }
}
