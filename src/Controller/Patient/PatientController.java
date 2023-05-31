/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Patient;

import Controller.Admin.AdminDashboardController;
import Controller.userLogedIn;
import Model.Appointment;
import Model.BookedAppointment;
import Model.User;
import View.ViewManager;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Malik
 */
public class PatientController implements Initializable {

    @FXML
    private AnchorPane main_form;
    @FXML
    private Button close;
    @FXML
    private Button minimize;
    @FXML
    private Label username;
    @FXML
    private Button home_btn;
    @FXML
    private Button logout;
    @FXML
    private AnchorPane home_form;
    @FXML
    private TableView<Appointment> Appointments_tableView;
    @FXML
    private TableView<BookedAppointment> booked_appointments;

    private ObservableList<Appointment> appointmentList;
    private ObservableList<BookedAppointment> bookedAppointmentsList;
    @FXML
    private TableColumn<?, ?> idCol;
    @FXML
    private TableColumn<?, ?> dateCol;
    @FXML
    private TableColumn<?, ?> dayCol;
    @FXML
    private TableColumn<?, ?> timeCol;
    @FXML
    private TableColumn<?, ?> statusCol;
    @FXML
    private TableColumn<?, ?> doctorCommnet;
    @FXML
    private Button BookedAppointmentsBtn;
    @FXML
    private TableColumn<?, ?> idBooked;
    @FXML
    private TableColumn<?, ?> dayColBooked;
    @FXML
    private TableColumn<?, ?> dateColBooked;
    @FXML
    private TableColumn<?, ?> timeColBooked;
    @FXML
    private TableColumn<?, ?> statusColBooked;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        getAppointments();
        getBookedAppointments();

    }

    private void getAppointments() {

        try {
            appointmentList = FXCollections.observableArrayList(Appointment.getAllAppointments());
            Appointments_tableView.setItems(appointmentList);

            idCol.setCellValueFactory(new PropertyValueFactory("id"));
            dateCol.setCellValueFactory(new PropertyValueFactory("appointment_date"));
            dayCol.setCellValueFactory(new PropertyValueFactory("appointment_day"));
            timeCol.setCellValueFactory(new PropertyValueFactory("appointment_time"));
            statusCol.setCellValueFactory(new PropertyValueFactory("status"));

        } catch (SQLException ex) {
            Logger.getLogger(AdminDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      
    int userid;
    private void getBookedAppointments() {
         userid = userLogedIn.getInstance().getUserId();
        System.out.println(userid);
        try {
            bookedAppointmentsList = FXCollections.observableArrayList(BookedAppointment.getAllBookedAppointments(userid));
            booked_appointments.setItems(bookedAppointmentsList);

            idBooked.setCellValueFactory(new PropertyValueFactory("id"));
            dateColBooked.setCellValueFactory(new PropertyValueFactory("appointment_date"));
            dayColBooked.setCellValueFactory(new PropertyValueFactory("appointment_day"));
            timeColBooked.setCellValueFactory(new PropertyValueFactory("appointment_time"));
            statusColBooked.setCellValueFactory(new PropertyValueFactory("status"));
            doctorCommnet.setCellValueFactory(new PropertyValueFactory("doctor_comment"));
        } catch (SQLException ex) {
            Logger.getLogger(AdminDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void close(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void minimize(ActionEvent event) {
        Stage stage = (Stage) main_form.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private void switchForm(ActionEvent event) {
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
        ViewManager.closePatientPage();
        ViewManager.openLoginPage();
    }

    @FXML
    private void salarySelect(MouseEvent event) {
    }

    @FXML
    private void BookAppointmentsBtn(ActionEvent event) throws SQLException, ClassNotFoundException {

        Appointment appointment = Appointments_tableView.getSelectionModel().getSelectedItem();
        int appointmentId = appointment.getId();
        

        if ((appointmentId - 1) < -1 || (userid - 1) < -1) {
            return;
        }
        Alert alert;

        alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cofirmation Message");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to Book?");
        Optional<ButtonType> option = alert.showAndWait();

        if (option.get().equals(ButtonType.OK)) {
            BookedAppointment bookedApp = new BookedAppointment(appointmentId, userid);
            bookedApp.newBook();
            int record = appointment.changeAppointmentToBooked(appointmentId);
            if (record > 0) {
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Booked!");
                appointmentShowListData();
                bookeShowListData();
            }
        }

    }

    public void appointmentShowListData() throws SQLException, ClassNotFoundException {
        appointmentList = FXCollections.observableArrayList(Appointment.getAllAppointments());
        Appointments_tableView.setItems(appointmentList);
    }

    public void bookeShowListData() throws SQLException, ClassNotFoundException {
        userid = userLogedIn.getInstance().getUserId();
        bookedAppointmentsList = FXCollections.observableArrayList(BookedAppointment.getAllBookedAppointments(userid));
        booked_appointments.setItems(bookedAppointmentsList);
    }
}
