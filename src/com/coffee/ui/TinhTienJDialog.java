/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coffee.ui;

import com.coffee.dao.BanDAO;
import com.coffee.dao.ChiTietHoaDonDAO;
import com.coffee.dao.HoaDonDAO;

import com.coffee.dao.SanPhamDAO;
import com.coffee.entity.Ban;
import com.coffee.entity.ChiTietHoaDon;
import com.coffee.entity.HoaDon;

import com.coffee.entity.SanPham;
import com.coffee.utils.MsgBox;
import static java.lang.Thread.sleep;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class TinhTienJDialog extends javax.swing.JDialog {

    int index = -1;
    SanPhamDAO daoSP = new SanPhamDAO();
    BanDAO daoB = new BanDAO();
    ChiTietHoaDonDAO daoct = new ChiTietHoaDonDAO();

    public TinhTienJDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        loadListSanPham();
//        loadDuLieuVao();
//        chenDuLieuVaoTable();
        fillComboBoxBan();
        rdoSizeM.setSelected(true);
        clock();
    }
    SimpleDateFormat ft = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss ");
    public void clock() {
        Thread clock = new Thread() {
            public void run() {
                try {
                    while (true) {
                        Date t = new Date();
                        lbl_timeNow.setText(ft.format(t));
                        sleep(1000);
                    }
                } catch (InterruptedException e) {
                }
            }
        };
        clock.start();
    }
    public ArrayList<ChiTietHoaDon> arrayct = new ArrayList<ChiTietHoaDon>();

    public void loadDuLieuVao() {
       
        int chcked=0;
        int line =arrayct.size();
        if(line>0){
            for (int i = 0; i < line; i++) {
                String maspkhchose=lblMaSP.getText();
                            
                if (arrayct.get(i).getMaSP().equals(lblMaSP.getText()) ) {

                    //int quanCu = (int) tblBill.getValueAt(i, 5);
                    int quanMoi = (int) spinerSoLuong.getValue();
                    int quanTotal =   arrayct.get(i).getSoLuong() + quanMoi;
                    double ttmoney=quanTotal*(arrayct.get(i).getGia());
                    arrayct.get(i).setSoLuong(quanTotal);                    
                    arrayct.get(i).setThanhTien(ttmoney);

                    chcked=1;
                  
                }
            }
            
        }
        if(chcked==0){
            ChiTietHoaDon ct = new ChiTietHoaDon();
            ct.setMaSP(lblMaSP.getText());
            ct.setTenSP(lblTenSP.getText());
            ct.setTenBan(cboTenBan.getSelectedItem().toString());
            ct.setSize(rdoSizeM.isSelected());
            ct.setSoLuong(Integer.parseInt(spinerSoLuong.getValue().toString()));
            ct.setGia(Double.parseDouble(lblGia.getText().toString()));
            ct.setThanhTien(Double.parseDouble(lblThanhTien.getText()));
            arrayct.add(ct);
        }
    }
    public void chenDuLieuVaoTable() {
        //chổ này nè
        //đây là cho cưng set data cho jtable ne
        //cho nay cung duyet cai aray nay r day vo
        DefaultTableModel model = (DefaultTableModel) tblBill.getModel();//cho
        model.setRowCount(0);
       
       // daoct.InsertListChitietHoaDon(arrayct);
        for (ChiTietHoaDon ct : arrayct) {
            //daoct.insert(nv);
           model.addRow(new Object[]{ct.getMaSP(), ct.getTenSP(), ct.getTenBan(), ct.getGia(), ct.isSize() ? "M" : "L", ct.getSoLuong(), ct.getThanhTien()});
        }
    }
    void loadListSanPham() {
        DefaultListModel model = new DefaultListModel();
        try {
            List<SanPham> list = daoSP.selectAll();
            for (SanPham sp : list) {
                model.addElement(sp.getTenSP());
            }
            listTenSP.setModel(model);
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }

    void fillComboBox() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboTenBan.getModel();
        model.removeAllElements();
        try {
            List<Ban> list = daoB.selectAll();
            for (Ban b : list) {
                model.addElement(b);
            }

        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy cập dữ liệu");
        }
    }

    void fillComboBoxBan() {
        DefaultComboBoxModel model = new DefaultComboBoxModel<>();
        //model.removeAllElements();
        try {
            List<Ban> list = daoB.selectAll();
            for (Ban b : list) {
                model.addElement(b);
            }
            cboTenBan.setModel(model);
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy cập dữ liệu");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        kGradientPanel1 = new com.k33ptoo.components.KGradientPanel();
        kGradientPanel3 = new com.k33ptoo.components.KGradientPanel();
        kGradientPanel2 = new com.k33ptoo.components.KGradientPanel();
        jPanel8 = new javax.swing.JPanel();
        btn_DeleteHD = new javax.swing.JButton();
        btn_AddHD = new javax.swing.JButton();
        jLabel42 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        spinerSoLuong = new javax.swing.JSpinner();
        cboTenBan = new javax.swing.JComboBox<>();
        jLabel43 = new javax.swing.JLabel();
        lblTenSP = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        rdoSizeM = new javax.swing.JRadioButton();
        rdoSizeL = new javax.swing.JRadioButton();
        lblGia = new javax.swing.JLabel();
        lbl_timeNow = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        lblThanhTien = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        lblMaSP = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtMaKH = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        listTenSP = new javax.swing.JList<>();
        jPanel16 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        txtTienNhan = new javax.swing.JTextField();
        jLabel53 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        txtTienThua = new javax.swing.JTextField();
        jLabel54 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        txtTongcong = new javax.swing.JTextField();
        jLabel56 = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        txtThanhTien = new javax.swing.JTextField();
        jLabel57 = new javax.swing.JLabel();
        btnIn = new javax.swing.JButton();
        jLabel46 = new javax.swing.JLabel();
        txtMaHoaDon = new javax.swing.JTextField();
        jPanel22 = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblBill = new rojerusan.RSTableMetro();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        kGradientPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        kGradientPanel3.setkEndColor(new java.awt.Color(241, 232, 212));
        kGradientPanel3.setkFillBackground(false);
        kGradientPanel3.setkStartColor(new java.awt.Color(238, 217, 176));

        kGradientPanel2.setkEndColor(new java.awt.Color(241, 232, 212));
        kGradientPanel2.setkFillBackground(false);
        kGradientPanel2.setkStartColor(new java.awt.Color(238, 217, 176));
        kGradientPanel2.setPreferredSize(new java.awt.Dimension(1247, 840));
        kGradientPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Order", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 24))); // NOI18N

        btn_DeleteHD.setBackground(new java.awt.Color(255, 255, 255));
        btn_DeleteHD.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_DeleteHD.setText("Xóa");
        btn_DeleteHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_DeleteHDActionPerformed(evt);
            }
        });

        btn_AddHD.setBackground(new java.awt.Color(255, 255, 255));
        btn_AddHD.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_AddHD.setText("Đặt Mua");
        btn_AddHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_AddHDActionPerformed(evt);
            }
        });

        jLabel42.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel42.setText("Bàn:");

        jLabel45.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel45.setText("Số lượng:");

        spinerSoLuong.setModel(new javax.swing.SpinnerNumberModel(1, 1, 10, 1));
        spinerSoLuong.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spinerSoLuongStateChanged(evt);
            }
        });

        cboTenBan.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        cboTenBan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboTenBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTenBanActionPerformed(evt);
            }
        });

        jLabel43.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel43.setText("Tên sản phẩm:");

        lblTenSP.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel48.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel48.setText("Đơn giá:");

        jLabel41.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel41.setText("Size:");

        rdoSizeM.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdoSizeM);
        rdoSizeM.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rdoSizeM.setText("M");
        rdoSizeM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoSizeMActionPerformed(evt);
            }
        });

        rdoSizeL.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdoSizeL);
        rdoSizeL.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rdoSizeL.setText("L");
        rdoSizeL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoSizeLActionPerformed(evt);
            }
        });

        lblGia.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lblGia.setText("....");

        lbl_timeNow.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbl_timeNow.setForeground(new java.awt.Color(220, 61, 61));
        lbl_timeNow.setText("Time Now");

        jLabel19.setText("------>");

        lblThanhTien.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblThanhTien.setForeground(new java.awt.Color(255, 0, 51));

        jLabel47.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel47.setText("Mã SP:");

        lblMaSP.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblMaSP.setText("........");

        jLabel1.setText("jLabel1");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel42)
                        .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(btn_AddHD, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)))
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(cboTenBan, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(spinerSoLuong, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                            .addComponent(txtMaKH))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblThanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(87, 87, 87)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel48)
                            .addComponent(jLabel43)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel41)
                                .addGap(64, 64, 64)
                                .addComponent(rdoSizeM))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel47)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblMaSP)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblGia)
                            .addComponent(rdoSizeL))
                        .addGap(28, 28, 28))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(121, 121, 121)
                        .addComponent(btn_DeleteHD, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lbl_timeNow)
                .addGap(172, 172, 172))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_timeNow)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addComponent(lblTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblGia)
                            .addComponent(jLabel48)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel42)
                            .addComponent(cboTenBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel41)
                            .addComponent(rdoSizeM)
                            .addComponent(rdoSizeL))
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addComponent(jLabel43))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblThanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel45)
                                        .addComponent(spinerSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel19)))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel47)
                    .addComponent(lblMaSP))
                .addGap(2, 2, 2)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_AddHD, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                    .addComponent(btn_DeleteHD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 11, Short.MAX_VALUE))
        );

        kGradientPanel2.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 0, 960, 330));

        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        listTenSP.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Drinks Menu", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 0, 18))); // NOI18N
        listTenSP.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        listTenSP.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        listTenSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listTenSPMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                listTenSPMouseEntered(evt);
            }
        });
        jScrollPane4.setViewportView(listTenSP);

        jPanel7.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 260, 130));

        jPanel16.setBackground(new java.awt.Color(0, 0, 0));
        jPanel16.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtTienNhan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtTienNhan.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTienNhan.setText("0");
        txtTienNhan.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTienNhanCaretUpdate(evt);
            }
        });
        txtTienNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTienNhanActionPerformed(evt);
            }
        });

        jLabel53.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel53.setText("Tiền Nhận:");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel53)
                .addGap(18, 18, 18)
                .addComponent(txtTienNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addComponent(jLabel53)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(txtTienNhan, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        jPanel16.add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 260, 30));

        txtTienThua.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtTienThua.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTienThua.setText("0");
        txtTienThua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTienThuaActionPerformed(evt);
            }
        });

        jLabel54.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel54.setText("Tiền Thừa:");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel54)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(txtTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(txtTienThua, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                .addComponent(jLabel54))
        );

        jPanel16.add(jPanel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 260, 30));

        txtTongcong.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtTongcong.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTongcong.setText("0");
        txtTongcong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTongcongActionPerformed(evt);
            }
        });

        jLabel56.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel56.setText("Tổng cộng:");

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addComponent(jLabel56)
                .addGap(18, 18, 18)
                .addComponent(txtTongcong, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(txtTongcong, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                .addComponent(jLabel56))
        );

        jPanel16.add(jPanel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 260, 30));

        txtThanhTien.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtThanhTien.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtThanhTien.setText("0");
        txtThanhTien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtThanhTienActionPerformed(evt);
            }
        });

        jLabel57.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel57.setText("Thành tiền:");

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(jLabel57)
                .addGap(18, 18, 18)
                .addComponent(txtThanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(txtThanhTien, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                .addComponent(jLabel57))
        );

        jPanel16.add(jPanel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 260, 30));

        jPanel7.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 260, 120));

        btnIn.setBackground(new java.awt.Color(0, 51, 255));
        btnIn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnIn.setForeground(new java.awt.Color(255, 255, 255));
        btnIn.setText("Lưu và In");
        btnIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInActionPerformed(evt);
            }
        });
        jPanel7.add(btnIn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 280, 260, 50));

        jLabel46.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel46.setText("Mã HD:");
        jPanel7.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 60, -1));

        txtMaHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaHoaDonActionPerformed(evt);
            }
        });
        jPanel7.add(txtMaHoaDon, new org.netbeans.lib.awtextra.AbsoluteConstraints(107, 250, 150, -1));

        kGradientPanel2.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 262, 330));

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        kGradientPanel2.add(jPanel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 676, -1, -1));

        jPanel23.setBackground(new java.awt.Color(231, 231, 231));
        jPanel23.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED), "Cart", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 24))); // NOI18N

        tblBill.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã SP", "Tên SP", "Tên bàn", "Đơn Giá", "Size", "Số Lượng", "Thành tiền"
            }
        ));
        tblBill.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        tblBill.setFuenteFilas(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        tblBill.setFuenteFilasSelect(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        tblBill.setGrosorBordeFilas(0);
        tblBill.setGrosorBordeHead(0);
        tblBill.setRowHeight(30);
        tblBill.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblBillMousePressed(evt);
            }
        });
        jScrollPane6.setViewportView(tblBill);

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 1056, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 152, Short.MAX_VALUE))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE)
                .addGap(25, 25, 25))
        );

        kGradientPanel2.add(jPanel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 1220, 490));

        javax.swing.GroupLayout kGradientPanel3Layout = new javax.swing.GroupLayout(kGradientPanel3);
        kGradientPanel3.setLayout(kGradientPanel3Layout);
        kGradientPanel3Layout.setHorizontalGroup(
            kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kGradientPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1226, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        kGradientPanel3Layout.setVerticalGroup(
            kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel3Layout.createSequentialGroup()
                .addComponent(kGradientPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 807, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout kGradientPanel1Layout = new javax.swing.GroupLayout(kGradientPanel1);
        kGradientPanel1.setLayout(kGradientPanel1Layout);
        kGradientPanel1Layout.setHorizontalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(kGradientPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 1081, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        kGradientPanel1Layout.setVerticalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(kGradientPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 805, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kGradientPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kGradientPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened

    }//GEN-LAST:event_formWindowOpened

    private void btn_DeleteHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_DeleteHDActionPerformed
        int index = tblBill.getRowCount();
        if(index <= -1){
            MsgBox.alert(this, "Chưa chọn xóa");
        }else{
            arrayct.remove(index);
            index = -1;
            JOptionPane.showMessageDialog(this, "Xóa thành công");
            chenDuLieuVaoTable();
        }
    }//GEN-LAST:event_btn_DeleteHDActionPerformed

    private void btn_AddHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AddHDActionPerformed

        loadDuLieuVao();
        chenDuLieuVaoTable();
        spinerSoLuong.setValue(1);
    }//GEN-LAST:event_btn_AddHDActionPerformed

    private void spinerSoLuongStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spinerSoLuongStateChanged
        int n;
        n = (Integer) spinerSoLuong.getValue();
        double a = Double.parseDouble(lblGia.getText());
        lblThanhTien.setText(String.valueOf(a * n));
    }//GEN-LAST:event_spinerSoLuongStateChanged

    private void cboTenBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTenBanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboTenBanActionPerformed

    private void rdoSizeMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoSizeMActionPerformed
        int index = listTenSP.getSelectedIndex();
        List<SanPham> list = daoSP.selectAll();
        lblGia.setText(String.valueOf(list.get(index).getGia()));
    }//GEN-LAST:event_rdoSizeMActionPerformed

    private void rdoSizeLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoSizeLActionPerformed
        int index = listTenSP.getSelectedIndex();
        List<SanPham> list = daoSP.selectAll();
        double giathem = 10000;
        lblGia.setText(String.valueOf(list.get(index).getGia()+giathem));

    }//GEN-LAST:event_rdoSizeLActionPerformed

    private void listTenSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listTenSPMouseClicked
        if (evt.getClickCount() == 1) {
            int index = listTenSP.getSelectedIndex();
            List<SanPham> list = daoSP.selectAll();
            lblTenSP.setText(list.get(index).getTenSP());
            lblGia.setText(String.valueOf(list.get(index).getGia()));
            lblMaSP.setText(list.get(index).getMaSP());
        }
    }//GEN-LAST:event_listTenSPMouseClicked

    private void listTenSPMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listTenSPMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_listTenSPMouseEntered

    private void txtTienNhanCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTienNhanCaretUpdate

    }//GEN-LAST:event_txtTienNhanCaretUpdate

    private void txtTienNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTienNhanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTienNhanActionPerformed

    private void txtTienThuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTienThuaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTienThuaActionPerformed

    private void txtTongcongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTongcongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTongcongActionPerformed

    private void txtThanhTienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtThanhTienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtThanhTienActionPerformed
    HoaDonDAO daohd = new HoaDonDAO();
    HoaDon getModelHD(){
       HoaDon hd = new HoaDon();
       
       return hd; 
    }
    void insertSP() {
        HoaDon model = getModelHD();
        

                daohd.insert(model);
     
    }
    private void btnInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInActionPerformed
        
        for(ChiTietHoaDon ct : arrayct){
            daoct.insert(ct);
        }
        
    
    
