package com.patikadev.Model;

import com.patikadev.Helper.DBConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Patika {
    private int id;
    private String name;

    public Patika(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public Patika() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static ArrayList<Patika> getList(){
        ArrayList<Patika> list = new ArrayList<>();
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM patika");
            while (rs.next()){
                list.add(new Patika(
                        rs.getInt("id"),
                        rs.getString("name")
                ));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public static boolean add(String name) {
        try {
            Statement st = DBConnector.getInstance().createStatement();
            st.executeUpdate("INSERT INTO patika (name) VALUES ('"+name+"')");
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    public static boolean update(int id,String name){
        String quey = "UPDATE patika SET name = '"+name+"' WHERE id = "+id;
        try {
            Statement st = DBConnector.getInstance().createStatement();
            st.executeUpdate(quey);
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    public static Patika getFetch(int id){
        Patika patika = null;
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM patika WHERE id = "+id+"");
            while (rs.next()){
                patika = new Patika(
                        rs.getInt("id"),
                        rs.getString("name")
                );
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return patika;
    }

    public static boolean delete(int id){
        ArrayList<Course> courses = Course.getCourse();
        for (Course course : courses) {
            if (course.getPatika_id() == id){
                Course.delete(course.getId());
            }
        }
        try {
            Statement st = DBConnector.getInstance().createStatement();
            st.executeUpdate("DELETE FROM patika WHERE id = "+id);
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }
}
