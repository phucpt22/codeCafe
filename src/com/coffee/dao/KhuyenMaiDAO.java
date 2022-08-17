/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coffee.dao;

import com.coffee.entity.KhuyenMai;
import com.coffee.utils.JdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class KhuyenMaiDAO {
    final String INSERT_SQL = "INSERT INTO KhuyenMai (MaKM,TenKM,NgayKM,NgayHetKM,MucGiamGia,MoTa) VALUES (?,?,?,?,?,?)";
    final String UPDATE_SQL = "UPDATE KhuyenMai SET TenKM=?,NgayKM=?,NgayHetKM=?,MucGiamGia=?,MoTa=? WHERE MaKM=?";
    final String DELETE_SQL = "DELETE FROM KhuyenMai WHERE MaKM=?";
    final String SELECTALL_SQL = "SELECT * FROM KhuyenMai";
    final String SELECTBYID_SQL = "SELECT * FROM KhuyenMai WHERE MaKM=?";

    public void insert(KhuyenMai entity) {
        JdbcHelper.update(INSERT_SQL, entity.getMaKM(),entity.getTenKM(),entity.getNgayKM(),entity.getNgayHetKM(),entity.getMucGiamGia(),entity.getMoTa());
    }


    public void update(KhuyenMai entity) {
        JdbcHelper.update(UPDATE_SQL, entity.getTenKM(),entity.getNgayKM(),entity.getNgayHetKM(),entity.getMucGiamGia(),entity.getMoTa(), entity.getMaKM());    
    }

    public void delete(String id) {
        JdbcHelper.update(DELETE_SQL,id);
    }


    public List<KhuyenMai> selectAll() {
        return selectBySql(SELECTALL_SQL);
    }


    public KhuyenMai selectById(Integer id) {
        List<KhuyenMai> list = this.selectBySql(SELECTBYID_SQL, id);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }


    public List<KhuyenMai> selectBySql(String sql, Object... args) {
        List<KhuyenMai> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while(rs.next()){
                KhuyenMai entity = new KhuyenMai();
                entity.setMaKM(rs.getInt("MaKM"));
                entity.setTenKM(rs.getString("TenKM"));
                entity.setNgayKM(rs.getDate("NgayKM"));
                entity.setNgayHetKM(rs.getDate("NgayHetKM"));
                entity.setMucGiamGia(rs.getInt("MucGiamGia"));
                entity.setMoTa(rs.getString("MoTa"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    
    public List<KhuyenMai> selectByKeyword(String keyword){
        String sql="SELECT * FROM KhuyenMai WHERE TenKM LIKE ? ";
        return this.selectBySql(sql, "%"+ keyword +"%");    
    }
}
