/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coffee.dao;

import com.coffee.entity.ChiTietHoaDon;
import com.coffee.utils.JdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class ChiTietHoaDonDAO extends CoffeeDAO<ChiTietHoaDon, String> {
    final String INSERT_SQL = "INSERT INTO ChiTietHoaDon (MaHD,MaSP,TenSP,TenBan,Gia,Size,SoLuong,ThanhTien) VALUES (?,?,?,?,?,?,?,?)";
    final String UPDATE_SQL = "UPDATE ChiTietHoaDon SET MaSP,TenSP,TenBan,Gia,Size,SoLuong,ThanhTien WHERE MaHD=?";
    final String DELETE_SQL = "DELETE FROM ChiTietHoaDon WHERE MaHD=?";
    final String SELECTALL_SQL = "SELECT * FROM ChiTietHoaDon";
    final String SELECTBYID_SQL = "SELECT * FROM ChiTietHoaDon WHERE MaHD=?";
    @Override
    public void insert(ChiTietHoaDon entity) {
        JdbcHelper.update(INSERT_SQL, entity.getMaHD(), entity.getMaSP(), entity.getTenSP(), entity.getTenBan(), entity.getGia(), entity.isSize(), entity.getSoLuong(), entity.getThanhTien());
    }

    @Override
    public void update(ChiTietHoaDon entity) {
        JdbcHelper.update(UPDATE_SQL, entity.getMaSP(), entity.getTenSP(), entity.getTenBan(), entity.getGia(), entity.isSize(), entity.getSoLuong(), entity.getThanhTien(), entity.getMaHD());    
    }

    @Override
    public void delete(String id) {
        JdbcHelper.update(DELETE_SQL,id);
    }

    @Override
    public List<ChiTietHoaDon> selectAll() {
        return selectBySql(SELECTALL_SQL);
    }

    @Override
    public ChiTietHoaDon selectById(String id) {
        List<ChiTietHoaDon> list = this.selectBySql(SELECTBYID_SQL, id);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<ChiTietHoaDon> selectBySql(String sql, Object... args) {
        List<ChiTietHoaDon> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while(rs.next()){
                ChiTietHoaDon entity = new ChiTietHoaDon();
                entity.setMaHD(rs.getString("MaHD"));
                entity.setMaSP(rs.getString("MaSP"));
                entity.setTenSP(rs.getString("TenSP"));
                entity.setTenBan(rs.getString("TenBan"));
                entity.setGia(rs.getDouble("Gia"));
                entity.setSize(rs.getBoolean("Size"));
                entity.setSoLuong(rs.getInt("SoLuong"));
                entity.setThanhTien(rs.getDouble("ThanhTien"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    
    public List<ChiTietHoaDon> selectByKeyword(String keyword){
        String sql="SELECT * FROM ChiTietHoaDon WHERE HoTen LIKE ? ";
        return this.selectBySql(sql, "%"+ keyword +"%");    
    }
}
