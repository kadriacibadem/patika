package com.patikadev.View;

import com.patikadev.Helper.Config;
import com.patikadev.Helper.Helper;
import com.patikadev.Helper.Item;
import com.patikadev.Model.Course;
import com.patikadev.Model.Operator;
import com.patikadev.Model.Patika;
import com.patikadev.Model.User;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class OperatorGUI extends JFrame {

    private JPanel wrapper;
    private JTabbedPane pnl_course_list;
    private JLabel lbl_welcome;
    private JPanel pnl_top;
    private JButton btn_logout;
    private JPanel pnl_user_list;
    private JScrollPane scrl_user_list;
    private JTable tbl_user_list;
    private JPanel pnl_user_form;
    private JTextField fld_user_name;
    private JTextField fld_user_username;
    private JPasswordField fld_user_pass;
    private JComboBox cmb_usertype;
    private JButton btn_user_add;
    private JTextField fld_user_id;
    private JButton btn_delete;
    private JTextField fld_search_user_name;
    private JTextField fld_search_user_username;
    private JComboBox cmb_search_user_type;
    private JButton btn_search_search;
    private JPanel pnl_patika_list;
    private JScrollPane scrl_patika_list;
    private JTable tbl_patika_list;
    private JPanel pnl_patika_add;
    private JTextField fld_patika_name;
    private JButton btn_patika_add;
    private JScrollPane scrl_course_list;
    private JTable tbl_course_list;
    private JPanel pnl_course_add;
    private JTextField fld_course_name;
    private JTextField fld_course_lang;
    private JComboBox cmb_course_patika;
    private JComboBox cmb_course_user;
    private JButton btn_course_add;
    private DefaultTableModel mdl_user_list;
    private Object[] row_user_list;

    private DefaultTableModel mdl_patika_list;
    private Object[] row_patika_list;
    private JPopupMenu patikaMenu;

    private DefaultTableModel mdl_course_list;
    private Object[] row_course_list;

    private final Operator operator;

    public OperatorGUI(Operator operator){
        this.operator = operator;

        add(wrapper);
        setSize(1000,500);
        setLocation(Helper.screenCenter("x", getSize()),Helper.screenCenter("y", getSize()));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);

        lbl_welcome.setText("Hoşgeldin " + operator.getName());

        // ModelUserList
        loadUserModel();

        tbl_user_list.getSelectionModel().addListSelectionListener(e -> {
            if(tbl_user_list.getSelectedRow() != -1){
                fld_user_id.setText(tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(),0).toString());
            }
        });

        tbl_user_list.getModel().addTableModelListener(e -> {
            if(e.getType() == TableModelEvent.UPDATE){
                int id = Integer.parseInt(tbl_user_list.getValueAt(e.getFirstRow(),0).toString());
                String name = tbl_user_list.getValueAt(e.getFirstRow(),1).toString();
                String username = tbl_user_list.getValueAt(e.getFirstRow(),2).toString();
                String password = tbl_user_list.getValueAt(e.getFirstRow(),3).toString();
                String type = tbl_user_list.getValueAt(e.getFirstRow(),4).toString();
                User user = new User(id,name,username,password,type);
                if(User.update(id,name,username,password,type)){
                    Helper.showMsg("ok");
                }
                loadUserModel();
                loadEducatorCombo();
                loadCourseModel();
            }
        });

        patikaMenu = new JPopupMenu();
        JMenuItem updateMenu = new JMenuItem("Güncelle");
        JMenuItem deleteMenu = new JMenuItem("Sil");
        patikaMenu.add(updateMenu);
        patikaMenu.add(deleteMenu);

        updateMenu.addActionListener(e -> {
            int id = Integer.parseInt(tbl_patika_list.getValueAt(tbl_patika_list.getSelectedRow(),0).toString());
            UpdatePatikaGUI updatePatikaGUI = new UpdatePatikaGUI(Patika.getFetch(id));
            updatePatikaGUI.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                    loadPatikaList();
                    loadPatikaCombo();
                    loadEducatorCombo();
                    loadCourseModel();
                }
            });
        });

        deleteMenu.addActionListener(e -> {
            if(Helper.confirm()){
                int id = Integer.parseInt(tbl_patika_list.getValueAt(tbl_patika_list.getSelectedRow(),0).toString());
                if(Patika.delete(id)){
                    Helper.showMsg("ok");
                    loadPatikaList();
                    loadPatikaCombo();
                    loadEducatorCombo();
                    loadCourseModel();
                }
            }
        });
        tbl_patika_list.addMouseListener(new java.awt.event.MouseAdapter(){
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                int row = tbl_patika_list.rowAtPoint(evt.getPoint());
                int col = tbl_patika_list.columnAtPoint(evt.getPoint());
                if (row >= 0 && col >= 0) {
                    tbl_patika_list.setRowSelectionInterval(row, row);
                    tbl_patika_list.setColumnSelectionInterval(col, col);
                }
                if (evt.getButton() == java.awt.event.MouseEvent.BUTTON3) {
                    patikaMenu.show(tbl_patika_list, evt.getX(), evt.getY());
                }
            }
        });
        loadPatikaList();

        btn_user_add.addActionListener(e -> {
            if(Helper.isEmpty(fld_user_name) || Helper.isEmpty(fld_user_username) || Helper.isEmpty(fld_user_pass)){
                Helper.showMsg("fill");
            }else{
                User user = new User();
                user.setName(fld_user_name.getText());
                user.setUsername(fld_user_username.getText());
                user.setPassword(fld_user_pass.getText());
                user.setType(cmb_usertype.getSelectedItem().toString());
                if(User.add(fld_user_name.getText(),fld_user_username.getText(),fld_user_pass.getText(),cmb_usertype.getSelectedItem().toString())){
                    Helper.showMsg("ok");
                    loadEducatorCombo();
                    row_user_list = new Object[]{
                            Collections.max(User.getList()).getId(),
                            user.getName(),
                            user.getUsername(),
                            user.getPassword(),
                            user.getType()
                    };
                    mdl_user_list.addRow(row_user_list);
                    fld_user_name.setText("");
                    fld_user_username.setText("");
                    fld_user_pass.setText("");
                    cmb_usertype.setSelectedIndex(0);
                }

            }
        });
        btn_delete.addActionListener(e -> {
            if(Helper.isEmpty(fld_user_id)){
                Helper.showMsg("fill");
            }else{
                if(Helper.confirm()){
                    User user = User.getFetchById(Integer.parseInt(fld_user_id.getText()));
                    if(user != null){
                        if(User.delete(Integer.parseInt(fld_user_id.getText()))){
                            Helper.showMsg("ok");
                            loadUserModel();
                            loadEducatorCombo();
                            loadCourseModel();
                            fld_user_id.setText("");
                        }
                    }else{
                        Helper.showMsg("notfound");
                    }
                }
            }

        });
        btn_search_search.addActionListener(e ->{
            String name = fld_search_user_name.getText();
            String username = fld_search_user_username.getText();
            String type = cmb_search_user_type.getSelectedItem().toString();
            String query = User.searchQuery(name,username,type);
            ArrayList<User> searchingUser = User.searchUserList(query);
            loadUserModel(searchingUser);
        });
        btn_logout.addActionListener(e -> {
            dispose();
            LoginGUI loginGUI = new LoginGUI();
        });
        btn_patika_add.addActionListener(e->{
            if(Helper.isEmpty(fld_patika_name)) {
                Helper.showMsg("fill");
            }else{
                if(Patika.add(fld_patika_name.getText())){
                    Helper.showMsg("ok");
                    loadPatikaList();
                    loadPatikaCombo();
                    fld_patika_name.setText("");
                }else{
                    Helper.showMsg("error");
                }
            }
        });

        // CourseList
        mdl_course_list = new DefaultTableModel();
        Object[] col_course_list = {"ID","Kurs Adı","Programlama Dili","Patika","Eğitmen Adı"};
        mdl_course_list.setColumnIdentifiers(col_course_list);
        row_course_list = new Object[col_course_list.length];
        loadCourseModel();
        tbl_course_list.setModel(mdl_course_list);
        tbl_course_list.getTableHeader().setReorderingAllowed(false);
        tbl_course_list.getColumnModel().getColumn(0).setMaxWidth(75);

        loadPatikaCombo();
        loadEducatorCombo();

        // ##### CourseList
        btn_course_add.addActionListener(e->{
            Item patikaItem = (Item) cmb_course_patika.getSelectedItem();
            Item userItem = (Item) cmb_course_user.getSelectedItem();
            if(Helper.isEmpty(fld_course_name) || Helper.isEmpty(fld_course_lang)) {
                Helper.showMsg("fill");
            }else{
                if(Course.add(userItem.getKey(),patikaItem.getKey(),fld_course_name.getText(),fld_course_lang.getText())){
                    Helper.showMsg("ok");
                    loadCourseModel();
                    fld_course_name.setText("");
                    fld_course_lang.setText("");
                    cmb_course_patika.setSelectedIndex(0);
                    cmb_course_user.setSelectedIndex(0);
                }else{
                    Helper.showMsg("error");
                }
            }
        });
    }

    private void loadCourseModel() {
        for (Course obj : Course.getCourse()){
            row_course_list = new Object[]{
                    obj.getId(),
                    obj.getName(),
                    obj.getLang(),
                    obj.getPatika().getName(),
                    obj.getEducator().getName()
            };
            mdl_course_list.addRow(row_course_list);
        }
    }


    private void loadPatikaList() {
        mdl_patika_list = new DefaultTableModel();
        Object[] col_patika_list = {"ID","Patika Adı"};
        mdl_patika_list.setColumnIdentifiers(col_patika_list);
        row_patika_list = new Object[col_patika_list.length];
        for (Patika obj : Patika.getList()){
            row_patika_list = new Object[]{
                    obj.getId(),
                    obj.getName()
            };
            mdl_patika_list.addRow(row_patika_list);
        }
        tbl_patika_list.setModel(mdl_patika_list);
        tbl_patika_list.getTableHeader().setReorderingAllowed(false);
        tbl_patika_list.getColumnModel().getColumn(0).setMaxWidth(75);
        tbl_patika_list.setComponentPopupMenu(patikaMenu);
    }

    public void loadUserModel(){
        mdl_user_list = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                if(column == 0){
                    return false;
                }
                return super.isCellEditable(row, column);
            }
        };
        Object[] col_user_list = {"ID", "Ad", "Kullanıcı Adı", "Şifre", "Tip"};
        mdl_user_list.setColumnIdentifiers(col_user_list);
        for (User obj : User.getList()){
            row_user_list = new Object[]{
                    obj.getId(),
                    obj.getName(),
                    obj.getUsername(),
                    obj.getPassword(),
                    obj.getType()
            };
            mdl_user_list.addRow(row_user_list);
        }

        tbl_user_list.setModel(mdl_user_list);
        tbl_user_list.getTableHeader().setReorderingAllowed(false);
    }

    public void loadUserModel(ArrayList<User> list){
        mdl_user_list = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                if(column == 0){
                    return false;
                }
                return super.isCellEditable(row, column);
            }
        };
        Object[] col_user_list = {"ID", "Ad", "Kullanıcı Adı", "Şifre", "Tip"};
        mdl_user_list.setColumnIdentifiers(col_user_list);
        for (User obj : list){
            row_user_list = new Object[]{
                    obj.getId(),
                    obj.getName(),
                    obj.getUsername(),
                    obj.getPassword(),
                    obj.getType()
            };
            mdl_user_list.addRow(row_user_list);
        }

        tbl_user_list.setModel(mdl_user_list);
        tbl_user_list.getTableHeader().setReorderingAllowed(false);
    }

    public void loadPatikaCombo(){
        cmb_course_patika.removeAllItems();
        for (Patika obj : Patika.getList()){
            cmb_course_patika.addItem(new Item(obj.getId(),obj.getName()));
        }
    }

    public void loadEducatorCombo(){
        cmb_course_user.removeAllItems();
        for (User obj : User.getList()){
            if(obj.getType().equals("educator")){
                cmb_course_user.addItem(new Item(obj.getId(),obj.getName()));
            }
        }
    }


    public static void main(String[] args) {
        Operator op = new Operator();
        op.setId(1);
        op.setName("Kadri");
        op.setUsername("kadri");
        op.setPassword("123");
        op.setType("operator");
        Helper.setLayout();
        OperatorGUI gui = new OperatorGUI(op);
    }
}
