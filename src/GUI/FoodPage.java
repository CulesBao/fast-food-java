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
    private JComboBox food_typeComboBox;
    private JTextField priceField;
    private JButton add_foodButton;
    private JTable table1;
    private JFrame frame;


    public FoodPage(){

        frame = new JFrame("Food Page");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(800, 600));
        frame.setResizable(false);

        frame.add(food_pagePanel);

        String[] columnNames = {"Mã hàng", "Tên hàng", "Nhóm hàng", "Giá bán"};
        Object[][] data = {};

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        table1.setModel(model);


        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
