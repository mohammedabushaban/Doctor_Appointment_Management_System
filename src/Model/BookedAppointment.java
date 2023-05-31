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

/**
 *
 * @author Malik
 */
public class BookedAppointment {

    private int id;
    private int appointment_id;
    private int user_id;
    private int booked_appointment_id;
    private String status;
    private String doctor_comment;
    private String appointment_date;
    private String appointment_day;
    private String appointment_time;
    private String firstname;
    private String lastname;

    public BookedAppointment() {

    }

    public BookedAppointment(int id, String appointment_date, String appointment_day, String appointment_time, String status, String doctor_comment) {
        this.status = status;
        this.id = id;
        this.appointment_date = appointment_date;
        this.appointment_day = appointment_day;
        this.appointment_time = appointment_time;
        this.doctor_comment = doctor_comment;
    }

    public BookedAppointment(int id, int appointment_id, int user_id, String status, String doctor_comment) {
        this.id = id;
        this.appointment_id = appointment_id;
        this.user_id = user_id;
        this.status = status;
        this.doctor_comment = doctor_comment;
    }

    public BookedAppointment(int appointment_id, int user_id, String status, String doctor_comment) {
        this.appointment_id = appointment_id;
        this.user_id = user_id;
        this.status = status;
        this.doctor_comment = doctor_comment;
    }

    public BookedAppointment(int id, int user_id, String firstname, String lastname, int appointment_id, String appointment_date, String appointment_day, String appointment_time, int booked_appointment_id, String status, String doctor_comment) {
        this.id = id;
        this.user_id = user_id;
        this.appointment_id = appointment_id;
        this.booked_appointment_id = booked_appointment_id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.appointment_date = appointment_date;
        this.appointment_day = appointment_day;
        this.appointment_time = appointment_time;
        this.status = status;
        this.doctor_comment = doctor_comment;
    }
    public BookedAppointment(int user_id,int appointment_id,int booked_appointment_id,String doctor_comment) {
        this.booked_appointment_id =booked_appointment_id;
        this.appointment_id = appointment_id;
        this.user_id = user_id;
        this.doctor_comment = doctor_comment;
    }

    public int getBooked_appointment_id() {
        return booked_appointment_id;
    }

    public void setBooked_appointment_id(int booked_appointment_id) {
        this.booked_appointment_id = booked_appointment_id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public BookedAppointment(int appointment_id, int user_id) {
        this.appointment_id = appointment_id;
        this.user_id = user_id;
    }

    public String getAppointment_date() {
        return appointment_date;
    }

    public void setAppointment_date(String appointment_date) {
        this.appointment_date = appointment_date;
    }

    public String getAppointment_day() {
        return appointment_day;
    }

    public void setAppointment_day(String appointment_day) {
        this.appointment_day = appointment_day;
    }

    public String getAppointment_time() {
        return appointment_time;
    }

    public void setAppointment_time(String appointment_time) {
        this.appointment_time = appointment_time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAppointment_id() {
        return appointment_id;
    }

    public void setAppointment_id(int appointment_id) {
        this.appointment_id = appointment_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDoctor_comment() {
        return doctor_comment;
    }

    public void setDoctor_comment(String doctor_comment) {
        this.doctor_comment = doctor_comment;
    }

    public int newBook() throws SQLException {
        Connection c = DB.getInstance().getConnection();
        PreparedStatement ps = null;
        int recordCounter = 0;
        String sql = "INSERT INTO Booked_APPOINTMENTS (ID,APPOINTMENT_ID,USER_ID) VALUE(?,?,?)";
        ps = c.prepareStatement(sql);
        ps.setInt(1, this.getId());
        ps.setInt(2, this.getAppointment_id());
        ps.setInt(3, this.getUser_id());

        recordCounter = ps.executeUpdate();

        if (recordCounter > 0) {
            System.out.println("New BOOKED APPOINTMENT Saved");
        }

        if (ps != null) {
            ps.close();
        }
        c.close();

        return recordCounter;
    }

    public static ArrayList<BookedAppointment> getAllBookedAppointments(int userId) throws SQLException, ClassNotFoundException {
        Connection c = DB.getInstance().getConnection();
        PreparedStatement ps;
        ResultSet rs = null;
        ArrayList<BookedAppointment> bookedAppointments = new ArrayList<>();
        String sql = "SELECT a.appointment_date, a.appointment_day, a.appointment_time, b.status, b.doctor_comment FROM booked_appointments b JOIN appointments a ON b.appointment_id = a.id WHERE b.user_id=?";
        ps = c.prepareStatement(sql);
        System.out.println(userId);
        ps.setInt(1, userId);
        rs = ps.executeQuery();
        int autoInc = 1;
        while (rs.next()) {
            BookedAppointment bookedAppointment = new BookedAppointment(autoInc++, rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));

            bookedAppointments.add(bookedAppointment);

        }
        if (ps != null) {
            ps.close();
        }
        c.close();
        return bookedAppointments;
    }

    public static ArrayList<BookedAppointment> getAllBookedAppointmentsDoctor() throws SQLException, ClassNotFoundException {
        Connection c = DB.getInstance().getConnection();
        PreparedStatement ps;
        ResultSet rs = null;
        ArrayList<BookedAppointment> bookedAppointments = new ArrayList<>();
        String sql = "SELECT u.id as user_id,u.firstname,u.lastname, a.id as appointment_id,a.appointment_date, a.appointment_day, a.appointment_time,b.id as booked_appointment_id, b.status, b.doctor_comment\n"
                + "FROM booked_appointments b\n"
                + "JOIN appointments a ON b.appointment_id = a.id\n"
                + "JOIN users u ON b.user_id = u.id";
        ps = c.prepareStatement(sql);
        rs = ps.executeQuery();
        int autoInc = 1;
        while (rs.next()) {
            BookedAppointment bookedAppointment = new BookedAppointment(autoInc++, rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8), rs.getString(9), rs.getString(10));
            bookedAppointments.add(bookedAppointment);
        }
        if (ps != null) {
            ps.close();
        }
        c.close();
        return bookedAppointments;
    }

    public int newComment(String comment , int userID,int appointmentID, int bookedAppointmentID) throws SQLException {
        System.out.println(userID);
        System.out.println(appointmentID);
        System.out.println(bookedAppointmentID);
        
        Connection c = DB.getInstance().getConnection();
        PreparedStatement psAppointment;
        int recordCounter = 0;
        String updateAppointmentsSql = "UPDATE appointments SET status = ? WHERE id = ?";
        psAppointment = c.prepareStatement(updateAppointmentsSql);
        psAppointment.setString(1, "booked");
        psAppointment.setInt(2, appointmentID);
        psAppointment.executeUpdate();
        if(recordCounter > 0) {
            System.out.println("Update Sql 1");
            System.out.println("New BOOKED APPOINTMENT Updated");
        }
        PreparedStatement psBookedAppointment = null;
        String updateBookedAppointmentsSql = "UPDATE booked_appointments SET status = ?, doctor_comment = ? WHERE user_id = ? AND appointment_id = ?";
        psBookedAppointment = c.prepareStatement(updateBookedAppointmentsSql);
        psBookedAppointment.setString(1, "finished");
        psBookedAppointment.setString(2, comment);
        psBookedAppointment.setInt(3, userID);
        psBookedAppointment.setInt(4, appointmentID);
        psBookedAppointment.executeUpdate();

        if (psBookedAppointment != null || psAppointment != null) {
            psAppointment.close();
            psBookedAppointment.close();
        }
        psAppointment.close();
        psBookedAppointment.close();
        return recordCounter;
    }
   

}
