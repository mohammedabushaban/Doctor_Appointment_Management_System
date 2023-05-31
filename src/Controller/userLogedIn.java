/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.Login.Login;
import Model.User;

/**
 *
 * @author Malik
 */
public class userLogedIn extends Login{

    private static userLogedIn instance;
    private int userId;

    private userLogedIn() {
        // Private constructor to prevent direct instantiation
    }

    public static userLogedIn getInstance() {
        if (instance == null) {
            instance = new userLogedIn();
        }
        return instance;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
