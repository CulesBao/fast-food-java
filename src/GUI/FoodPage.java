package GUI;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class FoodPage {
    private JPanel food_pagePanel;
    private JButton backButton;
    private JButton refreshButton;
    private JTextField food_idField;
    private JTextField food_nameField;
    private JComboBox food_typeComboBox;
    private JTextField priceField;
    private JButton add_foodButton;
    private JTable foodTable;
    private JTextField food_id_infField;
    private JTextField food_name_infField;
    private JTextField food_quantity_infField;
    private JButton confirmButton;
    private JButton deleteButton;
    private JComboBox food_type_infComboBox;
    private JFrame frame;


    public FoodPage(){

        frame = new JFrame("Food Page");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.setResizable(false);

        frame.add(food_pagePanel);

        String[] columnNames = {"Food ID", "Name", "Type", "Price"};
        Object[][] data = {};

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        foodTable.setModel(model);


        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
