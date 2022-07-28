/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coffee.dao;

import com.coffee.entity.BanHang;
import com.coffee.utils.JdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class BanHangDAO extends CoffeeDAO<BanHang, String>{
    final String INSERT_SQL = "INSERT INTO BanHang (MaSP,TenBan,TenSP,Size,SoLuong,GhiChu,TongTien) VALUES (?,?,?,?,?,?)";
    final String UPDATE_SQL = "UPDATE BanHang SET TenBan=?,TenSP=?,Size=?,SoLuong=?,GhiChu=?, TongTien WHERE MaSP=?";
    final String DELETE_SQL = "DELETE FROM BanHang WHERE MaSP=?";
    final String SELECTALL_SQL = "SELECT * FROM BanHang";
    final String SELECTBYID_SQL = "SELECT * FROM BanHang WHERE MaSP=?";
    @Override
    public void insert(BanHang entity) {
        JdbcHelper.update(INSERT_SQL, entity.getMaSP(),entity.getTenBan(),entity.getTenSP(),entity.isSize(),entity.getSoLuong(),entity.getGhiChu(),entity.getTongTien());
    }

    @Override
    public void update(BanHang entity) {
        JdbcHelper.update(UPDATE_SQL, entity.getTenBan(),entity.getTenSP(),entity.isSize(),entity.getSoLuong(),entity.getGhiChu(),entity.getTongTien(),entity.getMaSP());    
    }

    @Override
    public void delete(String id) {
        JdbcHelper.update(DELETE_SQL,id);
    }

    @Override
    public List<BanHang> selectAll() {
        return selectBySql(SELECTALL_SQL);
    }

    @Override
    public BanHang selectById(String id) {
        List<BanHang> list = this.selectBySql(SELECTBYID_SQL, id);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<BanHang> selectBySql(String sql, Object... args) {
        List<BanHang> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while(rs.next()){
                BanHang entity = new BanHang();
                entity.setMaSP(rs.getString("MaSP"));
                entity.setTenBan(rs.getString("TenBan"));
                entity.setTenSP(rs.getString("TenSP"));
                entity.setSize(rs.getBoolean("Size"));
                entity.setSoLuong(rs.getInt("SoLuong"));
                entity.setGhiChu(rs.getString("GhiChu"));
                entity.setTongTien(rs.getDouble("TongTien"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    public List<BanHang> selectTen() {
        String SELECTTenSP_SQL = "Select TenSP from BanHang";
        return selectBySql(SELECTTenSP_SQL);
    }
}
