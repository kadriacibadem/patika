package com.patikadev.Model;

import com.patikadev.Helper.DBConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Content {
    private int id;
    private String title;
    private String explanation;
    private String link;


    public Content(int id, String title, String explanation, String link) {
        this.id = id;
        this.title = title;
        this.explanation = explanation;
        this.link = link;
    }

    public Content() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }



    public static ArrayList<Content> getFetchContent(int user_id){
        ArrayList<Content> contents = new ArrayList<>();
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM coursecontent WHERE user_id = " + user_id);
            while (rs.next()){
                contents.add(new Content(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("explanation"),
                        rs.getString("link")
                ));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return contents;
    }

    public static void addContent(int course_id,int user_id,String title,String explanation,String link){
        try {
            Statement st = DBConnector.getInstance().createStatement();
            st.executeUpdate("INSERT INTO coursecontent (user_id,course_id,title,explanation,link) VALUES ("+user_id+","+course_id+",'"+title+"','"+explanation+"','"+link+"')");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void deleteContent(int id){
        try {
            Statement st = DBConnector.getInstance().createStatement();
            st.executeUpdate("DELETE FROM coursecontent WHERE id = "+id+"");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void updateContent(int id,String title,String explanation,String link){
        try {
            Statement st = DBConnector.getInstance().createStatement();
            st.executeUpdate("UPDATE coursecontent SET title = '"+title+"', explanation = '"+explanation+"', link = '"+link+"' WHERE id = "+id+"");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void addQuestion(int course_id,String question,String first,String second,String third){
        try {
            Statement st = DBConnector.getInstance().createStatement();
            st.executeUpdate("INSERT INTO questions (course_id,question,first,second,third) VALUES ("+course_id+",'"+question+"','"+first+"','"+second+"','"+third+"')");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    
}
