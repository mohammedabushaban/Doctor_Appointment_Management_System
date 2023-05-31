/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Admin;

import Model.Appointment;
import Model.HomeDashboard;
import Model.User;
import View.LoginPage;
import View.ViewManager;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Malik
 */
public class AdminDashboardController implements Initializable {

    private int idselect;

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
    private Button addPatient_btn;
    @FXML
    private Button Appointments_Data_btn;
    @FXML
    private Button logout;
    @FXML
    private AnchorPane home_form;
    @FXML
    private AnchorPane addPatinets_form;
    @FXML
    private TableView<User> addPatient_tableView;
    @FXML
    private TableColumn<?, ?> addPatientID;
    @FXML
    private TableColumn<?, ?> addPatientusername;
    @FXML
    private TableColumn<?, ?> addPatientFirstName;
    @FXML
    private TableColumn<?, ?> addPatientLastName;
    @FXML
    private TableColumn<?, ?> addPatientPassword;
    @FXML
    private TableColumn<?, ?> addPatientgender;
    @FXML
    private TableColumn<?, ?> addPatientphoneNum;
    @FXML
    private TableColumn<?, ?> addPatientAge;
    @FXML
    private TableColumn<?, ?> addPatientRole;
    @FXML
    private TableColumn<?, ?> addPatientEmail;
    @FXML
    private TextField addPatient_search;
    @FXML
    private TextField PatientFirstNameText;
    @FXML
    private TextField PatientLastNameText;
    @FXML
    private ComboBox<?> PatientGenderSelect;
    @FXML
    private ComboBox<?> PatientRoleSelect;
    @FXML
    private TextField PatientPasswordText;
    @FXML
    private TextField PatientphoneNumText;
    @FXML
    private TextField PatientAgeText;
    @FXML
    private TextField PatientEmailText;
    @FXML
    private AnchorPane Appointments_form;
    @FXML
    private TextField PatientUserNameText;

    private ObservableList<User> usersList;

    private ObservableList<Appointment> appointmentList;

