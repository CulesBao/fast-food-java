package DTO;

import java.sql.Timestamp;

public class AccountDTO {
  private int id;
  private String userName;
  private String passWord;
  private String fullName;
  private String role;
  private Timestamp createdAt;

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

  public boolean isValid() {
    return userName != null
        && !userName.isEmpty()
        && passWord != null
        && !passWord.isEmpty()
        && fullName != null
        && !fullName.isEmpty()
        && role != null
        && (role.equals("ADMIN") || role.equals("STAFF") || role.equals("ULTIMATE"));
  }
}
