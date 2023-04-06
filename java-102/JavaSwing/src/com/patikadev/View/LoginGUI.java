package com.patikadev.View;

import com.patikadev.Helper.Config;
import com.patikadev.Helper.Helper;
import com.patikadev.Model.Educator;
import com.patikadev.Model.Operator;
import com.patikadev.Model.Student;
import com.patikadev.Model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI extends JFrame {
    private JPanel wrapper;
    private JPanel wtop;
    private JPanel wbottom;
    private JTextField fld_user_username;
    private JPasswordField fld_user_pass;
    private JButton btn_login;

    public LoginGUI() {
        add(wrapper);
        setSize(500, 400);
        setLocation(Helper.screenCenter("x", getSize()), Helper.screenCenter("y", getSize()));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        btn_login.addActionListener(e -> {
            String username = fld_user_username.getText().trim();
            String pass = String.valueOf(fld_user_pass.getPassword());
            if (username.equals("") || pass.equals("")) {
                Helper.showMsg("fill");
            } else {
                User u = User.getFetchByUsernameAndPass(username, pass);
                if (u != null) {
                    switch (u.getType()) {
                        case "operator":
                            OperatorGUI operatorGUI = new OperatorGUI((Operator) u);
                            break;
                        case "educator":
                            EducatorGUI educatorGUI = new EducatorGUI((Educator) u);
                            break;
                        case "student":
                            StudentGUI studentGUI = new StudentGUI((Student) u);
                            break;
                    }
                    dispose();
                } else {
                    Helper.showMsg("Kullanıcı adı veya şifre hatalı");
                }
            }
        });
    }

    public static void main(String[] args) {
        Helper.setLayout();
        LoginGUI loginGUI = new LoginGUI();
    }
}
