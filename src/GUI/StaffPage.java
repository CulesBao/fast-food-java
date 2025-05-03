package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class StaffPage {
    private JPanel staff_pagePanel;
    private JButton backButton;
    private JButton refreshButton;
    private JTextField staff_idField;
    private JTextField staff_nameField;
    private JButton add_staffButton;
    private JTable staffTable;
    private JTextField staff_phoneField;
    private JTextField staff_id_infField;
    private JTextField staff_name_infField;
    private JTextField staff_username_infField;
    private JTextField staff_password_infField;
    private JTextField staff_phone_infField;
    private JButton confirmButton;
    private JButton lockButton;
    private JButton deleteButton;

    private JFrame frame;

    public StaffPage(){
        frame = new JFrame("Staff Page");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(800, 600));
        frame.setResizable(false);

        frame.add(staff_pagePanel);

        String[] columnNames = {"Staff ID", "Name", "Phone number"};
        Object[][] data = {};

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        staffTable.setModel(model);


        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
