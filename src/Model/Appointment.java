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
public class Appointment {

    private int id;
    private String appointment_date;
    private String appointment_day;
    private String appointment_time;
    private String status;

    public Appointment(String appointment_date, String appointment_day, String appointment_time, String status) {
        this.appointment_date = appointment_date;
        this.appointment_day = appointment_day;
        this.appointment_time = appointment_time;
        this.status = status;
    }

    public Appointment(String appointment_date, String appointment_day, String appointment_time) {
        this.appointment_date = appointment_date;
        this.appointment_day = appointment_day;
        this.appointment_time = appointment_time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int newAppointment() throws SQLException {
        Connection c = DB.getInstance().getConnection();
        PreparedStatement ps = null;
        int recordCounter = 0;
        String sql = "INSERT INTO APPOINTMENTS (ID,APPOINTMENT_DATE,APPOINTMENT_DAY,APPOINTMENT_TIME) VALUE(?,?,?,?)";
        ps = c.prepareStatement(sql);
        ps.setInt(1, this.getId());
        ps.setString(2, this.getAppointment_date());
        ps.setString(3, this.getAppointment_day());
        ps.setString(4, this.getAppointment_time());

        recordCounter = ps.executeUpdate();

        if (recordCounter > 0) {
            System.out.println("New APPOINTMENT Save()");
        }

        if (ps != null) {
            ps.close();
        }
        c.close();

        return recordCounter;
    }

    public static ArrayList<Appointment> getAllAppointments() throws SQLException, ClassNotFoundException {
        Connection c = DB.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Appointment> appointments = new ArrayList<>();
        String sql = "SELECT * FROM APPOINTMENTS WHERE STATUS =?";
        ps = c.prepareStatement(sql);
        ps.setString(1, "free");
        rs = ps.executeQuery();

        while (rs.next()) {
            Appointment appointment = new Appointment(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
            appointment.setId(rs.getInt(1));
            appointments.add(appointment);
        }
        if (ps != null) {
            ps.close();
        }
        c.close();
        return appointments;
    }

    public static int changeAppointmentToBooked(int appointment_id) throws SQLException {
        Connection c = DB.getInstance().getConnection();
        PreparedStatement ps;
        int recordCounter = 0;
        String sql = "UPDATE APPOINTMENTS SET STATUS=? WHERE id=?";
        ps = c.prepareStatement(sql);
        ps.setString(1, "booked");
        ps.setInt(2, appointment_id);

        recordCounter = ps.executeUpdate();
        if (recordCounter > 0) {
            System.out.println("Appointment with id : "+appointment_id+"Change Status Successfuly");
        }
        if (ps != null) {
            ps.close();
        }
        c.close();
        return recordCounter;
    }
   
 

}
