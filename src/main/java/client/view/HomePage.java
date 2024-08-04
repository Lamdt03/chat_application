package client.view;


import client.view.listener.boxChatSelectionListener;
import client.view.listener.sendMessageListener;
import client.view.listener.viewListFriendListener;

import javax.swing.*;
import java.awt.*;


public class HomePage extends JFrame {
    private static HomePage instance;
    public static JList<String> friendList;
    public static JTextField messageTextField;
    public static JTabbedPane chatTabbedPane;

    public HomePage() {
        // Set up the user interface
        setTitle("Chat Application");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create components
        friendList = new JList<>(new String[]{"user1", "user2", "user3"}); // Replace with your friend list data
        friendList.setBackground(Color.LIGHT_GRAY);
        JScrollPane friendListScrollPane = new JScrollPane(friendList);
        // Create the chat tabbed pane
        chatTabbedPane = new JTabbedPane();
        messageTextField = new JTextField(20);
        JButton sendButton = new JButton("Send");
        sendButton.setBackground(Color.BLUE);
        sendButton.setForeground(Color.WHITE);
        JButton viewListFriendButton = new JButton("FriendList");
        viewListFriendButton.setBackground(Color.BLUE);
        viewListFriendButton.setForeground(Color.WHITE);
        // Tạo panel chứa nút Friend List và TextField
        JPanel friendListPanel = new JPanel();
        friendListPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        friendListPanel.add(messageTextField);
        // Set layout
        setLayout(new BorderLayout());
        // Add the chat tabbed pane to the split pane
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, friendListScrollPane, chatTabbedPane);
        add(splitPane, BorderLayout.CENTER);
        add(splitPane, BorderLayout.CENTER);
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.add(messageTextField, BorderLayout.CENTER);
        bottomPanel.add(sendButton, BorderLayout.EAST);
        bottomPanel.add(viewListFriendButton,BorderLayout.WEST);
        add(bottomPanel, BorderLayout.SOUTH);
        // Thêm panel friendListPanel vào bottomPanel
    //    bottomPanel.add(friendListPanel, BorderLayout.WEST);

        // Event handling for the send button
        friendList.addMouseListener(new boxChatSelectionListener());
        sendButton.addActionListener(new sendMessageListener());
        viewListFriendButton.addActionListener(new viewListFriendListener());
    }


    public static HomePage getInstance() {
        if (instance == null) instance = new HomePage();
        return instance;
    }

    public static void main(String[] args) {
        HomePage homePage = new HomePage();
        homePage.setVisible(true);
    }
}
