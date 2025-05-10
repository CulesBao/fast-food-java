package GUI;

import BLL.FoodBLL;
import BLL.OrderBLL;
import Config.Session;
import DTO.DetailOrderItem;
import DTO.FoodDTO;
import DTO.ResponseDTO;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class OrdersPage {
  private static FoodBLL foodBLL = new FoodBLL();
  private static OrderBLL orderBLL = new OrderBLL();
  private JFrame frame = new JFrame("Orders Page");
  private List<DetailOrderItem> detailOrderItems = new ArrayList<>();
  private JPanel orders_pagePanel;
  private JTable food_listTable;
  private JTextField nameField;
  private JTextField quantityField;
  private JButton add_foodButton;
  private JButton backButton;
  private JButton createNewButton;
  private JButton deleteItemButton;
  private JTextField amountField;
  private JTextField customer_nameField;
  private JTextField customer_phoneField;
  private JTable order_detailTable;
  private JTextField priceField;
  private JTextField totalField;
  private JButton findButton;
  private JTextField food_name_findField;
  private JTextField tbFoodId;

  public OrdersPage() {
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
    order_detailTable.setModel(model2);

    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
    findButton.addActionListener(e -> handleFindButton());
    backButton.addActionListener(e -> handleBackButton());
    food_listTable.getSelectionModel().addListSelectionListener(e -> handleChangeRow());
    add_foodButton.addActionListener(e -> handleAddItem());
    deleteItemButton.addActionListener(e -> handleDeleteItem());
    createNewButton.addActionListener(e -> handleCreateNewButton());
  }

  private void handleBackButton() {
    frame.dispose();
    new StaffDashboard();
  }

  private void handleFindButton() {
    String name = food_name_findField.getText();
    ResponseDTO responseDTO = foodBLL.getFoodsByName(name);
    if (responseDTO.getSuccess()) {
      DefaultTableModel model = (DefaultTableModel) food_listTable.getModel();
      model.setRowCount(0);
      @SuppressWarnings("unchecked")
      java.util.List<FoodDTO> foodList = (java.util.List<FoodDTO>) responseDTO.getData();
      for (FoodDTO food : foodList) {
        model.addRow(
            new Object[] {
              food.getId(), food.getName(), food.getPrice(), food.getQuantity(), food.getCreatedAt()
            });
      }
    } else {
      JOptionPane.showMessageDialog(frame, responseDTO.getMessage());
    }
  }

  private void handleChangeRow() {
    int selectedRow = food_listTable.getSelectedRow();
    if (selectedRow != -1) {
      DefaultTableModel model = (DefaultTableModel) food_listTable.getModel();
      tbFoodId.setText(model.getValueAt(selectedRow, 0).toString());
      nameField.setText(model.getValueAt(selectedRow, 1).toString());
      priceField.setText(model.getValueAt(selectedRow, 2).toString());
      quantityField.setText(model.getValueAt(selectedRow, 3).toString());
    }
  }

  private void handleAddItem() {
    String foodId = tbFoodId.getText();
    String name = nameField.getText();
    String price = priceField.getText();
    String amount = amountField.getText();

    if (foodId.isEmpty()) {
      JOptionPane.showMessageDialog(frame, "Please select a food item.");
      return;
    }
    if (amount.isEmpty()) {
      JOptionPane.showMessageDialog(frame, "Please enter the amount.");
      return;
    }
    if (Integer.parseInt(amount) <= 0) {
      JOptionPane.showMessageDialog(frame, "Amount must be greater than 0.");
      return;
    }
    if (Integer.parseInt(amount) > Integer.parseInt(quantityField.getText())) {
      int confirm =
          JOptionPane.showConfirmDialog(
              frame,
              "The amount is greater than the available quantity. Do you want to continue?",
              "Confirm",
              JOptionPane.YES_NO_OPTION);
      if (confirm == JOptionPane.NO_OPTION) return;
    }

    detailOrderItems.add(
        new DetailOrderItem(
            Integer.parseInt(foodId),
            name,
            Integer.parseInt(amount),
            Double.parseDouble(price),
            Integer.parseInt(amount) * Double.parseDouble(price)));

    DefaultTableModel model = (DefaultTableModel) order_detailTable.getModel();
    model.setRowCount(0);
    for (DetailOrderItem item : detailOrderItems) {
      model.addRow(
          new Object[] {
            item.getId(),
            item.getFoodName(),
            item.getPrice(),
            item.getQuantity(),
            item.getTotalPrice()
          });
    }
    int total = 0;
    for (DetailOrderItem item : detailOrderItems) {
      total += item.getTotalPrice();
    }
    totalField.setText(String.valueOf(total));
  }

  private void handleDeleteItem() {
    int selectedRow = order_detailTable.getSelectedRow();
    if (selectedRow != -1) {
      DefaultTableModel model = (DefaultTableModel) order_detailTable.getModel();
      detailOrderItems.remove(selectedRow);
      model.removeRow(selectedRow);
      int total = 0;
      for (DetailOrderItem item : detailOrderItems) {
        total += item.getTotalPrice();
      }
      totalField.setText(String.valueOf(total));
    } else {
      JOptionPane.showMessageDialog(frame, "Please select an item to delete.");
    }
  }

  private void handleCreateNewButton() {
    String customerName = customer_nameField.getText();
    String customerPhone = customer_phoneField.getText();
    ResponseDTO responseDTO =
        orderBLL.createNewOrder(
            Session.getAccountId(), customerName, customerPhone, detailOrderItems);
    JOptionPane.showMessageDialog(frame, responseDTO.getMessage());
    if (responseDTO.getSuccess()) {
      detailOrderItems.clear();
      handleFindButton();
      totalField.setText("");
      customer_nameField.setText("");
      customer_phoneField.setText("");
      tbFoodId.setText("");
      nameField.setText("");
      priceField.setText("");
      quantityField.setText("");
    }
  }
}
