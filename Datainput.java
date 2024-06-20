/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication1;

/**
 *
 * @author Admin
 */
public class Datainput {

    String Hoten, Diachi, Thanhpho, Quocgia, Ngaythuephong, Ngaytraphong, SDT, Loaiphong, SoCMND;
    int Sophong;

    public Datainput() {
    }

    public Datainput(String Hoten, String Diachi, String Thanhpho, String Quocgia, String Ngaythuephong, String Ngaytraphong, String SDT, String Loaiphong, String SoCMND, int Sophong) {
        this.Hoten = Hoten;
        this.Diachi = Diachi;
        this.Thanhpho = Thanhpho;
        this.Quocgia = Quocgia;
        this.Ngaythuephong = Ngaythuephong;
        this.Ngaytraphong = Ngaytraphong;
        this.SoCMND = SoCMND;
        this.Loaiphong = Loaiphong;
        this.Sophong = Sophong;
        this.SDT = SDT;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getHoten() {
        return Hoten;
    }

    public void setHoten(String Hoten) {
        this.Hoten = Hoten;
    }

    public String getDiachi() {
        return Diachi;
    }

    public void setDiachi(String Diachi) {
        this.Diachi = Diachi;
    }

    public String getThanhpho() {
        return Thanhpho;
    }

    public void setThanhpho(String Thanhpho) {
        this.Thanhpho = Thanhpho;
    }

    public String getQuocgia() {
        return Quocgia;
    }

    public void setQuocgia(String Quocgia) {
        this.Quocgia = Quocgia;
    }

    public String getNgaythuephong() {
        return Ngaythuephong;
    }

    public void setNgaythuephong(String Ngaythuephong) {
        this.Ngaythuephong = Ngaythuephong;
    }

    public String getNgaytraphong() {
        return Ngaytraphong;
    }

    public void setNgaytraphong(String Ngaytraphong) {
        this.Ngaytraphong = Ngaytraphong;
    }

    public String getSoCMND() {
        return SoCMND;
    }

    public void setSoCMND(String SoCMND) {
        this.SoCMND = SoCMND;
    }

    public String getLoaiphong() {
        return Loaiphong;
    }

    public void setLoaiphong(String Loaiphong) {
        this.Loaiphong = Loaiphong;
    }

    public int getSophong() {
        return Sophong;
    }

    public void setSophong(int Sophong) {
        this.Sophong = Sophong;
    }
}
