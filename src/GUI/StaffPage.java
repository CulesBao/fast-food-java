package GUI;

import BLL.AccountBLL;
import DTO.AccountDTO;
import DTO.FindAccountDTO;
import DTO.ResponseDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class StaffPage {
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

    private JFrame frame;
    private static AccountBLL accountBLL = new AccountBLL();

    public StaffPage(){
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
    }
    private void handleBackButton() {
        frame.dispose();
        new GUI.AdminDashboard();
    }
    private void handleAddButton() {
        String staffName = staff_nameField.getText();
        String staffUsername = staff_usernameField.getText();
        String staffPassword = new String(staff_passwordField.getPassword());
        String staffPhone = staff_phoneField.getText();

        AccountDTO accountDTO = new AccountDTO(staffName, staffUsername, staffPassword, staffPhone, "STAFF");
        ResponseDTO responseDTO = accountBLL.registerAccount(accountDTO);
        if (responseDTO.getSuccess()) {
            JOptionPane.showMessageDialog(frame, "Staff added successfully!");
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
            java.util.List<FindAccountDTO> accountList = (java.util.List<FindAccountDTO>) responseDTO.getData();
            for (FindAccountDTO account : accountList) {
                model.addRow(new Object[]{
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
}
