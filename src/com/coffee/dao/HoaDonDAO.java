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


public class HoaDonDAO{
    final String INSERT_SQL = "INSERT INTO HoaDon (TenKH,TongTien,MaNV,NgayTao,GioDat) VALUES (?,?,?,?,?)";
    final String UPDATE_SQL = "UPDATE HoaDon SET TenKH=?,TongTien=?,MaNV=?,NgayTao=?,GioDat=? WHERE MaHD=?";
    final String DELETE_SQL = "DELETE FROM HoaDon WHERE MaHD=?";
    final String SELECTALL_SQL = "SELECT * FROM HoaDon";
    final String SELECTBYID_SQL = "SELECT * FROM HoaDon WHERE MaHD=?";

    public void insert(HoaDon entity) {
        JdbcHelper.update(INSERT_SQL, entity.getTenKH(),entity.getTongTien(),entity.getMaNV(),entity.getNgayTao(),entity.getGioDat());
    }


    public void update(HoaDon entity) {
        JdbcHelper.update(UPDATE_SQL, entity.getTenKH(),entity.getTongTien(),entity.getMaNV(),entity.getNgayTao(),entity.getGioDat(),entity.getMaHD());    
    }


    public void delete(String id) {
        JdbcHelper.update(DELETE_SQL,id);
    }


    public List<HoaDon> selectAll() {
        return selectBySql(SELECTALL_SQL);
    }


    public HoaDon selectById(String id) {
        List<HoaDon> list = this.selectBySql(SELECTBYID_SQL, id);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }


    public List<HoaDon> selectBySql(String sql, Object... args) {
        List<HoaDon> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while(rs.next()){
                HoaDon entity = new HoaDon();
                entity.setMaHD(rs.getInt("MaKH"));
                entity.setTenKH(rs.getString("TenKH"));
                entity.setTongTien(rs.getDouble("TongTien"));
                entity.setMaNV(rs.getString("MaNV"));
                entity.setNgayTao(rs.getDate("NgayTao"));
                entity.setGioDat(rs.getDate("GioDat"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    public List<HoaDon> selectTen() {
        String SELECTTenSP_SQL = "Select TenSP from HoaDon";
        return selectBySql(SELECTTenSP_SQL);
    }
}
