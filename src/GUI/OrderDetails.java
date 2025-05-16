package GUI;

import BLL.OrderItemBLL;
import DTO.ResponseDTO;
import DTO.DetailOrderItem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class OrderDetails {
    private JTable order_detailTable;
    private JTextField customer_nameField;
    private JTextField customer_phoneField;
    private JTextField totalField;
    private JTextField quantityField;
    private JPanel order_detailsPanel;

    private static final OrderItemBLL orderItemBLL = new OrderItemBLL();

    public OrderDetails(int orderId, String customerName, String customerPhone) {
        JFrame frame = new JFrame("Order Details");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setPreferredSize(new Dimension(800, 600));
        frame.setResizable(false);
        frame.setContentPane(order_detailsPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        customer_nameField.setText(customerName);
        customer_phoneField.setText(customerPhone);

        String[] columnNames = {"Order Item Id", "Food Name", "Quantity", "Price", "Total Price"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        order_detailTable.setModel(model);

        ResponseDTO responseDTO = orderItemBLL.getOrderItemsByOrderId(orderId);
        if (responseDTO.getSuccess()) {
            @SuppressWarnings("unchecked")
            List<DetailOrderItem> orderDetails = (List<DetailOrderItem>) responseDTO.getData();

            double totalPrice = 0;
            int totalQuantity = 0;

            for (DetailOrderItem orderDetail : orderDetails) {
                Object[] row = {
                        orderDetail.getId(),
                        orderDetail.getFoodName(),
                        orderDetail.getQuantity(),
                        orderDetail.getPrice(),
                        orderDetail.getTotalPrice()
                };
                model.addRow(row);
                totalPrice += orderDetail.getTotalPrice();
                totalQuantity += orderDetail.getQuantity();
            }

            totalField.setText(String.valueOf(totalPrice));
            quantityField.setText(String.valueOf(totalQuantity));
        } else {
            JOptionPane.showMessageDialog(frame, responseDTO.getMessage());
        }
    }
}
