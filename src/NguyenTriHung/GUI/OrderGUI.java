/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NguyenTriHung.GUI;

import NguyenTriHung.DAO.CustomerDAO;
import NguyenTriHung.DAO.billDAO;
import NguyenTriHung.DAO.orderDAO;
import NguyenTriHung.DAO.productDAO;
import NguyenTriHung.DTO.CustomerDTO;
import NguyenTriHung.DTO.mycombobox;
import NguyenTriHung.DTO.orderDTO;
import NguyenTriHung.DTO.productDTO;
import NguyenTriHung.DTO.userDTO;
import java.awt.Color;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author hung
 */
public class OrderGUI extends javax.swing.JFrame {

    userDTO dto = null;
    DefaultTableModel tblModel_1 = null;
    DefaultComboBoxModel cbbModel = null;
    DefaultTableModel tblModel_2 = null;
    DefaultTableModel tblModel_3 = null;
    DefaultTableModel tblModel_4 = null;
    DefaultTableModel tblModel_5 = null;
    int totalAll = 0;
    int countCustomer = 0;

    /**
     * Creates new form OrderGUI
     */
    public OrderGUI() {
        initComponents();

    }

    public OrderGUI(userDTO dto) {
        initComponents();
        this.dto = dto;
        tblclick.setSize(350, 350);
        showallorder.setSize(830, 280);
        tblModel_1 = (DefaultTableModel) tblcustomer.getModel();
        cbbModel = (DefaultComboBoxModel) cbbSearch.getModel();
        tblModel_2 = (DefaultTableModel) tblproduct.getModel();
        tblModel_3 = (DefaultTableModel) tblorder.getModel();
        tblModel_4 = (DefaultTableModel) tblallorder.getModel();
        tblModel_5 = (DefaultTableModel) tblallorder_product.getModel();
        loadData();
        loadData_loaiKH();
        txtTotal.setBackground(Color.WHITE);
        txtTotal.setOpaque(true);
        txtstaff.setText(dto.getUsername());
        loadData_allOrder();

    }

    public void autoCreateBill_ID() {
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("ss");
        String billID = "DA_" + df.format(date);
        txtorderid.setText(billID);
    }

    public void autoCreateBill_Date() {
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        txtdate.setText(df.format(date));
    }

    public void loadData() {
        tblModel_1.setRowCount(0);
        CustomerDAO dao = new CustomerDAO();
        Vector<CustomerDTO> dto = dao.showDetails();
        for (CustomerDTO customerDTO : dto) {
            tblModel_1.addRow(customerDTO.toVector2());
        }
    }

    public void click_mouse_tblcustomer(String id) {
        CustomerDAO dao = new CustomerDAO();
        CustomerDTO dto = dao.showList(id);
        txtcustomerid.setText(dto.getID());

    }

    public void click_mouse_tblproduct(int iD) {
        productDAO dao = new productDAO();
        productDTO dto = dao.showList(iD);
        txtID1.setText("" + dto.getID());
        txtname1.setText(dto.getName());
        txtprice1.setText("" + dto.getPrice());
        txtdescription1.setText("" + dto.getDescription());
    }

    private void loadData_loaiKH() {
        cbbModel.removeAllElements();
        orderDAO dao = new orderDAO();
        Vector<mycombobox> mb = dao.showDetails_Category();
        for (mycombobox object : mb) {
            cbbModel.addElement(object.toString());
        }
    }

    public void loadData_Order() {
        String orderid = txtorderid.getText();
        tblModel_3.setRowCount(0);
        orderDAO dao = new orderDAO();
        Vector<orderDTO> dto = dao.showDetails_Order(orderid);
        for (orderDTO dTO : dto) {
            tblModel_3.addRow(dTO.toVector());
        }
    }

    public void loadData_allOrder() {
        tblModel_4.setRowCount(0);
        orderDAO dao = new orderDAO();
        Vector<orderDTO> dto = dao.showDetails_Allorder();
        for (orderDTO dTO : dto) {
            tblModel_4.addRow(dTO.toVector2());
        }
    }

