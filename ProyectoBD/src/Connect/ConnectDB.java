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
import java.util.ArrayList;
import java.util.List;
import javax.lang.model.util.Types;
import oracle.jdbc.OracleTypes;
/**
 *
 * @author Alison
 */
public class ConnectDB {
    public static void InsertUserSys(String firstName, String middleName, 
                                    String lastName, String secondSurname, int idNumber, String email, 
                                    int phoneNumber, String username, String password, 
                                    int idDistrict, int idNationality, int idGender, int idType) throws SQLException {
        String host = "jdbc:oracle:thin:@localhost:1521:DBPrueba";
        String uName = "aa";
        String uPass = "aa";

        Connection con = DriverManager.getConnection(host, uName, uPass);
        CallableStatement stmt = con.prepareCall("{ call InsertUserSys(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }");

        stmt.setString(1, firstName);
        stmt.setString(2, middleName);
        stmt.setString(3, lastName);
        stmt.setString(4, secondSurname);
        stmt.setInt(5, idNumber);
        stmt.setNull(6, java.sql.Types.DATE); 
        stmt.setNull(7, java.sql.Types.BLOB); 
        stmt.setString(8, email);
        stmt.setInt(9, phoneNumber);
        stmt.setString(10, username);
        stmt.setString(11, password);
        stmt.setInt(12, idDistrict);
        stmt.setInt(13, idNationality);
        stmt.setInt(14, idGender);
        stmt.setInt(15, idType);
        stmt.getWarnings();
        stmt.execute();
        stmt.getMoreResults();
        
    }
    
    public static void getCategory() throws SQLException {
        String host = "jdbc:oracle:thin:@localhost:1521:DBPrueba";
        String uName = "aa";
        String uPass = "aa";

        Connection con = DriverManager.getConnection(host, uName, uPass);
        CallableStatement stmt = con.prepareCall("{ call getCategory(?)}");

        // Agregar un parámetro OUT ficticio
        stmt.registerOutParameter(1, OracleTypes.CURSOR);

        stmt.execute();

        ResultSet rs = (ResultSet) stmt.getObject(1);

        while (rs.next()) {
            System.out.println(rs.getInt("id") + " " + rs.getString("Name"));
        }
    }
    
    public static ArrayList<String> getGender() throws SQLException {
        ArrayList<String> generos = new ArrayList<>(); //lista para almacenar los géneros

        String host = "jdbc:oracle:thin:@localhost:1521:DBPrueba";
        String uName = "aa";
        String uPass = "aa";

        try (Connection con = DriverManager.getConnection(host, uName, uPass);
             CallableStatement stmt = con.prepareCall("{ call getGender(?)}")) {

            stmt.registerOutParameter(1, OracleTypes.CURSOR);
            stmt.execute();

            ResultSet rs = (ResultSet) stmt.getObject(1);

            while (rs.next()) {
                // Agregar cada género a la lista
                generos.add(rs.getString("Name"));
            }
        } 

        return generos; 
    }

        
    public static ArrayList getIdtype() throws SQLException {
        ArrayList<String> idTypes = new ArrayList<>();
        
        String host = "jdbc:oracle:thin:@localhost:1521:DBPrueba";
        String uName = "aa";
        String uPass = "aa";

        Connection con = DriverManager.getConnection(host, uName, uPass);
        CallableStatement stmt = con.prepareCall("{ call getIdtype(?)}");

        // Agregar un parámetro OUT ficticio
        stmt.registerOutParameter(1, OracleTypes.CURSOR);

        stmt.execute();

        ResultSet rs = (ResultSet) stmt.getObject(1);

        while (rs.next()) {
            System.out.println(rs.getInt("id") + " " + rs.getString("Name"));
            idTypes.add(rs.getString("Name"));
        }
        return idTypes;
    }
    
    public static ArrayList getNationality() throws SQLException {
        ArrayList<String> Nationalities = new ArrayList<>();
        
        String host = "jdbc:oracle:thin:@localhost:1521:DBPrueba";
        String uName = "aa";
        String uPass = "aa";

        Connection con = DriverManager.getConnection(host, uName, uPass);
        CallableStatement stmt = con.prepareCall("{ call getNationality(?)}");

        // Agregar un parámetro OUT ficticio
        stmt.registerOutParameter(1, OracleTypes.CURSOR);

        stmt.execute();

        ResultSet rs = (ResultSet) stmt.getObject(1);

        while (rs.next()) {
            System.out.println(rs.getInt("id") + " " + rs.getString("Name"));
            Nationalities.add(rs.getString("Name"));
        }
        return Nationalities;
    }
    
