/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coffee.ui;

import com.coffee.dao.SanPhamDAO;
import com.coffee.entity.SanPham;
import com.coffee.utils.Auth;
import com.coffee.utils.MsgBox;
import com.coffee.utils.XDate;
import com.coffee.utils.XImage;
import com.coffee.utils.utilityHelper;
import static java.awt.Color.pink;
import static java.awt.Color.white;
import java.io.File;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class SanPhamJDialog extends javax.swing.JDialog {

    int index = 0;   //vị trí SanPham đang được chọn
    SanPhamDAO dao = new SanPhamDAO();     
    JFileChooser fileChooser = new JFileChooser("C:\\Users\\phucl\\OneDrive\\Documents\\NetBeansProjects\\QuanLyCafe\\src\\logos");
    public SanPhamJDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        init();
    }
    void init(){
        setIconImage(XImage.getAppIcon());
        setLocationRelativeTo(null);
        setTitle("Quản lý sản phẩm");
        fileChooser.setDialogTitle("Chọn Logo cho sản phẩm");  
    }
    void selectImage() {
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) { 
            File file = fileChooser.getSelectedFile();   
            if (XImage.saveLogo(file)) {  
                // Hiển thị hình lên form
                lblHinh.setIcon(XImage.read(file.getName())); 
                lblHinh.setToolTipText(file.getName());
            }
        }
    }
    void tim(){
        DefaultTableModel model = (DefaultTableModel) tblBang.getModel();
        model.setRowCount(0);
        try {
            String keyword = txtTim.getText();
            List<SanPham> list = dao.selectByKeyword(keyword);
            for (SanPham sp : list) {
                Object[] row = {
                    sp.getTenSP(),
                    sp.getLoaiSP(),
                    sp.getGia(),
                    XDate.toString(sp.getNgayKM(),"dd/MM/yyyy"),
                    XDate.toString(sp.getNgayHetKM(),"dd/MM/yyyy"),
                    sp.getHinh()
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }
    
    void load() {
        DefaultTableModel model = (DefaultTableModel) tblBang.getModel();
        model.setRowCount(0);
        try {
            List<SanPham> list = dao.selectAll();
            for (SanPham sp : list) {
                Object[] row = {
                    sp.getTenSP(),
                    sp.getLoaiSP(),
                    sp.getGia(),
                    XDate.toString(sp.getNgayKM(),"dd/MM/yyyy"),
                    XDate.toString(sp.getNgayHetKM(),"dd/MM/yyyy"),
                    sp.getHinh()
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }
   void insert() {
        SanPham model = getModel();
        try{
            dao.insert(model);
            this.load();
            this.clear();
            MsgBox.alert(this, "Thêm mới thành công!");
        } catch (Exception e) {
            MsgBox.alert(this, "Thêm mới thất bại!");
        }
    }

    //lấy thông tin trên form, cập nhật vào bản ghi CSDL theo maCD
    //load lại bảng
    void update() {
        SanPham model = getModel();
        try {
            dao.update(model);
            this.load();
            MsgBox.alert(this, "Cập nhật thành công!");
        } catch (Exception e) {
            MsgBox.alert(this, "Cập nhật thất bại!");
        }
    }

    //xóa bản ghi trong CSDL theo maCD lấy trên form
    //load lại bảng
    //xóa trắng form, chuyển sang insertable
    void delete() {
        if (MsgBox.confirm(this, "Bạn có muốn xóa hay không?")) {
            String masp = txtTenSP.getText();
            try {
                dao.delete(masp);
                this.load();
                this.clear();
                MsgBox.alert(this, "Xóa thành công!");
            } catch (Exception e) {
                MsgBox.alert(this, "Xóa thất bại!");
            }
        }
    }

    void clear() {
        this.setModel(new SanPham());
        this.setStatus(true);
    }
    void edit() {
        try {
            String masp = (String) tblBang.getValueAt(this.index, 0);
            SanPham model = dao.selectById(masp);
            if (model != null) {
                this.setModel(model);
                this.setStatus(false);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }
    void setModel(SanPham model) {
        txtTenSP.setText(model.getTenSP());
        cboLoai.setSelectedItem(model.getLoaiSP());
        txtGia.setText(String.valueOf(model.getGia()));
        txtNgayKM.setText(String.valueOf(model.getNgayKM()));
        txtNgayHKM.setText(String.valueOf(model.getNgayHetKM()));
        lblHinh.setToolTipText(model.getHinh());
        if (model.getHinh() != null) {
            lblHinh.setIcon(XImage.read(model.getHinh()));

        }else{
            lblHinh.setIcon(XImage.read("noImage.png"));
        }
    }
    SanPham getModel() {
        SanPham model = new SanPham();
        model.setTenSP(txtTenSP.getText());
        model.setLoaiSP(String.valueOf(cboLoai.getSelectedItem()));
        model.setGia(Double.valueOf(txtGia.getText()));
        model.setNgayKM(XDate.toDate(txtNgayKM.getText(), "dd/MM/yyyy"));
        model.setNgayHetKM(XDate.toDate(txtNgayHKM.getText(), "dd/MM/yyyy"));
        model.setHinh(lblHinh.getToolTipText());    //lấy tên hình
        return model;
    }

    void setStatus(boolean insertable) {
        txtTenSP.setEditable(insertable);
        btnInsert.setEnabled(insertable);
        btnUpdate.setEnabled(!insertable);
        btnDelete.setEnabled(!insertable);
        boolean first = this.index > 0;
        boolean last = this.index < tblBang.getRowCount() - 1;

    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        txtNgayHKM = new javax.swing.JTextField();
        txtNgayKM = new javax.swing.JTextField();
        txtTenSP = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        cboLoai = new javax.swing.JComboBox<>();
        txtGia = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        btnInsert = new com.k33ptoo.components.KButton();
        btnNew = new com.k33ptoo.components.KButton();
        btnUpdate = new com.k33ptoo.components.KButton();
        btnDelete = new com.k33ptoo.components.KButton();
        lblHinh = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        txtTim = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblBang = new rojerusan.RSTableMetro();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(0, 0, 255));
        jLabel31.setText("Quản lý sản phẩm");

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel30.setText("Ảnh đính kèm");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông Tin Sản Phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 16))); // NOI18N

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Tên sản phẩm:");

        jLabel29.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel29.setText("Ngày KM:");

        jLabel28.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel28.setText("Ngày hết KM:");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText("Loại sản phẩm:");

        cboLoai.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cboLoai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel27.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel27.setText("Giá:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel28)
                        .addGap(18, 18, 18)
                        .addComponent(txtNgayHKM, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel29))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNgayKM, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(65, 65, 65)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(jLabel27))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel13)
                    .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNgayKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29)
                    .addComponent(jLabel27)
                    .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(txtNgayHKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chức Năng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 16))); // NOI18N

        btnInsert.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/coffee/anh/plus-free-icon-font.png"))); // NOI18N
        btnInsert.setText("Thêm");
        btnInsert.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnInsert.setkBackGroundColor(new java.awt.Color(197, 132, 43));
        btnInsert.setkBorderRadius(0);
        btnInsert.setkEndColor(new java.awt.Color(218, 173, 51));
        btnInsert.setkHoverEndColor(new java.awt.Color(203, 163, 72));
        btnInsert.setkHoverForeGround(new java.awt.Color(154, 83, 7));
        btnInsert.setkHoverStartColor(new java.awt.Color(180, 140, 50));
        btnInsert.setkStartColor(new java.awt.Color(162, 145, 90));
        btnInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertActionPerformed(evt);
            }
        });

        btnNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/coffee/anh/refresh-free-icon-font.png"))); // NOI18N
        btnNew.setText("Mới");
        btnNew.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnNew.setkBackGroundColor(new java.awt.Color(197, 132, 43));
        btnNew.setkBorderRadius(0);
        btnNew.setkEndColor(new java.awt.Color(218, 173, 51));
        btnNew.setkHoverEndColor(new java.awt.Color(203, 163, 72));
        btnNew.setkHoverForeGround(new java.awt.Color(154, 83, 7));
        btnNew.setkHoverStartColor(new java.awt.Color(180, 140, 50));
        btnNew.setkStartColor(new java.awt.Color(162, 145, 90));
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/coffee/anh/car-mechanic-free-icon-font.png"))); // NOI18N
        btnUpdate.setText("Sửa");
        btnUpdate.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnUpdate.setkBackGroundColor(new java.awt.Color(197, 132, 43));
        btnUpdate.setkBorderRadius(0);
        btnUpdate.setkEndColor(new java.awt.Color(218, 173, 51));
        btnUpdate.setkHoverEndColor(new java.awt.Color(203, 163, 72));
        btnUpdate.setkHoverForeGround(new java.awt.Color(154, 83, 7));
        btnUpdate.setkHoverStartColor(new java.awt.Color(180, 140, 50));
        btnUpdate.setkStartColor(new java.awt.Color(162, 145, 90));
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/coffee/anh/trash-free-icon-font.png"))); // NOI18N
        btnDelete.setText("Xóa");
        btnDelete.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnDelete.setkBackGroundColor(new java.awt.Color(197, 132, 43));
        btnDelete.setkBorderRadius(0);
        btnDelete.setkEndColor(new java.awt.Color(218, 173, 51));
        btnDelete.setkHoverEndColor(new java.awt.Color(203, 163, 72));
        btnDelete.setkHoverForeGround(new java.awt.Color(154, 83, 7));
        btnDelete.setkHoverStartColor(new java.awt.Color(180, 140, 50));
        btnDelete.setkStartColor(new java.awt.Color(162, 145, 90));
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(btnInsert, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(96, 96, 96)
                .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(101, 101, 101)
                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(102, 102, 102)
                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(49, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnInsert, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblHinh.setBackground(new java.awt.Color(255, 255, 255));
        lblHinh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblHinh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHinhMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel31)
                .addGap(508, 508, 508))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(lblHinh, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(jLabel30)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(82, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel30)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblHinh, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel31)
                        .addGap(50, 50, 50)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh Sách Sản Phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 16))); // NOI18N

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel22.setText("Tìm Kiếm");

        txtTim.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtTim.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtTimMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                txtTimMouseReleased(evt);
            }
        });
        txtTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimActionPerformed(evt);
            }
        });

        tblBang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Tên SP", "Loại SP", "Giá", "Ngày KM", "Ngày hết KM", "Hình"
            }
        ));
        tblBang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblBangMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(tblBang);

        jButton1.setBackground(new java.awt.Color(0, 0, 204));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/coffee/icon/loupe.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(220, Short.MAX_VALUE)
                .addComponent(jLabel22)
                .addGap(18, 18, 18)
                .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, 535, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(245, 245, 245))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(txtTim)
                        .addGap(3, 3, 3))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 16, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        if(utilityHelper.checkNullText(txtTenSP)&&
                utilityHelper.checkNullText(txtGia)&&
                utilityHelper.checkNullText(txtNgayHKM)&&
                utilityHelper.checkNullText(txtNgayKM)&&
                checkNullHinh()){
            if(utilityHelper.checkMaCD(txtTenSP)&&
                    utilityHelper.checkTenCD(txtGia)&&
                    utilityHelper.checkThoiLuong(txtNgayHKM)&&
                    utilityHelper.checkHocPhi(txtNgayKM)){
                if(checkTrungMa(txtTenSP)){
                    update();
                }
            }
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void txtTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        this.load();
        this.setStatus(true);
    }//GEN-LAST:event_formWindowOpened

    private void tblBangMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBangMousePressed
        if(evt.getClickCount() == 1){
            this.index = tblBang.rowAtPoint(evt.getPoint());
            if (this.index >= 0) {
                this.edit();
            }
        }
    }//GEN-LAST:event_tblBangMousePressed

    private void lblHinhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHinhMouseClicked
        if(evt.getClickCount() == 2){
            this.selectImage();
        }
    }//GEN-LAST:event_lblHinhMouseClicked
    public boolean checkNullHinh(){
        if(lblHinh.getToolTipText()!=null){
            return true;
        }else{
            MsgBox.alert(this, "Không được để trống hình.");
            return false;
        }
    }
    public boolean checkTrungMa(JTextField txt) {
        txt.setBackground(white);
        if (dao.selectById(txt.getText()) == null) {
            return true;
        } else {
            txt.setBackground(pink);
            MsgBox.alert(this, txt.getName() + " đã bị tồn tại.");
            return false;
        }
    }
    private void btnInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertActionPerformed
        if(utilityHelper.checkNullText(txtTenSP)&&
                utilityHelper.checkNullText(txtGia)&&
                utilityHelper.checkNullText(txtNgayHKM)&&
                utilityHelper.checkNullText(txtNgayKM)&&
                checkNullHinh()){
            if(utilityHelper.checkMaCD(txtTenSP)&&
                    utilityHelper.checkTenCD(txtGia)&&
                    utilityHelper.checkThoiLuong(txtNgayHKM)&&
                    utilityHelper.checkHocPhi(txtNgayKM)){
                if(checkTrungMa(txtTenSP)){
                    insert();
                }
            }
        }
    }//GEN-LAST:event_btnInsertActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        if(Auth.user.isVaiTro()){
            delete();
        }else{
            MsgBox.alert(this, "Chỉ quản lý mới được phép xóa");
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        this.clear();
    }//GEN-LAST:event_btnNewActionPerformed

    private void txtTimMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTimMouseReleased
        
    }//GEN-LAST:event_txtTimMouseReleased

    private void txtTimMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTimMousePressed
        
    }//GEN-LAST:event_txtTimMousePressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        tim();
        clear();
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SanPhamJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SanPhamJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SanPhamJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SanPhamJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                SanPhamJDialog dialog = new SanPhamJDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.k33ptoo.components.KButton btnDelete;
    private com.k33ptoo.components.KButton btnInsert;
    private com.k33ptoo.components.KButton btnNew;
    private com.k33ptoo.components.KButton btnUpdate;
    private javax.swing.JComboBox<String> cboLoai;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblHinh;
    private rojerusan.RSTableMetro tblBang;
    private javax.swing.JTextField txtGia;
    private javax.swing.JTextField txtNgayHKM;
    private javax.swing.JTextField txtNgayKM;
    private javax.swing.JTextField txtTenSP;
    private javax.swing.JTextField txtTim;
    // End of variables declaration//GEN-END:variables
}
