package DAL;

import Config.DBHelper;
import DTO.DetailOrderItem;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrderItemDAL {
  public List<DetailOrderItem> getOrderItemsByOrderId(int orderId) {
    ResultSet rs =
        DBHelper.executeQuery(
            "SELECT * FROM orderItems o JOIN foods f ON o.foodId = f.id WHERE o.orderId = ?", orderId);
    List<DetailOrderItem> orderItems = new ArrayList<>();
    try {
      while (rs.next()) {
        orderItems.add(
            new DetailOrderItem(
                rs.getInt("o.id"),
                rs.getString("f.name"),
                rs.getInt("o.quantity"),
                rs.getDouble("f.price"),
                rs.getDouble("o.quantity") * rs.getDouble("f.price")));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return orderItems;
  }
  public void addDetailOrderItem(int orderId, int foodId, int quantity) {
    DBHelper.executeUpdate(
        "INSERT INTO orderItems (orderId, foodId, quantity) VALUES (?, ?, ?)",
        orderId,
        foodId,
        quantity);
  }
}
