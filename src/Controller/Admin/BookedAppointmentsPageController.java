/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Admin;

import Controller.userLogedIn;
import Model.Appointment;
import Model.BookedAppointment;
import Model.User;
import View.ViewManager;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Malik
 */
public class BookedAppointmentsPageController implements Initializable {

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
    private AnchorPane Appointments_form;
    @FXML
    private TableView<BookedAppointment> BookedAppointments_tableView;
    @FXML
    private Button saveBtn;
    @FXML
    private Button ClearBtn;
    @FXML
    private TextArea commentField;
    @FXML
    private TableColumn<?, ?> idCol;
    @FXML
    private TableColumn<?, ?> patientNameCol;
    @FXML
    private TableColumn<?, ?> dateCol;
    @FXML
    private TableColumn<?, ?> dayCol;
    @FXML
    private TableColumn<?, ?> statusCol;
    @FXML
    private TableColumn<BookedAppointment, String> commentCol;
    @FXML
    private TableColumn<?, ?> timeCol;
    private ObservableList<BookedAppointment> bookedAppointmentsList;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        getBookedAppointments();
        // TODO
    }

    private void getBookedAppointments() {
        try {
            bookedAppointmentsList = FXCollections.observableArrayList(BookedAppointment.getAllBookedAppointmentsDoctor());
            BookedAppointments_tableView.setItems(bookedAppointmentsList);

            idCol.setCellValueFactory(new PropertyValueFactory("id"));
            TableColumn<User, Integer> userIDColumn = new TableColumn<>("user_id");
            userIDColumn.setCellValueFactory(new PropertyValueFactory<>("user_id"));
            userIDColumn.setVisible(false); // Hide the column
            patientNameCol.setCellValueFactory(new PropertyValueFactory("firstname"));
            dateCol.setCellValueFactory(new PropertyValueFactory("appointment_date"));
            TableColumn<User, Integer> appointmentIDColumn = new TableColumn<>("appointment_id");
            appointmentIDColumn.setCellValueFactory(new PropertyValueFactory<>("appointment_id"));
            appointmentIDColumn.setVisible(false); // Hide the column
            dayCol.setCellValueFactory(new PropertyValueFactory("appointment_day"));
            timeCol.setCellValueFactory(new PropertyValueFactory("appointment_time"));
            TableColumn<User, Integer> bookedAppointmentIDColumn = new TableColumn<>("booked_appointment_id");
            bookedAppointmentIDColumn.setCellValueFactory(new PropertyValueFactory<>("booked_appointment_id"));
            bookedAppointmentIDColumn.setVisible(false); // Hide the column
            statusCol.setCellValueFactory(new PropertyValueFactory("status"));
            commentCol.setCellValueFactory(new PropertyValueFactory("doctor_comment"));
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
        ViewManager.closeBookedAppointmentsPage();
        ViewManager.openAdminPage();
    }
    
    
    
    int userID;
    int appointmentID;
    int bookedAppointmentID;
    @FXML
    private void save(ActionEvent event) throws SQLException, ClassNotFoundException {
        String comment = commentField.getText();
        if (comment == null) {
            Alert alert;
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all information fields");
            alert.showAndWait();
        } else {
            BookedAppointment booked = new BookedAppointment();
            booked.newComment(comment,userID,appointmentID,bookedAppointmentID);
            bookedAppointmentShowListData();
        }

    }

    @FXML
    public void SelectRow() {
        userID = BookedAppointments_tableView.getSelectionModel().getSelectedItem().getUser_id();
        appointmentID = BookedAppointments_tableView.getSelectionModel().getSelectedItem().getAppointment_id();
        bookedAppointmentID = BookedAppointments_tableView.getSelectionModel().getSelectedItem().getBooked_appointment_id();
        String comment = BookedAppointments_tableView.getSelectionModel().getSelectedItem().getDoctor_comment();

        commentField.setText(comment);
    }

    @FXML
    private void clear(ActionEvent event) {
    }
    
    public void bookedAppointmentShowListData() throws SQLException, ClassNotFoundException {
        bookedAppointmentsList = FXCollections.observableArrayList(BookedAppointment.getAllBookedAppointmentsDoctor());
        BookedAppointments_tableView.setItems(bookedAppointmentsList);
    }
}
