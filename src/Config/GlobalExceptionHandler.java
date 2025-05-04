package Config;

import Utils.CustomDialog;

public class GlobalExceptionHandler implements Thread.UncaughtExceptionHandler {

  public static void setup() {
    Thread.setDefaultUncaughtExceptionHandler(new GlobalExceptionHandler());
  }

  @Override
  public void uncaughtException(Thread t, Throwable e) {
    e.printStackTrace();
    CustomDialog.show(false, "Unexpected Error:  " + e.getMessage());
  }
}
