package server;

import server.service.*;
import service.splitService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class Handler extends Thread {
    private final Socket clientSocket;
    private String username;
    BufferedReader in;
    public PrintWriter out;
    String message;
    private Boolean isRunning = true;
    splitService splitService = service.splitService.getInstance();
    CheckLoginService checkLoginService = server.service.CheckLoginService.getInstance();
    CheckSignUpService checkSignUpService = server.service.CheckSignUpService.getInstance();
    ListFriendService listFriendService = ListFriendService.getInstance();
    SendFriendRequestService sendFriendRequestService = server.service.SendFriendRequestService.getInstance();
    SendMessageService sendMessageService = server.service.SendMessageService.getInstance();
    String currentTime;
    public  ClientCommunicationService clientCommunicationService;

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setRunning(Boolean running) {
        isRunning = running;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Handler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }
    private String serverResponse;

    @Override
    public void run(){
        try {
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(),true);
            clientCommunicationService = new ClientCommunicationService(out);
            while (true){
               message = in.readLine();
               System.out.println("received message: "+ message);
               String[] messages = message.split(";");
               for (int i = 0; i <messages.length; i++){
                   if(messages[i].startsWith("currentTime: ")){
                        setCurrentTime(splitService.splitMessage(messages[i]));
                   }
                   else {
                       messages[i] = splitService.splitMessage(messages[i]);
                   }
               }
               switch (messages[0]){
                   case "LOGIN":
                       serverResponse = CheckLoginService.getInstance().response(messages[2],messages[3]);
                       if (checkLoginService.getServerResponse().equals("1")){
                           setUsername(messages[2]);
                       }
                       break;
                   case "SIGNUP":
                       serverResponse = checkSignUpService.response(messages[2],messages[3]);
                       break;
                   case "VIEWLISTFRIEND":
                       serverResponse = listFriendService.response(username);
                       break;
                   case "SENDFRIENDREQUEST":
                       sendFriendRequestService.SendFriendRequest(messages[1],messages[2],messages[3]);
                       break;
                   case "ACCEPTFRIENDREQUEST":
                   case "SENDMESSAGE":
                        sendMessageService.sendMessage(username,messages[1],messages[2],getCurrentTime());
                        break;

               }
               clientCommunicationService.sendMessageToServer(serverResponse);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
