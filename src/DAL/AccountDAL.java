package DAL;

import Config.DBHelper;
import DTO.AccountDTO;
import DTO.FindAccountDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;

public class AccountDAL {
  public AccountDTO getAccountByUsername(String username) throws SQLException {
    ResultSet rs = DBHelper.executeQuery("SELECT * FROM accounts WHERE username = ?", username);
    if (rs.next()) {
      return new AccountDTO(
          rs.getInt("id"),
          rs.getString("username"),
          rs.getString("password"),
          rs.getString("fullName"),
          rs.getString("phoneNumber"),
          rs.getString("role"),
          rs.getTimestamp("createdAt"));
    }
    return null;
  }

  public void createAccount(AccountDTO account) throws SQLException {
    ResultSet rs =
        DBHelper.executeQuery("SELECT * FROM accounts WHERE username = ?", account.getUserName());
    if (rs.next()) {
      throw new SQLException("Username already exists");
    }
    String hashedPassword = BCrypt.hashpw(account.getPassWord(), BCrypt.gensalt());
    DBHelper.executeUpdate(
        "INSERT INTO accounts (username, password, fullName, phoneNumber, role) VALUES (?, ?, ?, ?, ?)",
        account.getUserName(),
        hashedPassword,
        account.getFullName(),
        account.getPhoneNumber(),
        account.getRole());
  }

  public List<FindAccountDTO> getAccountsByNameAndPhoneNumber(String name, String phoneNumber)
      throws SQLException {
    ResultSet rs =
        DBHelper.executeQuery(
            "SELECT * FROM accounts WHERE "
                + "(COALESCE(fullName, '') LIKE COALESCE(?, COALESCE(fullName, ''))) AND "
                + "(COALESCE(phoneNumber, '') LIKE COALESCE(?, COALESCE(phoneNumber, '')))",
            name == null || name.isEmpty() ? null : "%" + name + "%",
            phoneNumber == null || phoneNumber.isEmpty() ? null : "%" + phoneNumber + "%");
    List<FindAccountDTO> accounts = new ArrayList<>();
    while (rs.next()) {
      accounts.add(
          new FindAccountDTO(
              rs.getInt("id"),
              rs.getString("username"),
              rs.getString("fullName"),
              rs.getString("phoneNumber"),
              rs.getString("role"),
              rs.getTimestamp("createdAt")));
    }
    return accounts;
  }

  public AccountDTO getAccountById(int id) throws SQLException {
    ResultSet rs = DBHelper.executeQuery("SELECT * FROM accounts WHERE id = ?", id);
    if (rs.next()) {
      return new AccountDTO(
          rs.getInt("id"),
          rs.getString("username"),
          rs.getString("password"),
          rs.getString("fullName"),
          rs.getString("phoneNumber"),
          rs.getString("role"),
          rs.getTimestamp("createdAt"));
    }
    return null;
  }

  public void changePassword(int id, String newPassword) throws SQLException {
    String hashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());
    DBHelper.executeUpdate("UPDATE accounts SET password = ? WHERE id = ?", hashedPassword, id);
  }
  public void updateAccount(AccountDTO account) throws SQLException {
    DBHelper.executeUpdate(
        "UPDATE accounts SET fullName = ?, phoneNumber = ?, role = ? WHERE id = ?",
        account.getFullName(),
        account.getPhoneNumber(),
        account.getRole(),
        account.getId());
  }
}
