/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Yahya
 */
public class LoginPage extends Stage {
    private Parent root;
    private double x = 0;
    private double y = 0;
    public LoginPage() throws IOException {
         root = FXMLLoader.load(getClass().getResource("LoginFXML/Login.fxml"));
        Scene scene = new Scene(root);
        root.setOnMousePressed((MouseEvent event) ->{
            x = event.getSceneX();
            y = event.getSceneY();
        });
        
        root.setOnMouseDragged((MouseEvent event) ->{
            this.setX(event.getScreenX() - x);
            this.setY(event.getScreenY() - y);
            
            this.setOpacity(.8);
        });
        
        root.setOnMouseReleased((MouseEvent event) ->{
            this.setOpacity(1);
        });
        
        this.initStyle(StageStyle.TRANSPARENT);
        this.setScene(scene);
        this.setTitle("Lgoin Page");
       
        this.show();
    }

}
