package CONFIG;

import java.sql.*;

public class DBHelper {

  private static final String URL = "jdbc:mysql://localhost:3306/fast_food_java";
  private static final String USER = "root";
  private static final String PASSWORD = "password";

  private static Connection connection;

  public static Connection getInstance() {
    if (connection == null) {
      try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(URL, USER, PASSWORD);
        System.out.println("Connected to MySQL!");
      } catch (SQLException | ClassNotFoundException e) {
        e.printStackTrace();
      }
    }
    return connection;
  }

  public static int executeUpdate(String sql, Object... params) {
    try {
      PreparedStatement stmt = getInstance().prepareStatement(sql);
      setParameters(stmt, params);
      return stmt.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return 0;
  }

  public static ResultSet executeQuery(String sql, Object... params) {
    try {
      PreparedStatement stmt = getInstance().prepareStatement(sql);
      setParameters(stmt, params);
      return stmt.executeQuery();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  private static void setParameters(PreparedStatement stmt, Object... params) throws SQLException {
    for (int i = 0; i < params.length; i++) {
      stmt.setObject(i + 1, params[i]);
    }
  }
}
