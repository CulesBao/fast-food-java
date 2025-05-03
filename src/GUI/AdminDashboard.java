package GUI;

import javax.swing.*;
import java.awt.*;

public class AdminDashboard {
    private JButton staff_manageButton;
    private JButton foodButton;
    private JButton revenueButton;
    private JButton accountButton;
    private JPanel staff_managePanel;
    private JPanel foodPanel;
    private JPanel ordersPanel;
    private JPanel revenuePanel;
    private JPanel admin_dashboardPanel;
    private JLabel foodImage;
    private JLabel staff_manageImage;
    private JLabel ordersImage;
    private JLabel revenueImage;
    private JButton logoutButton;

    private JFrame frame;
    public AdminDashboard() {
        frame = new JFrame("Admin Dashboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(800, 600));
        frame.setResizable(false);

        frame.add(admin_dashboardPanel);

        ImageIcon staff_manageIcon = new ImageIcon("C:\\Users\\minhs\\Downloads\\image.png");
        ImageIcon foodIcon = new ImageIcon("C:\\Users\\minhs\\Downloads\\staff_manage.png");
        ImageIcon ordersIcon = new ImageIcon("C:\\Users\\minhs\\Downloads\\image.png");
        ImageIcon revenueIcon = new ImageIcon("C:\\Users\\minhs\\Downloads\\image.png");

        staff_manageImage.setIcon(foodIcon);
        foodImage.setIcon(foodIcon);
        ordersImage.setIcon(foodIcon);
        revenueImage.setIcon(foodIcon);

        staff_manageButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        staff_manageButton.setHorizontalTextPosition(SwingConstants.CENTER);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
