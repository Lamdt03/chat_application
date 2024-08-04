package client.controller;


import client.service.ServerCommunicationService;
import service.CurrentTimeService;

public class Message {
    private final String type = "SENDMESSAGE";
    private static final Message Message = new Message();
    private Message(){}

    public static Message getInstance(){
        return Message;
    }
    public void sendMessage(String message,String receiver){
        String text = "type: " + type +";"
                + "receiver: " + receiver +";"
                + "message: " + message + ";"
                + "currentTime: " + CurrentTimeService.getInstance().getCurrentTime();
        ServerCommunicationService.getInstance().sendMessageToServer(text);
    }

}
