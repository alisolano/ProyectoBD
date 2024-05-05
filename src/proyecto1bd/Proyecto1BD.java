/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto1bd;
import Connect.ConnectDB;
import java.sql.SQLException;
/**
 *
 * @author Leo
 */
public class Proyecto1BD {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        ConnectDB.getCategory();
        ConnectDB.getGender();
        ConnectDB.getIdtype();
        /*String availability = ConnectDB.checkUsernameAvailability("john_doe");
        if (availability.equals("Username available")){
            System.out.println("no usado");
        } else {
            System.out.println("usuario registrado");
        }
         ConnectDB.InsertUserSys("Jose", "Daniel", "Arias", "Orozco", 84862725, "arias@example.com", 
                       402640000, "ar_ias", "aaaa", 1, 1, 1, 1);
        boolean isValid = ConnectDB.verifyUserPassword("john_doe", "password123");
        if (isValid){
            System.out.println("password correcta");
        } else {
            System.out.println("password incorrecta o usuario no existe");
        } */
    }
    
}
