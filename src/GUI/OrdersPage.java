package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class OrdersPage {
    private JPanel orders_pagePanel;
    private JTable ordersTable;
    private JTextField order_idField;
    private JTextField customer_nameField;
    private JComboBox order_statusComboBox;
    private JTextField customer_phoneField;
    private JButton add_foodButton;
    private JButton backButton;
    private JButton refreshButton;
    private JTextField order_id_infField;
    private JTextField customer_name_infField;
    private JComboBox rder_status_infComboBox;
    private JTextField customer_phone_infField;
    private JButton confirmButton;
    private JButton deleteButton;
    private JTextField totalField;
    private JTextField total_infField;

    public OrdersPage(){
        JFrame  frame = new JFrame("Orders Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(800, 600));
        frame.setResizable(false);

        frame.add(orders_pagePanel);
        String[] columnNames = {"Order ID", "Customer Name", "Status", "Phone", "Total"};
        Object[][] data = {};

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        ordersTable.setModel(model);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
