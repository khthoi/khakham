package javaapplication1;

import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ServicePanel extends JPanel {

    private JLabel nameLabel = new JLabel("Họ Tên:");
    private JLabel idLabel = new JLabel("Số CMND:");
    private JLabel phoneLabel = new JLabel("SĐT:");
    private JLabel addressLabel = new JLabel("Địa chỉ:");
    private JLabel cityLabel = new JLabel("Thành phố:");
    private JLabel countryLabel = new JLabel("Quốc gia (Việt Nam):");
    private JLabel checkInLabel = new JLabel("Ngày thuê phòng:");
    private JLabel checkOutLabel = new JLabel("Ngày trả phòng:");
    private JLabel roomTypeLabel = new JLabel("Loại phòng thuê:");
    private JLabel roomNumberLabel = new JLabel("Số phòng thuê:");

    private JTextField nameTextField = new JTextField(20);
    private JTextField idTextField = new JTextField(20);
    private JTextField phoneTextField = new JTextField(20);
    private JTextField addressTextField = new JTextField(20);
    private JTextField cityTextField = new JTextField(20);
    private JTextField countryTextField = new JTextField(20);

    private JComboBox<String> roomTypeComboBox;
    private JComboBox<String> roomNumberComboBox;
    private JDateChooser checkInDateChooser;
    private JDateChooser checkOutDateChooser;

    private DefaultTableModel tableModel;
    private JTable table;

    private JButton addButton = new JButton("Thêm");
    private JButton deleteButton = new JButton("Xóa");
    private JButton PayButton = new JButton("Thanh Toán");

    public ServicePanel() {
        setLayout(new BorderLayout());

        initializeComponents();

        addEventHandlers();

        showCustomers();
    }

    private void initializeComponents() {
        // Thiết lập bộ định dạng ngày và các combo box
        checkInDateChooser = new JDateChooser();
        checkInDateChooser.setDateFormatString("yyyy-MM-dd");
        checkInDateChooser.setPreferredSize(new Dimension(200, 20));

        checkOutDateChooser = new JDateChooser();
        checkOutDateChooser.setDateFormatString("yyyy-MM-dd");
        checkOutDateChooser.setPreferredSize(new Dimension(200, 20));

        String[] roomTypes = {"Normal", "VIP"};
        roomTypeComboBox = new JComboBox<>(roomTypes);
        roomNumberComboBox = new JComboBox<>();

        // Tạo tiêu đề
        JPanel titlePanel = new JPanel();
        JLabel tieude = new JLabel("QUẢN LÝ KHÁCH SẠN");
        titlePanel.add(tieude);
        titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0)); // Thêm khoảng cách trên và dưới cho tiêu đề
        add(titlePanel, BorderLayout.NORTH);

        // Tạo bảng để hiển thị thông tin
        String[] columnNames = {"Họ Tên", "Số CMND", "SĐT", "Địa chỉ", "Thành phố", "Quốc gia", "Loại phòng thuê", "Số phòng thuê", "Ngày thuê phòng", "Ngày trả phòng"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(1000, 400));
        add(scrollPane, BorderLayout.WEST);

        // Tạo các panel và layout cho các thành phần
        JPanel pnMain = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        // Add components to panel
        addComponentsToPanel(pnMain, gbc);

        add(pnMain, BorderLayout.CENTER);
    }

    private void addComponentsToPanel(JPanel panel, GridBagConstraints gbc) {
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(nameLabel, gbc);
        gbc.gridx = 1;
        panel.add(nameTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(idLabel, gbc);
        gbc.gridx = 1;
        panel.add(idTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(phoneLabel, gbc);
        gbc.gridx = 1;
        panel.add(phoneTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(addressLabel, gbc);
        gbc.gridx = 1;
        panel.add(addressTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(cityLabel, gbc);
        gbc.gridx = 1;
        panel.add(cityTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(countryLabel, gbc);
        gbc.gridx = 1;
        panel.add(countryTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        panel.add(roomTypeLabel, gbc);
        gbc.gridx = 1;
        panel.add(roomTypeComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        panel.add(roomNumberLabel, gbc);
        gbc.gridx = 1;
        panel.add(roomNumberComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        panel.add(checkInLabel, gbc);
        gbc.gridx = 1;
        panel.add(checkInDateChooser, gbc);

        gbc.gridx = 0;
        gbc.gridy = 9;
        panel.add(checkOutLabel, gbc);
        gbc.gridx = 1;
        panel.add(checkOutDateChooser, gbc);

        JPanel buttonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints buttonGbc = new GridBagConstraints();
        buttonGbc.insets = new Insets(5, 10, 5, 10);
        buttonGbc.ipadx = 15;
        buttonGbc.gridx = 0;
        buttonPanel.add(addButton, buttonGbc);
        buttonGbc.gridx = 1;
        buttonPanel.add(deleteButton, buttonGbc);
        buttonGbc.gridx = 2;
        buttonPanel.add(PayButton, buttonGbc);

        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(buttonPanel, gbc);
    }

    /**
     *
     * @param date
     * @return
     */
    // Định dạng kiểu dữ liệu Date sang kiểu dữ liệu String
    protected String convertToDatabaseFormat(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }

    // Thiết lập sự kiện cho các nút bấm
    private void addEventHandlers() {
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addCustomer();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteCustomer();
            }
        });

        PayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PaymentforCustomer();
            }
        });

        countryTextField.setText("Việt Nam");
        roomTypeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateRoomNumberComboBox();
            }
        });
    }

    private void addCustomer() {
        // Logic to add customer
        String name = nameTextField.getText();
        String id = idTextField.getText();
        String phone = phoneTextField.getText();
        String address = addressTextField.getText();
        String city = cityTextField.getText();
        String country = countryTextField.getText();
        String roomType = (String) roomTypeComboBox.getSelectedItem();
        String roomNumber = (String) roomNumberComboBox.getSelectedItem();
        Date checkIn = checkInDateChooser.getDate();
        Date checkOut = checkOutDateChooser.getDate();

        String convertedCheckIn = convertToDatabaseFormat(checkIn);
        String convertedCheckOut = convertToDatabaseFormat(checkOut);

        Datainput newCustomer = new Datainput(name,
                address,
                city,
                country,
                convertedCheckIn,
                convertedCheckOut,
                phone,
                roomType,
                id,
                Integer.parseInt(roomNumber));
        DatainputModify.insert(newCustomer);
        showCustomers();

        nameTextField.setText("");
        idTextField.setText("");
        phoneTextField.setText("");
        addressTextField.setText("");
        cityTextField.setText("");
        checkInDateChooser.setDate(null);
        checkOutDateChooser.setDate(null);
        roomTypeComboBox.setSelectedIndex(0);
        roomNumberComboBox.removeAllItems();
        countryTextField.setText("Việt Nam");
    }

    private void deleteCustomer() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            String customerID = (String) table.getValueAt(selectedRow, 1);
            DatainputModify.delete(customerID);
            showCustomers();
        }
    }

    private void PaymentforCustomer() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            String customerID = (String) table.getValueAt(selectedRow, 1);
            DatainputModify.thanhtoan(customerID);
            deleteCustomer();
            JOptionPane.showMessageDialog(this, "Đã Thanh Toán!");
            showCustomers();
            updatePaymentPanel();
        }
    }

    public void updateRoomNumberComboBox() {
        String roomType = (String) roomTypeComboBox.getSelectedItem();
        roomNumberComboBox.removeAllItems();
        List<String> emptyRooms = DatainputModify.getRentedrooms();

        if ("Normal".equals(roomType)) {
            for (int i = 5; i <= 15; i++) {
                if (!emptyRooms.contains(String.valueOf(i))) {
                    roomNumberComboBox.addItem(String.valueOf(i));
                }
            }
        } else if ("VIP".equals(roomType)) {
            for (int i = 1; i <= 4; i++) {
                if (!emptyRooms.contains(String.valueOf(i))) {
                    roomNumberComboBox.addItem(String.valueOf(i));
                }
            }
        }
    }

    private void updatePaymentPanel() {
        // this ở đây là lớp chứa phương thức này (ServicePanel)
        MenuExample menuExample = (MenuExample) SwingUtilities.getWindowAncestor(this);
        PaymentPanel paymentPanel = menuExample.getPaymentPanel(); // trả về cửa sổ Payment hiện tại qua phương thức getPaymentPanel()
        paymentPanel.showBills();
    }

    private void showCustomers() {
        List<Datainput> lists = DatainputModify.findAll();
        tableModel.setRowCount(0);
        for (Datainput data : lists) {
            tableModel.addRow(new Object[]{data.getHoten(),
                data.getSoCMND(),
                data.getSDT(),
                data.getDiachi(),
                data.getThanhpho(),
                data.getQuocgia(),
                data.getLoaiphong(),
                data.getSophong(),
                data.getNgaythuephong(),
                data.getNgaytraphong()});
        }
    }
}
