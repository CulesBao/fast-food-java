package DTO;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class FoodDTO {
  private int id;
  private String name;
  private BigDecimal price;
  private boolean available;
  private Timestamp createdAt;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public boolean isAvailable() {
    return available;
  }

  public void setAvailable(boolean available) {
    this.available = available;
  }

  public Timestamp getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Timestamp createdAt) {
    this.createdAt = createdAt;
  }

  public boolean isValid() {
    return name != null
        && !name.isEmpty()
        && price != null
        && price.compareTo(BigDecimal.ZERO) > 0
        && createdAt != null;
  }
}
