package GUI;

import Config.Session;
import java.awt.*;
import javax.swing.*;

public class StaffDashboard {
  private JFrame frame = new JFrame("Staff Dashboard");
  private JPanel staff_dashboardPanel;
  private JButton logoutButton;
  private JButton ordersButton;
  private JButton revenueButton;
  private JLabel ordersImage;
  private JLabel accountImage;

  public StaffDashboard() {
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setPreferredSize(new Dimension(500, 300));
    frame.setResizable(false);

    frame.add(staff_dashboardPanel);

    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
    logoutButton.addActionListener(e -> handleLogOut());
    ordersButton.addActionListener(e -> handleOrderButton());
  }



  private void handleLogOut() {
    int response =
        JOptionPane.showConfirmDialog(
            frame, "Are you sure you want to log out?", "Log Out", JOptionPane.YES_NO_OPTION);
    if (response == JOptionPane.YES_OPTION) {
      Session.clearSession();
      frame.dispose();
      new LoginForm();
    }
  }

  private void handleOrderButton() {
    frame.dispose();
    new OrdersPage();
  }
}
