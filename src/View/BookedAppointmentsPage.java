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
public class BookedAppointmentsPage extends Stage{
     private double x = 0;
    private double y = 0;
    
    // All Scenes that BookedAppointmentsPage page have
    private Scene BookedAppointmentsPage;
    
    public BookedAppointmentsPage() throws IOException{
        //load BookedAppointmentsPage FXML File in BookedAppointmentsPage Scene
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminFXML/BookedAppointmentsPage.fxml"));
        Parent root = loader.load();     
        BookedAppointmentsPage = new Scene(root);
        
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
        
        // Set BookedAppointmentsPage Scene in Admin Dasboard ( dashboard Scene )
        this.setScene(BookedAppointmentsPage);
    }
}
