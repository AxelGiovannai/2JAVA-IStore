
package ui.admin;

import javax.swing.*;
import java.awt.*;

public class WhitelistManagementPanel extends JPanel {

    public WhitelistManagementPanel(Runnable showAdminDashboard) {
        setLayout(new BorderLayout());

        JButton backButton = new JButton("Back to Dashboard");
        backButton.addActionListener(e -> showAdminDashboard.run());
        add(backButton, BorderLayout.SOUTH);
    }
}