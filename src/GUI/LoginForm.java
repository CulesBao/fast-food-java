package GUI;

import javax.swing.*;
import java.awt.*;

public class LoginForm {
    private JTextField textField1;
    private JPanel panel1;
    private JPasswordField passwordField1;
    private JButton loginButton;

    private JFrame frame;
    public LoginForm() {
        frame = new JFrame("Fast Food");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(300, 250));
        frame.setResizable(false);

        frame.add(panel1);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}