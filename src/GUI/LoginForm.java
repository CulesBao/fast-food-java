package GUI;

import javax.swing.*;
import java.awt.*;

public class LoginForm {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JPanel panel1;

    public LoginForm() {
        // Khởi tạo JFrame
        JFrame frame = new JFrame("Fast Food");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(500, 300));
        frame.setResizable(false);

        // Khởi tạo panel1 với BoxLayout để căn giữa
        panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
        panel1.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Thêm padding

        // Khởi tạo các thành phần
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        loginButton = new JButton("Login");

        // Tạo các panel con để căn chỉnh tốt hơn
        JPanel usernamePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        usernamePanel.add(new JLabel("Username:"));
        usernamePanel.add(usernameField);

        JPanel passwordPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        passwordPanel.add(new JLabel("Password:"));
        passwordPanel.add(passwordField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(loginButton);

        // Thêm các thành phần vào panel1
        panel1.add(Box.createVerticalGlue()); // Đẩy nội dung vào giữa
        panel1.add(usernamePanel);
        panel1.add(Box.createVerticalStrut(10)); // Khoảng cách giữa các thành phần
        panel1.add(passwordPanel);
        panel1.add(Box.createVerticalStrut(20));
        panel1.add(buttonPanel);
        panel1.add(Box.createVerticalGlue());

        // Thêm panel1 vào frame
        frame.add(panel1);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}