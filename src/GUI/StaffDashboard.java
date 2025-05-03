package GUI;

import javax.swing.*;
import java.awt.*;

public class StaffDashboard {
    private JPanel staff_dashboardPanel;
    private JButton logoutButton;
    private JButton ordersButton;
    private JButton accountButton;
    private JPanel ordersPanel;
    private JPanel accountPanel;
    private JLabel ordersImage;
    private JLabel accountImage;

    public StaffDashboard(){
        JFrame frame = new JFrame("Staff Dashboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(800, 600));
        frame.setResizable(false);

        frame.add(staff_dashboardPanel);

        ImageIcon ordersIcon = new ImageIcon("C:\\Users\\minhs\\Downloads\\staff_manage.png");
        ImageIcon accountIcon = new ImageIcon("C:\\Users\\minhs\\Downloads\\staff_manage.png");

        ordersImage.setIcon(ordersIcon);
        accountImage.setIcon(accountIcon);


        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}
