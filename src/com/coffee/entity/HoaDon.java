/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coffee.entity;


import com.coffee.utils.XDate;
import java.util.Date;

public class HoaDon {
    
    int MaHD;
    String TenKH;
    double TongTien;
    String MaNV;
    Date NgayTao = XDate.now();

    public HoaDon() {
    }

    public HoaDon(int MaHD, String TenKH, double TongTien, String MaNV) {
        this.MaHD = MaHD;
        this.TenKH = TenKH;
        this.TongTien = TongTien;
        this.MaNV = MaNV;
    }

    public int getMaHD() {
        return MaHD;
    }

    public void setMaHD(int MaHD) {
        this.MaHD = MaHD;
    }

    
    public String getTenKH() {
        return TenKH;
    }

    public void setTenKH(String TenKH) {
        this.TenKH = TenKH;
    }

    public double getTongTien() {
        return TongTien;
    }

    public void setTongTien(double TongTien) {
        this.TongTien = TongTien;
    }

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }

    public Date getNgayTao() {
        return NgayTao;
    }

    public void setNgayTao(Date NgayTao) {
        this.NgayTao = NgayTao;
    }
}
