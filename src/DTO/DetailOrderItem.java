package DTO;

public class DetailOrderItem {
  private int id;
  private String foodName;
  private int quantity;
  private double price;
  private double totalPrice;

  public DetailOrderItem(int id, String foodName, int quantity, double price, double totalPrice) {
    this.id = id;
    this.foodName = foodName;
    this.quantity = quantity;
    this.price = price;
    this.totalPrice = totalPrice;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getFoodName() {
    return foodName;
  }

  public void setFoodName(String foodName) {
    this.foodName = foodName;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public double getTotalPrice() {
    return totalPrice;
  }

  public void setTotalPrice(double totalPrice) {
    this.totalPrice = price * quantity;
  }
}