//        try {
//            JasperReport rpt = JasperCompileManager.compileReport("C:\\Users\\phucl\\OneDrive\\Documents\\NetBeansProjects\\QLCafe\\src\\com\\coffee\\ui\\HoaDon.jrxml");
//            String user = "sa";
//            String pass = "123";
//            String url = "jdbc:sqlserver://localhost:1433;database=DuAn1";
//            Connection conn = DriverManager.getConnection(url, user, pass);
//            JasperPrint p = JasperFillManager.fillReport(rpt, null, conn);
//            JasperViewer.viewReport(p, false);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }//GEN-LAST:event_btnInActionPerformed

    private void tblBillMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBillMousePressed
        if (evt.getClickCount() == 1) {
            this.index = tblBill.rowAtPoint(evt.getPoint());
            if (this.index >= 0) {

            }
        }
    }//GEN-LAST:event_tblBillMousePressed

    private void txtMaHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaHoaDonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaHoaDonActionPerformed

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
            java.util.logging.Logger.getLogger(TinhTienJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TinhTienJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TinhTienJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TinhTienJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                TinhTienJDialog dialog = new TinhTienJDialog(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnIn;
    private javax.swing.JButton btn_AddHD;
    private javax.swing.JButton btn_DeleteHD;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboTenBan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private com.k33ptoo.components.KGradientPanel kGradientPanel1;
    private com.k33ptoo.components.KGradientPanel kGradientPanel2;
    private com.k33ptoo.components.KGradientPanel kGradientPanel3;
    private javax.swing.JLabel lblGia;
    private javax.swing.JLabel lblMaSP;
    private javax.swing.JLabel lblTenSP;
    private javax.swing.JLabel lblThanhTien;
    private javax.swing.JLabel lbl_timeNow;
    private javax.swing.JList<String> listTenSP;
    private javax.swing.JRadioButton rdoSizeL;
    private javax.swing.JRadioButton rdoSizeM;
    private javax.swing.JSpinner spinerSoLuong;
    private rojerusan.RSTableMetro tblBill;
    private javax.swing.JTextField txtMaHoaDon;
    private javax.swing.JTextField txtMaKH;
    private javax.swing.JTextField txtThanhTien;
    private javax.swing.JTextField txtTienNhan;
    private javax.swing.JTextField txtTienThua;
    private javax.swing.JTextField txtTongcong;
    // End of variables declaration//GEN-END:variables

    private void InsertListChitietHoaDon(ArrayList<ChiTietHoaDon> arrayct) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