    @FXML
    private Button addPatinet_addBtn;
    @FXML
    private Button addPatinet_updateBtn;
    @FXML
    private Button addPatinet_deleteBtn;
    @FXML
    private Button addPatinet_clearBtn;
    @FXML
    private Button Appointment_AddBtn;
    @FXML
    private Button Appointment_clearBtn;
    @FXML
    private Button BookedAppointmentsBtn;
    @FXML
    private TableView<Appointment> appointment_tableView;
    @FXML
    private DatePicker appointmentDate;
    @FXML
    private ComboBox<?> appointmentDay;
    @FXML
    private TextField appointmentTime;
    @FXML
    private TableColumn<Appointment, String> idCol;
    @FXML
    private TableColumn<Appointment, String> dateCol;
    @FXML
    private TableColumn<Appointment, String> dayCol;
    @FXML
    private TableColumn<Appointment, String> timeCol;
    @FXML
    private TableColumn<Appointment, String> statusCol;
    @FXML
    private Label home_totalUsers;
    @FXML
    private Label home_totalAppointment;
    @FXML
    private Label home_totalBookedAppointment;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
        try {
            // TODO
            int totalUser = HomeDashboard.TotalUser();
            home_totalUsers.setText(String.valueOf(totalUser));
            
             int totalAppointment = HomeDashboard.TotalAppointment();
            home_totalAppointment.setText(String.valueOf(totalAppointment));
            
               int totalBookedAppointment = HomeDashboard.TotalBookedAppointment();
            home_totalBookedAppointment.setText(String.valueOf(totalBookedAppointment));
        } catch (SQLException ex) {
            Logger.getLogger(AdminDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
     
        try {
            usersList = FXCollections.observableArrayList(User.getAllUsers());
            addPatient_tableView.setItems(usersList);

        } catch (SQLException ex) {
            Logger.getLogger(AdminDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdminDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }

        addPatientID.setCellValueFactory(new PropertyValueFactory("id"));
        addPatientusername.setCellValueFactory(new PropertyValueFactory("username"));
        addPatientPassword.setCellValueFactory(new PropertyValueFactory("password"));
        addPatientFirstName.setCellValueFactory(new PropertyValueFactory("firstname"));
        addPatientLastName.setCellValueFactory(new PropertyValueFactory("lastname"));
        addPatientAge.setCellValueFactory(new PropertyValueFactory("age"));
        addPatientEmail.setCellValueFactory(new PropertyValueFactory("email"));
        addPatientphoneNum.setCellValueFactory(new PropertyValueFactory("phone"));
        addPatientgender.setCellValueFactory(new PropertyValueFactory("gender"));
        addPatientRole.setCellValueFactory(new PropertyValueFactory("role"));

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
    private void switchForm(ActionEvent event) throws Exception {
        if (event.getSource() == home_btn) {
            home_form.setVisible(true);
            addPatinets_form.setVisible(false);
            Appointments_form.setVisible(false);

            home_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
            addPatient_btn.setStyle("-fx-background-color:transparent");
            Appointments_Data_btn.setStyle("-fx-background-color:transparent");

        } else if (event.getSource() == addPatient_btn) {
            home_form.setVisible(false);
            addPatinets_form.setVisible(true);
            Appointments_form.setVisible(false);

            addPatient_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
            home_btn.setStyle("-fx-background-color:transparent");
            Appointments_Data_btn.setStyle("-fx-background-color:transparent");

            addPatientGendernList();
            addPatientRoleList();

        } else if (event.getSource() == Appointments_Data_btn) {
            home_form.setVisible(false);
            addPatinets_form.setVisible(false);
            Appointments_form.setVisible(true);
            AppointmentDayList();
            AppointmentsShowListData();
            Appointments_Data_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
            addPatient_btn.setStyle("-fx-background-color:transparent");
            home_btn.setStyle("-fx-background-color:transparent");

        }
    }

    @FXML
    private void logout(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Message");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to logout?");
        Optional<ButtonType> option = alert.showAndWait();
        try {
            if (option.get().equals(ButtonType.OK)) {
                ViewManager.closeAdminPage();
                ViewManager.openLoginPage();
            }
        } catch (IOException ex) {
            Logger.getLogger(LoginPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void PatinetSearch() {
        addPatientsearch();
    }

    @FXML
    public void addPatientsearch() {
        FilteredList<User> filter = new FilteredList<>(usersList, e -> true);

        addPatient_search.textProperty().addListener((Observable, oldValue, newValue) -> {

            filter.setPredicate(predicateUserData -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String searchKey = newValue.toLowerCase();

                if (predicateUserData.getFirstname().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateUserData.getLastname().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateUserData.getGender().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateUserData.getPhone().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateUserData.getRole().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateUserData.getUsername().toLowerCase().contains(searchKey)) {
                    return true;

                } else {
                    return false;
                }
            });
        });

        SortedList<User> sortList = new SortedList<>(filter);

        sortList.comparatorProperty().bind(addPatient_tableView.comparatorProperty());
        addPatient_tableView.setItems(sortList);
    }

    public void addPatientGendernList() {
        String[] listGender = {"Male", "Female", "Others"};
        List<String> listG = new ArrayList<>();
        for (String data : listGender) {
            listG.add(data);
        }
        ObservableList listData = FXCollections.observableArrayList(listG);
        PatientGenderSelect.setItems(listData);
    }

    public void addPatientRoleList() {
        String[] listRole = {"doctor", "user"};
        List<String> listR = new ArrayList<>();
        for (String data : listRole) {
            listR.add(data);
        }
        ObservableList listData = FXCollections.observableArrayList(listR);
        PatientRoleSelect.setItems(listData);
    }

    @FXML
    private void addPatinetAdd(ActionEvent event) throws SQLException, ClassNotFoundException {
        // get data from all text fields 
        String username = PatientUserNameText.getText();
        String firstname = PatientFirstNameText.getText();
        String lastname = PatientLastNameText.getText();
        String password = PatientPasswordText.getText();
        String email = PatientEmailText.getText();
        String phone = PatientphoneNumText.getText();
        int age = Integer.parseInt(PatientAgeText.getText());
        String gender = (String) PatientGenderSelect.getSelectionModel().getSelectedItem();
        String role = (String) PatientRoleSelect.getSelectionModel().getSelectedItem();

        if (username == null || firstname == null || lastname == null || password == null || email == null || phone == null || gender == null || role == null) {
            Alert alert;
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();
        } else {
            // make an user object having this data
            User user = new User(username, firstname, lastname, age, email, password, phone, gender, role); // save the user in database by save method;
            user.save();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("User inserted");
            alert.setContentText("User inserted");
            alert.showAndWait();
        }

        addPatientShowListData();
        addPatientReset();
    }

    @FXML
    public void addPatinetSelectRowData() {
        User UserD = addPatient_tableView.getSelectionModel().getSelectedItem();
        int num = addPatient_tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        idselect = Integer.parseInt(String.valueOf(UserD.getId()));
        PatientUserNameText.setText(UserD.getUsername());
        PatientFirstNameText.setText(UserD.getFirstname());
        PatientLastNameText.setText(UserD.getLastname());
        PatientPasswordText.setText(UserD.getPassword());
        PatientEmailText.setText(UserD.getEmail());
        PatientphoneNumText.setText(UserD.getPhone());
        PatientAgeText.setText(String.valueOf(UserD.getAge()));
        ObservableList listGender = FXCollections.observableArrayList(UserD.getGender());
        PatientGenderSelect.setItems(listGender);
        ObservableList role = FXCollections.observableArrayList(UserD.getRole());
        PatientRoleSelect.setItems(role);
    }

    @FXML
    private void addPatinetUpdate(ActionEvent event) throws SQLException {
        String username = PatientUserNameText.getText();
        String firstname = PatientFirstNameText.getText();
        String lastname = PatientLastNameText.getText();
        String password = PatientPasswordText.getText();
        String email = PatientEmailText.getText();
        String phone = PatientphoneNumText.getText();
        int age = Integer.parseInt(PatientAgeText.getText());
        String gender = (String) PatientGenderSelect.getSelectionModel().getSelectedItem();
        String role = (String) PatientRoleSelect.getSelectionModel().getSelectedItem();

        Alert alert;

        alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Cofirmation Message");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to UPDATE Patient ID: " + idselect + "?");
        Optional<ButtonType> option = alert.showAndWait();

        if (option.get().equals(ButtonType.OK)) {
            try {
                //Update the selected user from database table using Update method in our User model
                User selectedUser = new User(username, firstname, lastname, age, email, password, phone, gender, role);
                selectedUser.update(idselect);
                addPatientShowListData();
                addPatientReset();

                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Updated!");
                alert.showAndWait();

            } catch (SQLException ex) {
                Logger.getLogger(AdminDashboardController.class
                        .getName()).log(Level.SEVERE, null, ex);

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AdminDashboardController.class
                        .getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    @FXML
    private void addPatinetDelete(ActionEvent event) {
        //check if there is an user selected from the TableView
        if (addPatient_tableView.getSelectionModel().getSelectedItem() != null) {
            //store the selected user from the TableView in new user object
            User selectedUser = addPatient_tableView.getSelectionModel().getSelectedItem();

            //show an confirmation alert and make the deletion on confirm event
            Alert deleteConfirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
            deleteConfirmAlert.setTitle("User delete");
            deleteConfirmAlert.setContentText("Are you sure to delete this user ?");
            deleteConfirmAlert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {

                    try {
                        //delete the selected user from database table using delete method in our User model
                        selectedUser.delete();
                        addPatientShowListData();

                    } catch (SQLException ex) {
                        Logger.getLogger(AdminDashboardController.class
                                .getName()).log(Level.SEVERE, null, ex);

                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(AdminDashboardController.class
                                .getName()).log(Level.SEVERE, null, ex);
                    }

                    Alert deletedSuccessAlert = new Alert(Alert.AlertType.INFORMATION);
                    deletedSuccessAlert.setTitle("User deleted");
                    deletedSuccessAlert.setContentText("User deleted");
                    deletedSuccessAlert.show();
                }
            });

        } else {
            Alert warnAlert = new Alert(Alert.AlertType.WARNING);
            warnAlert.setTitle("Select an user");
            warnAlert.setContentText("Please select an user from the table view");
            warnAlert.show();
        }
    }

    @FXML
    private void addPatinetReset(ActionEvent event) {
        addPatientReset();
    }

    public void addPatientReset() {
        PatientUserNameText.setText("");
        PatientAgeText.setText("");
        PatientEmailText.setText("");
        PatientFirstNameText.setText("");
        PatientLastNameText.setText("");
        PatientRoleSelect.getSelectionModel().clearSelection();
        PatientGenderSelect.getSelectionModel().clearSelection();
        PatientphoneNumText.setText("");
        PatientPasswordText.setText("");

//        getData.path = "";
    }

    public void addPatientShowListData() throws SQLException, ClassNotFoundException {
        usersList = FXCollections.observableArrayList(User.getAllUsers());
        addPatient_tableView.setItems(usersList);
    }

    public void AppointmentsShowListData() throws SQLException, ClassNotFoundException {
        System.out.println("Controller.Admin.AdminDashboardController.AppointmentsShowListData()");
        try {
            appointmentList = FXCollections.observableArrayList(Appointment.getAllAppointments());
            appointment_tableView.setItems(appointmentList);

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

    @FXML
    private void BookedAppointmentsPage(ActionEvent event) throws IOException {
        ViewManager.openBookedAppointmentsPage();
        ViewManager.closeAdminPage();
    }

    public void AppointmentDayList() {
        String[] listGender = {"Saturday", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday"};
        List<String> listG = new ArrayList<>();
        for (String data : listGender) {
            listG.add(data);
        }
        ObservableList listData = FXCollections.observableArrayList(listG);
        appointmentDay.setItems(listData);
    }

    @FXML
    private void AppointmentAdd(ActionEvent event) throws SQLException {

        LocalDate i = appointmentDate.getValue();
        String AppointmentDate = String.valueOf(i);
        String AppointmentDay = (String) appointmentDay.getSelectionModel().getSelectedItem();
        String AppointmentTime = appointmentTime.getText();
        try {
            if (AppointmentDate == null || AppointmentDay == null || AppointmentTime == null) {
                Alert alert;
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all Appointment fields");
                alert.showAndWait();
            } else {
                // make an Appointment object having this data
                Appointment newApp = new Appointment(AppointmentDate, AppointmentDay, AppointmentTime);
                newApp.newAppointment();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Appointment inserted");
                alert.setContentText("Appointment inserted");
                alert.showAndWait();
                AppointmentsShowListData();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            Alert warnAlert = new Alert(Alert.AlertType.WARNING);
            warnAlert.setTitle("Error");
            warnAlert.setContentText(e.getMessage());
            warnAlert.show();
        }

    }

    @FXML
    private void AppointmentReset(ActionEvent event) {
    }

}
