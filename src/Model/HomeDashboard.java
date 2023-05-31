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
import javafx.stage.Stage;

/**
 *
 * @author Malik
 */
public class HomeDashboard {

    private static ResultSet result;
   static PreparedStatement ps;

    public static int TotalUser() throws SQLException {

        String usersSql = "SELECT COUNT(id) FROM USERS";
//        String appointmentsSql = "SELECT COUNT(id) FROM appointments";
//        String booked_appointmentsSql = "SELECT COUNT(id) FROM booked_appointments";
        Connection c = DB.getInstance().getConnection();

        int countUserData = 0;
        try {
            ps = c.prepareStatement(usersSql);
            result = ps.executeQuery();

            while (result.next()) {
                countUserData = result.getInt("COUNT(id)");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return countUserData;

    }
     public static int TotalAppointment() throws SQLException {
        String appointmentsSql = "SELECT COUNT(id) FROM appointments";
        Connection c = DB.getInstance().getConnection();

        int countAppointmentData = 0;
        try {
            ps = c.prepareStatement(appointmentsSql);
            result = ps.executeQuery();

            while (result.next()) {
                countAppointmentData = result.getInt("COUNT(id)");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return countAppointmentData;

    }
      public static int TotalBookedAppointment() throws SQLException {
        String booked_appointmentsSql = "SELECT COUNT(id) FROM booked_appointments";
        Connection c = DB.getInstance().getConnection();

        int countBookedAppointmentData = 0;
        try {
            ps = c.prepareStatement(booked_appointmentsSql);
            result = ps.executeQuery();

            while (result.next()) {
                countBookedAppointmentData = result.getInt("COUNT(id)");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return countBookedAppointmentData;

    }
}
