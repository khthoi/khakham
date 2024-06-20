package javaapplication1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class DataoutputModify {

    public static List<Dataoutput> hienthithanhtoan() {
        List<Dataoutput> daura = new ArrayList<>();
        Connection con = null;
        PreparedStatement state = null;
        ResultSet resultSet = null;

        try {
            // Kết nối với cơ sở dữ liệu
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel_management", "root", "123456");

            // Truy vấn SQL
            String sql = "SELECT * FROM data_output";
            state = con.prepareStatement(sql);
            resultSet = state.executeQuery();

            // Thêm dữ liệu vào danh sách
            while (resultSet.next()) {
                Dataoutput dout = new Dataoutput(
                        resultSet.getString("HoTen"),
                        resultSet.getString("PhoneNumber"),
                        resultSet.getString("checkin_date"),
                        resultSet.getString("checkout_date"),
                        resultSet.getInt("Roomnumber"),
                        resultSet.getString("SoCMND"),
                        resultSet.getInt("STT"),
                        resultSet.getInt("Thanhtien")
                );
                daura.add(dout);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatainputModify.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Đóng các tài nguyên
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (state != null) {
                    state.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DatainputModify.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return daura;
    }

    public static List<Dataoutput> timkiemkhachhang(String soCMND, String sdt, String hoTen, String ngayThuePhong) {
        List<Dataoutput> ketQua = new ArrayList<>();
        Connection con = null;
        PreparedStatement state = null;
        ResultSet resultSet = null;

        try {
            // Kết nối với cơ sở dữ liệu
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel_management", "root", "123456");

            // Tạo truy vấn SQL với các tiêu chí tìm kiếm
            String sql = "SELECT * FROM data_output WHERE 1=1";
            if (soCMND != null) {
                sql += " AND SoCMND = ?";
            }
            if (sdt != null && !sdt.isEmpty()) {
                sql += " AND PhoneNumber = ?";
            }
            if (hoTen != null && !hoTen.isEmpty()) {
                sql += " AND HoTen LIKE ? ";
            }
            if (ngayThuePhong != null && !ngayThuePhong.isEmpty()) {
                sql += " AND checkin_date = ?";
            }

            state = con.prepareStatement(sql);

            int paramIndex = 1; // Bắt đầu với tham số đầu tiên
            if (soCMND != null) {
                state.setString(paramIndex, soCMND);
            }
            if (sdt != null && !sdt.isEmpty()) {
                state.setString(paramIndex++, sdt);
            }
            if (hoTen != null && !hoTen.isEmpty()) {
                state.setString(paramIndex++, "%" + hoTen + "%"); // Sử dụng LIKE cho tìm kiếm một phần của tên
            }
            if (ngayThuePhong != null && !ngayThuePhong.isEmpty()) {
                state.setString(paramIndex++, ngayThuePhong);
            }
            resultSet = state.executeQuery();

            // Thêm dữ liệu vào danh sách kết quả
            while (resultSet.next()) {
                Dataoutput dout = new Dataoutput(
                        resultSet.getString("HoTen"),
                        resultSet.getString("PhoneNumber"),
                        resultSet.getString("checkin_date"),
                        resultSet.getString("checkout_date"),
                        resultSet.getInt("Roomnumber"),
                        resultSet.getString("SoCMND"),
                        resultSet.getInt("STT"),
                        resultSet.getInt("Thanhtien")
                );
                ketQua.add(dout);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatainputModify.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ketQua;
    }
}
