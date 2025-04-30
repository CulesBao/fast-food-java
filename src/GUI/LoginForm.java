package GUI;

import javax.swing.*;
import java.awt.*;

public class LoginForm {
    private JTextField usernameField;
    private JPanel panel1;
    private JPasswordField passwordField;
    private JButton loginButton;

    private JFrame frame;
    public LoginForm() {
        frame = new JFrame("Fast Food");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(500, 300));
        frame.setResizable(false);

        frame.add(panel1);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}