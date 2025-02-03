// src/main/java/service/LoginService.java
package service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import ui.admin.AdminDashboardPanel;
import ui.user.UserManagementPanel;
import user.UserService;
import user.entity.UserEntity;
import user.entity.RoleEnum;

import javax.swing.*;
import java.util.function.Consumer;

public class LoginService {
    private final UserService userService;
    private final Runnable showWhitelistManagement;
    private final Runnable showInventoryManagement;
    private final Runnable showStoreManagement;
    private final Runnable showItemManagement;
    private final Runnable showAdminCreateStore;
    private final Runnable showAdminUserAccess;

    public LoginService(UserService userService, Runnable showWhitelistManagement, Runnable showInventoryManagement, Runnable showStoreManagement, Runnable showItemManagement, Runnable showAdminCreateStore, Runnable showAdminUserAccess) {
        this.userService = userService;
        this.showWhitelistManagement = showWhitelistManagement;
        this.showInventoryManagement = showInventoryManagement;
        this.showStoreManagement = showStoreManagement;
        this.showItemManagement = showItemManagement;
        this.showAdminCreateStore = showAdminCreateStore;
        this.showAdminUserAccess = showAdminUserAccess;
    }

    public void handleLogin(String email, String password, Consumer<JPanel> onLoginSuccess, Runnable onLogout) {
        UserEntity user = userService.findUserByEmail(email);
        if (user != null && BCrypt.verifyer().verify(password.toCharArray(), user.getPassword()).verified) {
            if (user.getRole() == RoleEnum.SUPERADMIN) {
                onLoginSuccess.accept(new AdminDashboardPanel(userService, onLogout, showWhitelistManagement, showInventoryManagement, showStoreManagement, showItemManagement, showAdminCreateStore, showAdminUserAccess));
            } else {
                onLoginSuccess.accept(new UserManagementPanel(userService, onLogout));
            }
        } else {
            JOptionPane.showMessageDialog(null, "Invalid email or password", "Login Failed", JOptionPane.ERROR_MESSAGE);
        }
    }
}