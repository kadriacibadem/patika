package com.patikadev.View;

import com.patikadev.Helper.Config;
import com.patikadev.Helper.Helper;
import com.patikadev.Model.Content;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContentGUI extends JFrame{
    private JPanel wrapper;
    private JPanel pnl_main;
    private JLabel lbl_content_name;
    private JPanel pnl_add_content;
    private JTextField fld_content_title;
    private JTextField fld_content_explanation;
    private JTextField fld_link;
    private JButton btn_content_add;

    public ContentGUI(int course_id, int educator_id) {
        add(wrapper);
        setSize(400, 500);
        setLocation(Helper.screenCenter("x", getSize()), Helper.screenCenter("y", getSize()));
        setVisible(true);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        btn_content_add.addActionListener(e -> {
            String title = fld_content_title.getText().trim();
            String explanation = fld_content_explanation.getText().trim();
            String link = fld_link.getText().trim();
            if(title.equals("") || explanation.equals("") || link.equals("")){
                Helper.showMsg("fill");
            }else{
                Content.addContent(course_id,educator_id,title,explanation,link);
                Helper.showMsg("success");
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        new ContentGUI(1,51);
    }
}
