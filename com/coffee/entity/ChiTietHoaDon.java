/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coffee.entity;

/**
 *
 * @author Admin
 */
public class ChiTietHoaDon {
    String MaHD;
    String MaSP;
    String TenSP;
    String TenBan;
    double Gia;
    boolean Size;
    int SoLuong;
    double ThanhTien;

    public ChiTietHoaDon() {
    }

    public ChiTietHoaDon(String MaHD, String MaSP, String TenSP, String TenBan, double Gia, boolean Size, int SoLuong, double ThanhTien) {
        this.MaHD = MaHD;
        this.MaSP = MaSP;
        this.TenSP = TenSP;
        this.TenBan = TenBan;
        this.Gia = Gia;
        this.Size = Size;
        this.SoLuong = SoLuong;
        this.ThanhTien = ThanhTien;

    }

    public String getMaHD() {
        return MaHD;
    }

    public void setMaHD(String MaHD) {
        this.MaHD = MaHD;
    }

    public String getMaSP() {
        return MaSP;
    }

    public void setMaSP(String MaSP) {
        this.MaSP = MaSP;
    }

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String TenSP) {
        this.TenSP = TenSP;
    }

    public String getTenBan() {
        return TenBan;
    }

    public void setTenBan(String TenBan) {
        this.TenBan = TenBan;
    }

    public double getGia() {
        return Gia;
    }

    public void setGia(double Gia) {
        this.Gia = Gia;
    }

    public boolean isSize() {
        return Size;
    }

    public void setSize(boolean Size) {
        this.Size = Size;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    public double getThanhTien() {
        return ThanhTien;
    }

    public void setThanhTien(double ThanhTien) {
        this.ThanhTien = ThanhTien;
    }
}
