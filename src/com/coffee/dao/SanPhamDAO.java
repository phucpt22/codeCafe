/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coffee.dao;

import com.coffee.entity.SanPham;
import com.coffee.utils.JdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class SanPhamDAO extends CoffeeDAO<SanPham, String>{
    final String INSERT_SQL = "INSERT INTO SanPham (TenSP, LoaiSP, Gia, NgayKM, NgayHetKM, Hinh) VALUES (?,?,?,?,?,?)";
    final String UPDATE_SQL = "UPDATE SanPham SET LoaiSP=?, Gia=?, NgayKM=?, NgayHetKM=?, Hinh=? WHERE TenSP=?";
    final String DELETE_SQL = "DELETE FROM SanPham WHERE TenSP=?";
    final String SELECTALL_SQL = "SELECT * FROM SanPham";
    final String SELECTBYID_SQL = "SELECT * FROM SanPham WHERE TenSP=?";
    @Override
    public void insert(SanPham entity) {
        JdbcHelper.update(INSERT_SQL, entity.getTenSP(),entity.getLoaiSP(),entity.getGia(),entity.getNgayKM(),entity.getNgayHetKM(),entity.getHinh());
    }

    @Override
    public void update(SanPham entity) {
        JdbcHelper.update(UPDATE_SQL, entity.getLoaiSP(),entity.getGia(),entity.getNgayKM(),entity.getNgayHetKM(),entity.getHinh(), entity.getTenSP());    
    }

    @Override
    public void delete(String id) {
        JdbcHelper.update(DELETE_SQL,id);
    }

    @Override
    public List<SanPham> selectAll() {
        return selectBySql(SELECTALL_SQL);
    }

    @Override
    public SanPham selectById(String id) {
        List<SanPham> list = this.selectBySql(SELECTBYID_SQL, id);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }
    public List<SanPham> selectByKeyword(String keyword){
        String sql = "SELECT * FROM SanPham WHERE TenSP LIKE ?";
        return this.selectBySql(sql, "%"+keyword+"%");
    }
    @Override
    public List<SanPham> selectBySql(String sql, Object... args) {
        List<SanPham> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while(rs.next()){
                SanPham entity = new SanPham();
                entity.setTenSP(rs.getString("TenSP"));
                entity.setLoaiSP(rs.getString("LoaiSP"));
                entity.setGia(rs.getDouble("Gia"));
                entity.setNgayKM(rs.getDate("NgayKM"));
                entity.setNgayHetKM(rs.getDate("NgayHetKM"));
                entity.setHinh(rs.getString("Hinh"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
