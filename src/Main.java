import CONFIG.GlobalExceptionHandler;
import CONFIG.SeedData;

public class Main {
  public static void main(String[] args) {
    GlobalExceptionHandler.setup();
    new GUI.AdminDashboard();
    new SeedData();
  }
}
