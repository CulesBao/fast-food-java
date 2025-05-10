package DAL;

import Config.DBHelper;
import DTO.FoodDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FoodDAL {
  public List<FoodDTO> getFoodsByName(String name) throws SQLException {
    ResultSet rs =
        DBHelper.executeQuery(
            "SELECT * FROM foods WHERE COALESCE(name, '') LIKE COALESCE(?, COALESCE(name, ''))",
            name == null || name.isEmpty() ? null : "%" + name + "%");
    List<FoodDTO> foods = new ArrayList<>();
    while (rs.next()) {
      foods.add(
          new FoodDTO(
              rs.getInt("id"),
              rs.getString("name"),
              rs.getBigDecimal("price"),
              rs.getInt("quantity"),
              rs.getTimestamp("createdAt")));
    }
    return foods;
  }

  public void createFood(FoodDTO food) throws SQLException {
    DBHelper.executeUpdate(
        "INSERT INTO foods (name, price, quantity) VALUES (?, ?, ?)",
        food.getName(),
        food.getPrice(),
        food.getQuantity());
  }

  public void updateFood(FoodDTO food) throws SQLException {
    DBHelper.executeUpdate(
        "UPDATE foods SET name = ?, price = ?, quantity = ? WHERE id = ?",
        food.getName(),
        food.getPrice(),
        food.getQuantity(),
        food.getId());
  }

  public void updateQuantityByTransactionId(int foodId, int quantity) throws SQLException {
    DBHelper.executeUpdate(
        "UPDATE foods SET quantity = quantity - ? WHERE id = ?", quantity, foodId);
  }

  public void deleteFood(int foodId) throws SQLException {
    DBHelper.executeUpdate("DELETE FROM foods WHERE id = ?", foodId);
  }
}
