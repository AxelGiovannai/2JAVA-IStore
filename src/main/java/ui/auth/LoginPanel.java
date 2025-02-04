// src/main/java/ui/auth/LoginPanel.java
package ui.auth;

import service.LoginService;
import user.UserService;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

public class LoginPanel extends JPanel {
    private final LoginService loginService;

    public LoginPanel(UserService userService, Runnable onBack, Consumer<JPanel> onLoginSuccess, Runnable onLogout, Runnable showWhitelistManagement, Runnable showStoreManagement, Runnable showAdminCreateStore, Runnable showAdminUserAccess) {
        this.loginService = new LoginService(userService, showWhitelistManagement, showStoreManagement, showAdminCreateStore, showAdminUserAccess);
        setLayout(new GridBagLayout());
        setBackground(Color.DARK_GRAY);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel userLabel = new JLabel("Email:");
        userLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(userLabel, gbc);

        JTextField userField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(userField, gbc);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(passLabel, gbc);

        JPasswordField passField = new JPasswordField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(passField, gbc);

        JButton loginButton = new JButton("Login");
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(loginButton, gbc);

        JButton backButton = new JButton("Back");
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        add(backButton, gbc);

        backButton.addActionListener(e -> onBack.run());
        loginButton.addActionListener(e -> {
            String email = userField.getText();
            String password = new String(passField.getPassword());
            loginService.handleLogin(email, password, onLoginSuccess, onLogout);
        });
    }
}