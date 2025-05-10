import Config.GlobalExceptionHandler;
import Config.SeedData;

public class Main {
  public static void main(String[] args) {
    GlobalExceptionHandler.setup();
    new GUI.FoodPage();
    new SeedData();
  }
}
