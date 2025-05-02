package DTO;

public class LoginFormDTO {
  private String username;
  private String password;

  public LoginFormDTO(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }
}
