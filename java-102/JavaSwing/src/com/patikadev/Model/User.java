package com.patikadev.Model;

import com.patikadev.Helper.DBConnector;
import com.patikadev.Helper.Helper;

import java.lang.reflect.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class User implements Comparable<User>{
    private int id;
    private String name;
    private String username;
    private String password;
    private String type;

    public User(int id, String name, String username, String password, String type) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.type = type;
    }
    public User() {
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static ArrayList<User> getList(){
        ArrayList<User> userList = new ArrayList<>();
        User obj;
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM user");
            while (rs.next()){
                obj = new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("type")
                );
                userList.add(obj);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }



    public static boolean add(String name,String username,String pass,String type){
        User findUser = User.getFetchByUsername(username);
        if (findUser != null){
            Helper.showMsg("Bu kullanıcı adı zaten kullanılıyor!");
            return false;
        }
        try {
            Statement st = DBConnector.getInstance().createStatement();
            st.executeUpdate("INSERT INTO user (name,username,password,type) VALUES ('"+name+"','"+username+"','"+pass+"','"+type+"')");
            return true;

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static User getFetchByUsername(String username){
        User obj = null;
        String query = "SELECT * FROM user WHERE username = ?";
        try {
            PreparedStatement st = DBConnector.getInstance().prepareStatement(query);
            st.setString(1,username);
            ResultSet rs = st.executeQuery();
            if (rs.next()){
                obj = new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("type")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return obj;
    }

    public static User getFetchByUsernameAndPass(String username,String pass){
        User obj = null;
        String query = "SELECT * FROM user WHERE username = ? AND password = ?";
        try {
            PreparedStatement st = DBConnector.getInstance().prepareStatement(query);
            st.setString(1,username);
            st.setString(2,pass);
            ResultSet rs = st.executeQuery();
            if (rs.next()){
                switch (rs.getString("type")){
                    case "operator":
                        obj = new Operator(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("username"),
                                rs.getString("password"),
                                rs.getString("type")
                        );
                        break;
                    case "educator":
                        obj = new Educator(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("username"),
                                rs.getString("password"),
                                rs.getString("type")
                        );
                        break;
                    default:
                        obj = new User(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("username"),
                                rs.getString("password"),
                                rs.getString("type")
                        );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return obj;
    }

    public static User getFetchById(int id){
        User obj = null;
        String query = "SELECT * FROM user WHERE id = ?";
        try {
            PreparedStatement st = DBConnector.getInstance().prepareStatement(query);
            st.setInt(1,id);
            ResultSet rs = st.executeQuery();
            if (rs.next()){
                obj = new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("type")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return obj;
    }

    public static boolean delete(int id){
        ArrayList<Course> courseList = Course.getListByUser(id);
        for (Course course : courseList){
            Course.delete(course.getId());
        }
        try {
            Statement st = DBConnector.getInstance().createStatement();
            st.executeUpdate("DELETE FROM user WHERE id = "+id);
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean update(int id,String name, String username,String password,String type){
        User findUser = User.getFetchByUsername(username);
        if (findUser != null && findUser.getId() != id){
            Helper.showMsg("Bu kullanıcı adı zaten kullanılıyor!");
            return false;
        }
        try {
                Statement st = DBConnector.getInstance().createStatement();
                st.executeUpdate("UPDATE user SET name = '"+name+"', username = '"+username+"', password = '"+password+"', type = '"+type+"' WHERE id = "+id);
                return true;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static ArrayList<User> searchUserList(String query){
        ArrayList<User> userList = new ArrayList<>();
        User obj;
        try {
            Statement st = DBConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()){
                obj = new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("type")
                );
                userList.add(obj);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }

    public static String searchQuery(String name,String username,String type){
        String query = "SELECT * FROM user WHERE username LIKE '%{{username}}%' AND name LIKE '%{{name}}%'";
        query = query.replace("{{username}}",username);
        query = query.replace("{{name}}",name);
        if(!type.isEmpty()){
            query += " AND type='{{type}}'";
            query = query.replace("{{type}}",type);

        }

        return query;
    }


    @Override
    public int compareTo(User o) {
        return Integer.compare(this.id,o.id);
    }
}
