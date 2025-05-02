package DTO;

import java.sql.Timestamp;

public class AccountDTO {
  private int id;
  private String userName;
  private String passWord;
  private String fullName;
  private String role;
  private Timestamp createdAt;

  public AccountDTO(int id, String userName, String passWord, String fullName, String role, Timestamp createdAt) {
    this.id = id;
    this.userName = userName;
    this.passWord = passWord;
    this.fullName = fullName;
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

  public String getPassWord() {
    return passWord;
  }

  public void setPassWord(String passWord) {
    this.passWord = passWord;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
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
