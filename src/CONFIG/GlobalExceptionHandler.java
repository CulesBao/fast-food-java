package CONFIG;

import javax.swing.*;
import java.awt.*;

public class GlobalExceptionHandler implements Thread.UncaughtExceptionHandler{
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        e.printStackTrace();

        EventQueue.invokeLater(() -> {
            JOptionPane.showMessageDialog(
                    null,
                    "Error: " + e.getMessage(),
                    "System Error",
                    JOptionPane.ERROR_MESSAGE
            );
        });
    }

    public static void setup() {
        Thread.setDefaultUncaughtExceptionHandler(new GlobalExceptionHandler());
    }
}
