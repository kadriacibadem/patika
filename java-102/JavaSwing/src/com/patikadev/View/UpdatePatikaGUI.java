package com.patikadev.View;

import com.patikadev.Helper.Config;
import com.patikadev.Helper.Helper;
import com.patikadev.Model.Patika;

import javax.swing.*;

public class UpdatePatikaGUI extends JFrame{
    private JPanel wrapper;
    private JTextField fld_patika_name;
    private JButton btn_update;
    private Patika patika;
    public UpdatePatikaGUI(Patika patika){
        this.patika = patika;
        add(wrapper);
        setSize(300, 150);
        setLocation(Helper.screenCenter("x", getSize()), Helper.screenCenter("y", getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);

        fld_patika_name.setText(patika.getName());
        btn_update.addActionListener(e -> {
            String name = fld_patika_name.getText();
            if (name.equals("")){
                Helper.showMsg("fill");
            }else{
                patika.setName(name);
                boolean status = patika.update(patika.getId(),fld_patika_name.getText());
                if (status){
                    Helper.showMsg("done");
                }
                dispose();
            }
        });
    }
}
