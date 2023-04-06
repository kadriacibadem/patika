package com.patikadev.View;

import com.patikadev.Helper.Config;
import com.patikadev.Helper.Helper;
import com.patikadev.Model.Content;
import com.patikadev.Model.Course;
import com.patikadev.Model.Educator;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class EducatorGUI extends JFrame{
    private JPanel wrapper;
    private JTabbedPane tab_content;
    private JLabel lbl_welcome;
    private JButton btn_logout;
    private JPanel pnl_courselist;
    private JScrollPane scrl_courselist;
    private JTable tbl_course;
    private JPanel pnl_content;
    private JScrollPane scrl_content;
    private JTable tbl_content;
    private JTextField fld_question;
    private JTextField fld_first;
    private JTextField fld_second;
    private JTextField fld_third;
    private JButton btn_send_question;
    private JTextField fld_question_content_id;

    private JPopupMenu popup_course;
    private JPopupMenu popup_content;

    private DefaultTableModel mdl_course_list;
    private Object[] row_course_list;

    private DefaultTableModel mdl_content_list;
    private Object[] row_content_list;
    private Educator educator;


    public EducatorGUI(Educator educator) {
        this.educator = educator;
        add(wrapper);
        setSize(1000, 500);
        setLocation(Helper.screenCenter("x", getSize()), Helper.screenCenter("y", getSize()));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);

        lbl_welcome.setText("Hoşgeldiniz " + educator.getName());
        loadCourseModel();
        loadContentModel();

        popup_course = new JPopupMenu();
        popup_content = new JPopupMenu();
        JMenuItem addContent = new JMenuItem("İçerik Ekle");
        JMenuItem deleteContent = new JMenuItem("İçerik Sil");
        popup_content.add(deleteContent);
        popup_course.add(addContent);

        addContent.addActionListener(e -> {
            int selectedRow = tbl_course.getSelectedRow();
            if(selectedRow == -1){
                Helper.showMsg("error");
            }else{
                int course_id = (int) tbl_course.getValueAt(selectedRow, 0);
                new ContentGUI(course_id, educator.getId()).addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        loadContentModel();
                    }
                });
            }
        });

        deleteContent.addActionListener(e -> {
            int selectedRow = tbl_content.getSelectedRow();
            if(selectedRow == -1){
                Helper.showMsg("error");
            }else{
                int id = (int) tbl_content.getValueAt(selectedRow, 0);
                Content.deleteContent(id);
                loadCourseModel();
                loadContentModel();
            }
        });

        tbl_course.setComponentPopupMenu(popup_course);
        tbl_content.setComponentPopupMenu(popup_content);
        tbl_course.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                int row = tbl_course.rowAtPoint(evt.getPoint());
                int col = tbl_course.columnAtPoint(evt.getPoint());
                if (row >= 0 && col >= 0) {
                    tbl_course.setRowSelectionInterval(row, row);
                }
            }
        });

        tbl_content.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                int row = tbl_content.rowAtPoint(evt.getPoint());
                int col = tbl_content.columnAtPoint(evt.getPoint());
                if (row >= 0 && col >= 0) {
                    tbl_content.setRowSelectionInterval(row, row);
                    fld_question_content_id.setText(tbl_content.getValueAt(row, 0).toString());
                }
            }
        });

        tbl_content.getModel().addTableModelListener(e->{
            if(e.getType() == TableModelEvent.UPDATE){
                int id = (int) tbl_content.getValueAt(e.getFirstRow(), 0);
                String title = (String) tbl_content.getValueAt(e.getFirstRow(), 1);
                String explanation = (String) tbl_content.getValueAt(e.getFirstRow(), 2);
                String link = (String) tbl_content.getValueAt(e.getFirstRow(), 3);
                Content.updateContent(id, title, explanation, link);
                loadContentModel();
            }
        });

        btn_send_question.addActionListener(e->{
            int content_id = Integer.parseInt(fld_question_content_id.getText());
            String question = fld_question.getText();
            String first = fld_first.getText();
            String second = fld_second.getText();
            String third = fld_third.getText();
            if(question.equals("") || first.equals("") || second.equals("") || third.equals("")){
                Helper.showMsg("fill");
            }else{
                Content.addQuestion(content_id, question, first, second, third);
                Helper.showMsg("success");
            }
            fld_question.setText("");
            fld_first.setText("");
            fld_second.setText("");
            fld_third.setText("");
            fld_question_content_id.setText("");
        });
        btn_logout.addActionListener(e->{
            dispose();
            new LoginGUI();
        });
    }

    public void loadCourseModel(){
        mdl_course_list = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;

            }};
        mdl_course_list.setColumnIdentifiers(new String[]{"ID", "Ders Adı", "Dersin Dili"});
        row_course_list = new Object[mdl_course_list.getColumnCount()];
        tbl_course.setModel(mdl_course_list);

        for(Course c : Course.getListByUser(51)){
            row_course_list[0] = c.getId();
            row_course_list[1] = c.getName();
            row_course_list[2] = c.getLang();
            mdl_course_list.addRow(row_course_list);
        }
        tbl_course.getTableHeader().setReorderingAllowed(false);
        tbl_course.getColumnModel().getColumn(0).setMaxWidth(75);

    }

    public void loadContentModel() {
        mdl_content_list = new DefaultTableModel();
        mdl_content_list.setColumnIdentifiers(new String[]{"ID", "İçerik Başlığı", "İçerik Açıklaması", "İçerik Linki"});
        row_content_list = new Object[mdl_content_list.getColumnCount()];
        tbl_content.setModel(mdl_content_list);

        for(Content c : Content.getFetchContent(educator.getId())){
            row_content_list[0] = c.getId();
            row_content_list[1] = c.getTitle();
            row_content_list[2] = c.getExplanation();
            row_content_list[3] = c.getLink();
            mdl_content_list.addRow(row_content_list);
        }
        tbl_content.getTableHeader().setReorderingAllowed(false);
        tbl_content.getColumnModel().getColumn(0).setMaxWidth(75);
    }

    public static void main(String[] args) {
        Helper.setLayout();
        EducatorGUI educatorGUI = new EducatorGUI(new Educator());
    }
}
