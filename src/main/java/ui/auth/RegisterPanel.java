package ui.auth;

import service.RegistrationService;
import user.UserService;
import whitelist.WhitelistService;

import javax.swing.*;
import java.awt.*;

/**
 * Panel for user registration in the authentication interface.
 */
public class RegisterPanel extends JPanel {
    private final RegistrationService registrationService;

    /**
     * Constructs a new RegisterPanel.
     *
     * @param whitelistService the whitelist service
     * @param onBack the runnable to execute on back action
     * @param userService the user service
     */
    public RegisterPanel(WhitelistService whitelistService, Runnable onBack, UserService userService) {
        this.registrationService = new RegistrationService(whitelistService, userService);
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

        JLabel pseudoLabel = new JLabel("Pseudo:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(pseudoLabel, gbc);

        JTextField pseudoField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(pseudoField, gbc);

        JButton registerButton = new JButton("Register");
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        add(registerButton, gbc);

        JButton backButton = new JButton("Back");
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.CENTER;
        add(backButton, gbc);

        backButton.addActionListener(e -> onBack.run());
        registerButton.addActionListener(e -> {
            String email = emailField.getText();
            String password = new String(passField.getPassword());
            String pseudo = pseudoField.getText();
            registrationService.register(email, password, pseudo);
        });
    }
}