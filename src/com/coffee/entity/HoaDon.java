/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coffee.entity;

import java.util.Date;


public class HoaDon {
    String MaHD;
    String MaNV;
    String TenBan;
    String MaSP;
    double TongTien;
    Date NgayTao;
    String TenKH;

    public HoaDon() {
    }

    public HoaDon(String MaHD, String MaNV, String TenBan, String MaSP, double TongTien, Date NgayTao, String TenKH) {
        this.MaHD = MaHD;
        this.MaNV = MaNV;
        this.TenBan = TenBan;
        this.MaSP = MaSP;
        this.TongTien = TongTien;
        this.NgayTao = NgayTao;
        this.TenKH = TenKH;
    }

    public String getMaHD() {
        return MaHD;
    }

    public void setMaHD(String MaHD) {
        this.MaHD = MaHD;
    }

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }

    public String getTenBan() {
        return TenBan;
    }

    public void setTenBan(String TenBan) {
        this.TenBan = TenBan;
    }

    public String getMaSP() {
        return MaSP;
    }

    public void setMaSP(String MaSP) {
        this.MaSP = MaSP;
    }

    public double getTongTien() {
        return TongTien;
    }

    public void setTongTien(double TongTien) {
        this.TongTien = TongTien;
    }

    public Date getNgayTao() {
        return NgayTao;
    }

    public void setNgayTao(Date NgayTao) {
        this.NgayTao = NgayTao;
    }

    public String getTenKH() {
        return TenKH;
    }

    public void setTenKH(String TenKH) {
        this.TenKH = TenKH;
    }
    
}
