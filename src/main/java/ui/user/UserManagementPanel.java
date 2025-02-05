package ui.user;

import user.UserService;
import user.entity.UserEntity;

import javax.swing.*;
import java.awt.*;

public class UserManagementPanel extends JPanel {
    private final UserService userService;
    private final UserEntity currentUser;
    private final Runnable showInventoryViewPanel;
    private final Runnable onLogout;

    public UserManagementPanel(UserService userService, UserEntity currentUser, Runnable showInventoryViewPanel, Runnable onLogout) {
        this.userService = userService;
        this.currentUser = currentUser;
        this.showInventoryViewPanel = showInventoryViewPanel;
        this.onLogout = onLogout;

        setLayout(new BorderLayout());
        System.out.println("ca rentre dans UserManagementPanel");
        JPanel userInfoPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel userLabel = new JLabel("User: " + currentUser.getPseudo());
        gbc.gridx = 0;
        gbc.gridy = 0;
        userInfoPanel.add(userLabel, gbc);

        JLabel storeLabel = new JLabel("Store: " + (currentUser.getStore() != null ? currentUser.getStore().getName() : "None"));
        gbc.gridx = 0;
        gbc.gridy = 1;
        userInfoPanel.add(storeLabel, gbc);

        add(userInfoPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        JButton inventoryButton = new JButton("Go to Inventory View");
        inventoryButton.addActionListener(e -> showInventoryViewPanel.run());
        buttonPanel.add(inventoryButton, gbc);

        gbc.gridy = 1;
        gbc.gridwidth = 1;
        JLabel newPseudoLabel = new JLabel("New Pseudo:");
        buttonPanel.add(newPseudoLabel, gbc);

        gbc.gridx = 1;
        JTextField newPseudoField = new JTextField(20);
        buttonPanel.add(newPseudoField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        JButton changePseudoButton = new JButton("Change Pseudo");
        changePseudoButton.addActionListener(e -> {
            String newPseudo = newPseudoField.getText();
            if (!newPseudo.isEmpty()) {
                currentUser.setPseudo(newPseudo);
                userService.updateUser(currentUser);
                userLabel.setText("User: " + newPseudo);
            }
        });
        buttonPanel.add(changePseudoButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        JLabel newPasswordLabel = new JLabel("New Password:");
        buttonPanel.add(newPasswordLabel, gbc);

        gbc.gridx = 1;
        JPasswordField newPasswordField = new JPasswordField(20);
        buttonPanel.add(newPasswordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        JButton changePasswordButton = new JButton("Change Password");
        changePasswordButton.addActionListener(e -> {
            String newPassword = new String(newPasswordField.getPassword());
            if (!newPassword.isEmpty()) {
                currentUser.setPassword(newPassword);
                userService.updateUser(currentUser);
            }
        });
        buttonPanel.add(changePasswordButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> onLogout.run());
        buttonPanel.add(logoutButton, gbc);

        add(buttonPanel, BorderLayout.SOUTH);
    }
}