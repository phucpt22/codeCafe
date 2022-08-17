/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coffee.entity;

import java.util.Date;


public class KhuyenMai {
    int MaKM;
    String TenKM;
    Date NgayKM;
    Date NgayHetKM;
    int MucGiamGia;
    String MoTa;

    public KhuyenMai() {
    }

    public KhuyenMai(int MaKM, String TenKM, Date NgayKM, Date NgayHetKM, int MucGiamGia, String MoTa) {
        this.MaKM = MaKM;
        this.TenKM = TenKM;
        this.NgayKM = NgayKM;
        this.NgayHetKM = NgayHetKM;
        this.MucGiamGia = MucGiamGia;
        this.MoTa = MoTa;
    }

    public int getMaKM() {
        return MaKM;
    }

    public void setMaKM(int MaKM) {
        this.MaKM = MaKM;
    }

    public String getTenKM() {
        return TenKM;
    }

    public void setTenKM(String TenKM) {
        this.TenKM = TenKM;
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

    public int getMucGiamGia() {
        return MucGiamGia;
    }

    public void setMucGiamGia(int MucGiamGia) {
        this.MucGiamGia = MucGiamGia;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String MoTa) {
        this.MoTa = MoTa;
    }
    
}