    public static ArrayList getCountry() throws SQLException {
        ArrayList<String> Countries = new ArrayList<>();
        
        String host = "jdbc:oracle:thin:@localhost:1521:DBPrueba";
        String uName = "aa";
        String uPass = "aa";

        Connection con = DriverManager.getConnection(host, uName, uPass);
        CallableStatement stmt = con.prepareCall("{ call getCountry(?)}");

        // Agregar un parámetro OUT ficticio
        stmt.registerOutParameter(1, OracleTypes.CURSOR);

        stmt.execute();

        ResultSet rs = (ResultSet) stmt.getObject(1);

        while (rs.next()) {
            System.out.println(rs.getInt("id") + " " + rs.getString("Name"));
            Countries.add(rs.getString("Name"));
        }
        return Countries;
    }
    
    public static ArrayList getStates(String country) throws SQLException {
        ArrayList<String> States = new ArrayList<>();
        
        String host = "jdbc:oracle:thin:@localhost:1521:DBPrueba";
        String uName = "aa";
        String uPass = "aa";
        CallableStatement stmt;
        Connection con = DriverManager.getConnection(host, uName, uPass);
        if (country.equals("Costa Rica")){
            stmt = con.prepareCall("{ call getStates(?)}");
        } else {
            return States;
        }
       
        // Agregar un parámetro OUT ficticio
        stmt.registerOutParameter(1, OracleTypes.CURSOR);

        stmt.execute();

        ResultSet rs = (ResultSet) stmt.getObject(1);

        while (rs.next()) {
            System.out.println(rs.getInt("id") + " " + rs.getString("Name"));
            States.add(rs.getString("Name"));
        }
        return States;
    }

    public static ArrayList getDistricts(String canton) throws SQLException {
        ArrayList<String> Districts = new ArrayList<>();
        
        String host = "jdbc:oracle:thin:@localhost:1521:DBPrueba";
        String uName = "aa";
        String uPass = "aa";
        CallableStatement stmt;

        Connection con = DriverManager.getConnection(host, uName, uPass);
        if (canton.equals("Alajuela")){
            stmt = con.prepareCall("{ call getDistrict1(?)}");
        } else if (canton.equals("Heredia")){
            stmt = con.prepareCall("{ call getDistrict2(?)}"); 
        } else if (canton.equals("Limon")){
            stmt = con.prepareCall("{ call getDistrict3(?)}"); 
        } else if (canton.equals("Cartago")){
            stmt = con.prepareCall("{ call getDistrict4(?)}"); 
        } else if (canton.equals("San José")){
            stmt = con.prepareCall("{ call getDistrict5(?)}"); 
        } else if (canton.equals("Guanacaste")){
            stmt = con.prepareCall("{ call getDistrict6(?)}"); 
        } else {
            stmt = con.prepareCall("{ call getDistrict7(?)}"); 
        }

        stmt.registerOutParameter(1, OracleTypes.CURSOR);

        stmt.execute();

        ResultSet rs = (ResultSet) stmt.getObject(1);

        while (rs.next()) {
            System.out.println(rs.getInt("id") + " " + rs.getString("Name"));
            Districts.add(rs.getString("Name"));
        }
        return Districts;
    }
    
    public static String checkUsernameAvailability(String username) throws SQLException {
        String result = "";

        String host = "jdbc:oracle:thin:@localhost:1521:DBPrueba";
        String uName = "aa";
        String uPass = "aa";

        try (Connection con = DriverManager.getConnection(host, uName, uPass)) {
            try (CallableStatement stmt = con.prepareCall("{ call CheckUsernameAvailability(?, ?) }")) {
                stmt.setString(1, username);
                stmt.registerOutParameter(2, java.sql.Types.VARCHAR);
                stmt.execute();
                result = stmt.getString(2);
            }
        }

        return result;
    }
    
    public static boolean verifyUserPassword(String username, String password) {
        String host = "jdbc:oracle:thin:@localhost:1521:DBPrueba";
        String uName = "aa";
        String uPass = "aa";
        boolean result = false;

        try (Connection con = DriverManager.getConnection(host, uName, uPass)) {
            CallableStatement stmt = con.prepareCall("{call VerifyUserPassword(?, ?, ?)}");
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.registerOutParameter(3, java.sql.Types.NUMERIC);
            stmt.execute();

            int count = stmt.getInt(3);
            if (count == 1) {
                result = true; 
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return result;
    }
}


