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
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author Alison
 */
public class ConnectDB {
    
    private static Map<String, Integer> nationalityIdMap = new HashMap<>();

    public static void InsertUserSys(String firstName, String middleName, 
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
    
    public static void InsertUserSysAdministrator(String firstName, String middleName, 
                                    String lastName, String secondSurname, int idNumber, String email, 
                                    int phoneNumber, String username, String password, 
                                    int idDistrict, int idNationality, int idGender, int idType) throws SQLException {
        String host = "jdbc:oracle:thin:@localhost:1521:DBPrueba";
        String uName = "proyectoDBA";
        String uPass = "proyectoDBA";

        Connection con = DriverManager.getConnection(host, uName, uPass);
        CallableStatement stmt = con.prepareCall("{ call InsertUserSysAdministrator(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }");

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
    
    public static void InsertPerson(String p_FirstName, String p_MiddleName, String p_LastName,
                                    String p_SecondSurname, String p_Biography, int p_Height,
                                    String p_Trivia, int p_idDistrict) throws SQLException {
            
        String host = "jdbc:oracle:thin:@localhost:1521:DBPrueba";
        String uName = "proyectoDBA";
        String uPass = "proyectoDBA";

        Connection con = DriverManager.getConnection(host, uName, uPass);
        CallableStatement stmt = con.prepareCall("{ call InsertPerson(?,?,?,?,?,?,?,?,?,?,?) }");

        stmt.setString(1, p_FirstName);
        stmt.setString(2, p_MiddleName);
        stmt.setString(3, p_LastName);
        stmt.setString(4, p_SecondSurname);
        stmt.setString(5, p_Biography);
        stmt.setNull(6, java.sql.Types.DATE); 
        stmt.setFloat(7, p_Height);
        stmt.setNull(8, java.sql.Types.BLOB); 
        stmt.setString(9, p_Trivia);
        stmt.setInt(10, p_idDistrict);

        stmt.execute();
        stmt.close();
    }
    
    
    public static void updatePerson(int p_id, String p_FirstName, String p_MiddleName, 
                                    String p_LastName, String p_SecondSurname, String p_Biography, 
                                    Float p_Height, String p_Trivia, 
                                    int p_idDistrict) throws SQLException {
        
        String host = "jdbc:oracle:thin:@localhost:1521:DBPrueba";
        String uName = "proyectoDBA";
        String uPass = "proyectoDBA";

        Connection con = DriverManager.getConnection(host, uName, uPass);
        CallableStatement stmt = con.prepareCall("{ call UpdatePerson(?,?,?,?,?,?,?,?,?,?,?) }");

        stmt.setInt(1, p_id);
        stmt.setString(2, p_FirstName);
        stmt.setString(3, p_MiddleName);
        stmt.setString(4, p_LastName);
        stmt.setString(5, p_SecondSurname);
        stmt.setString(6, p_Biography);
        stmt.setNull(7, java.sql.Types.DATE); 
        stmt.setFloat(8, p_Height);
        stmt.setNull(9, java.sql.Types.BLOB); 
        stmt.setString(10, p_Trivia);
        stmt.setInt(11, p_idDistrict);

        stmt.execute();
        stmt.close();
    }
        
    
    public static void updateProduction(int p_id, int p_idCategory, String p_Title, 
                                         int p_Duration, String p_Synopsis, String p_Trailer, 
                                         int p_ReleaseYear) throws SQLException {
        
        String host = "jdbc:oracle:thin:@localhost:1521:DBPrueba";
        String uName = "proyectoDBA";
        String uPass = "proyectoDBA";

        Connection con = DriverManager.getConnection(host, uName, uPass);
        CallableStatement stmt = con.prepareCall("{ call UpdateProduction(?,?,?,?,?,?,?,?) }");

        stmt.setInt(1, p_id);
        stmt.setInt(2, p_idCategory);
        stmt.setString(3, p_Title);
        stmt.setInt(4, p_Duration);
        stmt.setString(5, p_Synopsis);
        stmt.setString(6, p_Trailer);
        stmt.setInt(7, p_ReleaseYear);
        stmt.setNull(8, java.sql.Types.BLOB); 

        stmt.execute();
        stmt.close();
    }
    
    public static void getCategory() throws SQLException {
        String host = "jdbc:oracle:thin:@localhost:1521:DBPrueba";
        String uName = "proyectoDBA";
        String uPass = "proyectoDBA";

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
        String uName = "proyectoDBA";
        String uPass = "proyectoDBA";

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
        String uName = "proyectoDBA";
        String uPass = "proyectoDBA";

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
        String uName = "proyectoDBA";
        String uPass = "proyectoDBA";

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
        String uName = "proyectoDBA";
        String uPass = "proyectoDBA";

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
        String uName = "proyectoDBA";
        String uPass = "proyectoDBA";
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
        String uName = "proyectoDBA";
        String uPass = "proyectoDBA";
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
    
    public static boolean checkUsernameAvailability(String username) throws SQLException {
        String result = "";

        String host = "jdbc:oracle:thin:@localhost:1521:DBPrueba";
        String uName = "proyectoDBA";
        String uPass = "proyectoDBA";

        try (Connection con = DriverManager.getConnection(host, uName, uPass)) {
            try (CallableStatement stmt = con.prepareCall("{ call CheckUsernameAvailability(?, ?) }")) {
                stmt.setString(1, username);
                stmt.registerOutParameter(2, java.sql.Types.VARCHAR);
                stmt.execute();
                result = stmt.getString(2);
            }
        }
        if (result.equals("Username available")){
            System.out.println("no usado");
            return true;
        } else {
            System.out.println("usuario registrado");
            return false;
        }       
    }
    
    public static boolean verifyUserPassword(String username, String password) {
        String host = "jdbc:oracle:thin:@localhost:1521:DBPrueba";
        String uName = "proyectoDBA";
        String uPass = "proyectoDBA";
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
    
    public static void insertProduction(int idCategory, String title, int duration, String synopsis, String trailer, int releaseYear, byte[] photo) {
        String host = "jdbc:oracle:thin:@localhost:1521:DBPrueba";
        String uName = "proyectoDBA";
        String uPass = "proyectoDBA";

        try (Connection con = DriverManager.getConnection(host, uName, uPass)) {
            CallableStatement stmt = con.prepareCall("{ call InsertProduction(?, ?, ?, ?, ?, ?, ?) }");

            stmt.setInt(1, idCategory);
            stmt.setString(2, title);
            stmt.setInt(3, duration);
            stmt.setString(4, synopsis);
            stmt.setString(5, trailer);
            stmt.setInt(6, releaseYear);
            stmt.setBytes(7, photo);

            stmt.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public static void getProductionInfo(String productionName) {
        String host = "jdbc:oracle:thin:@localhost:1521:DBPrueba";
        String uName = "proyectoDBA";
        String uPass = "proyectoDBA";

        try (Connection con = DriverManager.getConnection(host, uName, uPass)) {
            CallableStatement stmt = con.prepareCall("{call getProductionInfo(?, ?, ?, ?)}");

            stmt.setString(1, productionName);
            stmt.registerOutParameter(2, java.sql.Types.VARCHAR);
            stmt.registerOutParameter(3, java.sql.Types.VARCHAR);
            stmt.registerOutParameter(4, java.sql.Types.VARCHAR);
            stmt.execute();
            String title = stmt.getString(2);
            String synopsis = stmt.getString(3);
            String category = stmt.getString(4);

            System.out.println("Título: " + title);
            System.out.println("Sinopsis: " + synopsis);
            System.out.println("Categoría: " + category);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    public static int getGenderIDByName(String genderName) throws SQLException {
        int genderID = 0; 
        String host = "jdbc:oracle:thin:@localhost:1521:DBPrueba";
        String uName = "proyectoDBA";
        String uPass = "proyectoDBA";

        try (Connection con = DriverManager.getConnection(host, uName, uPass);
             CallableStatement stmt = con.prepareCall("{ call GetGenderIDByName(?, ?)}")) {

            stmt.setString(1, genderName); 

            stmt.registerOutParameter(2, java.sql.Types.NUMERIC);

            stmt.execute();

            genderID = stmt.getInt(2);
        }

        return genderID; 
    }

}


