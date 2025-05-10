package DAL;

import Config.DBHelper;
import DTO.FindDetailOrder;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OrderDAL {
  public int createNewOrder(int staffId, String customerName, String customerPhonenumber)
      throws SQLException {
    if (customerName.isEmpty()) customerName = "Unknown";
    if (customerPhonenumber.isEmpty()) customerPhonenumber = "Unknown";
    DBHelper.executeUpdate(
        "INSERT INTO orders (staffId, customerName, customerPhoneNumber) VALUES (?, ?, ?)",
        staffId,
        customerName,
        customerPhonenumber);
    ResultSet rs =
        DBHelper.executeQuery(
            "SELECT id FROM orders WHERE staffId = ? AND customerName = ? AND customerPhoneNumber = ? ORDER BY createdAt DESC LIMIT 1",
            staffId,
            customerName,
            customerPhonenumber);
    if (rs.next()) {
      return rs.getInt("id");
    } else {
      throw new SQLException("Failed to create new order");
    }
  }

  public List<FindDetailOrder> getDetailOrderByDateTime(
      int staffId, String startDate, String endDate) throws SQLException {
    ResultSet rs;
    if (staffId == 1) {
      rs =
          DBHelper.executeQuery(
              "SELECT o.id, s.fullName AS staffName, o.customerName, o.customerPhoneNumber, o.status,"
                  + "(SELECT SUM(f.price * oi.quantity) FROM orderItems oi JOIN foods f ON f.id = oi.foodId WHERE oi.orderId = o.id) AS totalPrice, "
                  + "o.createdAt FROM orders o JOIN accounts s ON o.staffId = s.id "
                  + "WHERE o.createdAt BETWEEN ? AND ? ORDER BY o.createdAt DESC",
              startDate,
              endDate);
    } else {
      rs =
          DBHelper.executeQuery(
              "SELECT o.id, s.fullName AS staffName, o.customerName, o.customerPhoneNumber, o.status,"
                  + "(SELECT SUM(f.price * oi.quantity) FROM orderItems oi JOIN foods f ON f.id = oi.foodId WHERE oi.orderId = o.id) AS totalPrice, "
                  + "o.createdAt FROM orders o JOIN accounts s ON o.staffId = s.id "
                  + "WHERE o.createdAt BETWEEN ? AND ? AND s.id = ? ORDER BY o.createdAt DESC",
              startDate,
              endDate,
              staffId);
    }
    List<FindDetailOrder> detailOrders = new java.util.ArrayList<>();
    while (rs.next()) {
      FindDetailOrder detailOrder = new FindDetailOrder();
      detailOrder.setId(rs.getInt("id"));
      detailOrder.setStaffName(rs.getString("staffName"));
      detailOrder.setCustomerName(rs.getString("customerName"));
      detailOrder.setCustomerPhoneNumber(rs.getString("customerPhoneNumber"));
      detailOrder.setStatus(rs.getString("status"));
      detailOrder.setTotalPrice(rs.getDouble("totalPrice"));
      detailOrder.setCreatedAt(rs.getTimestamp("createdAt"));
      detailOrders.add(detailOrder);
    }
    return detailOrders;
  }

  public void updateOrderStatus(int orderId, String status) throws SQLException {
    DBHelper.executeUpdate("UPDATE orders SET status = ? WHERE id = ?", status, orderId);
  }
}
