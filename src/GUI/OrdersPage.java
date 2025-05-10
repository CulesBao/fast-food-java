package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class OrdersPage {
    private JPanel orders_pagePanel;
    private JTable food_listTable;
    private JTextField nameField;
    private JTextField quantityField;
    private JButton add_foodButton;
    private JButton backButton;
    private JButton confirmButton;
    private JButton deleteButton;
    private JTextField amountField;
    private JTextField customer_nameField;
    private JTextField customer_phoneField;
    private JTable order_detailTable;
    private JTextField priceField;
    private JTextField totalField;
    private JButton findButton;
    private JTextField food_name_findField;

    public OrdersPage(){
        JFrame  frame = new JFrame("Orders Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1200, 600));
        frame.setResizable(false);

        frame.add(orders_pagePanel);
        String[] column1Names = {" Food ID", "Food Name", "Price", "Quantity", "Created At"};
        Object[][] data1 = {};

        String[] column2Names = {"ID", "Name", "Price", "Quantity", "Total"};
        Object[][] data2 = {};

        DefaultTableModel model1 = new DefaultTableModel(data1, column1Names);
        food_listTable.setModel(model1);

        DefaultTableModel model2 = new DefaultTableModel(data2, column2Names);
        food_listTable.setModel(model2);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
