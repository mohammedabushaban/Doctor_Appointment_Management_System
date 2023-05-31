/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.User;
import View.ViewManager;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Malik
 */
public class Register implements Initializable {

    @FXML
    private AnchorPane main_form;
    @FXML
    private TextField firstname;
    @FXML
    private PasswordField password;
    @FXML
    private Button close;
    @FXML
    private TextField username;
    @FXML
    private TextField lastname;
    @FXML
    private TextField phone;
    @FXML
    private TextField email;
    @FXML
    private RadioButton male;
    @FXML
    private ToggleGroup genderGroup;
    @FXML
    private RadioButton female;
    @FXML
    private TextField age;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void saveUserBtn(ActionEvent event) throws SQLException, IOException {
             if (this.username.getText().isEmpty() || this.password.getText().isEmpty()) {
                Alert alert;
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all information fields");
                alert.showAndWait();
      }else{
       // get data from all text fields 
        String username = this.username.getText();
        String firstname = this.firstname.getText();
        String lastname = this.lastname.getText();
        String password = this.password.getText();
        String email = this.email.getText();
        String phone = this.phone.getText();
        int age = Integer.parseInt(this.age.getText());
        String gender = ((RadioButton) genderGroup.getSelectedToggle()).getText();
        // save the user in database by save method
        User user = new User(username, firstname, lastname, age, email, password, phone, gender);
        user.registerNewPatient();
        ViewManager.openPatientPage();
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("User inserted");
        alert.setContentText("User inserted");
        ViewManager.closeRegisterPage();        
          }
    }
    @FXML
    private void Close(ActionEvent event) throws IOException {
        ViewManager.closePatientPage();
        ViewManager.openLoginPage();
    }
    
}
