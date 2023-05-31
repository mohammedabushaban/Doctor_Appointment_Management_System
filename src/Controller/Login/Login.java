/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Login;

import Controller.userLogedIn;
import Model.DB;
import View.LoginPage;
import View.ViewManager;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Malik
 */
public class Login implements Initializable {

    @FXML
    private AnchorPane main_form;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Button loginBtn;
    @FXML
    private Button close;

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    Alert alert;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void loginBtn(ActionEvent event) throws SQLException, IOException {
        String sql = "SELECT * FROM users WHERE username = ? and password = ?";
        connect = DB.getInstance().getConnection();
        try {
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, this.username.getText());
            prepare.setString(2, this.password.getText());
            result = prepare.executeQuery();

            if (this.username.getText().isEmpty() || this.password.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all information fields");
                alert.showAndWait();
            } else {
                if (result.next()) {
                    int userid = result.getInt("id");
                      userLogedIn.getInstance().setUserId(userid);
                    if (result.getString("role").equals("doctor")) {
                        ViewManager.openAdminPage();
                        ViewManager.closeLoginPage();
                    } else if (result.getString("role").equals("user")) {
                        ViewManager.openPatientPage();
                        ViewManager.closeLoginPage();
                      
                    }
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Login");
//                    alert.showAndWait();  
                    
                     
                } else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Wrong Username/Password");
                    alert.showAndWait();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void Close(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void registrationBtn(ActionEvent event) throws IOException {
        ViewManager.openRegisterPage();
        ViewManager.closeLoginPage();
    }
}
