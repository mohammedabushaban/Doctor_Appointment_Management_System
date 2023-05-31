/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Malik
 */
public class User {

   
    private int id;
    private String username;
    private String firstname;
    private String lastname;
    private int age;
    private String password;
    private String gender;
    private String phone;
    private String role;
    private String email;

    public User(String username, String firstname, String lastname, int age, String email, String password, String phone, String gender, String role) {
        this.id = id;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.password = password;
        this.gender = gender;
        this.phone = phone;
        this.role = role;
        this.email = email;
    }

    public User(String username, String firstname, String lastname, int age, String email, String password, String phone, String gender) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.password = password;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String fisrtname) {
        this.firstname = fisrtname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int save() throws SQLException {
        Connection c = DB.getInstance().getConnection();
        PreparedStatement ps = null;
        int recordCounter = 0;
        String sql = "INSERT INTO USERS (ID,USERNAME,FIRSTNAME,LASTNAME,AGE,EMAIL,PASSWORD,PHONE,GENDER,ROLE) VALUE(?,?,?,?,?,?,?,?,?,?)";
        ps = c.prepareStatement(sql);
        ps.setInt(1, this.getId());
        ps.setString(2, this.getUsername());
        ps.setString(3, this.getFirstname());
        ps.setString(4, this.getLastname());
        ps.setInt(5, this.getAge());
        ps.setString(6, this.getEmail());
        ps.setString(7, this.getPassword());
        ps.setString(8, this.getPhone());
        ps.setString(9, this.getGender());
        ps.setString(10, this.getRole());

        recordCounter = ps.executeUpdate();

        if (recordCounter > 0) {
            System.out.println(this.getUsername() + " " + "User Save()");
        }

        if (ps != null) {
            ps.close();
        }
        c.close();

        return recordCounter;
    }

    //get all users from users table
    public static ArrayList<User> getAllUsers() throws SQLException, ClassNotFoundException {
        Connection c = DB.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<User> users = new ArrayList<>();
        String sql = "SELECT * FROM USERS";
        ps = c.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()) {
            User user = new User(rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10));
            user.setId(rs.getInt(1));
            users.add(user);

        }
        if (ps != null) {
            ps.close();
        }
        c.close();
        return users;
    }

    //update an existing user in users table 
    public int update(int userId) throws SQLException {
        Connection c = DB.getInstance().getConnection();
        PreparedStatement ps;
        int recordCounter = 0;
        String sql = "UPDATE USERS SET USERNAME=?,FIRSTNAME=?,LASTNAME=?,AGE=?,EMAIL=?,PASSWORD=?,PHONE=?,GENDER=?,ROLE=? WHERE ID=?";
        ps = c.prepareStatement(sql);
        ps.setString(1, this.getUsername());
        ps.setString(2, this.getFirstname());
        ps.setString(3, this.getLastname());
        ps.setInt(4, this.getAge());
        ps.setString(5, this.getEmail());
        ps.setString(6, this.getPassword());
        ps.setString(7, this.getPhone());
        ps.setString(8, this.getGender());
        ps.setString(9, this.getRole());
        ps.setInt(10, userId);

        recordCounter = ps.executeUpdate();
        System.out.println(recordCounter);
        if (recordCounter > 0) {
            System.out.println("User with id : " + userId + " was updated successfully!");
        }
        if (ps != null) {
            ps.close();
        }
        c.close();
        return recordCounter;
    }

    //delete an user from users table 
    public int delete() throws SQLException, ClassNotFoundException {
        Connection c = DB.getInstance().getConnection();
        PreparedStatement ps = null;
        int recordCounter = 0;
        String sql = "DELETE FROM USERS WHERE ID=? ";
        ps = c.prepareStatement(sql);
        ps.setInt(1, this.getId());
        recordCounter = ps.executeUpdate();
        if (recordCounter > 0) {
            System.out.println("The user with id : " + this.getId() + " was deleted successfully!");
        }
        if (ps != null) {
            ps.close();
        }
        c.close();
        return recordCounter;
    }

    public int registerNewPatient() throws SQLException {
        Connection c = DB.getInstance().getConnection();
        PreparedStatement ps = null;
        int recordCounter = 0;
        String sql = "INSERT INTO USERS (ID,USERNAME,FIRSTNAME,LASTNAME,AGE,EMAIL,PASSWORD,PHONE,GENDER) VALUE(?,?,?,?,?,?,?,?,?)";
        ps = c.prepareStatement(sql);
        ps.setInt(1, this.getId());
        ps.setString(2, this.getUsername());
        ps.setString(3, this.getFirstname());
        ps.setString(4, this.getLastname());
        ps.setInt(5, this.getAge());
        ps.setString(6, this.getEmail());
        ps.setString(7, this.getPassword());
        ps.setString(8, this.getPhone());
        ps.setString(9, this.getGender());

        recordCounter = ps.executeUpdate();

        if (recordCounter > 0) {
            System.out.println(this.getUsername() + " " + "User Save()");
        }

        if (ps != null) {
            ps.close();
        }
        c.close();

        return recordCounter;
    }

}
