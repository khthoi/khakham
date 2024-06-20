package javaapplication1;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuExample extends JFrame {

    private ServicePanel servicePanel;
    private PaymentPanel paymentPanel;

    public MenuExample() {
        super("Booking Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1600, 800);

        servicePanel = new ServicePanel();
        paymentPanel = new PaymentPanel();

        createMenuBar();
        add(servicePanel);  // mặc định sẽ để bảng dịch vụ được xuất hiện 
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Options");

        JMenuItem serviceMenuItem = new JMenuItem("Bảng dịch vụ");
        JMenuItem paymentMenuItem = new JMenuItem("Bảng thanh toán");

        serviceMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showServicePanel();
            }
        });

        paymentMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPaymentPanel();
            }
        });

        menu.add(serviceMenuItem);
        menu.add(paymentMenuItem);
        menuBar.add(menu);
        setJMenuBar(menuBar);
    }

    private void showServicePanel() {
        getContentPane().removeAll();
        getContentPane().add(servicePanel);
        revalidate();
        repaint();
    }

    // cài đặt cho phương thức này để gọi được ở nút thanh toán ở bảng Service
    public PaymentPanel getPaymentPanel() {
        return paymentPanel;
    }

    private void showPaymentPanel() {
        getContentPane().removeAll();
        getContentPane().add(paymentPanel);
        revalidate();
        repaint();
    }

    public void showWindow() {
        setLocationRelativeTo(null); // Đảm bảo rằng cửa sổ được đặt ở giữa màn hình mỗi khi hiển thị
        setVisible(true);
    }
}
