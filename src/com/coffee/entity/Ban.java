/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coffee.entity;


public class Ban {
    String TenBan;
    String GhiChu;

    public Ban() {
    }

    public Ban(String TenBan, String GhiChu) {
        this.TenBan = TenBan;
        this.GhiChu = GhiChu;
    }

    public String getTenBan() {
        return TenBan;
    }

    public void setTenBan(String TenBan) {
        this.TenBan = TenBan;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String GhiChu) {
        this.GhiChu = GhiChu;
    }
    @Override
    public String toString(){
        return this.getTenBan();
    }
}
