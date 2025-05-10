package GUI;

import BLL.AccountBLL;
import DTO.AccountDTO;
import DTO.FindAccountDTO;
import DTO.ResponseDTO;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class StaffPage {
  private static AccountBLL accountBLL = new AccountBLL();
  private JPanel staff_pagePanel;
  private JButton backButton;
  private JTextField staff_nameField;
  private JButton add_staffButton;
  private JTable staffTable;
  private JTextField staff_phoneField;
  private JTextField staff_id_infField;
  private JTextField staff_name_infField;
  private JTextField staff_username_infField;
  private JTextField staff_phone_infField;
  private JButton changeButton;
  private JButton deleteButton;
  private JTextField name_findField;
  private JTextField phone_findField;
  private JButton findButton;
  private JTextField staff_usernameField;
  private JPasswordField staff_passwordField;
  private JButton changePasswordButton;
  private JPasswordField new_passwordField;
  private JPasswordField confirm_passwordField;
  private JPasswordField confirmPasswordField;
  private JFrame frame;

  public StaffPage() {
    frame = new JFrame("Staff Page");
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.setPreferredSize(new Dimension(1200, 600));
    frame.setResizable(false);

    frame.add(staff_pagePanel);

    String[] columnNames = {"Staff ID", "Name", "Username", "Created at", "Phone number"};
    Object[][] data = {};

    DefaultTableModel model = new DefaultTableModel(data, columnNames);
    staffTable.setModel(model);

    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
    backButton.addActionListener(e -> handleBackButton());
    add_staffButton.addActionListener(e -> handleAddButton());
    findButton.addActionListener(e -> handleFindButton());
    staffTable.addMouseListener(
        new java.awt.event.MouseAdapter() {
          public void mouseClicked(java.awt.event.MouseEvent evt) {
            handleChangeRow();
          }
        });
    changeButton.addActionListener(e -> handleChangeButton());
    changePasswordButton.addActionListener(e -> handleChangePassword());
    deleteButton.addActionListener(e -> handleDeleteButton());
  }

  private void handleBackButton() {
    frame.dispose();
    new GUI.AdminDashboard();
  }

  private void handleAddButton() {
    String staffName = staff_nameField.getText();
    String staffUsername = staff_usernameField.getText();
    String staffPassword = new String(staff_passwordField.getPassword());
    String comfirmPassword = new String(confirmPasswordField.getPassword());
    String staffPhone = staff_phoneField.getText();

    AccountDTO accountDTO =
        new AccountDTO(staffUsername, staffPassword, staffName, staffPhone, "STAFF");
    ResponseDTO responseDTO = accountBLL.registerAccount(accountDTO, comfirmPassword);
    if (responseDTO.getSuccess()) {
      JOptionPane.showMessageDialog(frame, "Staff added successfully!");
      handleFindButton();
      staff_nameField.setText("");
      staff_usernameField.setText("");
      staff_passwordField.setText("");
      staff_phoneField.setText("");
    } else {
      JOptionPane.showMessageDialog(frame, "Failed to add staff: " + responseDTO.getMessage());
    }
  }

  private void handleFindButton() {
    String staffName = name_findField.getText();
    String staffPhone = phone_findField.getText();

    ResponseDTO responseDTO = accountBLL.getAccountsByNameAndPhoneNumber(staffName, staffPhone);
    if (responseDTO.getSuccess()) {
      DefaultTableModel model = (DefaultTableModel) staffTable.getModel();
      model.setRowCount(0);
      @SuppressWarnings("unchecked")
      java.util.List<FindAccountDTO> accountList =
          (java.util.List<FindAccountDTO>) responseDTO.getData();
      for (FindAccountDTO account : accountList) {
        model.addRow(
            new Object[] {
              account.getId(),
              account.getFullName(),
              account.getUserName(),
              account.getCreatedAt(),
              account.getPhoneNumber()
            });
      }
    } else {
      JOptionPane.showMessageDialog(frame, "Failed to find staff: " + responseDTO.getMessage());
    }
  }

  private void handleChangeRow() {
    int selectedRow = staffTable.getSelectedRow();
    if (selectedRow != -1) {
      staff_id_infField.setText(staffTable.getValueAt(selectedRow, 0).toString());
      staff_name_infField.setText(staffTable.getValueAt(selectedRow, 1).toString());
      staff_username_infField.setText(staffTable.getValueAt(selectedRow, 2).toString());
      staff_phone_infField.setText(staffTable.getValueAt(selectedRow, 4).toString());
    }
  }

  private void handleChangeButton() {
    String staffId = staff_id_infField.getText();
    String staffName = staff_name_infField.getText();
    String staffPhone = staff_phone_infField.getText();
    if (staffId.isEmpty()) {
      JOptionPane.showMessageDialog(frame, "Please select a staff member to update.");
      return;
    }
    AccountDTO accountDTO = new AccountDTO(Integer.parseInt(staffId), staffName, staffPhone);
    ResponseDTO responseDTO = accountBLL.updateAccount(accountDTO);
    if (responseDTO.getSuccess()) {
      JOptionPane.showMessageDialog(frame, "Staff updated successfully!");
      handleFindButton();
    } else {
      JOptionPane.showMessageDialog(frame, "Failed to update staff: " + responseDTO.getMessage());
    }
  }

  private void handleChangePassword() {
    String staffId = staff_id_infField.getText();
    String newPassword = new String(new_passwordField.getPassword());
    String confirmPassword = new String(confirm_passwordField.getPassword());

    if (staffId.isEmpty()) {
      JOptionPane.showMessageDialog(frame, "Please select a staff member to change the password.");
      return;
    }

    if (newPassword.equals(confirmPassword)) {
      ResponseDTO responseDTO =
          accountBLL.changePassword(Integer.parseInt(staffId), newPassword, confirmPassword);
      if (responseDTO.getSuccess()) {
        JOptionPane.showMessageDialog(frame, "Password changed successfully!");
      } else {
        JOptionPane.showMessageDialog(
            frame, "Failed to change password: " + responseDTO.getMessage());
      }
    } else {
      JOptionPane.showMessageDialog(frame, "Passwords do not match!");
    }
  }

  private void handleDeleteButton() {
    int option =
        JOptionPane.showConfirmDialog(
            frame,
            "Are you sure you want to delete this staff member?\nThis action cannot be undone.",
            "Delete Staff",
            JOptionPane.YES_NO_OPTION);
    if (option == JOptionPane.NO_OPTION) {
      return;
    }
    String staffId = staff_id_infField.getText();
    if (!staffId.isEmpty()) {
      ResponseDTO responseDTO = accountBLL.deleteAccount(Integer.parseInt(staffId));
      if (responseDTO.getSuccess()) {
        JOptionPane.showMessageDialog(frame, "Staff deleted successfully!");
        handleFindButton();
      } else {
        JOptionPane.showMessageDialog(frame, "Failed to delete staff: " + responseDTO.getMessage());
      }
    } else {
      JOptionPane.showMessageDialog(frame, "Please select a staff member to delete.");
    }
  }
}
