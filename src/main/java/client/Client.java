package client;


import client.view.announcement.*;
import service.splitService;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
public class Client {

     splitService splitService = service.splitService.getInstance();
     public static BufferedReader in;
     public static PrintWriter out;

    String message;




    public void start() throws Exception{
        Socket socket = new Socket("localhost",2403);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(),true);
        while (true){
            message = in.readLine();
            System.out.println("received message: " + message);
            String[] messages = message.split(";");
            for (int i = 0; i <messages.length;i++){
                messages[i] = splitService.splitMessage(messages[i]);
            }
            if(messages[0].equals("CheckLogin")){
                contextNotification.getInstance().setNotification(new LoginNotification());
            }
            else if(messages[0].equals("CheckSignUp")){
                contextNotification.getInstance().setNotification(new SignUpNotification());
            }
            else if(messages[0].equals("receiveMessage")){

            } else if (messages[0].equals("ViewListFriend")) {
                contextNotification.getInstance().setNotification(new friendListNotification());
            }
            else if(messages[0].equals("logOut")){
                contextNotification.getInstance().setNotification(new logOutNotification());
            }
            contextNotification.getInstance().executeNotification(messages[1]);
        }
    }

    public static void main(String[] args) throws Exception {
        client.view.LoginForm.getInstance(); // login view
        Client client = new Client();
        client.start();
    }
}


