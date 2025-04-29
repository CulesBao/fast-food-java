package DTO;

import java.sql.Timestamp;

public class OrderDTO {
  private int id;
  private int staffId;
  private String status;
  private Timestamp createdAt;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getStaffId() {
    return staffId;
  }

  public void setStaffId(int staffId) {
    this.staffId = staffId;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Timestamp getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Timestamp createdAt) {
    this.createdAt = createdAt;
  }

  public boolean isValid() {
    return staffId > 0
        && status != null
        && (status.equals("PENDING") || status.equals("PAID") || status.equals("CANCEL"))
        && createdAt != null;
  }
}
