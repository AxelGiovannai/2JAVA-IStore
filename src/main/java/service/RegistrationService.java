// src/main/java/service/RegistrationService.java
package service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import user.UserService;
import whitelist.WhitelistService;
import user.entity.UserEntity;
import user.entity.RoleEnum;

import javax.swing.*;

public class RegistrationService {
    private final WhitelistService whitelistService;
    private final UserService userService;

    public RegistrationService(WhitelistService whitelistService, UserService userService) {
        this.whitelistService = whitelistService;
        this.userService = userService;
    }

    public void register(String email, String password, String pseudo) {
        if (whitelistService.isEmailWhitelisted(email)) {
            try {
                String hashedPassword = BCrypt.withDefaults().hashToString(12, password.toCharArray());
                UserEntity newUser = new UserEntity(email, hashedPassword, pseudo, RoleEnum.EMPLOYEE);
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