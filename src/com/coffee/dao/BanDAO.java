/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coffee.dao;

import com.coffee.entity.Ban;
import com.coffee.utils.JdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class BanDAO extends CoffeeDAO<Ban, String>{
    final String INSERT_SQL = "INSERT INTO Ban (TenBan,GhiChu) VALUES (?,?,?,?,?,?)";
    final String UPDATE_SQL = "UPDATE Ban SET GhiChu=? WHERE TenBan=?";
    final String DELETE_SQL = "DELETE FROM Ban WHERE TenBan=?";
    final String SELECTALL_SQL = "SELECT * FROM Ban";
    final String SELECTBYID_SQL = "SELECT * FROM Ban WHERE TenBan=?";
    @Override
    public void insert(Ban entity) {
        JdbcHelper.update(INSERT_SQL, entity.getTenBan(),entity.getGhiChu());
    }

    @Override
    public void update(Ban entity) {
        JdbcHelper.update(UPDATE_SQL, entity.getTenBan(),entity.getGhiChu());    
    }

    @Override
    public void delete(String id) {
        JdbcHelper.update(DELETE_SQL,id);
    }

    @Override
    public List<Ban> selectAll() {
        return selectBySql(SELECTALL_SQL);
    }

    @Override
    public Ban selectById(String id) {
        List<Ban> list = this.selectBySql(SELECTBYID_SQL, id);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<Ban> selectBySql(String sql, Object... args) {
        List<Ban> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while(rs.next()){
                Ban entity = new Ban();
                entity.setTenBan(rs.getString("TenBan"));
                entity.setGhiChu(rs.getString("GhiChu"));
                
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

}
