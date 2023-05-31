/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Malik
 */
public class PatientDashboard extends Stage{
    private double x = 0;
    private double y = 0;
    
    // All Scenes that AdminDashboard page have
    private Scene PatientDashboard;
    
    public PatientDashboard() throws IOException{
        //load PatientFXML File in PatientDashboard Scene
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PatientFXML/Patient.fxml"));
        Parent root = loader.load();     
        PatientDashboard = new Scene(root);
        
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
        
        // Set Main Scene in Admin Dasboard ( dashboard Scene )
        this.setScene(PatientDashboard);
    }
}
