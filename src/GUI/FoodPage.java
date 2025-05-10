package GUI;


import BLL.FoodBLL;
import DTO.FoodDTO;
import DTO.ResponseDTO;
import java.awt.*;
import java.math.BigDecimal;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class FoodPage {
    private final FoodBLL foodBLL = new FoodBLL();
    private JPanel food_pagePanel;
    private JButton backButton;
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
        frame.setPreferredSize(new Dimension(1200, 600));
        frame.setResizable(false);

        frame.add(food_pagePanel);

        String[] columnNames = {"Food ID", "Name", "Quantity", "Price", "Created At"};
        Object[][] data = {};

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        foodTable.setModel(model);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        backButton.addActionListener(e -> handleBackButton());
        add_foodButton.addActionListener(e -> handleAddButton());
        findButton.addActionListener(e -> handleFindButton());
        foodTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                handleChangeRow();
            }
        });
        confirmButton.addActionListener(e -> handleConfirmButton());
    }
    public void handleBackButton() {
        backButton.addActionListener(e -> {
            frame.dispose();
            new AdminDashboard();
        });
    }
    public void handleAddButton(){
        String name = food_nameField.getText();
        String price = food_priceField.getText();
        String quantity = food_quantityField.getText();

        FoodDTO foodDTO = new FoodDTO(name, new BigDecimal(price), Integer.parseInt(quantity));
        ResponseDTO responseDTO = foodBLL.addNewFood(foodDTO);
        if (responseDTO.getSuccess()) {
            JOptionPane.showMessageDialog(frame, "Food added successfully!");
            handleFindButton();
            food_nameField.setText("");
            food_priceField.setText("");
            food_quantityField.setText("");
        } else {
            JOptionPane.showMessageDialog(frame, responseDTO.getMessage());
        }
    }
    public void handleFindButton() {
        String name = food_name_findField.getText();
        ResponseDTO responseDTO = foodBLL.getFoodsByName(name);
        if (responseDTO.getSuccess()) {
            DefaultTableModel model = (DefaultTableModel) foodTable.getModel();
            model.setRowCount(0);
            @SuppressWarnings("unchecked")
            java.util.List<FoodDTO> foodList = (java.util.List<FoodDTO>) responseDTO.getData();
            for (FoodDTO food : foodList) {
                model.addRow(new Object[]{
                        food.getId(),
                        food.getName(),
                        food.getQuantity(),
                        food.getPrice(),
                        food.getCreatedAt()
                });
            }
        } else {
            JOptionPane.showMessageDialog(frame, responseDTO.getMessage());
        }
    }
    public void handleChangeRow() {
        int selectedRow = foodTable.getSelectedRow();
        if (selectedRow != -1) {
            food_id_infField.setText(foodTable.getValueAt(selectedRow, 0).toString());
            food_name_infField.setText(foodTable.getValueAt(selectedRow, 1).toString());
            food_quantity_infField.setText(foodTable.getValueAt(selectedRow, 2).toString());
            food_price_infField.setText(foodTable.getValueAt(selectedRow, 3).toString());
        }
    }
    public void handleConfirmButton(){
        int id = Integer.parseInt(food_id_infField.getText());
        String name = food_name_infField.getText();
        String price = food_price_infField.getText();
        String quantity = food_quantity_infField.getText();

        FoodDTO foodDTO = new FoodDTO(id, name, new BigDecimal(price), Integer.parseInt(quantity));
        ResponseDTO responseDTO = foodBLL.updateFood(foodDTO);
        if (responseDTO.getSuccess()) {
            JOptionPane.showMessageDialog(frame, "Food updated successfully!");
            handleFindButton();
        } else {
            JOptionPane.showMessageDialog(frame, responseDTO.getMessage());
        }
    }
}
