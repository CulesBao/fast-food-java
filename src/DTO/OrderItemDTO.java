package DTO;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class OrderItemDTO {
  private int id;
  private int orderId;
  private int foodId;
  private int quantity;
  private BigDecimal unitPrice;
  private Timestamp createdAt;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getOrderId() {
    return orderId;
  }

  public void setOrderId(int orderId) {
    this.orderId = orderId;
  }

  public int getFoodId() {
    return foodId;
  }

  public void setFoodId(int foodId) {
    this.foodId = foodId;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public BigDecimal getUnitPrice() {
    return unitPrice;
  }

  public void setUnitPrice(BigDecimal unitPrice) {
    this.unitPrice = unitPrice;
  }

  public Timestamp getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Timestamp createdAt) {
    this.createdAt = createdAt;
  }

  public boolean isValid() {
    return orderId > 0
        && foodId > 0
        && quantity > 0
        && unitPrice != null
        && unitPrice.compareTo(BigDecimal.ZERO) > 0
        && createdAt != null;
  }
}
