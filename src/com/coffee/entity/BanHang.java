/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coffee.entity;

public class BanHang {
    String MaSP;
    String TenSP;
    String TenBan;
    int SoLuong;
    boolean Size;
    String GhiChu;

    public BanHang() {
    }

    public BanHang(String MaSP, String TenSP, String TenBan, int SoLuong, boolean Size, String GhiChu) {
        this.MaSP = MaSP;
        this.TenSP = TenSP;
        this.TenBan = TenBan;
        this.SoLuong = SoLuong;
        this.Size = Size;
        this.GhiChu = GhiChu;
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

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    public boolean isSize() {
        return Size;
    }

    public void setSize(boolean Size) {
        this.Size = Size;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String GhiChu) {
        this.GhiChu = GhiChu;
    }
    
}
