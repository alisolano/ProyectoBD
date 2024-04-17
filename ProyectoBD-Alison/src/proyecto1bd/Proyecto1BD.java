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
        //ConnectDB.insertPerson(0, firstName, middleName, lastName, secondSurname);
        ConnectDB.getCategoria();
        ConnectDB.insertUser("John", "Doe", "Vargas", "Araya", 123456789, "john@example.com", 
                        123456789, "john_doe", "password123", 1, 1, 1, 1);
    }
    
}
