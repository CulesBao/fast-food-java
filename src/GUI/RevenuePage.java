package GUI;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class RevenuePage {
    private JPanel revenue_pagePanel;
    private JPanel start_dateChooser;
    private JPanel end_dateChooser;
    private JButton viewStatsButton;
    private JTable revenueTable;
    private JButton backButton;

    private JDateChooser startDateChooser;
    private JDateChooser endDateChooser;

    public RevenuePage() {
        JFrame frame = new JFrame("Revenue Page");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(800, 600));
        frame.setResizable(false);

        frame.add(revenue_pagePanel);

        startDateChooser = new JDateChooser();
        startDateChooser.setDateFormatString("dd/MM/yyyy");
        start_dateChooser.setLayout(new BorderLayout());
        start_dateChooser.add(startDateChooser, BorderLayout.CENTER);

        endDateChooser = new JDateChooser();
        endDateChooser.setDateFormatString("dd/MM/yyyy");
        end_dateChooser.setLayout(new BorderLayout());
        end_dateChooser.add(endDateChooser, BorderLayout.CENTER);

        String[] columnNames = {"Order ID", "Customer Name", "Date", "Total Amount"};
        Object[][] data = {};

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        revenueTable.setModel(model);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public java.util.Date getStartDate() {
        return startDateChooser.getDate();
    }

    public java.util.Date getEndDate() {
        return endDateChooser.getDate();
    }
}
