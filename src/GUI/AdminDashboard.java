package GUI;

import javax.swing.*;
import java.awt.*;

public class AdminDashboard {
    private JPanel admin_dashboardPanel;
    private JButton logoutButton;
    private JButton foodButton;
    private JButton staffButton;
    private JButton revenueButton;
    private JButton accountButton;
    private JPanel foodPanel;
    private JPanel staffPanel;
    private JPanel revenuePanel;
    private JPanel accountPanel;
    private JLabel foodImage;
    private JLabel staffImage;
    private JLabel revenueImage;
    private JLabel accountImage;

    private JFrame frame;
    public AdminDashboard() {
        frame = new JFrame("Admin Dashboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(800, 600));
        frame.setResizable(false);

        frame.add(admin_dashboardPanel);

        ImageIcon staffIcon = new ImageIcon("C:\\Users\\minhs\\Downloads\\image.png");
        ImageIcon foodIcon = new ImageIcon("C:\\Users\\minhs\\Downloads\\staff_manage.png");
        ImageIcon accountIcon = new ImageIcon("C:\\Users\\minhs\\Downloads\\image.png");
        ImageIcon revenueIcon = new ImageIcon("C:\\Users\\minhs\\Downloads\\image.png");

        staffImage.setIcon(foodIcon);
        foodImage.setIcon(foodIcon);
        accountImage.setIcon(foodIcon);
        revenueImage.setIcon(foodIcon);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
