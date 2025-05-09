package DTO;

import java.sql.Timestamp;

public class FindAccountDTO {
  private int id;
  private String userName;
  private String fullName;
  private String phoneNumber;
  private String role;
  private Timestamp createdAt;

  public FindAccountDTO(
      int id,
      String userName,
      String fullName,
      String phoneNumber,
      String role,
      Timestamp createdAt) {
    this.id = id;
    this.userName = userName;
    this.fullName = fullName;
    this.phoneNumber = phoneNumber;
    this.role = role;
    this.createdAt = createdAt;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public Timestamp getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Timestamp createdAt) {
    this.createdAt = createdAt;
  }
}
