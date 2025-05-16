package GUI;

import Config.Session;

import javax.swing.*;
import java.awt.*;

public class AdminDashboard {
    private JPanel admin_dashboardPanel;
    private JButton logoutButton;
    private JButton foodButton;
    private JButton staffButton;
    private JButton revenueButton;
    private JFrame frame;
    public AdminDashboard() {
        frame = new JFrame("Admin Dashboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(500, 300));
        frame.setResizable(false);

        frame.add(admin_dashboardPanel);


        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        logoutButton.addActionListener(e -> handleLogOut());
        foodButton.addActionListener(e -> handleFoodButton());
        staffButton.addActionListener(e -> handleStaffButton());
        revenueButton.addActionListener(e -> handleRevenueButton());
    }
    private void handleLogOut() {
        int response = JOptionPane.showConfirmDialog(frame, "Are you sure you want to log out?", "Log Out", JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION) {
            Session.clearSession();
            frame.dispose();
            new LoginForm();
        }
    }
    private void handleFoodButton() {
        new FoodPage();
        frame.dispose();
    }
    private void handleStaffButton() {
        new StaffPage();
        frame.dispose();
    }
    private void handleRevenueButton() {
        new RevenuePage();
        frame.dispose();
    }
}
