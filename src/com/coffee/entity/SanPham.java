/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coffee.entity;

import java.util.Date;


public class SanPham {
    String TenSP;
    String LoaiSP;
    double Gia;
    Date NgayKM;
    Date NgayHetKM;
    String Hinh;

    public SanPham() {
    }

    public SanPham(String TenSP, String LoaiSP, double Gia, Date NgayKM, Date NgayHetKM, String Hinh) {
        this.TenSP = TenSP;
        this.LoaiSP = LoaiSP;
        this.Gia = Gia;
        this.NgayKM = NgayKM;
        this.NgayHetKM = NgayHetKM;
        this.Hinh = Hinh;
    }

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String TenSP) {
        this.TenSP = TenSP;
    }

    public String getLoaiSP() {
        return LoaiSP;
    }

    public void setLoaiSP(String LoaiSP) {
        this.LoaiSP = LoaiSP;
    }

    public double getGia() {
        return Gia;
    }

    public void setGia(double Gia) {
        this.Gia = Gia;
    }

    public Date getNgayKM() {
        return NgayKM;
    }

    public void setNgayKM(Date NgayKM) {
        this.NgayKM = NgayKM;
    }

    public Date getNgayHetKM() {
        return NgayHetKM;
    }

    public void setNgayHetKM(Date NgayHetKM) {
        this.NgayHetKM = NgayHetKM;
    }

    public String getHinh() {
        return Hinh;
    }

    public void setHinh(String Hinh) {
        this.Hinh = Hinh;
    }
    
    
}
