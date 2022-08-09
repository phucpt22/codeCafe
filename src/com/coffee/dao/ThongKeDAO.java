/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coffee.dao;

import com.coffee.utils.JdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ThongKeDAO {
     private List<Object[]> getListOfArray(String sql, String[] cols, Object...args){
        try {
            List<Object[]> list=new ArrayList<>();
            ResultSet rs = JdbcHelper.query(sql, args);
            while(rs.next()){
                Object[] vals = new Object[cols.length];
                for(int i=0; i<cols.length; i++){
                    vals[i] = rs.getObject(cols[i]);
                }
                list.add(vals);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
     public List<Object[]> getSanPhamDaBan() {
        String sql = "{CALL sp_SanPhamDaBan}";
        String[] cols = {"Tên sản phẩm", "Số lượng mua", "Ngày Tạo"};
        return this.getListOfArray(sql, cols);
    }
     public List<Object[]> getThongKeDoanhThu() {
        String sql = "{CALL sp_DoanhThu}";
        String[] cols = {"SoHD","TenSP", "Doanh thu", "ThapNhat", "CaoNhat", "TrungBinh"};
        return this.getListOfArray(sql, cols);
    }
    public List<Object[]> getLichSuHD(int nam) {
        String sql = "{CALL sp_LichSuHoaDon(?)}";
        String[] cols = {"MaHD","MaSP","TenSP","Gia","SoLuong","ThanhTien","TenBan","MaNV","NgayTao"};
        return this.getListOfArray(sql, cols, nam);
    }
    
}
