package GUI;

import BLL.OrderBLL;
import Config.Session;
import DTO.ResponseDTO;
import com.toedter.calendar.JDateChooser;
import java.awt.*;
import java.util.Calendar;
import java.util.Date;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class RevenuePage {
  private JPanel revenue_pagePanel;
  private JPanel start_dateChooser;
  private JPanel end_dateChooser;
  private JButton viewStatsButton;
  private JTable revenueTable;
  private JButton backButton;
  private JTextField order_id_infField;
  private JTextField customer_name_infField;
  private JTextField customer_phone_infField;
  private JComboBox status_comboBox;
  private JButton updateButton;
  private JButton detailButton;
  private JButton deleteButton;

  private JDateChooser startDateChooser;
  private JDateChooser endDateChooser;

  private JFrame frame = new JFrame("Revenue Page");
  private OrderBLL orderBLL = new OrderBLL();

  public RevenuePage() {
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setPreferredSize(new Dimension(1200, 600));
    frame.setResizable(false);
    status_comboBox.addItem("CANCEL");
    status_comboBox.addItem("PENDING");
    status_comboBox.addItem("PAID");
    frame.add(revenue_pagePanel);

    startDateChooser = new JDateChooser();
    startDateChooser.setDateFormatString("dd/MM/yyyy");
    start_dateChooser.setLayout(new BorderLayout());
    start_dateChooser.add(startDateChooser, BorderLayout.CENTER);

    endDateChooser = new JDateChooser();
    endDateChooser.setDateFormatString("dd/MM/yyyy");
    end_dateChooser.setLayout(new BorderLayout());
    end_dateChooser.add(endDateChooser, BorderLayout.CENTER);

    String[] columnNames = {
      "Order ID",
      "Staff Name",
      "Customer Name",
      "Customer Phone Number",
      "Status",
      "Total Price",
      "Date"
    };
    Object[][] data = {};

    DefaultTableModel model = new DefaultTableModel(data, columnNames);
    revenueTable.setModel(model);

    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);

    backButton.addActionListener(e -> handleBackButton());
    viewStatsButton.addActionListener(e -> handleViewStatsButton());
    revenueTable.getSelectionModel().addListSelectionListener(e -> handleRowSelection());
    updateButton.addActionListener(e -> handleUpdateButton());
  }

  public java.util.Date getStartDate() {
    return startDateChooser.getDate();
  }

  private java.util.Date getEndDate() {
    Date endDate = endDateChooser.getDate();
    if (endDate != null) {
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(endDate);
      calendar.add(Calendar.DAY_OF_MONTH, 1);
      return calendar.getTime();
    }
    return null;
  }

  private void handleBackButton() {
    frame.dispose();
    if (Session.getAccountId() == 1) new GUI.AdminDashboard();
    else new GUI.StaffDashboard();
  }

  private void handleViewStatsButton() {
    Date startDate = getStartDate();
    Date endDate = getEndDate();
    if (startDate == null || endDate == null) {
      JOptionPane.showMessageDialog(frame, "Please select a valid date range.");
      return;
    }

    String startDateString = new java.text.SimpleDateFormat("yyyy-MM-dd").format(startDate);
    String endDateString = new java.text.SimpleDateFormat("yyyy-MM-dd").format(endDate);
    ResponseDTO responseDTO =
        orderBLL.getDetailOrderByDateTime(Session.getAccountId(), startDateString, endDateString);

    if (responseDTO.getSuccess()) {
      DefaultTableModel model = (DefaultTableModel) revenueTable.getModel();
      model.setRowCount(0);

      @SuppressWarnings("unchecked")
      java.util.List<DTO.FindDetailOrder> detailOrders =
          (java.util.List<DTO.FindDetailOrder>) responseDTO.getData();

      for (DTO.FindDetailOrder detailOrder : detailOrders) {
        Object[] row = {
          detailOrder.getId(),
          detailOrder.getStaffName(),
          detailOrder.getCustomerName(),
          detailOrder.getCustomerPhoneNumber(),
          detailOrder.getStatus(),
          detailOrder.getTotalPrice(),
          new java.text.SimpleDateFormat("dd/MM/yyyy").format(detailOrder.getCreatedAt())
        };
        model.addRow(row);
      }
    } else {
      JOptionPane.showMessageDialog(frame, responseDTO.getMessage());
    }
  }

  private void handleRowSelection() {
    int selectedRow = revenueTable.getSelectedRow();
    if (selectedRow != -1) {
      DefaultTableModel model = (DefaultTableModel) revenueTable.getModel();
      order_id_infField.setText(model.getValueAt(selectedRow, 0).toString());
      customer_name_infField.setText(model.getValueAt(selectedRow, 2).toString());
      customer_phone_infField.setText(model.getValueAt(selectedRow, 3).toString());
      status_comboBox.setSelectedItem(model.getValueAt(selectedRow, 4).toString());
    }
  }
  private void handleUpdateButton(){
    String orderId = order_id_infField.getText();
    String status = status_comboBox.getSelectedItem().toString();
    if (orderId.isEmpty()) {
      JOptionPane.showMessageDialog(frame, "Please select an order to update.");
      return;
    }
    ResponseDTO responseDTO = orderBLL.updateOrderStatus(Integer.parseInt(orderId), status);
    if (responseDTO.getSuccess()) {
      JOptionPane.showMessageDialog(frame, "Update order status successfully.");
      handleViewStatsButton();
    } else {
      JOptionPane.showMessageDialog(frame, responseDTO.getMessage());
    }
  }
}
