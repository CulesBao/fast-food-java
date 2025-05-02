package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class StaffPage {
    private JPanel staff_pagePanel;
    private JButton backButton;
    private JButton refreshButton;
    private JTextField staff_idField;
    private JTextField food_nameField;
    private JButton add_staffButton;
    private JTable staffTable;
    private JLabel staff_nameField;
    private JTextField phone_numberField;

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
