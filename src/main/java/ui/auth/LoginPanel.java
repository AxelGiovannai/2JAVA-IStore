package ui.auth;

import service.LoginService;
import user.UserService;
import store.item.ItemService;
import store.inventory.InventoryService;
import ui.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

/**
 * Panel for user login in the authentication interface.
 */
public class LoginPanel extends JPanel {
    private final LoginService loginService;

    /**
     * Constructs a new LoginPanel.
     *
     * @param userService the user service
     * @param onBack the runnable to execute on back action
     * @param onLoginSuccess the consumer to execute on successful login
     * @param onLogout the runnable to execute on logout
     * @param showWhitelistManagement the runnable to show whitelist management
     * @param showStoreManagement the runnable to show store management
     * @param showAdminCreateStore the runnable to show admin create store
     * @param showAdminUserAccess the runnable to show admin user access
     * @param showUserManagementPanel the runnable to show user management panel
     * @param itemService the item service
     * @param inventoryService the inventory service
     * @param showInventoryViewPanel the runnable to show inventory view panel
     * @param mainFrame the main frame of the application
     */
    public LoginPanel(UserService userService, Runnable onBack, Consumer<JPanel> onLoginSuccess, Runnable onLogout, Runnable showWhitelistManagement, Runnable showStoreManagement, Runnable showAdminCreateStore, Runnable showAdminUserAccess, Runnable showUserManagementPanel, ItemService itemService, InventoryService inventoryService, Runnable showInventoryViewPanel, MainFrame mainFrame) {
        this.loginService = new LoginService(userService, showWhitelistManagement, showStoreManagement, showAdminCreateStore, showAdminUserAccess, showUserManagementPanel, itemService, inventoryService, showInventoryViewPanel, mainFrame);
        setLayout(new GridBagLayout());
        setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel emailLabel = new JLabel("Email:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(emailLabel, gbc);

        JTextField emailField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(emailField, gbc);

        JLabel passLabel = new JLabel("Password:");
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
            String email = emailField.getText();
            String password = new String(passField.getPassword());
            loginService.handleLogin(email, password, onLoginSuccess, onLogout);
        });
    }
}