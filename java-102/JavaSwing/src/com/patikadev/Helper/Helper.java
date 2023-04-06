package com.patikadev.Helper;

import javax.swing.*;
import java.awt.*;

public class Helper {

    public static void setLayout(){
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }
    public static int screenCenter(String axis, Dimension size){
        int point = 0 ;
        switch (axis){
            case "x":
                point = (Toolkit.getDefaultToolkit().getScreenSize().width - size.width) / 2;
                break;
           case "y":
                point = (Toolkit.getDefaultToolkit().getScreenSize().height - size.height) / 2;
                break;
            default:
                point = 0;
        }
        return point;
    }

    public static boolean isEmpty(JTextField tf){
        return tf.getText().trim().isEmpty();
    }

    public static void showMsg(String str){
        optionPageTR();
        String msg;
        String title;
        switch (str){
            case "fill":
                msg = "Lütfen tüm alanları doldurunuz.";
                title = "Hata";
                break;
            case "done":
                msg = "İşlem başarıyla tamamlandı.";
                title = "Başarılı";
                break;
              case "error":
                msg = "Bir hata oluştu.";
                title = "Hata";
                break;

            default:
                msg = str;
                title = "Mesaj";
        }
        JOptionPane.showMessageDialog(null, msg, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public static boolean confirm(){
        optionPageTR();
        int result = JOptionPane.showConfirmDialog(null, "Emin misiniz?", "Onay", JOptionPane.YES_NO_OPTION);
        return result == JOptionPane.YES_OPTION;
    }

    public static void optionPageTR(){
        UIManager.put("OptionPane.yesButtonText", "Evet");
        UIManager.put("OptionPane.noButtonText", "Hayır");
        UIManager.put("OptionPane.cancelButtonText", "İptal");
        UIManager.put("OptionPane.okButtonText", "Tamam");
    }
}
