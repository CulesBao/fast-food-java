package GUI;

import CONFIG.DBHelper;
import DTO.*;
import org.mindrot.jbcrypt.BCrypt;
import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class LoginForm {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JPanel panel1;
    private final JFrame frame;

    public LoginForm() {
        // Khởi tạo JFrame
        frame = new JFrame("Fast Food");
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

        // Thêm ActionListener cho loginButton
        loginButton.addActionListener(e -> handleLogin());
    }

    // Xử lý sự kiện đăng nhập
    private void handleLogin() {
        // Lấy thông tin từ giao diện và tạo LoginFormDTO
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();
        LoginFormDTO loginFormDTO = new LoginFormDTO(username, password);

        // Gọi phương thức đăng nhập và nhận ResponseDTO
        ResponseDTO response = authenticateUser(loginFormDTO);

        // Hiển thị kết quả cho người dùng
        if (response.getSuccess()) {
            JOptionPane.showMessageDialog(frame, response.getMessage(), "Success", JOptionPane.INFORMATION_MESSAGE);
            // TODO: Chuyển đến giao diện tiếp theo dựa trên role (dữ liệu role nằm trong response.getData())
        } else {
            JOptionPane.showMessageDialog(frame, response.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Phương thức xác thực người dùng
    private ResponseDTO authenticateUser(LoginFormDTO loginFormDTO) {
        // Kiểm tra dữ liệu đầu vào
        if (loginFormDTO.getUsername().isEmpty() || loginFormDTO.getPassword().isEmpty()) {
            return new ResponseDTO(false, "Please enter both username and password", null);
        }

        // Truy vấn cơ sở dữ liệu để kiểm tra thông tin đăng nhập
        String query = "SELECT password, role FROM accounts WHERE username = ?";
        try (ResultSet rs = DBHelper.executeQuery(query, loginFormDTO.getUsername())) {
            if (rs != null && rs.next()) {
                String hashedPassword = rs.getString("password");
                String role = rs.getString("role");

                // So sánh mật khẩu với jbcrypt
                if (BCrypt.checkpw(loginFormDTO.getPassword(), hashedPassword)) {
                    // Đăng nhập thành công, trả về role trong data
                    return new ResponseDTO(true, "Login successfully! Role: " + role, role);
                } else {
                    return new ResponseDTO(false, "Invalid username or password", null);
                }
            } else {
                return new ResponseDTO(false, "Invalid username or password", null);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return new ResponseDTO(false, "Database error: " + ex.getMessage(), null);
        }
    }
}