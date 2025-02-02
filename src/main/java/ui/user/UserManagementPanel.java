// src/main/java/ui/user/UserManagementPanel.java
package ui.user;

import user.UserService;

import javax.swing.*;
import java.awt.*;

public class UserManagementPanel extends JPanel {

    public UserManagementPanel(UserService userService, Runnable onLogout) {
        setLayout(new BorderLayout());
        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> onLogout.run());
        add(logoutButton, BorderLayout.SOUTH);
    }
}