package BLL;

import Config.Session;
import DAL.AccountDAL;
import DTO.AccountDTO;
import DTO.LoginFormDTO;
import DTO.ResponseDTO;
import org.mindrot.jbcrypt.BCrypt;

public class AccountBLL {
  private final AccountDAL accountDAL = new AccountDAL();

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
        return new ResponseDTO(true, "Login successful", accountDTO);
      } else {
        return new ResponseDTO(false, "Wrong username or password", null);
      }
    } catch (Exception ex) {
      return new ResponseDTO(false, "Database error: " + ex.getMessage(), null);
    }
  }
}
