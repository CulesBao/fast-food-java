package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class OrderDetails {
    private JTable order_detailTable;
    private JTextField customer_nameField;
    private JTextField customer_phoneField;
    private JTextField totalField;
    private JTextField quantityField;
    private JPanel order_detailsPanel;

    public OrderDetails() {
        JFrame frame = new JFrame("Food Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(800, 600));
        frame.setResizable(false);

        frame.add(order_detailsPanel);

        String[] columnNames = {"Order ID", "FoodName", "Quantity", "Total Price"};
        Object[][] data = {};

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        order_detailTable.setModel(model);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
