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

public class DatainputModify {

    public static List<Datainput> findAll() {
        List<Datainput> dauvao = new ArrayList<>();
        Connection con = null;
        PreparedStatement state = null;
        ResultSet resultSet = null;

        try {
            // Kết nối với cơ sở dữ liệu
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel_management", "root", "123456");

            // Truy vấn SQL
            String sql = "SELECT * FROM data_input";
            state = con.prepareStatement(sql);
            resultSet = state.executeQuery();

            // Thêm dữ liệu vào danh sách
            while (resultSet.next()) {
                Datainput din = new Datainput(
                        resultSet.getString("Hoten"),
                        resultSet.getString("Address"),
                        resultSet.getString("City"),
                        resultSet.getString("Country"),
                        resultSet.getString("checkin_date"),
                        resultSet.getString("checkout_date"),
                        resultSet.getString("PhoneNumber"),
                        resultSet.getString("roomtype"),
                        resultSet.getString("CustomerID"),
                        resultSet.getInt("Roomnumber")
                );
                dauvao.add(din);
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

        return dauvao;
    }

    public static List<String> getRentedrooms() {
        List<String> rentedRooms = new ArrayList<>();
        Connection con = null;
        PreparedStatement state = null;
        ResultSet resultSet = null;

        try {
            // Kết nối với cơ sở dữ liệu
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel_management", "root", "123456");

            // Truy vấn SQL để lấy danh sách các phòng đã được sử dụng
            String sql = "SELECT DISTINCT roomNumber FROM data_input"; //DISTINCT để loại bỏ các giá trị trùng lặp
            state = con.prepareStatement(sql);
            resultSet = state.executeQuery();

            // Thêm các phòng đã được sử dụng vào danh sách
            while (resultSet.next()) {
                String roomNumber = resultSet.getString("roomNumber");
                rentedRooms.add(roomNumber);
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

        return rentedRooms;
    }

    public static void insert(Datainput din) {
        Connection con = null;
        PreparedStatement state = null;

        try {
            // Kết nối với cơ sở dữ liệu
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel_management", "root", "123456");

            // Truy vấn SQL
            String sql = "INSERT INTO data_input (Hoten, Address, City, Country, checkin_date, checkout_date, PhoneNumber, CustomerID, roomtype, Roomnumber) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            state = con.prepareStatement(sql);

            state.setString(1, din.getHoten());
            state.setString(2, din.getDiachi());
            state.setString(3, din.getThanhpho());
            state.setString(4, din.getQuocgia());
            state.setString(5, din.getNgaythuephong());
            state.setString(6, din.getNgaytraphong());
            state.setString(7, din.getSDT());
            state.setString(8, din.getSoCMND());
            state.setString(9, din.getLoaiphong());
            state.setInt(10, din.getSophong());

            state.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DatainputModify.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Đóng các tài nguyên
            try {
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
    }

    public static void delete(String id) {
        Connection con = null;
        PreparedStatement state = null;

        try {
            // Kết nối với cơ sở dữ liệu
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel_management", "root", "123456");

            // Truy vấn SQL
            String sql = "DELETE FROM data_input WHERE CustomerID = ?";
            state = con.prepareStatement(sql);

            state.setString(1, id);

            state.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DatainputModify.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Đóng các tài nguyên
            try {
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
    }

    public static void thanhtoan(String customerID) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel_management", "root", "123456");
            String sql = "SELECT TransferCustomerData(?)";
            stmt = conn.prepareCall(sql);

            // Thiết lập tham số đầu vào
            stmt.setString(1, customerID);

            // Thực thi thủ tục lưu trữ
            stmt.execute();

            System.out.println("Data transferred successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
