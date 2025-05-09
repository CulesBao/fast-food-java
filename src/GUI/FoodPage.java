package GUI;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class FoodPage {
    private JPanel food_pagePanel;
    private JButton backButton;
    private JButton refreshButton;
    private JTextField food_idField;
    private JTextField food_nameField;
    private JTextField food_priceField;
    private JButton add_foodButton;
    private JTable foodTable;
    private JTextField food_id_infField;
    private JTextField food_name_infField;
    private JTextField food_quantity_infField;
    private JButton confirmButton;
    private JButton deleteButton;
    private JTextField food_price_infField;
    private JTextField food_quantityField;
    private JButton findButton;
    private JTextField food_name_findField;
    private JFrame frame;


    public FoodPage(){

        frame = new JFrame("Food Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(800, 600));
        frame.setResizable(false);

        frame.add(food_pagePanel);

        String[] columnNames = {"Food ID", "Name", "Quantity", "Price", "Created At"};
        Object[][] data = {};

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        foodTable.setModel(model);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
