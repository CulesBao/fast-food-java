package GUI;

import javax.swing.*;
import java.awt.*;

public class StaffAccountPage {
    private JPanel staff_account_pagePanel;
    private JTextField username_Field;
    private JTextField nameField;
    private JTextField phoneField;
    private JTextField cccdField;
    private JButton confirmButton;
    private JButton backButton;
    private JPasswordField old_passwordField;
    private JPasswordField new_passwordField;
    private JPasswordField reconfirm_passwordField;
    private JButton changePasswordButton;

    public StaffAccountPage() {
        JFrame frame = new JFrame("Staff Account");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(800, 600));
        frame.setResizable(false);
        frame.setContentPane(staff_account_pagePanel);


        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
