package com.patikadev.View;

import com.patikadev.Helper.Config;
import com.patikadev.Helper.Helper;
import com.patikadev.Model.Course;
import com.patikadev.Model.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class StudentGUI extends JFrame{
    private Student student;
    private JPanel wrapper;
    private JTabbedPane tab_student;
    private JTable tbl_course;
    private JScrollPane scrll_course;
    private JLabel lbl_welcome;
    private JButton btn_log_out;
    private DefaultTableModel mdl_course_list;
    private Object[] row_course_list;

    public StudentGUI(Student student) {
        this.student = student;
        add(wrapper);
        setSize(500, 400);
        setLocation(Helper.screenCenter("x", getSize()),Helper.screenCenter("y", getSize()));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);

        lbl_welcome.setText("Hoşgeldin " + student.getName());
    }

    public void loadCourses(){
        mdl_course_list = new DefaultTableModel();
        Object[] columns_name = {"ID", "Name", "Dil"};
        mdl_course_list.setColumnIdentifiers(columns_name);
        row_course_list = new Object[columns_name.length];
        for(Course course : Course.getListCourse()){
            row_course_list[0] = course.getId();
            row_course_list[1] = course.getName();
            row_course_list[2] = course.getLang();
            mdl_course_list.addRow(row_course_list);
        }
        tbl_course.setModel(mdl_course_list);
        tbl_course.getTableHeader().setReorderingAllowed(false);
        tbl_course.getColumnModel().getColumn(0).setMaxWidth(75);
    };
    public static void main(String[] args) {
        Helper.setLayout();
        StudentGUI studentGUI = new StudentGUI(new Student());
    }
}
