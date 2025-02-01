package service;

import user.UserService;
import whitelist.WhitelistService;
import user.entity.UserEntity;

import javax.swing.*;

public class RegistrationService {
    private final WhitelistService whitelistService;
    private final UserService userService;

    public RegistrationService(WhitelistService whitelistService) {
        this.whitelistService = whitelistService;
        this.userService = userService;
    }

    public void register(String email, String password) {
        if (whitelistService.isEmailWhitelisted(email)) {
            try {
                UserEntity newUser = new UserEntity(email, password);
                userService.saveUser(newUser);
                JOptionPane.showMessageDialog(null, "Registration successful", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Registration failed: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Your Email is not whitelisted", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
