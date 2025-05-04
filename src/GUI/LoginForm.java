package GUI;

import BLL.AccountBLL;
import DTO.*;
import Utils.CustomDialog;
import java.awt.*;
import javax.swing.*;

public class LoginForm {
  private final AccountBLL accountBLL = new AccountBLL();
  private final JFrame frame;
  private JTextField usernameField;
  private JPasswordField passwordField;
  private JButton loginButton;
  private JPanel panel1;

  public LoginForm() {
    frame = new JFrame("Fast Food");
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    frame.setPreferredSize(new Dimension(800, 600));
    frame.setResizable(false);

    panel1 = new JPanel();
    panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
    panel1.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

    usernameField = new JTextField(20);
    passwordField = new JPasswordField(20);
    loginButton = new JButton("Login");

    JPanel usernamePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    usernamePanel.add(new JLabel("Username:"));
    usernamePanel.add(usernameField);

    JPanel passwordPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    passwordPanel.add(new JLabel("Password:"));
    passwordPanel.add(passwordField);

    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    buttonPanel.add(loginButton);

    panel1.add(Box.createVerticalGlue());
    panel1.add(usernamePanel);
    panel1.add(Box.createVerticalStrut(10));
    panel1.add(passwordPanel);
    panel1.add(Box.createVerticalStrut(20));
    panel1.add(buttonPanel);
    panel1.add(Box.createVerticalGlue());

    frame.add(panel1);

    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);

    loginButton.addActionListener(e -> handleLogin());
  }

  private void handleLogin() {
    String username = usernameField.getText().trim();
    String password = new String(passwordField.getPassword()).trim();
    LoginFormDTO loginFormDTO = new LoginFormDTO(username, password);

    ResponseDTO response = accountBLL.authenticateUser(loginFormDTO);

    if (response.getSuccess()) {
      CustomDialog.show(true, response.getMessage());
    }
    else {
      CustomDialog.show(false, "Error: " + response.getMessage());
    }
  }
}
