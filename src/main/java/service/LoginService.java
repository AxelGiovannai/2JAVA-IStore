package service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import user.UserService;
import user.entity.RoleEnum;
import user.entity.UserEntity;
import store.item.ItemService;
import store.inventory.InventoryService;
import ui.MainFrame;
import ui.admin.AdminDashboardPanel;
import ui.user.UserManagementPanel;

import javax.swing.*;
import java.util.function.Consumer;

/**
 * Service for handling user login.
 */
public class LoginService {
    private final UserService userService;
    private final Runnable showWhitelistManagement;
    private final Runnable showStoreManagement;
    private final Runnable showAdminCreateStore;
    private final Runnable showAdminUserAccess;
    private final Runnable showUserManagementPanel;
    private final ItemService itemService;
    private final InventoryService inventoryService;
    private final Runnable showInventoryViewPanel;
    private final MainFrame mainFrame;

    /**
     * Constructs a new LoginService.
     *
     * @param userService the user service
     * @param showWhitelistManagement the runnable to show whitelist management
     * @param showStoreManagement the runnable to show store management
     * @param showAdminCreateStore the runnable to show admin create store
     * @param showAdminUserAccess the runnable to show admin user access
     * @param showUserManagementPanel the runnable to show user management panel
     * @param itemService the item service
     * @param inventoryService the inventory service
     * @param showInventoryViewPanel the runnable to show inventory view panel
     * @param mainFrame the main frame
     */
    public LoginService(UserService userService, Runnable showWhitelistManagement, Runnable showStoreManagement, Runnable showAdminCreateStore, Runnable showAdminUserAccess, Runnable showUserManagementPanel, ItemService itemService, InventoryService inventoryService, Runnable showInventoryViewPanel, MainFrame mainFrame) {
        this.userService = userService;
        this.showWhitelistManagement = showWhitelistManagement;
        this.showStoreManagement = showStoreManagement;
        this.showAdminCreateStore = showAdminCreateStore;
        this.showAdminUserAccess = showAdminUserAccess;
        this.showUserManagementPanel = showUserManagementPanel;
        this.itemService = itemService;
        this.inventoryService = inventoryService;
        this.showInventoryViewPanel = showInventoryViewPanel;
        this.mainFrame = mainFrame;
    }

    /**
     * Handles user login.
     *
     * @param email the user's email
     * @param password the user's password
     * @param onLoginSuccess the consumer to handle successful login
     * @param onLogout the runnable to handle logout
     */
    public void handleLogin(String email, String password, Consumer<JPanel> onLoginSuccess, Runnable onLogout) {
        UserEntity user = userService.findUserByEmail(email);
        if (user != null && BCrypt.verifyer().verify(password.toCharArray(), user.getPassword()).verified) {
            System.out.println("if1 User role: " + user.getRole());
            if (user.getRole() == RoleEnum.SUPERADMIN) {
                System.out.println("if sAD User role: " + user.getRole());
                onLoginSuccess.accept(new AdminDashboardPanel(userService, onLogout, showWhitelistManagement, showStoreManagement, showAdminCreateStore, showAdminUserAccess));
            } else if (user.getRole() == RoleEnum.ADMIN || user.getRole() == RoleEnum.EMPLOYEE) {
                System.out.println("if AD/EM User role: " + user.getRole());
                onLoginSuccess.accept(new UserManagementPanel(userService, user, showInventoryViewPanel, onLogout));
            }
        } else {
            JOptionPane.showMessageDialog(null, "Invalid email or password", "Login Failed", JOptionPane.ERROR_MESSAGE);
        }
    }
}