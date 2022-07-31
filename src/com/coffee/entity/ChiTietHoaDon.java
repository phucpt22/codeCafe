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
    int MaHD;
    String TenSP;
    String TenBan;
    double Gia;
    boolean Size;
    int SoLuong;
    double TongTien;

    public ChiTietHoaDon() {
    }

    public ChiTietHoaDon(int MaHD, String TenSP, String TenBan, double Gia, boolean Size, int SoLuong, double TongTien) {
        this.MaHD = MaHD;
        this.TenSP = TenSP;
        this.TenBan = TenBan;
        this.Gia = Gia;
        this.Size = Size;
        this.SoLuong = SoLuong;
        this.TongTien = TongTien;
    }

    public int getMaHD() {
        return MaHD;
    }

    public void setMaHD(int MaHD) {
        this.MaHD = MaHD;
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

    public double getTongTien() {
        return TongTien;
    }

    public void setTongTien(double TongTien) {
        this.TongTien = TongTien;
    }
}
