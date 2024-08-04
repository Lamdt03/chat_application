package client.view.listener;
import client.view.UserChatBox;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static client.view.HomePage.*;


public class sendMessageListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        // Get the selected chat box
        UserChatBox selectedChatBox = (UserChatBox) chatTabbedPane.getSelectedComponent();

        // Get the message from the message text field
        String message = messageTextField.getText();

        // Append the message to the chat box
        JTextArea chatTextArea = selectedChatBox.getChatTextArea();
        chatTextArea.append(message + "\n");

        // Clear the message text field
        messageTextField.setText("");
    }
}
