// src/main/java/service/RegistrationService.java
package service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import user.UserService;
import user.entity.UserEntity;
import user.entity.RoleEnum;
import whitelist.WhitelistService;

import javax.swing.*;

public class RegistrationService {
    private final WhitelistService whitelistService;
    private final UserService userService;

    /**
     * Constructs a new RegistrationService.
     *
     * @param whitelistService the whitelist service
     * @param userService the user service
     */
    public RegistrationService(WhitelistService whitelistService, UserService userService) {
        this.whitelistService = whitelistService;
        this.userService = userService;
    }

    /**
     * Registers a new user.
     *
     * @param email the user's email
     * @param password the user's password
     * @param pseudo the user's pseudo
     */
    public void register(String email, String password, String pseudo) {
        if (whitelistService.isEmailWhitelisted(email)) {
            String hashedPassword = BCrypt.withDefaults().hashToString(12, password.toCharArray());
            try {
                UserEntity newUser = new UserEntity(email, hashedPassword, pseudo, RoleEnum.EMPLOYEE);
                userService.saveUser(newUser);
                JOptionPane.showMessageDialog(null, "Registration successful", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Registration failed: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Email is not whitelisted", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}