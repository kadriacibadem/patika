package com.patikadev.Model;

import com.patikadev.Helper.DBConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Course {
    private int id;
    private int user_id;
    private int patika_id;
    private String name;
    private String lang;

    private Patika patika;
    private User educator;

    public Course(int id, int user_id, int patika_id, String name, String lang) {
        this.id = id;
        this.user_id = user_id;
        this.patika_id = patika_id;
        this.name = name;
        this.lang = lang;
        this.patika = Patika.getFetch(patika_id);
        this.educator = User.getFetchById(user_id);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getPatika_id() {
        return patika_id;
    }

    public void setPatika_id(int patika_id) {
        this.patika_id = patika_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public Patika getPatika() {
        return patika;
    }

    public void setPatika(Patika patika) {
        this.patika = patika;
    }

    public User getEducator() {
        return educator;
    }

    public void setEducator(User educator) {
        this.educator = educator;
    }

    public static ArrayList<Course> getCourse(){
        ArrayList<Course> courses = new ArrayList<>();
        Course obj;
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM course");
            while (rs.next()){
                obj = new Course(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getInt("patika_id"),
                        rs.getString("name"),
                        rs.getString("lang")
                );
                courses.add(obj);
            }
        }catch (Exception e){
            e.getMessage();
        }
        return courses;
    }

    public static boolean add(int user_id,int patika_id, String name, String lang){
        try {
            Statement st = DBConnector.getInstance().createStatement();
            st.executeUpdate("INSERT INTO course (user_id, patika_id, name, lang) VALUES ("+user_id+", "+patika_id+", '"+name+"', '"+lang+"')");
            return true;
        }catch (Exception e){
            e.getMessage();
            return false;
        }
    }

    public static ArrayList<Course> getListByUser(int user_id){
        ArrayList<Course> courses = new ArrayList<>();
        Course obj;
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM course WHERE user_id = "+user_id+"");
            while (rs.next()){
                obj = new Course(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getInt("patika_id"),
                        rs.getString("name"),
                        rs.getString("lang")
                );
                courses.add(obj);
            }
        }catch (Exception e){
            e.getMessage();
        }
        return courses;
    }

    public static ArrayList<Course> getListCourse(){
        ArrayList<Course> courses = new ArrayList<>();
        Course obj;
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM course");
            while (rs.next()){
                obj = new Course(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getInt("patika_id"),
                        rs.getString("name"),
                        rs.getString("lang")
                );
                courses.add(obj);
            }
        }catch (Exception e){
            e.getMessage();
        }
        return courses;
    }

    public static boolean delete(int id){
        try {
            Statement st = DBConnector.getInstance().createStatement();
            st.executeUpdate("DELETE FROM course WHERE id = "+id+"");
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
