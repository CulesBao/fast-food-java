package DAL;

import Config.DBHelper;
import DTO.AccountDTO;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDAL {
  public AccountDTO getAccountByUsername(String username) throws SQLException {
    ResultSet rs = DBHelper.executeQuery("SELECT * FROM accounts WHERE username = ?", username);
    if (rs.next()) {
      return new AccountDTO(
          rs.getInt("id"),
          rs.getString("username"),
          rs.getString("password"),
          rs.getString("fullName"),
          rs.getString("role"),
          rs.getTimestamp("createdAt"));
    }
    return null;
  }
}
