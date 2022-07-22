/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coffee.dao;

import com.coffee.entity.NhanVien;
import com.coffee.utils.JdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class NhanVienDAO extends CoffeeDAO<NhanVien, String>{
    final String INSERT_SQL = "INSERT INTO NhanVien (MaNV,HoTen,NgaySinh,VaiTro,SDT,DiaChi,GioiTinh,MatKhau) VALUES (?,?,?,?,?,?,?,?)";
    final String UPDATE_SQL = "UPDATE NhanVien SET HoTen=?,NgaySinh=?,VaiTro=?,SDT=?,DiaChi=?,GioiTinh=?,MatKhau=? WHERE MaNV=?";
    final String DELETE_SQL = "DELETE FROM NhanVien WHERE MaNV=?";
    final String SELECTALL_SQL = "SELECT * FROM NhanVien";
    final String SELECTBYID_SQL = "SELECT * FROM NhanVien WHERE MaNV=?";
    @Override
    public void insert(NhanVien entity) {
        JdbcHelper.update(INSERT_SQL, entity.getMaNV(),entity.getHoTen(),entity.getNgaySinh(),entity.isVaiTro(),entity.getSDT(),entity.getDiaChi(),entity.isGioiTinh(),entity.getMatKhau());
    }

    @Override
    public void update(NhanVien entity) {
        JdbcHelper.update(UPDATE_SQL, entity.getHoTen(),entity.getNgaySinh(),entity.isVaiTro(),entity.getSDT(),entity.getDiaChi(),entity.isGioiTinh(),entity.getMatKhau(),entity.getMaNV());    
    }

    @Override
    public void delete(String id) {
        JdbcHelper.update(DELETE_SQL,id);
    }

    @Override
    public List<NhanVien> selectAll() {
        return selectBySql(SELECTALL_SQL);
    }

    @Override
    public NhanVien selectById(String id) {
        List<NhanVien> list = this.selectBySql(SELECTBYID_SQL, id);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<NhanVien> selectBySql(String sql, Object... args) {
        List<NhanVien> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while(rs.next()){
                NhanVien entity = new NhanVien();
                entity.setMaNV(rs.getString("MaNV"));
                entity.setHoTen(rs.getString("HoTen"));
                entity.setNgaySinh(rs.getDate("NgaySinh"));
                entity.setVaiTro(rs.getBoolean("VaiTro"));
                entity.setSDT(rs.getString("SDT"));
                entity.setDiaChi(rs.getString("DiaChi"));
                entity.setGioiTinh(rs.getBoolean("GioiTinh"));
                entity.setMatKhau(rs.getString("MatKhau"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
