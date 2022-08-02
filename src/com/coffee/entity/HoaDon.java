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
    String MaNV;
    Date NgayTao = XDate.now();
    String gioDat;

    public HoaDon(int MaHD, String TenKH, String MaNV, String gioDat) {
        this.MaHD = MaHD;
        this.TenKH = TenKH;
        this.MaNV = MaNV;
        this.gioDat = gioDat;
    }

    public HoaDon() {
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

    public String getGioDat() {
        return gioDat;
    }

    public void setGioDat(String gioDat) {
        this.gioDat = gioDat;
    }

    

    @Override
    public String toString(){
        return gioDat;
    }

   
}
