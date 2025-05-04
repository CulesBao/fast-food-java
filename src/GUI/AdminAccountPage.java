package GUI;

import javax.swing.*;
import java.awt.*;

public class AdminAccountPage {
    private JPanel admin_account_pagePanel;
    private JTextField username_Field;
    private JTextField nameField;
    private JTextField phoneField;
    private JTextField cccdField;
    private JButton editButton;
    private JButton backButton;
    private JPasswordField old_passwordField;
    private JPasswordField new_passwordField;
    private JPasswordField reconfirm_passwordField;
    private JButton changePasswordButton;

    public AdminAccountPage(){
        JFrame frame = new JFrame("Admin Account");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(800, 600));
        frame.setResizable(false);
        frame.setContentPane(admin_account_pagePanel);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
