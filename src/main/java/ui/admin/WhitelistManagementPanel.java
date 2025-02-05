package ui.admin;

import whitelist.WhitelistService;
import whitelist.entity.WhitelistedEmailEntity;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Panel for managing the whitelist in the admin interface.
 */
public class WhitelistManagementPanel extends JPanel {
    private final WhitelistService whitelistService;
    private final DefaultListModel<String> emailListModel;

    /**
     * Constructs a new WhitelistManagementPanel.
     *
     * @param whitelistService the whitelist service
     * @param showAdminDashboard the runnable to show the admin dashboard
     */
    public WhitelistManagementPanel(WhitelistService whitelistService, Runnable showAdminDashboard) {
        this.whitelistService = whitelistService;
        this.emailListModel = new DefaultListModel<>();

        setLayout(new BorderLayout());

        JButton backButton = new JButton("Back to Dashboard");
        backButton.addActionListener(e -> showAdminDashboard.run());
        add(backButton, BorderLayout.SOUTH);

        JList<String> emailList = new JList<>(emailListModel);
        JScrollPane scrollPane = new JScrollPane(emailList);
        add(scrollPane, BorderLayout.CENTER);

        JPanel addEmailPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel emailLabel = new JLabel("Email:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        addEmailPanel.add(emailLabel, gbc);

        JTextField emailField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        addEmailPanel.add(emailField, gbc);

        JButton addButton = new JButton("Add Email");
        gbc.gridx = 1;
        gbc.gridy = 1;
        addEmailPanel.add(addButton, gbc);

        JButton deleteButton = new JButton("Delete Email");
        gbc.gridx = 1;
        gbc.gridy = 2;
        addEmailPanel.add(deleteButton, gbc);

        add(addEmailPanel, BorderLayout.NORTH);

        addButton.addActionListener(e -> {
            String email = emailField.getText();
            if (!email.isEmpty()) {
                try {
                    whitelistService.addEmailToWhitelist(email);
                    emailListModel.addElement(email);
                    emailField.setText("");
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        deleteButton.addActionListener(e -> {
            String email = emailField.getText();
            if (!email.isEmpty()) {
                try {
                    whitelistService.deleteEmailFromWhitelist(email);
                    emailListModel.removeElement(email);
                    emailField.setText("");
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        loadEmails();
    }

    /**
     * Loads existing emails into the list model.
     */
    private void loadEmails() {
        List<WhitelistedEmailEntity> emails = whitelistService.getAllEmails();
        for (WhitelistedEmailEntity emailEntity : emails) {
            emailListModel.addElement(emailEntity.getEmail());
        }
    }
}