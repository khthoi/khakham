/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication1;

/**
 *
 * @author Admin
 */
public class Dataoutput {

    String HoTen, SDT, Ngaythuephong, Ngaytraphong, SoCMND;
    int RoomNumber, STT, Thanhtien;

    public Dataoutput(String HoTen, String SDT, String Ngaythuephong, String Ngaytraphong, int RoomNumber, String SoCMND, int STT, int Thanhtien) {
        this.HoTen = HoTen;
        this.SDT = SDT;
        this.Ngaythuephong = Ngaythuephong;
        this.Ngaytraphong = Ngaytraphong;
        this.RoomNumber = RoomNumber;
        this.SoCMND = SoCMND;
        this.STT = STT;
        this.Thanhtien = Thanhtien;
    }

    public String getNgaythuephong() {
        return Ngaythuephong;
    }

    public String getNgaytraphong() {
        return Ngaytraphong;
    }

    public String getHoTen() {
        return HoTen;
    }

    public String getSDT() {
        return SDT;
    }

    public int getRoomNumber() {
        return RoomNumber;
    }

    public String getSoCMND() {
        return SoCMND;
    }

    public int getSTT() {
        return STT;
    }

    public int getThanhtien() {
        return Thanhtien;
    }
}
