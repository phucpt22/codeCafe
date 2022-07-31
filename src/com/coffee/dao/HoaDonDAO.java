/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coffee.dao;

import com.coffee.entity.HoaDon;
import com.coffee.utils.JdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class HoaDonDAO extends CoffeeDAO<HoaDon, String>{
    final String INSERT_SQL = "INSERT INTO HoaDon (MaHD,MaNV,TenBan,MaSP,TongTien,NgayTao,TenKH) VALUES (?,?,?,?,?,?,?)";
    final String UPDATE_SQL = "UPDATE HoaDon SET TenBan=?,MaSP=?,TongTien=?,NgayTao=?,TenKH=? WHERE MaHD=?";
    final String DELETE_SQL = "DELETE FROM HoaDon WHERE MaHD=?";
    final String SELECTALL_SQL = "SELECT * FROM HoaDon";
    final String SELECTBYID_SQL = "SELECT * FROM HoaDon WHERE MaHD=?";
    @Override
    public void insert(HoaDon entity) {
        JdbcHelper.update(INSERT_SQL, entity.getMaHD(), entity.getMaNV(), entity.getTenBan(), entity.getMaSP(), entity.getNgayTao(), entity.getTenKH(), entity.getTongTien());
    }

    @Override
    public void update(HoaDon entity) {
        JdbcHelper.update(UPDATE_SQL, entity.getMaNV(), entity.getTenBan(), entity.getMaSP(), entity.getNgayTao(), entity.getTenKH(), entity.getTongTien(), entity.getMaHD());    
    }

    @Override
    public void delete(String id) {
        JdbcHelper.update(DELETE_SQL,id);
    }

    @Override
    public List<HoaDon> selectAll() {
        return selectBySql(SELECTALL_SQL);
    }

    @Override
    public HoaDon selectById(String id) {
        List<HoaDon> list = this.selectBySql(SELECTBYID_SQL, id);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<HoaDon> selectBySql(String sql, Object... args) {
        List<HoaDon> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while(rs.next()){
                HoaDon entity = new HoaDon();
                entity.setMaHD(rs.getString("MaHD"));
                entity.setMaNV(rs.getString("MaNV"));
                entity.setTenBan(rs.getString("TenBan"));
                entity.setMaSP(rs.getString("MaSP"));
                entity.setNgayTao(rs.getDate("NgayTao"));
                entity.setTenKH(rs.getString("TenKH"));
                entity.setTongTien(rs.getDouble("TongTien"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
