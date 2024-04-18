/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Connect;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import oracle.jdbc.OracleTypes;
/**
 *
 * @author Alison
 */
public class ConnectDB {
    public static void insertUser(String firstName, String middleName, 
                                    String lastName, String secondSurname, int idNumber, String email, 
                                    int phoneNumber, String username, String password, 
                                    int idDistrict, int idNationality, int idGender, int idType) throws SQLException {
        String host = "jdbc:oracle:thin:@localhost:1521:DBPrueba";
        String uName = "proyectoDBA";
        String uPass = "proyectoDBA";

        Connection con = DriverManager.getConnection(host, uName, uPass);
        CallableStatement stmt = con.prepareCall("{ call InsertUserSys(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }");

        stmt.setString(1, firstName);
        stmt.setString(2, middleName);
        stmt.setString(3, lastName);
        stmt.setString(4, secondSurname);
        stmt.setInt(5, idNumber);
        stmt.setNull(6, java.sql.Types.DATE); // p_Birthdate
        stmt.setNull(7, java.sql.Types.BLOB); // p_Photo
        stmt.setString(8, email);
        stmt.setInt(9, phoneNumber);
        stmt.setString(10, username);
        stmt.setString(11, password);
        stmt.setInt(12, idDistrict);
        stmt.setInt(13, idNationality);
        stmt.setInt(14, idGender);
        stmt.setInt(15, idType);
        
        stmt.execute();
        //insertClient(); // No se pasa ningún parámetro ya que el ID se genera automáticamente
    }
    
    public static void insertPerson(String firstName, String middleName, 
                                    String lastName, String secondSurname, String biography, int height,
                                    String trivia, int idDistrict) throws SQLException {
        String host = "jdbc:oracle:thin:@localhost:1521:DBPrueba";
        String uName = "proyectoDBA";
        String uPass = "proyectoDBA";

        Connection con = DriverManager.getConnection(host, uName, uPass);
        CallableStatement stmt = con.prepareCall("{ call InsertUserSys(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }");

        stmt.setString(1, firstName);
        stmt.setString(2, middleName);
        stmt.setString(3, lastName);
        stmt.setString(4, secondSurname);
        stmt.setString(5, biography);
        stmt.setNull(6, java.sql.Types.DATE); // p_Birthdate
        stmt.setInt(7, height);
        stmt.setNull(8, java.sql.Types.BLOB); // p_Photo
        stmt.setString(9, trivia);
        stmt.setInt(10, idDistrict);
  
        stmt.execute();
     }

    public static void insertProduction(int idCategory, String title, int duration, 
                                        String synopsis, String trailer, int releaseYear) throws SQLException {
        String host = "jdbc:oracle:thin:@localhost:1521:DBPrueba";
        String uName = "proyectoDBA";
        String uPass = "proyectoDBA";

        Connection con = DriverManager.getConnection(host, uName, uPass);
        CallableStatement stmt = con.prepareCall("{ call InsertProduction(?,?,?,?,?,?,?) }");

        stmt.setInt(1, idCategory);
        stmt.setString(2, title);
        stmt.setInt(3, duration);
        stmt.setString(4, synopsis);
        stmt.setString(5, trailer);
        stmt.setInt(6, releaseYear);
        stmt.setNull(7, java.sql.Types.BLOB); // p_Photo

        stmt.execute();
    }

    /*public static void insertClient() throws SQLException {
        String host = "jdbc:oracle:thin:@localhost:1521:DBPrueba";
        String uName = "proyectoDBA";
        String uPass = "proyectoDBA";

        Connection con = DriverManager.getConnection(host, uName, uPass);
        CallableStatement stmt = con.prepareCall("{ call InsertClient() }");

        stmt.execute();
    }*/

    
    public static void getCategoria() throws SQLException {
        String host = "jdbc:oracle:thin:@localhost:1521:DBPrueba";
        String uName = "proyectoDBA";
        String uPass = "proyectoDBA";

        Connection con = DriverManager.getConnection(host, uName, uPass);
        CallableStatement stmt = con.prepareCall("{ call getCategoria(?)}");

        // Agregar un parámetro OUT ficticio
        stmt.registerOutParameter(1, OracleTypes.CURSOR);

        stmt.execute();

        ResultSet rs = (ResultSet) stmt.getObject(1);

        while (rs.next()) {
            System.out.println(rs.getInt("id") + " " + rs.getString("Name"));
        }
    }

}
