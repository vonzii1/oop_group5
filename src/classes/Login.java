/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

/**
 *
 * @author User
 */
public class Login {
    
    private String emailAddress;
    private String password;

    public Login(String emailAddress, String password) {
        this.emailAddress = emailAddress;
        this.password = password;
    }
    
    public boolean verifyLoginCedentials(String emailAddress, String password){
        return true;
    }
    
}
