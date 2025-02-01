package ui;

import ui.auth.LoginPanel;
import ui.auth.RegisterPanel;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame() {
        setTitle("IStore Application");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        showMainPanel();
    }

    private void showMainPanel() {
        getContentPane().removeAll();
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBackground(Color.DARK_GRAY);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JButton registerButton = new JButton("Créer un compte");
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(registerButton, gbc);

        JButton loginButton = new JButton("Connexion");
        gbc.gridx = 1;
        gbc.gridy = 0;
        mainPanel.add(loginButton, gbc);

        registerButton.addActionListener(e -> showRegisterPanel());
        loginButton.addActionListener(e -> showLoginPanel());

        add(mainPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void showRegisterPanel() {
        getContentPane().removeAll();
        add(new RegisterPanel(this::showMainPanel), BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void showLoginPanel() {
        getContentPane().removeAll();
        add(new LoginPanel(this::showMainPanel), BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
        });
    }
}