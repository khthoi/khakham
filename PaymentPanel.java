package javaapplication1;

import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

public class PaymentPanel extends ServicePanel {

    private JTable table;
    private DefaultTableModel tableModel;
    private JButton searchButton;
    private JButton showAllButton;
    private JTextField cmndField;
    private JTextField sdtField;
    private JTextField hoTenField;
    private JDateChooser checkInDateChooser;

    public PaymentPanel() {
        setLayout(new BorderLayout());

        String[] columnNames = {"STT", "Họ Tên", "Số CMND", "SĐT", "Ngày thuê phòng", "Ngày trả phòng", "Số Phòng", "Thành tiền"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(800, 600));
        add(scrollPane, BorderLayout.CENTER);

        initializeComponents();

        addComponents();

        addEventHandlers();

        showBills();
    }

    private void initializeComponents() {
        cmndField = new JTextField(15);
        sdtField = new JTextField(15);
        hoTenField = new JTextField(15);

        checkInDateChooser = new JDateChooser();
        checkInDateChooser.setDateFormatString("yyyy-MM-dd");
        checkInDateChooser.setPreferredSize(new Dimension(151, 20));

        searchButton = new JButton("Tìm kiếm");
        showAllButton = new JButton("Hiển thị danh sách");
    }

    private void addComponents() {
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        searchPanel.add(new JLabel("Số CMND:"), gbc);

        gbc.gridx = 1;
        searchPanel.add(cmndField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        searchPanel.add(new JLabel("Số điện thoại:"), gbc);

        gbc.gridx = 1;
        searchPanel.add(sdtField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        searchPanel.add(new JLabel("Họ tên:"), gbc);

        gbc.gridx = 1;
        searchPanel.add(hoTenField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        searchPanel.add(new JLabel("Ngày thuê phòng:"), gbc);

        gbc.gridx = 1;
        searchPanel.add(checkInDateChooser, gbc);

        JPanel buttonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints buttonGbc = new GridBagConstraints();
        buttonGbc.insets = new Insets(5, 10, 5, 10);
        buttonGbc.ipadx = 15;
        buttonGbc.gridx = 0;
        buttonPanel.add(searchButton, buttonGbc);
        buttonGbc.gridx = 1;
        buttonPanel.add(showAllButton, buttonGbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        searchPanel.add(buttonPanel, gbc);

        add(searchPanel, BorderLayout.EAST);
    }

    private void addEventHandlers() {
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchBills();
            }
        });

        showAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showBills();
            }
        });
    }

    protected void showBills() {
        List<Dataoutput> lists = DataoutputModify.hienthithanhtoan();

        tableModel.setRowCount(0);

        for (Dataoutput data : lists) {
            tableModel.addRow(new Object[]{data.getSTT(),
                data.getHoTen(),
                data.getSoCMND(),
                data.getSDT(),
                data.getNgaythuephong(),
                data.getNgaytraphong(),
                data.getRoomNumber(),
                data.getThanhtien()});
        }
    }

    private void searchBills() {
        String soCMND = cmndField.getText();
        String sdt = sdtField.getText();
        String hoTen = hoTenField.getText();
        Date checkIn = checkInDateChooser.getDate();
        String convertedCheckIn = null;

        if (checkIn != null) {
            convertedCheckIn = convertToDatabaseFormat(checkIn);
        }

        List<Dataoutput> lists = DataoutputModify.timkiemkhachhang(
                soCMND.isEmpty() ? null : soCMND,
                sdt.isEmpty() ? null : sdt,
                hoTen.isEmpty() ? null : hoTen,
                convertedCheckIn
        );

        tableModel.setRowCount(0);
        for (Dataoutput data : lists) {
            tableModel.addRow(new Object[]{data.getSTT(),
                data.getHoTen(),
                data.getSoCMND(),
                data.getSDT(),
                data.getNgaythuephong(),
                data.getNgaytraphong(),
                data.getRoomNumber(),
                data.getThanhtien()});
        }
    }
}
