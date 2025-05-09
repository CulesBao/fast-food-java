package BLL;

import Config.Session;
import DAL.AccountDAL;
import DTO.AccountDTO;
import DTO.LoginFormDTO;
import DTO.ResponseDTO;
import java.sql.SQLException;
import org.mindrot.jbcrypt.BCrypt;

public class AccountBLL {
  private final AccountDAL accountDAL = new AccountDAL();

  private void checkPassword(String password, String confirmPassword) throws Exception {
    if (password.isEmpty() || confirmPassword.isEmpty()) {
      throw new Exception("Please enter both password and confirm password");
    }
    if (!password.equals(confirmPassword)) {
      throw new Exception("Password and confirm password do not match");
    }
    if (password.length() < 4) {
      throw new Exception("Password must be at least 4 characters");
    }
    if (password.length() > 20) {
      throw new Exception("Password must be less than 20 characters");
    }
    for (char c : password.toCharArray()) {
      if (Character.isWhitespace(c)) {
        throw new Exception("Password must not contain whitespace");
      }
    }
  }

  private void checkUsername(String username) throws Exception {
    if (username.isEmpty()) {
      throw new Exception("Please enter username");
    }
    if (username.length() < 4) {
      throw new Exception("Username must be at least 4 characters");
    }
    if (username.length() > 20) {
      throw new Exception("Username must be less than 20 characters");
    }
    for (char c : username.toCharArray()) {
      if (Character.isWhitespace(c)) {
        throw new Exception("Username must not contain whitespace");
      }
    }
  }

  public ResponseDTO authenticateUser(LoginFormDTO loginFormDTO) {
    if (loginFormDTO.getUsername().isEmpty() || loginFormDTO.getPassword().isEmpty()) {
      return new ResponseDTO(false, "Please enter both username and password", null);
    }

    try {
      AccountDTO accountDTO = accountDAL.getAccountByUsername(loginFormDTO.getUsername());
      if (accountDTO == null) {
        return new ResponseDTO(false, "Wrong username or password", null);
      }
      if (BCrypt.checkpw(loginFormDTO.getPassword(), accountDTO.getPassWord())) {
        Session.setSession(accountDTO.getId(), accountDTO.getFullName(), accountDTO.getRole());
        return new ResponseDTO(true, "Login successfully", accountDTO);
      } else {
        return new ResponseDTO(false, "Wrong username or password", null);
      }
    } catch (Exception ex) {
      return new ResponseDTO(false, "Database error: " + ex.getMessage(), null);
    }
  }

  public ResponseDTO registerAccount(AccountDTO accountDTO, String confirmPassword) {
    try {
      checkUsername(accountDTO.getUserName());
      checkPassword(accountDTO.getPassWord(), confirmPassword);
      accountDAL.createAccount(accountDTO);
      return new ResponseDTO(true, "Account created successfully", null);
    } catch (SQLException ex) {
      return new ResponseDTO(false, "Database error: " + ex.getMessage(), null);
    } catch (Exception ex) {
      return new ResponseDTO(false, ex.getMessage(), null);
    }
  }

  public ResponseDTO updateAccount(AccountDTO accountDTO) {
    try {
      accountDAL.updateAccount(accountDTO);
      return new ResponseDTO(true, "Account updated successfully", null);
    } catch (SQLException ex) {
      return new ResponseDTO(false, "Database error: " + ex.getMessage(), null);
    } catch (Exception ex) {
      return new ResponseDTO(false, ex.getMessage(), null);
    }
  }

  public ResponseDTO changePassword(int id, String newPassword, String confirmPassword) {
    try {
      checkPassword(newPassword, confirmPassword);
      accountDAL.changePassword(id, newPassword);
      return new ResponseDTO(true, "Password changed successfully", null);
    } catch (SQLException ex) {
      return new ResponseDTO(false, "Database error: " + ex.getMessage(), null);
    } catch (Exception ex) {
      return new ResponseDTO(false, ex.getMessage(), null);
    }
  }

  public ResponseDTO getAccountsByNameAndPhoneNumber(String name, String phoneNumber) {
    try {
      return new ResponseDTO(
          true,
          "Get accounts successfully",
          accountDAL.getAccountsByNameAndPhoneNumber(name, phoneNumber));
    } catch (SQLException ex) {
      return new ResponseDTO(false, "Database error: " + ex.getMessage(), null);
    } catch (Exception ex) {
      return new ResponseDTO(false, ex.getMessage(), null);
    }
  }
}
