import CONFIG.DBHelper;
import CONFIG.GlobalExceptionHandler;

public class Main {
  public static void main(String[] args) {
    GlobalExceptionHandler.setup();
    DBHelper.getInstance();
    new GUI.LoginForm();
  }
}
