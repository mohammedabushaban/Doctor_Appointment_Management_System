/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.io.IOException;

/**
 *
 * @author Malik
 */
public class ViewManager {

    public static LoginPage loginPage;
    public static AdminDashboard adminPage;
    public static PatientDashboard PatientPage;
    public static RegisterPage registerPage;
    public static BookedAppointmentsPage BookedAppointmentsPage;

    private ViewManager() {
    }

    public static void openLoginPage() throws IOException {
        if (loginPage == null) {
            loginPage = new LoginPage();
            loginPage.show();
        } else {
            loginPage.show();
        }

    }

    public static void closeLoginPage() {
        if (loginPage != null) {
            loginPage.close();
        }
    }

    public static void openAdminPage() throws IOException {
        if (adminPage == null) {
            adminPage = new AdminDashboard();
            adminPage.show();
        } else {
            adminPage.show();
        }

    }

    public static void closeAdminPage() {
        if (adminPage != null) {
            adminPage.close();
        }
    }

     public static void openPatientPage() throws IOException{
        if (PatientPage == null) {
            PatientPage = new PatientDashboard();
            PatientPage.show();
        } else {
            PatientPage.show();
        }
        
    }
    public static void closePatientPage(){
        if(PatientPage != null)
            PatientPage.close();
    }
         public static void openRegisterPage() throws IOException{
        if (registerPage == null) {
            registerPage = new RegisterPage();
            registerPage.show();
        } else {
            registerPage.show();
        }
        
    }
    public static void closeRegisterPage(){
        if(registerPage != null)
            registerPage.close();
    }
    
    public static void openBookedAppointmentsPage() throws IOException{
        if(BookedAppointmentsPage == null){
            BookedAppointmentsPage = new BookedAppointmentsPage();
            BookedAppointmentsPage.show();
        }else{
            BookedAppointmentsPage.show();
        }
    }
    
    public static void closeBookedAppointmentsPage(){
        if(BookedAppointmentsPage != null){
            BookedAppointmentsPage.close();
        }
    }
    
}