    public List<orderDTO> collectDataOfBill() {
        List<orderDTO> billDetail = new ArrayList<>();
        try {
            String orderID = txtorderid.getText();
            String phoneCustomer = txtcustomerid.getText();
            String staff = txtstaff.getText();
            String date = txtdate.getText();
            for (int i = 0; i < tblModel_3.getRowCount(); i++) {
                String name = (String) tblModel_3.getValueAt(i, 0);
                int price = (int) tblModel_3.getValueAt(i, 1);
                int quantity = (int) tblModel_3.getValueAt(i, 2);
                int quantity_x_price = (int) tblModel_3.getValueAt(i, 3);
                billDetail.add(new orderDTO(name, price, quantity, orderID, phoneCustomer, date, staff));
            }
        } catch (Exception e) {
        }
        return billDetail;
    }

    public void loadData_allorder_product(String id, String date, String orderID) {
        tblModel_5.setRowCount(0);
        billDAO dao = new billDAO();
        Vector<orderDTO> dto = dao.showBill_Product(id, date, orderID);
        for (orderDTO dTO : dto) {
            tblModel_5.addRow(dTO.toVector());
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

        tblclick = new javax.swing.JDialog();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtID1 = new javax.swing.JTextField();
        txtname1 = new javax.swing.JTextField();
        txtprice1 = new javax.swing.JTextField();
        txtquantity1 = new javax.swing.JTextField();
        txtdescription1 = new javax.swing.JTextField();
        btnOK = new javax.swing.JButton();
        showallorder = new javax.swing.JDialog();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblallorder = new javax.swing.JTable();
        btnprint = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblallorder_product = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblcustomer = new javax.swing.JTable();
        cbbSearch = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblproduct = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtorderid = new javax.swing.JTextField();
        txtdate = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblorder = new javax.swing.JTable();
        txtTotal = new javax.swing.JLabel();
        btninsert = new javax.swing.JButton();
        btnshowall = new javax.swing.JButton();
        btnremove = new javax.swing.JButton();
        btnclear = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtcustomerid = new javax.swing.JTextField();
        btnquantity = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        txtstaff = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        jPanel2.setBackground(new java.awt.Color(255, 153, 51));

        jLabel4.setText("ID:");

        jLabel5.setText("Name:");

        jLabel6.setText("Price:");

        jLabel7.setText("Quantity:");

        jLabel8.setText("Description:");

        btnOK.setText("OK");
        btnOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOKActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtname1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtID1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtprice1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtdescription1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtquantity1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(116, 116, 116)
                        .addComponent(btnOK, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(txtID1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtname1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtprice1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtquantity1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(txtdescription1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(btnOK)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout tblclickLayout = new javax.swing.GroupLayout(tblclick.getContentPane());
        tblclick.getContentPane().setLayout(tblclickLayout);
        tblclickLayout.setHorizontalGroup(
            tblclickLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        tblclickLayout.setVerticalGroup(
            tblclickLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(255, 153, 51));

        tblallorder.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "orderID", "customerID", "Date", "Total"
            }
        ));
        tblallorder.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblallorderMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblallorder);

        btnprint.setBackground(new java.awt.Color(0, 102, 255));
        btnprint.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnprint.setText("PRINT SELECTED ORDER");
        btnprint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnprintActionPerformed(evt);
            }
        });

        tblallorder_product.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "nameProduct", "Price", "Quantity", "Quantity-x-Price"
            }
        ));
        jScrollPane5.setViewportView(tblallorder_product);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap(578, Short.MAX_VALUE)
                        .addComponent(btnprint))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnprint, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout showallorderLayout = new javax.swing.GroupLayout(showallorder.getContentPane());
        showallorder.getContentPane().setLayout(showallorderLayout);
        showallorderLayout.setHorizontalGroup(
            showallorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        showallorderLayout.setVerticalGroup(
            showallorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));

        tblcustomer.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "lastname", "phone"
            }
        ));
        tblcustomer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblcustomerMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblcustomer);

        cbbSearch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        cbbSearch.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbSearchItemStateChanged(evt);
            }
        });

        tblproduct.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Price", "Description", "Quantity"
            }
        ));
        tblproduct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblproductMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblproduct);
        if (tblproduct.getColumnModel().getColumnCount() > 0) {
            tblproduct.getColumnModel().getColumn(2).setResizable(false);
            tblproduct.getColumnModel().getColumn(3).setResizable(false);
        }

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("OrderID:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Date:");

        tblorder.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Price", "Quantity", "Quantity x Price"
            }
        ));
        jScrollPane3.setViewportView(tblorder);

        txtTotal.setBackground(new java.awt.Color(255, 255, 255));
        txtTotal.setText("Total:");

        btninsert.setText("Insert order");
        btninsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btninsertActionPerformed(evt);
            }
        });

        btnshowall.setText("show all order");
        btnshowall.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnshowallActionPerformed(evt);
            }
        });

        btnremove.setText("Remove product");
        btnremove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnremoveActionPerformed(evt);
            }
        });

        btnclear.setText("Clear");
        btnclear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnclearActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Customer:");

        btnquantity.setText(">>>");
        btnquantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnquantityActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Staff:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane2)
                        .addComponent(jScrollPane1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(cbbSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(144, 144, 144)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtorderid)
                            .addComponent(txtcustomerid, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                            .addComponent(txtdate, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                            .addComponent(txtstaff))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(btnquantity)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(btninsert)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnshowall)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnremove)
                                        .addGap(11, 11, 11)
                                        .addComponent(btnclear, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jScrollPane3)))
                            .addComponent(txtTotal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtcustomerid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtorderid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txtstaff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbbSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btninsert)
                    .addComponent(btnshowall)
                    .addComponent(btnremove)
                    .addComponent(btnclear))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(99, 99, 99)
                                .addComponent(btnquantity))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        jMenu1.setText("File");
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu1MouseClicked(evt);
            }
        });

        jMenuItem1.setText("Change password");
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblcustomerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblcustomerMouseClicked
        // TODO add your handling code here:
        int click = tblcustomer.getSelectedRow();
        if (click >= 0) {
            click_mouse_tblcustomer((String) tblModel_1.getValueAt(click, 0));
        }
    }//GEN-LAST:event_tblcustomerMouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        autoCreateBill_ID();
        autoCreateBill_Date();
        txtcustomerid.setEditable(false);
        txtdate.setEditable(false);
        txtorderid.setEditable(false);
        txtstaff.setEditable(false);

    }//GEN-LAST:event_formWindowOpened

    private void btnquantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnquantityActionPerformed
        // TODO add your handling code here:
        tblclick.setVisible(true);


    }//GEN-LAST:event_btnquantityActionPerformed

    private void tblproductMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblproductMouseClicked
        // TODO add your handling code here:
        try {
            int clicked = tblproduct.getSelectedRow();
            if (clicked >= 0) {
                click_mouse_tblproduct((int) tblModel_2.getValueAt(clicked, 0));
            }
            txtID1.setEditable(false);
            txtname1.setEditable(false);
            txtprice1.setEditable(false);
            txtdescription1.setEditable(false);
        } catch (Exception e) {
        }


    }//GEN-LAST:event_tblproductMouseClicked

    private void btnOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOKActionPerformed
        // TODO add your handling code here:
        try {

            int row = tblproduct.getSelectedRow();
            int IDproduct = (int) tblproduct.getValueAt(row, 0);

            productDAO productdao = new productDAO();
            productDTO productdto = productdao.showList(IDproduct);

            orderDAO dao = new orderDAO();
            String name = txtname1.getText();
            int price = Integer.parseInt(txtprice1.getText());
            int quantity = Integer.parseInt(txtquantity1.getText());
            int maxquantity = Integer.valueOf(productdto.getQuantity());
            int quantity_x_price = price * quantity;
            String orderid = txtorderid.getText();
            String phoneCustomer = txtcustomerid.getText();
            boolean check = true;
            if (quantity > 0 && quantity <= maxquantity) {
                totalAll += quantity_x_price;
                for (int i = 0; i < tblorder.getRowCount(); i++) {
                    String nametbl = (String) tblorder.getValueAt(i, 0);
                    if (nametbl.equalsIgnoreCase(name)) {
                        int oldprice = (int) tblorder.getValueAt(i, 1);
                        int oldquantity = (int) tblorder.getValueAt(i, 2);
                        quantity += oldquantity;
                        quantity_x_price = quantity * price;
                        tblorder.setValueAt(quantity, i, 2);
                        tblorder.setValueAt(quantity_x_price, i, 3);
                        check = false;

                    }
                }
                if (check) {
                    Vector v = new Vector();
                    v.add(name);
                    v.add(price);
                    v.add(quantity);
                    v.add(quantity_x_price);
                    v.add(orderid);
                    v.add(phoneCustomer);

                    tblModel_3.addRow(v);

                }
                txtTotal.setText("Total: " + totalAll);

            } else {
                if (quantity == 0) {
                    JOptionPane.showMessageDialog(null, "Please choose quantity!!", "Error!!!", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Max quantity of this service is " + maxquantity + "!!", "Error!!!", JOptionPane.ERROR_MESSAGE);
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Dòng quantity sai rồi kìa.", "Error", 0);
        }

        tblclick.dispose();


    }//GEN-LAST:event_btnOKActionPerformed

    private void btnremoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnremoveActionPerformed
        // TODO add your handling code here:
        int row = tblorder.getSelectedRow();
        if (row >= 0) {
            int totalrow = (int) tblModel_3.getValueAt(row, 3);
            totalAll -= totalrow;
            tblModel_3.removeRow(row);
            txtTotal.setText("Total: " + String.valueOf(totalAll));
            JOptionPane.showMessageDialog(this, "Remove success!");
        } else {
            JOptionPane.showMessageDialog(this, "Please choose an product");
        }
    }//GEN-LAST:event_btnremoveActionPerformed

    private void btninsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btninsertActionPerformed
        // TODO add your handling code here:
        boolean check = true;
        boolean check2 = true;
        if (tblorder.getRowCount() <= 0) {
            JOptionPane.showMessageDialog(null, "Empty Table.", "Error!", 0);
        } else {
            orderDAO dao = new orderDAO();
            for (int i = 0; i < tblorder.getRowCount(); i++) {
                String name = (String) tblorder.getValueAt(i, 0);
                int price = (int) tblorder.getValueAt(i, 1);
                int quantity = (int) tblorder.getValueAt(i, 2);
                dao = new orderDAO();
                productDTO temp_2 = dao.findNameProduct(name);
                int quantitySQL = temp_2.getQuantity();
                if (quantitySQL < quantity) {
                    JOptionPane.showMessageDialog(this, "Không đủ hàng đẻ bán!");
                    return;
                }
            }
            String orderid = txtorderid.getText();
            String IDcustomer = txtcustomerid.getText();
            String date = txtdate.getText();
            String staff = txtstaff.getText();
            if (!dao.check_Order(IDcustomer)) {
                return;
            }

            for (int i = 0; i < tblorder.getRowCount(); i++) {
                String name = (String) tblorder.getValueAt(i, 0);
                int price = (int) tblorder.getValueAt(i, 1);
                int quantity = (int) tblorder.getValueAt(i, 2);
                int quantity_x_price = price * quantity;

                productDAO productdao = new productDAO();

                orderDTO dto = new orderDTO(name, price, quantity, quantity_x_price, orderid, IDcustomer, date, staff);
                List<orderDTO> listBillDetail = collectDataOfBill();
                productDTO temp_2 = dao.findNameProduct(name);
                int quantitySQL = temp_2.getQuantity();

                temp_2.setQuantity(temp_2.getQuantity() - quantity);

                check = dao.Insert_Order2(dto, listBillDetail) && dao.update_quantity(temp_2);
            }
            if (check) {
                JOptionPane.showMessageDialog(this, "Export bill success.");
                cbbSearchItemStateChanged(null);
            } else {
                JOptionPane.showMessageDialog(null, "Export bill failed.", "Error!", 0);
            }

        }
    }//GEN-LAST:event_btninsertActionPerformed

    private void btnshowallActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnshowallActionPerformed
        // TODO add your handling code here:
        showallorder.setVisible(true);
        loadData_allOrder();

    }//GEN-LAST:event_btnshowallActionPerformed

    private void btnclearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnclearActionPerformed
        // TODO add your handling code here:
        while (tblModel_3.getRowCount() > 0) {
            tblModel_3.removeRow(0);
            totalAll = 0;
            txtTotal.setText("Total: " + String.valueOf(totalAll));
        }

    }//GEN-LAST:event_btnclearActionPerformed

    private void cbbSearchItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbSearchItemStateChanged
        // TODO add your handling code here:
        orderDAO dao = new orderDAO();
        tblModel_2.setRowCount(0);
        if (cbbSearch.getSelectedIndex() >= 0) {
            String category = cbbSearch.getSelectedItem().toString();
            Vector<productDTO> dto = dao.showDetails_Product(category);
            for (productDTO dTO : dto) {
                tblModel_2.addRow(dTO.toVector2());
            }
        }
    }//GEN-LAST:event_cbbSearchItemStateChanged

    private void btnprintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnprintActionPerformed
        // TODO add your handling code here:
        Writer writer = null;
        billDAO dao = new billDAO();
        int click = tblallorder.getSelectedRow();
        if (click >= 0) {
            String orderID = (String) tblallorder.getValueAt(click, 0);
            String id = (String) tblallorder.getValueAt(click, 1);
            String date = (String) tblallorder.getValueAt(click, 2);
            int total = (int) tblallorder.getValueAt(click, 3);
            try {
                writer = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream("filename.txt"), "utf-8"));

                writer.write("CustomerID:" + id + "\tDate: " + date + "\n");
                writer.write("orderID: " + orderID);
                writer.write("\n\t\tTHE 7ELEVENT MINIMART\r\n");
                writer.write("\t\t\t\t\tĐịa chỉ: 590 CMT8, P.11, Q.3, TPHCM\r\n");
                writer.write("\t\t\t\t\tSĐT: 01212692802\r\n");
                writer.write("---------------------------------------------------------------------------------\n");

                for (int i = 0; i < tblallorder_product.getRowCount(); i++) {
                    String s1 = (String) tblallorder_product.getValueAt(i, 0);
                    int s2 = (int) tblallorder_product.getValueAt(i, 1);
                    int s3 = (int) tblallorder_product.getValueAt(i, 2);
                    int s4 = (int) tblallorder_product.getValueAt(i, 3);
                    writer.write("nameProduct: " + s1 + " \tprice: " + s2 + " \tquantity: " + s3 + " \tquantity_x_price: " + s4 + "\n");
                    writer.write("---------------------------------------------------------------------------------\n");
                }
                writer.write("\t\t\t\t\t\t\t\tTổng tiền: " + total + "\r\n\r\n");
                writer.close();

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, ex);
                // Report
            } finally {
                try {
                    writer.close();
                } catch (Exception ex) {/*ignore*/
                }
            }
        }


    }//GEN-LAST:event_btnprintActionPerformed

    private void tblallorderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblallorderMouseClicked
        // TODO add your handling code here:
        billDAO dao = new billDAO();
        try {
            int clicked = tblallorder.getSelectedRow();
            if (clicked >= 0) {
                String orderID = (String) tblModel_4.getValueAt(clicked, 0);
                String idcustomer = (String) tblModel_4.getValueAt(clicked, 1);
                String date = (String) tblModel_4.getValueAt(clicked, 2);
                loadData_allorder_product(idcustomer, date, orderID);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }//GEN-LAST:event_tblallorderMouseClicked

    private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseClicked
        // TODO add your handling code here:
        ChangepasswordGUI gui = new ChangepasswordGUI(dto);

        gui.setVisible(true);
        this.dispose();

    }//GEN-LAST:event_jMenu1MouseClicked

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
            java.util.logging.Logger.getLogger(OrderGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OrderGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OrderGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OrderGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new OrderGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnOK;
    private javax.swing.JButton btnclear;
    private javax.swing.JButton btninsert;
    private javax.swing.JButton btnprint;
    private javax.swing.JButton btnquantity;
    private javax.swing.JButton btnremove;
    private javax.swing.JButton btnshowall;
    private javax.swing.JComboBox<String> cbbSearch;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JDialog showallorder;
    private javax.swing.JTable tblallorder;
    private javax.swing.JTable tblallorder_product;
    private javax.swing.JDialog tblclick;
    private javax.swing.JTable tblcustomer;
    private javax.swing.JTable tblorder;
    private javax.swing.JTable tblproduct;
    private javax.swing.JTextField txtID1;
    private javax.swing.JLabel txtTotal;
    private javax.swing.JTextField txtcustomerid;
    private javax.swing.JTextField txtdate;
    private javax.swing.JTextField txtdescription1;
    private javax.swing.JTextField txtname1;
    private javax.swing.JTextField txtorderid;
    private javax.swing.JTextField txtprice1;
    private javax.swing.JTextField txtquantity1;
    private javax.swing.JTextField txtstaff;
    // End of variables declaration//GEN-END:variables
}
