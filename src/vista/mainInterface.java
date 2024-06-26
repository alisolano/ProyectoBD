/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista;
import Connect.ConnectDB;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
/**
 *
 * @author Leo
 */

public class mainInterface extends javax.swing.JFrame {
    
    public String firstName, middleName, lastName, secondSurname, biography,
            trivia, birthdate, currentUser;
    public int Height, idDistrict;
    
    public String username, password, email, name, secondName, lastNames, secondLastNames, address, 
                  gender ,idType, nationality, country, region, district;
    public int phone, idNum, realgender, realidtype, realidDistrict, realidnationality;
    public Date realBirthdate;
    public byte[] photo;
    
    /**
     * Creates new form mainInterface
     */
    public mainInterface() {
        initComponents();
        try {
        ArrayList<String> categories = ConnectDB.getCategory();
        for (String category : categories) {
            productionEditorGenreCB.addItem(category);
        }
        ArrayList<String> genders = ConnectDB.getGender();
        for (String gender : genders) {
            userEditorGenderCB.addItem(gender);
            personEditorGenderCB.addItem(gender);
        }
        ArrayList<String> idType = ConnectDB.getIdtype();
        for (String id : idType) {
            userEditorIdTypeCB.addItem(id);
    }
        ArrayList<String> Nationalities = ConnectDB.getNationality();
        for (String country : Nationalities) {
             userEditorNationalityCB.addItem(country);
             personEditorNationCB.addItem(country);
        }
        ArrayList<String> Countries = ConnectDB.getCountry();
        for (String country : Countries) {
            userEditorCountryCB.addItem(country);
            personEditorBirthplaceCB.addItem(country);
        }
        userEditorCountryCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userEditorRegionCB.removeAllItems();

                country = (String) userEditorCountryCB.getSelectedItem();
                System.out.println(country);
                try {
                    ArrayList<String> States = ConnectDB.getStates(country);
                            for (String state : States) {
                                userEditorRegionCB.addItem(state);
                            }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        userEditorRegionCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Limpiar los elementos actuales del ComboBox 6
                userEditorDistrictCB.removeAllItems();

                // Obtener el elemento seleccionado por el usuario
                region = (String) userEditorRegionCB.getSelectedItem();
                System.out.println(region);
                try {
                    ArrayList<String> districts = ConnectDB.getDistricts(region);
                    for (String district : districts) {
                        userEditorDistrictCB.addItem(district);
                    }
                    System.out.println("" + districts);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Hubo un error", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void setTags(){
        mainUserTag.setText(currentUser);
        userEditorUsernameTag.setText(currentUser);
        personEditorUsernameTag1.setText(currentUser);
        personEditorUsernameTag.setText(currentUser);
        adminUsernameTag.setText(currentUser);
        personEditorUsernameTag2.setText(currentUser);
        personEditorUsernameTag3.setText(currentUser);
        personEditorUsernameTag4.setText(currentUser);
        if(ConnectDB.checkUserType(currentUser)){
            adminPanelBttn.setVisible(true);
            editProductionBttn.setVisible(true);
        }
        else{
            adminPanelBttn.setVisible(false);
            //editProductionBttn.setVisible(false);
        }
    }
    
    public void setImages(){
        
    }
    
    
    public void userEditorCleaner(){
        userEditorUsername.setText("");
        userEditorAddress.setText("");
        userEditorBirthdate.setText("");
        userEditorName.setText("");
        userEditorEmail.setText("");
        userEditorIdNum.setText("");
        userEditorPassword.setText("");
        userEditorPasswordConf.setText("");
        userEditorPhone.setText("");
        userEditorSecondName.setText("");
        userEditorSecondSurname.setText("");
        userEditorSurname.setText("");
        userEditorCountryCB.setSelectedIndex(-1);
        userEditorDistrictCB.setSelectedIndex(-1);
        userEditorGenderCB.setSelectedIndex(-1);
        userEditorIdTypeCB.setSelectedIndex(-1);
        userEditorNationalityCB.setSelectedIndex(-1);
        userEditorRegionCB.setSelectedIndex(-1);
    }
    
    public void productionEditorCleaner(){
        productionEditorName.setText("");
        productionEditorCategoryCB.setSelectedIndex(-1);
        productionEditorGenreCB.setSelectedIndex(-1);
        productionEditorPlaytime.setText("");
        productionEditorRelease.setText("");
        productionEditorStoryline.setText("");
    }

    public void personEditorCleaner(){
        userEditorUsername.setText("");
        userEditorEmail.setText("");
        userEditorPassword.setText("");
        userEditorPasswordConf.setText(trivia);
        userEditorName.setText("");
        userEditorSecondName.setText("");
        userEditorSurname.setText("");
        userEditorSecondSurname.setText("");
        userEditorPhone.setText("");
        userEditorIdNum.setText("");
        userEditorCountryCB.setSelectedIndex(-1);
        userEditorDistrictCB.setSelectedIndex(-1);
        userEditorGenderCB.setSelectedIndex(-1);
        userEditorIdTypeCB.setSelectedIndex(-1);
        userEditorNationalityCB.setSelectedIndex(-1);
        userEditorRegionCB.setSelectedIndex(-1);
    }
    
    public void productionEditorSetter(String title, String category, String genre, int releaseDate, float playTime, String storyline){
        //Sets text fields
        productionEditorName.setText(title);
        productionEditorRelease.setText(Float.toString(playTime));
        productionEditorStoryline.setText(storyline);
        
        //Sets CBs right item
        for(int i = 0; i < productionEditorCategoryCB.getItemCount(); i++){
            if(productionEditorCategoryCB.getItemAt(i).equals(category))
                productionEditorCategoryCB.setSelectedIndex(i);
        }
        for(int i = 0; i < productionEditorGenreCB.getItemCount(); i++){
            if(productionEditorGenreCB.getItemAt(i).equals(category))
                productionEditorGenreCB.setSelectedIndex(i);
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        personPopUp = new javax.swing.JFrame();
        jPanel16 = new javax.swing.JPanel();
        jScrollPane12 = new javax.swing.JScrollPane();
        popUpList = new javax.swing.JList<>();
        popUpSearchBttn = new javax.swing.JButton();
        popUpSelectBttn = new javax.swing.JButton();
        popUpRoleCB = new javax.swing.JComboBox<>();
        jLabel26 = new javax.swing.JLabel();
        popUpSearchTxt = new javax.swing.JTextField();
        imageChooser = new javax.swing.JFrame();
        jPanel17 = new javax.swing.JPanel();
        jFileChooser1 = new javax.swing.JFileChooser();
        jScrollPane6 = new javax.swing.JScrollPane();
        jPanel15 = new javax.swing.JPanel();
        resultPanel = new javax.swing.JPanel();
        FilmTitle20 = new javax.swing.JLabel();
        filmPic11 = new javax.swing.JPanel();
        FilmTitle21 = new javax.swing.JLabel();
        FilmTitle22 = new javax.swing.JLabel();
        FilmTitle23 = new javax.swing.JLabel();
        FilmTitle24 = new javax.swing.JLabel();
        FilmTitle25 = new javax.swing.JLabel();
        FilmTitle26 = new javax.swing.JLabel();
        FilmTitle27 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        resultPanel1 = new javax.swing.JPanel();
        FilmTitle28 = new javax.swing.JLabel();
        filmPic12 = new javax.swing.JPanel();
        FilmTitle29 = new javax.swing.JLabel();
        FilmTitle30 = new javax.swing.JLabel();
        FilmTitle31 = new javax.swing.JLabel();
        FilmTitle32 = new javax.swing.JLabel();
        FilmTitle33 = new javax.swing.JLabel();
        FilmTitle34 = new javax.swing.JLabel();
        FilmTitle35 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        resultPanel2 = new javax.swing.JPanel();
        FilmTitle36 = new javax.swing.JLabel();
        filmPic13 = new javax.swing.JPanel();
        FilmTitle37 = new javax.swing.JLabel();
        FilmTitle38 = new javax.swing.JLabel();
        FilmTitle39 = new javax.swing.JLabel();
        FilmTitle40 = new javax.swing.JLabel();
        FilmTitle41 = new javax.swing.JLabel();
        FilmTitle42 = new javax.swing.JLabel();
        FilmTitle43 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        resultPanel3 = new javax.swing.JPanel();
        FilmTitle44 = new javax.swing.JLabel();
        filmPic14 = new javax.swing.JPanel();
        FilmTitle45 = new javax.swing.JLabel();
        FilmTitle46 = new javax.swing.JLabel();
        FilmTitle47 = new javax.swing.JLabel();
        FilmTitle48 = new javax.swing.JLabel();
        FilmTitle49 = new javax.swing.JLabel();
        FilmTitle50 = new javax.swing.JLabel();
        FilmTitle51 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTextArea4 = new javax.swing.JTextArea();
        resultPanel4 = new javax.swing.JPanel();
        FilmTitle52 = new javax.swing.JLabel();
        filmPic15 = new javax.swing.JPanel();
        FilmTitle53 = new javax.swing.JLabel();
        FilmTitle54 = new javax.swing.JLabel();
        FilmTitle55 = new javax.swing.JLabel();
        FilmTitle56 = new javax.swing.JLabel();
        FilmTitle57 = new javax.swing.JLabel();
        FilmTitle58 = new javax.swing.JLabel();
        FilmTitle59 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTextArea5 = new javax.swing.JTextArea();
        resultPanel5 = new javax.swing.JPanel();
        FilmTitle60 = new javax.swing.JLabel();
        filmPic16 = new javax.swing.JPanel();
        FilmTitle61 = new javax.swing.JLabel();
        FilmTitle62 = new javax.swing.JLabel();
        FilmTitle63 = new javax.swing.JLabel();
        FilmTitle64 = new javax.swing.JLabel();
        FilmTitle65 = new javax.swing.JLabel();
        FilmTitle66 = new javax.swing.JLabel();
        FilmTitle67 = new javax.swing.JLabel();
        jScrollPane11 = new javax.swing.JScrollPane();
        jTextArea6 = new javax.swing.JTextArea();
        mainPanel = new javax.swing.JPanel();
        menuBar = new javax.swing.JPanel();
        mainSearch = new javax.swing.JTextField();
        mainSearchButton = new javax.swing.JButton();
        mainLogin = new javax.swing.JButton();
        mainPanelTitle1 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        filmPic = new javax.swing.JPanel();
        LblTitlePic1 = new javax.swing.JLabel();
        FilmTitle1 = new javax.swing.JLabel();
        f1s1 = new javax.swing.JLabel();
        f1s2 = new javax.swing.JLabel();
        f1s3 = new javax.swing.JLabel();
        f2s4 = new javax.swing.JLabel();
        f2s5 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        filmPic2 = new javax.swing.JPanel();
        LblTitlePic2 = new javax.swing.JLabel();
        FilmTitle2 = new javax.swing.JLabel();
        f1s4 = new javax.swing.JLabel();
        f1s5 = new javax.swing.JLabel();
        f1s6 = new javax.swing.JLabel();
        f2s6 = new javax.swing.JLabel();
        f2s7 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        filmPic3 = new javax.swing.JPanel();
        LblTitlePic3 = new javax.swing.JLabel();
        FilmTitle3 = new javax.swing.JLabel();
        f1s7 = new javax.swing.JLabel();
        f1s8 = new javax.swing.JLabel();
        f1s9 = new javax.swing.JLabel();
        f2s8 = new javax.swing.JLabel();
        f2s9 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        filmPic4 = new javax.swing.JPanel();
        LblTitlePic4 = new javax.swing.JLabel();
        FilmTitle4 = new javax.swing.JLabel();
        f1s10 = new javax.swing.JLabel();
        f1s11 = new javax.swing.JLabel();
        f1s12 = new javax.swing.JLabel();
        f2s10 = new javax.swing.JLabel();
        f2s11 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        filmPic5 = new javax.swing.JPanel();
        LblTitlePic5 = new javax.swing.JLabel();
        FilmTitle5 = new javax.swing.JLabel();
        f1s13 = new javax.swing.JLabel();
        f1s14 = new javax.swing.JLabel();
        f1s15 = new javax.swing.JLabel();
        f2s12 = new javax.swing.JLabel();
        f2s13 = new javax.swing.JLabel();
        loggedMain = new javax.swing.JPanel();
        menuBar4 = new javax.swing.JPanel();
        mainSearch1 = new javax.swing.JTextField();
        mainUserTag = new javax.swing.JLabel();
        mainSearchButton1 = new javax.swing.JButton();
        LogOutBttn1 = new javax.swing.JButton();
        mainPanelTitle2 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        filmPic6 = new javax.swing.JPanel();
        LblLoggedPic1 = new javax.swing.JLabel();
        FilmTitle6 = new javax.swing.JLabel();
        f1s16 = new javax.swing.JLabel();
        f1s17 = new javax.swing.JLabel();
        f1s18 = new javax.swing.JLabel();
        f2s14 = new javax.swing.JLabel();
        f2s15 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        filmPic7 = new javax.swing.JPanel();
        LblLoggedPic2 = new javax.swing.JLabel();
        FilmTitle7 = new javax.swing.JLabel();
        f1s19 = new javax.swing.JLabel();
        f1s20 = new javax.swing.JLabel();
        f1s21 = new javax.swing.JLabel();
        f2s16 = new javax.swing.JLabel();
        f2s17 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        filmPic8 = new javax.swing.JPanel();
        LblLoggedPic3 = new javax.swing.JLabel();
        FilmTitle8 = new javax.swing.JLabel();
        f1s22 = new javax.swing.JLabel();
        f1s23 = new javax.swing.JLabel();
        f1s24 = new javax.swing.JLabel();
        f2s18 = new javax.swing.JLabel();
        f2s19 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        filmPic9 = new javax.swing.JPanel();
        LblLoggedPic4 = new javax.swing.JLabel();
        FilmTitle9 = new javax.swing.JLabel();
        f1s25 = new javax.swing.JLabel();
        f1s26 = new javax.swing.JLabel();
        f1s27 = new javax.swing.JLabel();
        f2s20 = new javax.swing.JLabel();
        f2s21 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        filmPic10 = new javax.swing.JPanel();
        LblLoggedPic5 = new javax.swing.JLabel();
        FilmTitle10 = new javax.swing.JLabel();
        f1s28 = new javax.swing.JLabel();
        f1s29 = new javax.swing.JLabel();
        f1s30 = new javax.swing.JLabel();
        f2s22 = new javax.swing.JLabel();
        f2s23 = new javax.swing.JLabel();
        adminPanelBttn = new javax.swing.JButton();
        userEditor = new javax.swing.JPanel();
        menuBar1 = new javax.swing.JPanel();
        userEditorReturnBtn = new javax.swing.JButton();
        userEditorClearBtn = new javax.swing.JButton();
        userEditorSubmitBtn = new javax.swing.JButton();
        userEditorUsernameTag = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        userEditorUsername = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        userEditorEmail = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        userEditorPassword = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        userEditorPasswordConf = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        userEditorName = new javax.swing.JTextField();
        userEditorSecondName = new javax.swing.JTextField();
        userEditorGenderCB = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        userEditorPhone = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        userEditorIdNum = new javax.swing.JTextField();
        userEditorBirthdate = new javax.swing.JFormattedTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        userEditorNationalityCB = new javax.swing.JComboBox<>();
        userEditorCountryCB = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        userEditorRegionCB = new javax.swing.JComboBox<>();
        userEditorDistrictCB = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        userEditorAddress = new javax.swing.JTextArea();
        userEditorIdTypeCB = new javax.swing.JComboBox<>();
        userEditorClearPicBtn = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        pfpLbl = new javax.swing.JLabel();
        userEditorSelectPicBtn = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        userEditorSurname = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        userEditorSecondSurname = new javax.swing.JTextField();
        jPanel20 = new javax.swing.JPanel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel36 = new javax.swing.JLabel();
        productionEditor = new javax.swing.JPanel();
        menuBar3 = new javax.swing.JPanel();
        personEditorReturnBtn1 = new javax.swing.JButton();
        personEditorClearBtn1 = new javax.swing.JButton();
        personEditorSubmitBtn1 = new javax.swing.JButton();
        personEditorUsernameTag1 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        productionEditorName = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        productionEditorStoryline = new javax.swing.JTextArea();
        personEditorSelectBtn1 = new javax.swing.JButton();
        personEditorClearPicBtn1 = new javax.swing.JButton();
        personEditorAddP1 = new javax.swing.JButton();
        personEditorAddRel1 = new javax.swing.JButton();
        personEditorAddTrivia1 = new javax.swing.JButton();
        jLabel41 = new javax.swing.JLabel();
        productionEditorCategoryCB = new javax.swing.JComboBox<>();
        productionEditorGenreCB = new javax.swing.JComboBox<>();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        personEditorAddP2 = new javax.swing.JButton();
        jLabel44 = new javax.swing.JLabel();
        productionEditorPlaytime = new javax.swing.JTextField();
        productionEditorLinkTrailer = new javax.swing.JButton();
        productionEditorRelease = new javax.swing.JTextField();
        filmPic1 = new javax.swing.JLabel();
        personEditor = new javax.swing.JPanel();
        menuBar2 = new javax.swing.JPanel();
        personEditorReturnBtn = new javax.swing.JButton();
        personEditorClearBtn = new javax.swing.JButton();
        personEditorSubmitBtn = new javax.swing.JButton();
        personEditorUsernameTag = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        personEditorName = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        personEditorMidName = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        personEditorHeight = new javax.swing.JTextField();
        personEditorGenderCB = new javax.swing.JComboBox<>();
        jLabel23 = new javax.swing.JLabel();
        personEditorBirthdate = new javax.swing.JFormattedTextField();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        personEditorNationCB = new javax.swing.JComboBox<>();
        personEditorBirthplaceCB = new javax.swing.JComboBox<>();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        personEditorRegionCB = new javax.swing.JComboBox<>();
        personEditorDistrictCB = new javax.swing.JComboBox<>();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        personEditorAddress = new javax.swing.JTextArea();
        personEditorSurname = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        personEditorBio = new javax.swing.JTextArea();
        jLabel33 = new javax.swing.JLabel();
        personEditorSelectBtn = new javax.swing.JButton();
        personEditorClearPicBtn = new javax.swing.JButton();
        personEditorAddP = new javax.swing.JButton();
        personEditorAddRel = new javax.swing.JButton();
        personEditorAddTrivia = new javax.swing.JButton();
        personEditorSecondSurname = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        pfpLbl12 = new javax.swing.JLabel();
        searchResults = new javax.swing.JPanel();
        menuBar5 = new javax.swing.JPanel();
        mainSearch2 = new javax.swing.JTextField();
        mainSearchButton2 = new javax.swing.JButton();
        mainLogin1 = new javax.swing.JButton();
        mainPanelTitle4 = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        filterChB5 = new javax.swing.JCheckBox();
        filterChB6 = new javax.swing.JCheckBox();
        filterChB7 = new javax.swing.JCheckBox();
        filterChB8 = new javax.swing.JCheckBox();
        filterChB9 = new javax.swing.JCheckBox();
        jSpinner7 = new javax.swing.JSpinner();
        FilmTitle17 = new javax.swing.JLabel();
        jSpinner8 = new javax.swing.JSpinner();
        FilmTitle18 = new javax.swing.JLabel();
        jSpinner9 = new javax.swing.JSpinner();
        FilmTitle19 = new javax.swing.JLabel();
        jPanel24 = new javax.swing.JPanel();
        personEditorGenderCB4 = new javax.swing.JComboBox<>();
        seeProductionBttn = new javax.swing.JButton();
        editProductionBttn = new javax.swing.JButton();
        jScrollPane17 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        adminGUI = new javax.swing.JPanel();
        menuBar6 = new javax.swing.JPanel();
        adminReturnBttn = new javax.swing.JButton();
        adminUsernameTag = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        adminEditProductionBttn = new javax.swing.JButton();
        adminCreateProductionBttn = new javax.swing.JButton();
        adminEditPersonBttn = new javax.swing.JButton();
        adminCreatePersonBttn = new javax.swing.JButton();
        adminEditUserBttn = new javax.swing.JButton();
        adminCreateBttn = new javax.swing.JButton();
        jPanel19 = new javax.swing.JPanel();
        adminStatisticsBttn = new javax.swing.JButton();
        jLabel34 = new javax.swing.JLabel();
        productionVisualiser = new javax.swing.JPanel();
        menuBar7 = new javax.swing.JPanel();
        prodVReturnBtn = new javax.swing.JButton();
        return2main = new javax.swing.JButton();
        personEditorUsernameTag2 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        personEditorAddRel3 = new javax.swing.JButton();
        personEditorAddTrivia3 = new javax.swing.JButton();
        filmPic18 = new javax.swing.JPanel();
        prodVPicLbl = new javax.swing.JLabel();
        prodVTitleLbl = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        prodVCategoryLbl = new javax.swing.JLabel();
        prodVGenreLbl = new javax.swing.JLabel();
        prodVRelease = new javax.swing.JLabel();
        prodVPlaytime = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        jScrollPane15 = new javax.swing.JScrollPane();
        jTextArea7 = new javax.swing.JTextArea();
        jScrollPane13 = new javax.swing.JScrollPane();
        jScrollPane14 = new javax.swing.JScrollPane();
        personVisualiser = new javax.swing.JPanel();
        menuBar8 = new javax.swing.JPanel();
        prodVReturnBtn1 = new javax.swing.JButton();
        return2main1 = new javax.swing.JButton();
        personEditorUsernameTag3 = new javax.swing.JLabel();
        personVEditBttn = new javax.swing.JButton();
        jLabel51 = new javax.swing.JLabel();
        filmPic19 = new javax.swing.JPanel();
        personVPicLbl = new javax.swing.JLabel();
        personVNameLbl = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        personVCategoryLbl = new javax.swing.JLabel();
        personVGenreLbl = new javax.swing.JLabel();
        personVReleaseLbl = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        personVProductionsScroll = new javax.swing.JScrollPane();
        personVRelsScroll = new javax.swing.JScrollPane();
        reviewWindow = new javax.swing.JPanel();
        menuBar9 = new javax.swing.JPanel();
        personEditorReturnBtn2 = new javax.swing.JButton();
        personEditorClearBtn2 = new javax.swing.JButton();
        personEditorSubmitBtn2 = new javax.swing.JButton();
        personEditorUsernameTag4 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jScrollPane16 = new javax.swing.JScrollPane();
        productionEditorStoryline1 = new javax.swing.JTextArea();
        starsSpinner = new javax.swing.JSpinner();
        jLabel54 = new javax.swing.JLabel();

        personPopUp.setTitle("Person selector");
        personPopUp.setMinimumSize(new java.awt.Dimension(500, 300));
        personPopUp.setResizable(false);

        jPanel16.setBackground(new java.awt.Color(136, 202, 252));

        popUpList.setBackground(new java.awt.Color(237, 204, 111));
        popUpList.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(210, 235, 255), 4));
        popUpList.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        popUpList.setForeground(new java.awt.Color(64, 64, 102));
        jScrollPane12.setViewportView(popUpList);

        popUpSearchBttn.setBackground(new java.awt.Color(237, 204, 111));
        popUpSearchBttn.setFont(new java.awt.Font("Gadugi", 1, 18)); // NOI18N
        popUpSearchBttn.setForeground(new java.awt.Color(48, 89, 138));
        popUpSearchBttn.setText("Search");
        popUpSearchBttn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(210, 235, 255), 4));
        popUpSearchBttn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                popUpSearchBttnActionPerformed(evt);
            }
        });

        popUpSelectBttn.setBackground(new java.awt.Color(237, 204, 111));
        popUpSelectBttn.setFont(new java.awt.Font("Gadugi", 1, 18)); // NOI18N
        popUpSelectBttn.setForeground(new java.awt.Color(48, 89, 138));
        popUpSelectBttn.setText("Select");
        popUpSelectBttn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(210, 235, 255), 4, true));
        popUpSelectBttn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                popUpSelectBttnActionPerformed(evt);
            }
        });

        popUpRoleCB.setBackground(new java.awt.Color(237, 204, 111));
        popUpRoleCB.setFont(new java.awt.Font("Gadugi", 1, 12)); // NOI18N
        popUpRoleCB.setForeground(new java.awt.Color(48, 89, 138));
        popUpRoleCB.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(210, 235, 255), 4));

        jLabel26.setBackground(new java.awt.Color(210, 235, 255));
        jLabel26.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(64, 64, 102));
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel26.setText("Role");

        popUpSearchTxt.setBackground(new java.awt.Color(237, 204, 111));
        popUpSearchTxt.setFont(new java.awt.Font("Gadugi", 1, 12)); // NOI18N
        popUpSearchTxt.setForeground(new java.awt.Color(48, 89, 138));
        popUpSearchTxt.setToolTipText("");
        popUpSearchTxt.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(210, 235, 255), 4));

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(popUpSelectBttn, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(popUpRoleCB, 0, 182, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                        .addComponent(popUpSearchTxt)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(popUpSearchBttn, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane12))
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(popUpSearchBttn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(popUpSearchTxt))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(popUpSelectBttn)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(popUpRoleCB))
                .addContainerGap())
        );

        javax.swing.GroupLayout personPopUpLayout = new javax.swing.GroupLayout(personPopUp.getContentPane());
        personPopUp.getContentPane().setLayout(personPopUpLayout);
        personPopUpLayout.setHorizontalGroup(
            personPopUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        personPopUpLayout.setVerticalGroup(
            personPopUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        imageChooser.setTitle("Person selector");
        imageChooser.setMinimumSize(new java.awt.Dimension(630, 450));

        jPanel17.setBackground(new java.awt.Color(136, 202, 252));

        jFileChooser1.setAcceptAllFileFilterUsed(false);
        jFileChooser1.setCurrentDirectory(new java.io.File("C:\\Users\\Marvin\\Desktop"));
        jFileChooser1.setFont(new java.awt.Font("Gadugi", 1, 12)); // NOI18N
        jFileChooser1.setToolTipText("");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jFileChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, 630, Short.MAX_VALUE)
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jFileChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout imageChooserLayout = new javax.swing.GroupLayout(imageChooser.getContentPane());
        imageChooser.getContentPane().setLayout(imageChooserLayout);
        imageChooserLayout.setHorizontalGroup(
            imageChooserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        imageChooserLayout.setVerticalGroup(
            imageChooserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jScrollPane6.setBackground(new java.awt.Color(226, 121, 59));
        jScrollPane6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel15.setBackground(new java.awt.Color(226, 121, 59));
        jPanel15.setLayout(new javax.swing.BoxLayout(jPanel15, javax.swing.BoxLayout.PAGE_AXIS));

        resultPanel.setBackground(new java.awt.Color(68, 51, 41));
        resultPanel.setMaximumSize(new java.awt.Dimension(855, 203));
        resultPanel.setMinimumSize(new java.awt.Dimension(846, 203));
        resultPanel.setName(""); // NOI18N

        FilmTitle20.setFont(new java.awt.Font("Gadugi", 1, 28)); // NOI18N
        FilmTitle20.setForeground(new java.awt.Color(254, 249, 217));
        FilmTitle20.setText("Title");

        filmPic11.setBackground(new java.awt.Color(254, 249, 217));

        javax.swing.GroupLayout filmPic11Layout = new javax.swing.GroupLayout(filmPic11);
        filmPic11.setLayout(filmPic11Layout);
        filmPic11Layout.setHorizontalGroup(
            filmPic11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 123, Short.MAX_VALUE)
        );
        filmPic11Layout.setVerticalGroup(
            filmPic11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        FilmTitle21.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        FilmTitle21.setForeground(new java.awt.Color(254, 249, 217));
        FilmTitle21.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        FilmTitle21.setText("Score");

        FilmTitle22.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        FilmTitle22.setForeground(new java.awt.Color(254, 249, 217));
        FilmTitle22.setText("*stars lmao*");

        FilmTitle23.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        FilmTitle23.setForeground(new java.awt.Color(254, 249, 217));
        FilmTitle23.setText("*date*");

        FilmTitle24.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        FilmTitle24.setForeground(new java.awt.Color(254, 249, 217));
        FilmTitle24.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        FilmTitle24.setText("Release date");

        FilmTitle25.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        FilmTitle25.setForeground(new java.awt.Color(254, 249, 217));
        FilmTitle25.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        FilmTitle25.setText("Director");

        FilmTitle26.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        FilmTitle26.setForeground(new java.awt.Color(254, 249, 217));
        FilmTitle26.setText("Name");

        FilmTitle27.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        FilmTitle27.setForeground(new java.awt.Color(254, 249, 217));
        FilmTitle27.setText("Storyline");

        jTextArea1.setColumns(20);
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed nec arcu in tortor tincidunt fermentum. Cras sodales gravida nibh non interdum. Vivamus ac lobortis neque, ac euismod purus. Vivamus at auctor felis. Aenean luctus metus ut velit aliquam sollicitudin. Quisque laoreet pharetra est id consequat. Sed rhoncus nisi id sagittis auctor.");
        jScrollPane5.setViewportView(jTextArea1);

        javax.swing.GroupLayout resultPanelLayout = new javax.swing.GroupLayout(resultPanel);
        resultPanel.setLayout(resultPanelLayout);
        resultPanelLayout.setHorizontalGroup(
            resultPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(resultPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(resultPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(FilmTitle20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(resultPanelLayout.createSequentialGroup()
                        .addGroup(resultPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(FilmTitle21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(FilmTitle27, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(FilmTitle22, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(FilmTitle24, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(FilmTitle23, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(FilmTitle25, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(FilmTitle26, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 73, Short.MAX_VALUE))
                    .addComponent(jScrollPane5))
                .addGap(18, 18, 18)
                .addComponent(filmPic11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        resultPanelLayout.setVerticalGroup(
            resultPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(resultPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(resultPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(filmPic11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(resultPanelLayout.createSequentialGroup()
                        .addComponent(FilmTitle20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(resultPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(FilmTitle21)
                            .addComponent(FilmTitle22)
                            .addComponent(FilmTitle24)
                            .addComponent(FilmTitle23)
                            .addComponent(FilmTitle25)
                            .addComponent(FilmTitle26))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(FilmTitle27)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel15.add(resultPanel);

        resultPanel1.setBackground(new java.awt.Color(68, 51, 41));
        resultPanel1.setMaximumSize(new java.awt.Dimension(855, 203));
        resultPanel1.setMinimumSize(new java.awt.Dimension(846, 203));
        resultPanel1.setName(""); // NOI18N

        FilmTitle28.setFont(new java.awt.Font("Gadugi", 1, 28)); // NOI18N
        FilmTitle28.setForeground(new java.awt.Color(254, 249, 217));
        FilmTitle28.setText("Title");

        filmPic12.setBackground(new java.awt.Color(254, 249, 217));

        javax.swing.GroupLayout filmPic12Layout = new javax.swing.GroupLayout(filmPic12);
        filmPic12.setLayout(filmPic12Layout);
        filmPic12Layout.setHorizontalGroup(
            filmPic12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 123, Short.MAX_VALUE)
        );
        filmPic12Layout.setVerticalGroup(
            filmPic12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        FilmTitle29.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        FilmTitle29.setForeground(new java.awt.Color(254, 249, 217));
        FilmTitle29.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        FilmTitle29.setText("Score");

        FilmTitle30.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        FilmTitle30.setForeground(new java.awt.Color(254, 249, 217));
        FilmTitle30.setText("*stars lmao*");

        FilmTitle31.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        FilmTitle31.setForeground(new java.awt.Color(254, 249, 217));
        FilmTitle31.setText("*date*");

        FilmTitle32.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        FilmTitle32.setForeground(new java.awt.Color(254, 249, 217));
        FilmTitle32.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        FilmTitle32.setText("Release date");

        FilmTitle33.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        FilmTitle33.setForeground(new java.awt.Color(254, 249, 217));
        FilmTitle33.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        FilmTitle33.setText("Director");

        FilmTitle34.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        FilmTitle34.setForeground(new java.awt.Color(254, 249, 217));
        FilmTitle34.setText("Name");

        FilmTitle35.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        FilmTitle35.setForeground(new java.awt.Color(254, 249, 217));
        FilmTitle35.setText("Storyline");

        jTextArea2.setColumns(20);
        jTextArea2.setLineWrap(true);
        jTextArea2.setRows(5);
        jTextArea2.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed nec arcu in tortor tincidunt fermentum. Cras sodales gravida nibh non interdum. Vivamus ac lobortis neque, ac euismod purus. Vivamus at auctor felis. Aenean luctus metus ut velit aliquam sollicitudin. Quisque laoreet pharetra est id consequat. Sed rhoncus nisi id sagittis auctor.");
        jScrollPane7.setViewportView(jTextArea2);

        javax.swing.GroupLayout resultPanel1Layout = new javax.swing.GroupLayout(resultPanel1);
        resultPanel1.setLayout(resultPanel1Layout);
        resultPanel1Layout.setHorizontalGroup(
            resultPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(resultPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(resultPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(FilmTitle28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(resultPanel1Layout.createSequentialGroup()
                        .addGroup(resultPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(FilmTitle29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(FilmTitle35, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(FilmTitle30, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(FilmTitle32, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(FilmTitle31, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(FilmTitle33, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(FilmTitle34, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 73, Short.MAX_VALUE))
                    .addComponent(jScrollPane7))
                .addGap(18, 18, 18)
                .addComponent(filmPic12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        resultPanel1Layout.setVerticalGroup(
            resultPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(resultPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(resultPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(filmPic12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(resultPanel1Layout.createSequentialGroup()
                        .addComponent(FilmTitle28)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(resultPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(FilmTitle29)
                            .addComponent(FilmTitle30)
                            .addComponent(FilmTitle32)
                            .addComponent(FilmTitle31)
                            .addComponent(FilmTitle33)
                            .addComponent(FilmTitle34))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(FilmTitle35)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel15.add(resultPanel1);

        resultPanel2.setBackground(new java.awt.Color(68, 51, 41));
        resultPanel2.setMaximumSize(new java.awt.Dimension(855, 203));
        resultPanel2.setMinimumSize(new java.awt.Dimension(846, 203));
        resultPanel2.setName(""); // NOI18N

        FilmTitle36.setFont(new java.awt.Font("Gadugi", 1, 28)); // NOI18N
        FilmTitle36.setForeground(new java.awt.Color(254, 249, 217));
        FilmTitle36.setText("Title");

        filmPic13.setBackground(new java.awt.Color(254, 249, 217));

        javax.swing.GroupLayout filmPic13Layout = new javax.swing.GroupLayout(filmPic13);
        filmPic13.setLayout(filmPic13Layout);
        filmPic13Layout.setHorizontalGroup(
            filmPic13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 123, Short.MAX_VALUE)
        );
        filmPic13Layout.setVerticalGroup(
            filmPic13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        FilmTitle37.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        FilmTitle37.setForeground(new java.awt.Color(254, 249, 217));
        FilmTitle37.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        FilmTitle37.setText("Score");

        FilmTitle38.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        FilmTitle38.setForeground(new java.awt.Color(254, 249, 217));
        FilmTitle38.setText("*stars lmao*");

        FilmTitle39.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        FilmTitle39.setForeground(new java.awt.Color(254, 249, 217));
        FilmTitle39.setText("*date*");

        FilmTitle40.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        FilmTitle40.setForeground(new java.awt.Color(254, 249, 217));
        FilmTitle40.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        FilmTitle40.setText("Release date");

        FilmTitle41.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        FilmTitle41.setForeground(new java.awt.Color(254, 249, 217));
        FilmTitle41.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        FilmTitle41.setText("Director");

        FilmTitle42.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        FilmTitle42.setForeground(new java.awt.Color(254, 249, 217));
        FilmTitle42.setText("Name");

        FilmTitle43.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        FilmTitle43.setForeground(new java.awt.Color(254, 249, 217));
        FilmTitle43.setText("Storyline");

        jTextArea3.setColumns(20);
        jTextArea3.setLineWrap(true);
        jTextArea3.setRows(5);
        jTextArea3.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed nec arcu in tortor tincidunt fermentum. Cras sodales gravida nibh non interdum. Vivamus ac lobortis neque, ac euismod purus. Vivamus at auctor felis. Aenean luctus metus ut velit aliquam sollicitudin. Quisque laoreet pharetra est id consequat. Sed rhoncus nisi id sagittis auctor.");
        jScrollPane8.setViewportView(jTextArea3);

        javax.swing.GroupLayout resultPanel2Layout = new javax.swing.GroupLayout(resultPanel2);
        resultPanel2.setLayout(resultPanel2Layout);
        resultPanel2Layout.setHorizontalGroup(
            resultPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(resultPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(resultPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(FilmTitle36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(resultPanel2Layout.createSequentialGroup()
                        .addGroup(resultPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(FilmTitle37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(FilmTitle43, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(FilmTitle38, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(FilmTitle40, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(FilmTitle39, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(FilmTitle41, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(FilmTitle42, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 73, Short.MAX_VALUE))
                    .addComponent(jScrollPane8))
                .addGap(18, 18, 18)
                .addComponent(filmPic13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        resultPanel2Layout.setVerticalGroup(
            resultPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(resultPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(resultPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(filmPic13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(resultPanel2Layout.createSequentialGroup()
                        .addComponent(FilmTitle36)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(resultPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(FilmTitle37)
                            .addComponent(FilmTitle38)
                            .addComponent(FilmTitle40)
                            .addComponent(FilmTitle39)
                            .addComponent(FilmTitle41)
                            .addComponent(FilmTitle42))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(FilmTitle43)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel15.add(resultPanel2);

        resultPanel3.setBackground(new java.awt.Color(68, 51, 41));
        resultPanel3.setMaximumSize(new java.awt.Dimension(855, 203));
        resultPanel3.setMinimumSize(new java.awt.Dimension(846, 203));
        resultPanel3.setName(""); // NOI18N

        FilmTitle44.setFont(new java.awt.Font("Gadugi", 1, 28)); // NOI18N
        FilmTitle44.setForeground(new java.awt.Color(254, 249, 217));
        FilmTitle44.setText("Title");

        filmPic14.setBackground(new java.awt.Color(254, 249, 217));

        javax.swing.GroupLayout filmPic14Layout = new javax.swing.GroupLayout(filmPic14);
        filmPic14.setLayout(filmPic14Layout);
        filmPic14Layout.setHorizontalGroup(
            filmPic14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 123, Short.MAX_VALUE)
        );
        filmPic14Layout.setVerticalGroup(
            filmPic14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        FilmTitle45.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        FilmTitle45.setForeground(new java.awt.Color(254, 249, 217));
        FilmTitle45.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        FilmTitle45.setText("Score");

        FilmTitle46.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        FilmTitle46.setForeground(new java.awt.Color(254, 249, 217));
        FilmTitle46.setText("*stars lmao*");

        FilmTitle47.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        FilmTitle47.setForeground(new java.awt.Color(254, 249, 217));
        FilmTitle47.setText("*date*");

        FilmTitle48.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        FilmTitle48.setForeground(new java.awt.Color(254, 249, 217));
        FilmTitle48.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        FilmTitle48.setText("Release date");

        FilmTitle49.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        FilmTitle49.setForeground(new java.awt.Color(254, 249, 217));
        FilmTitle49.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        FilmTitle49.setText("Director");

        FilmTitle50.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        FilmTitle50.setForeground(new java.awt.Color(254, 249, 217));
        FilmTitle50.setText("Name");

        FilmTitle51.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        FilmTitle51.setForeground(new java.awt.Color(254, 249, 217));
        FilmTitle51.setText("Storyline");

        jTextArea4.setColumns(20);
        jTextArea4.setLineWrap(true);
        jTextArea4.setRows(5);
        jTextArea4.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed nec arcu in tortor tincidunt fermentum. Cras sodales gravida nibh non interdum. Vivamus ac lobortis neque, ac euismod purus. Vivamus at auctor felis. Aenean luctus metus ut velit aliquam sollicitudin. Quisque laoreet pharetra est id consequat. Sed rhoncus nisi id sagittis auctor.");
        jScrollPane9.setViewportView(jTextArea4);

        javax.swing.GroupLayout resultPanel3Layout = new javax.swing.GroupLayout(resultPanel3);
        resultPanel3.setLayout(resultPanel3Layout);
        resultPanel3Layout.setHorizontalGroup(
            resultPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(resultPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(resultPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(FilmTitle44, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(resultPanel3Layout.createSequentialGroup()
                        .addGroup(resultPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(FilmTitle45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(FilmTitle51, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(FilmTitle46, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(FilmTitle48, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(FilmTitle47, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(FilmTitle49, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(FilmTitle50, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 73, Short.MAX_VALUE))
                    .addComponent(jScrollPane9))
                .addGap(18, 18, 18)
                .addComponent(filmPic14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        resultPanel3Layout.setVerticalGroup(
            resultPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(resultPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(resultPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(filmPic14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(resultPanel3Layout.createSequentialGroup()
                        .addComponent(FilmTitle44)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(resultPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(FilmTitle45)
                            .addComponent(FilmTitle46)
                            .addComponent(FilmTitle48)
                            .addComponent(FilmTitle47)
                            .addComponent(FilmTitle49)
                            .addComponent(FilmTitle50))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(FilmTitle51)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel15.add(resultPanel3);

        resultPanel4.setBackground(new java.awt.Color(68, 51, 41));
        resultPanel4.setMaximumSize(new java.awt.Dimension(855, 203));
        resultPanel4.setMinimumSize(new java.awt.Dimension(846, 203));
        resultPanel4.setName(""); // NOI18N

        FilmTitle52.setFont(new java.awt.Font("Gadugi", 1, 28)); // NOI18N
        FilmTitle52.setForeground(new java.awt.Color(254, 249, 217));
        FilmTitle52.setText("Title");

        filmPic15.setBackground(new java.awt.Color(254, 249, 217));

        javax.swing.GroupLayout filmPic15Layout = new javax.swing.GroupLayout(filmPic15);
        filmPic15.setLayout(filmPic15Layout);
        filmPic15Layout.setHorizontalGroup(
            filmPic15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 123, Short.MAX_VALUE)
        );
        filmPic15Layout.setVerticalGroup(
            filmPic15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        FilmTitle53.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        FilmTitle53.setForeground(new java.awt.Color(254, 249, 217));
        FilmTitle53.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        FilmTitle53.setText("Score");

        FilmTitle54.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        FilmTitle54.setForeground(new java.awt.Color(254, 249, 217));
        FilmTitle54.setText("*stars lmao*");

        FilmTitle55.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        FilmTitle55.setForeground(new java.awt.Color(254, 249, 217));
        FilmTitle55.setText("*date*");

        FilmTitle56.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        FilmTitle56.setForeground(new java.awt.Color(254, 249, 217));
        FilmTitle56.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        FilmTitle56.setText("Release date");

        FilmTitle57.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        FilmTitle57.setForeground(new java.awt.Color(254, 249, 217));
        FilmTitle57.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        FilmTitle57.setText("Director");

        FilmTitle58.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        FilmTitle58.setForeground(new java.awt.Color(254, 249, 217));
        FilmTitle58.setText("Name");

        FilmTitle59.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        FilmTitle59.setForeground(new java.awt.Color(254, 249, 217));
        FilmTitle59.setText("Storyline");

        jTextArea5.setColumns(20);
        jTextArea5.setLineWrap(true);
        jTextArea5.setRows(5);
        jTextArea5.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed nec arcu in tortor tincidunt fermentum. Cras sodales gravida nibh non interdum. Vivamus ac lobortis neque, ac euismod purus. Vivamus at auctor felis. Aenean luctus metus ut velit aliquam sollicitudin. Quisque laoreet pharetra est id consequat. Sed rhoncus nisi id sagittis auctor.");
        jScrollPane10.setViewportView(jTextArea5);

        javax.swing.GroupLayout resultPanel4Layout = new javax.swing.GroupLayout(resultPanel4);
        resultPanel4.setLayout(resultPanel4Layout);
        resultPanel4Layout.setHorizontalGroup(
            resultPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(resultPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(resultPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(FilmTitle52, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(resultPanel4Layout.createSequentialGroup()
                        .addGroup(resultPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(FilmTitle53, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(FilmTitle59, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(FilmTitle54, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(FilmTitle56, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(FilmTitle55, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(FilmTitle57, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(FilmTitle58, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 73, Short.MAX_VALUE))
                    .addComponent(jScrollPane10))
                .addGap(18, 18, 18)
                .addComponent(filmPic15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        resultPanel4Layout.setVerticalGroup(
            resultPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(resultPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(resultPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(filmPic15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(resultPanel4Layout.createSequentialGroup()
                        .addComponent(FilmTitle52)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(resultPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(FilmTitle53)
                            .addComponent(FilmTitle54)
                            .addComponent(FilmTitle56)
                            .addComponent(FilmTitle55)
                            .addComponent(FilmTitle57)
                            .addComponent(FilmTitle58))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(FilmTitle59)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel15.add(resultPanel4);

        resultPanel5.setBackground(new java.awt.Color(68, 51, 41));
        resultPanel5.setMaximumSize(new java.awt.Dimension(855, 203));
        resultPanel5.setMinimumSize(new java.awt.Dimension(846, 203));
        resultPanel5.setName(""); // NOI18N

        FilmTitle60.setFont(new java.awt.Font("Gadugi", 1, 28)); // NOI18N
        FilmTitle60.setForeground(new java.awt.Color(254, 249, 217));
        FilmTitle60.setText("Title");

        filmPic16.setBackground(new java.awt.Color(254, 249, 217));

        javax.swing.GroupLayout filmPic16Layout = new javax.swing.GroupLayout(filmPic16);
        filmPic16.setLayout(filmPic16Layout);
        filmPic16Layout.setHorizontalGroup(
            filmPic16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 123, Short.MAX_VALUE)
        );
        filmPic16Layout.setVerticalGroup(
            filmPic16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        FilmTitle61.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        FilmTitle61.setForeground(new java.awt.Color(254, 249, 217));
        FilmTitle61.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        FilmTitle61.setText("Score");

        FilmTitle62.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        FilmTitle62.setForeground(new java.awt.Color(254, 249, 217));
        FilmTitle62.setText("*stars lmao*");

        FilmTitle63.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        FilmTitle63.setForeground(new java.awt.Color(254, 249, 217));
        FilmTitle63.setText("*date*");

        FilmTitle64.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        FilmTitle64.setForeground(new java.awt.Color(254, 249, 217));
        FilmTitle64.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        FilmTitle64.setText("Release date");

        FilmTitle65.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        FilmTitle65.setForeground(new java.awt.Color(254, 249, 217));
        FilmTitle65.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        FilmTitle65.setText("Director");

        FilmTitle66.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        FilmTitle66.setForeground(new java.awt.Color(254, 249, 217));
        FilmTitle66.setText("Name");

        FilmTitle67.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        FilmTitle67.setForeground(new java.awt.Color(254, 249, 217));
        FilmTitle67.setText("Storyline");

        jTextArea6.setColumns(20);
        jTextArea6.setLineWrap(true);
        jTextArea6.setRows(5);
        jTextArea6.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed nec arcu in tortor tincidunt fermentum. Cras sodales gravida nibh non interdum. Vivamus ac lobortis neque, ac euismod purus. Vivamus at auctor felis. Aenean luctus metus ut velit aliquam sollicitudin. Quisque laoreet pharetra est id consequat. Sed rhoncus nisi id sagittis auctor.");
        jScrollPane11.setViewportView(jTextArea6);

        javax.swing.GroupLayout resultPanel5Layout = new javax.swing.GroupLayout(resultPanel5);
        resultPanel5.setLayout(resultPanel5Layout);
        resultPanel5Layout.setHorizontalGroup(
            resultPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(resultPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(resultPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(FilmTitle60, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(resultPanel5Layout.createSequentialGroup()
                        .addGroup(resultPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(FilmTitle61, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(FilmTitle67, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(FilmTitle62, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(FilmTitle64, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(FilmTitle63, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(FilmTitle65, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(FilmTitle66, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 73, Short.MAX_VALUE))
                    .addComponent(jScrollPane11))
                .addGap(18, 18, 18)
                .addComponent(filmPic16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        resultPanel5Layout.setVerticalGroup(
            resultPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(resultPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(resultPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(filmPic16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(resultPanel5Layout.createSequentialGroup()
                        .addComponent(FilmTitle60)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(resultPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(FilmTitle61)
                            .addComponent(FilmTitle62)
                            .addComponent(FilmTitle64)
                            .addComponent(FilmTitle63)
                            .addComponent(FilmTitle65)
                            .addComponent(FilmTitle66))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(FilmTitle67)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel15.add(resultPanel5);

        jScrollPane6.setViewportView(jPanel15);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.CardLayout());

        mainPanel.setBackground(new java.awt.Color(226, 121, 59));

        menuBar.setBackground(new java.awt.Color(68, 51, 41));

        mainSearch.setBackground(new java.awt.Color(254, 249, 217));
        mainSearch.setFont(new java.awt.Font("Gadugi", 0, 14)); // NOI18N
        mainSearch.setForeground(new java.awt.Color(201, 198, 145));
        mainSearch.setText("jTextField1");
        mainSearch.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(201, 198, 145), 2));

        mainSearchButton.setBackground(new java.awt.Color(254, 249, 217));
        mainSearchButton.setForeground(new java.awt.Color(201, 198, 145));
        mainSearchButton.setText("X");
        mainSearchButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(201, 198, 145), 2));

        mainLogin.setBackground(new java.awt.Color(68, 51, 41));
        mainLogin.setFont(new java.awt.Font("Gadugi", 1, 20)); // NOI18N
        mainLogin.setForeground(new java.awt.Color(254, 249, 217));
        mainLogin.setText("Log in");
        mainLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mainLoginActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout menuBarLayout = new javax.swing.GroupLayout(menuBar);
        menuBar.setLayout(menuBarLayout);
        menuBarLayout.setHorizontalGroup(
            menuBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuBarLayout.createSequentialGroup()
                .addGap(170, 170, 170)
                .addComponent(mainSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 643, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mainSearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(menuBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuBarLayout.createSequentialGroup()
                    .addContainerGap(1000, Short.MAX_VALUE)
                    .addComponent(mainLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );
        menuBarLayout.setVerticalGroup(
            menuBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuBarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(menuBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mainSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(mainSearchButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(menuBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuBarLayout.createSequentialGroup()
                    .addContainerGap(8, Short.MAX_VALUE)
                    .addComponent(mainLogin)
                    .addContainerGap(8, Short.MAX_VALUE)))
        );

        mainPanelTitle1.setFont(new java.awt.Font("Gadugi", 1, 48)); // NOI18N
        mainPanelTitle1.setForeground(new java.awt.Color(254, 249, 217));
        mainPanelTitle1.setText("Top 5 films");

        jPanel7.setBackground(new java.awt.Color(226, 121, 59));
        jPanel7.setLayout(new java.awt.GridLayout(1, 0));

        jPanel1.setBackground(new java.awt.Color(226, 121, 59));
        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel1.setToolTipText("");

        filmPic.setBackground(new java.awt.Color(68, 51, 41));

        javax.swing.GroupLayout filmPicLayout = new javax.swing.GroupLayout(filmPic);
        filmPic.setLayout(filmPicLayout);
        filmPicLayout.setHorizontalGroup(
            filmPicLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(LblTitlePic1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        filmPicLayout.setVerticalGroup(
            filmPicLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(LblTitlePic1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)
        );

        FilmTitle1.setFont(new java.awt.Font("Gadugi", 1, 24)); // NOI18N
        FilmTitle1.setForeground(new java.awt.Color(254, 249, 217));
        FilmTitle1.setText("Title 1");

        f1s1.setText("f1s1");

        f1s2.setText("f1s2");

        f1s3.setText("f1s3");

        f2s4.setText("f1s4");

        f2s5.setText("f1s5");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(f1s1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(f1s2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(f1s3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(f2s4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(f2s5))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(filmPic, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(FilmTitle1, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(filmPic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(FilmTitle1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(f1s1)
                    .addComponent(f1s2)
                    .addComponent(f1s3)
                    .addComponent(f2s4)
                    .addComponent(f2s5))
                .addContainerGap())
        );

        jPanel7.add(jPanel1);

        jPanel3.setBackground(new java.awt.Color(226, 121, 59));
        jPanel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel3.setToolTipText("");

        filmPic2.setBackground(new java.awt.Color(68, 51, 41));

        javax.swing.GroupLayout filmPic2Layout = new javax.swing.GroupLayout(filmPic2);
        filmPic2.setLayout(filmPic2Layout);
        filmPic2Layout.setHorizontalGroup(
            filmPic2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(LblTitlePic2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        filmPic2Layout.setVerticalGroup(
            filmPic2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(LblTitlePic2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)
        );

        FilmTitle2.setFont(new java.awt.Font("Gadugi", 1, 24)); // NOI18N
        FilmTitle2.setForeground(new java.awt.Color(254, 249, 217));
        FilmTitle2.setText("Title 1");

        f1s4.setText("f1s1");

        f1s5.setText("f1s2");

        f1s6.setText("f1s3");

        f2s6.setText("f1s4");

        f2s7.setText("f1s5");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(f1s4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(f1s5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(f1s6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(f2s6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(f2s7))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(filmPic2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(FilmTitle2, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(filmPic2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(FilmTitle2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(f1s4)
                    .addComponent(f1s5)
                    .addComponent(f1s6)
                    .addComponent(f2s6)
                    .addComponent(f2s7))
                .addContainerGap())
        );

        jPanel7.add(jPanel3);

        jPanel4.setBackground(new java.awt.Color(226, 121, 59));
        jPanel4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel4.setToolTipText("");

        filmPic3.setBackground(new java.awt.Color(68, 51, 41));

        javax.swing.GroupLayout filmPic3Layout = new javax.swing.GroupLayout(filmPic3);
        filmPic3.setLayout(filmPic3Layout);
        filmPic3Layout.setHorizontalGroup(
            filmPic3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(LblTitlePic3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        filmPic3Layout.setVerticalGroup(
            filmPic3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(LblTitlePic3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)
        );

        FilmTitle3.setFont(new java.awt.Font("Gadugi", 1, 24)); // NOI18N
        FilmTitle3.setForeground(new java.awt.Color(254, 249, 217));
        FilmTitle3.setText("Title 1");

        f1s7.setText("f1s1");

        f1s8.setText("f1s2");

        f1s9.setText("f1s3");

        f2s8.setText("f1s4");

        f2s9.setText("f1s5");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(f1s7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(f1s8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(f1s9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(f2s8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(f2s9))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(filmPic3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(FilmTitle3, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(filmPic3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(FilmTitle3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(f1s7)
                    .addComponent(f1s8)
                    .addComponent(f1s9)
                    .addComponent(f2s8)
                    .addComponent(f2s9))
                .addContainerGap())
        );

        jPanel7.add(jPanel4);

        jPanel5.setBackground(new java.awt.Color(226, 121, 59));
        jPanel5.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel5.setToolTipText("");

        filmPic4.setBackground(new java.awt.Color(68, 51, 41));

        javax.swing.GroupLayout filmPic4Layout = new javax.swing.GroupLayout(filmPic4);
        filmPic4.setLayout(filmPic4Layout);
        filmPic4Layout.setHorizontalGroup(
            filmPic4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(LblTitlePic4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        filmPic4Layout.setVerticalGroup(
            filmPic4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(LblTitlePic4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)
        );

        FilmTitle4.setFont(new java.awt.Font("Gadugi", 1, 24)); // NOI18N
        FilmTitle4.setForeground(new java.awt.Color(254, 249, 217));
        FilmTitle4.setText("Title 1");

        f1s10.setText("f1s1");

        f1s11.setText("f1s2");

        f1s12.setText("f1s3");

        f2s10.setText("f1s4");

        f2s11.setText("f1s5");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(f1s10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(f1s11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(f1s12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(f2s10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(f2s11))
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(filmPic4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(FilmTitle4, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(filmPic4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(FilmTitle4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(f1s10)
                    .addComponent(f1s11)
                    .addComponent(f1s12)
                    .addComponent(f2s10)
                    .addComponent(f2s11))
                .addContainerGap())
        );

        jPanel7.add(jPanel5);

        jPanel6.setBackground(new java.awt.Color(226, 121, 59));
        jPanel6.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel6.setToolTipText("");

        filmPic5.setBackground(new java.awt.Color(68, 51, 41));

        javax.swing.GroupLayout filmPic5Layout = new javax.swing.GroupLayout(filmPic5);
        filmPic5.setLayout(filmPic5Layout);
        filmPic5Layout.setHorizontalGroup(
            filmPic5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(LblTitlePic5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        filmPic5Layout.setVerticalGroup(
            filmPic5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(LblTitlePic5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)
        );

        FilmTitle5.setFont(new java.awt.Font("Gadugi", 1, 24)); // NOI18N
        FilmTitle5.setForeground(new java.awt.Color(254, 249, 217));
        FilmTitle5.setText("Title 1");

        f1s13.setText("f1s1");

        f1s14.setText("f1s2");

        f1s15.setText("f1s3");

        f2s12.setText("f1s4");

        f2s13.setText("f1s5");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(f1s13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(f1s14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(f1s15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(f2s12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(f2s13))
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(filmPic5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(FilmTitle5, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(filmPic5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(FilmTitle5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(f1s13)
                    .addComponent(f1s14)
                    .addComponent(f1s15)
                    .addComponent(f2s12)
                    .addComponent(f2s13))
                .addContainerGap())
        );

        jPanel7.add(jPanel6);

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menuBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, 1200, Short.MAX_VALUE)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainPanelTitle1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addComponent(menuBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(mainPanelTitle1)
                .addGap(18, 18, 18)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, 431, Short.MAX_VALUE))
        );

        getContentPane().add(mainPanel, "mainPanel");

        loggedMain.setBackground(new java.awt.Color(226, 121, 59));

        menuBar4.setBackground(new java.awt.Color(68, 51, 41));

        mainSearch1.setBackground(new java.awt.Color(254, 249, 217));
        mainSearch1.setFont(new java.awt.Font("Gadugi", 0, 14)); // NOI18N
        mainSearch1.setForeground(new java.awt.Color(201, 198, 145));
        mainSearch1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(201, 198, 145), 2));

        mainUserTag.setFont(new java.awt.Font("Gadugi", 1, 24)); // NOI18N
        mainUserTag.setForeground(new java.awt.Color(254, 249, 217));
        mainUserTag.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        mainUserTag.setText("Username");

        mainSearchButton1.setBackground(new java.awt.Color(254, 249, 217));
        mainSearchButton1.setForeground(new java.awt.Color(201, 198, 145));
        mainSearchButton1.setText("X");
        mainSearchButton1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(201, 198, 145), 2));
        mainSearchButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mainSearchButton1ActionPerformed(evt);
            }
        });

        LogOutBttn1.setBackground(new java.awt.Color(68, 51, 41));
        LogOutBttn1.setFont(new java.awt.Font("Gadugi", 1, 20)); // NOI18N
        LogOutBttn1.setForeground(new java.awt.Color(254, 249, 217));
        LogOutBttn1.setText("Log out");
        LogOutBttn1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        LogOutBttn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LogOutBttn1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout menuBar4Layout = new javax.swing.GroupLayout(menuBar4);
        menuBar4.setLayout(menuBar4Layout);
        menuBar4Layout.setHorizontalGroup(
            menuBar4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuBar4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(LogOutBttn1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mainSearch1, javax.swing.GroupLayout.PREFERRED_SIZE, 642, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mainSearchButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(menuBar4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuBar4Layout.createSequentialGroup()
                    .addContainerGap(1015, Short.MAX_VALUE)
                    .addComponent(mainUserTag, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(41, Short.MAX_VALUE)))
        );
        menuBar4Layout.setVerticalGroup(
            menuBar4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuBar4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(menuBar4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LogOutBttn1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(mainSearch1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mainSearchButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(menuBar4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuBar4Layout.createSequentialGroup()
                    .addContainerGap(9, Short.MAX_VALUE)
                    .addComponent(mainUserTag)
                    .addContainerGap(9, Short.MAX_VALUE)))
        );

        mainPanelTitle2.setFont(new java.awt.Font("Gadugi", 1, 48)); // NOI18N
        mainPanelTitle2.setForeground(new java.awt.Color(254, 249, 217));
        mainPanelTitle2.setText("Top 5 films");

        jPanel9.setBackground(new java.awt.Color(226, 121, 59));
        jPanel9.setLayout(new java.awt.GridLayout(1, 0));

        jPanel10.setBackground(new java.awt.Color(226, 121, 59));
        jPanel10.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel10.setToolTipText("");

        filmPic6.setBackground(new java.awt.Color(68, 51, 41));

        javax.swing.GroupLayout filmPic6Layout = new javax.swing.GroupLayout(filmPic6);
        filmPic6.setLayout(filmPic6Layout);
        filmPic6Layout.setHorizontalGroup(
            filmPic6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(LblLoggedPic1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        filmPic6Layout.setVerticalGroup(
            filmPic6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(LblLoggedPic1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)
        );

        FilmTitle6.setFont(new java.awt.Font("Gadugi", 1, 24)); // NOI18N
        FilmTitle6.setForeground(new java.awt.Color(254, 249, 217));
        FilmTitle6.setText("Title 1");

        f1s16.setText("f1s1");

        f1s17.setText("f1s2");

        f1s18.setText("f1s3");

        f2s14.setText("f1s4");

        f2s15.setText("f1s5");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(f1s16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(f1s17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(f1s18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(f2s14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(f2s15))
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(filmPic6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(FilmTitle6, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(filmPic6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(FilmTitle6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(f1s16)
                    .addComponent(f1s17)
                    .addComponent(f1s18)
                    .addComponent(f2s14)
                    .addComponent(f2s15))
                .addContainerGap())
        );

        jPanel9.add(jPanel10);

        jPanel11.setBackground(new java.awt.Color(226, 121, 59));
        jPanel11.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel11.setToolTipText("");

        filmPic7.setBackground(new java.awt.Color(68, 51, 41));

        javax.swing.GroupLayout filmPic7Layout = new javax.swing.GroupLayout(filmPic7);
        filmPic7.setLayout(filmPic7Layout);
        filmPic7Layout.setHorizontalGroup(
            filmPic7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(LblLoggedPic2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        filmPic7Layout.setVerticalGroup(
            filmPic7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(LblLoggedPic2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)
        );

        FilmTitle7.setFont(new java.awt.Font("Gadugi", 1, 24)); // NOI18N
        FilmTitle7.setForeground(new java.awt.Color(254, 249, 217));
        FilmTitle7.setText("Title 1");

        f1s19.setText("f1s1");

        f1s20.setText("f1s2");

        f1s21.setText("f1s3");

        f2s16.setText("f1s4");

        f2s17.setText("f1s5");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(f1s19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(f1s20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(f1s21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(f2s16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(f2s17))
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(filmPic7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(FilmTitle7, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(filmPic7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(FilmTitle7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(f1s19)
                    .addComponent(f1s20)
                    .addComponent(f1s21)
                    .addComponent(f2s16)
                    .addComponent(f2s17))
                .addContainerGap())
        );

        jPanel9.add(jPanel11);

        jPanel12.setBackground(new java.awt.Color(226, 121, 59));
        jPanel12.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel12.setToolTipText("");

        filmPic8.setBackground(new java.awt.Color(68, 51, 41));

        javax.swing.GroupLayout filmPic8Layout = new javax.swing.GroupLayout(filmPic8);
        filmPic8.setLayout(filmPic8Layout);
        filmPic8Layout.setHorizontalGroup(
            filmPic8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(LblLoggedPic3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        filmPic8Layout.setVerticalGroup(
            filmPic8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(LblLoggedPic3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)
        );

        FilmTitle8.setFont(new java.awt.Font("Gadugi", 1, 24)); // NOI18N
        FilmTitle8.setForeground(new java.awt.Color(254, 249, 217));
        FilmTitle8.setText("Title 1");

        f1s22.setText("f1s1");

        f1s23.setText("f1s2");

        f1s24.setText("f1s3");

        f2s18.setText("f1s4");

        f2s19.setText("f1s5");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(f1s22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(f1s23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(f1s24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(f2s18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(f2s19))
                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(filmPic8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(FilmTitle8, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(filmPic8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(FilmTitle8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(f1s22)
                    .addComponent(f1s23)
                    .addComponent(f1s24)
                    .addComponent(f2s18)
                    .addComponent(f2s19))
                .addContainerGap())
        );

        jPanel9.add(jPanel12);

        jPanel13.setBackground(new java.awt.Color(226, 121, 59));
        jPanel13.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel13.setToolTipText("");

        filmPic9.setBackground(new java.awt.Color(68, 51, 41));

        javax.swing.GroupLayout filmPic9Layout = new javax.swing.GroupLayout(filmPic9);
        filmPic9.setLayout(filmPic9Layout);
        filmPic9Layout.setHorizontalGroup(
            filmPic9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(LblLoggedPic4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        filmPic9Layout.setVerticalGroup(
            filmPic9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(LblLoggedPic4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)
        );

        FilmTitle9.setFont(new java.awt.Font("Gadugi", 1, 24)); // NOI18N
        FilmTitle9.setForeground(new java.awt.Color(254, 249, 217));
        FilmTitle9.setText("Title 1");

        f1s25.setText("f1s1");

        f1s26.setText("f1s2");

        f1s27.setText("f1s3");

        f2s20.setText("f1s4");

        f2s21.setText("f1s5");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(f1s25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(f1s26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(f1s27)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(f2s20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(f2s21))
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(filmPic9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(FilmTitle9, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(filmPic9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(FilmTitle9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(f1s25)
                    .addComponent(f1s26)
                    .addComponent(f1s27)
                    .addComponent(f2s20)
                    .addComponent(f2s21))
                .addContainerGap())
        );

        jPanel9.add(jPanel13);

        jPanel14.setBackground(new java.awt.Color(226, 121, 59));
        jPanel14.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel14.setToolTipText("");

        filmPic10.setBackground(new java.awt.Color(68, 51, 41));

        javax.swing.GroupLayout filmPic10Layout = new javax.swing.GroupLayout(filmPic10);
        filmPic10.setLayout(filmPic10Layout);
        filmPic10Layout.setHorizontalGroup(
            filmPic10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(LblLoggedPic5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        filmPic10Layout.setVerticalGroup(
            filmPic10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(LblLoggedPic5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)
        );

        FilmTitle10.setFont(new java.awt.Font("Gadugi", 1, 24)); // NOI18N
        FilmTitle10.setForeground(new java.awt.Color(254, 249, 217));
        FilmTitle10.setText("Title 1");

        f1s28.setText("f1s1");

        f1s29.setText("f1s2");

        f1s30.setText("f1s3");

        f2s22.setText("f1s4");

        f2s23.setText("f1s5");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(f1s28)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(f1s29)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(f1s30)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(f2s22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(f2s23))
                    .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(filmPic10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(FilmTitle10, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(filmPic10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(FilmTitle10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(f1s28)
                    .addComponent(f1s29)
                    .addComponent(f1s30)
                    .addComponent(f2s22)
                    .addComponent(f2s23))
                .addContainerGap())
        );

        jPanel9.add(jPanel14);

        adminPanelBttn.setBackground(new java.awt.Color(68, 51, 41));
        adminPanelBttn.setFont(new java.awt.Font("Gadugi", 1, 12)); // NOI18N
        adminPanelBttn.setForeground(new java.awt.Color(254, 249, 217));
        adminPanelBttn.setText("Access admin panel");
        adminPanelBttn.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        adminPanelBttn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminPanelBttnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout loggedMainLayout = new javax.swing.GroupLayout(loggedMain);
        loggedMain.setLayout(loggedMainLayout);
        loggedMainLayout.setHorizontalGroup(
            loggedMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menuBar4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, 1200, Short.MAX_VALUE)
            .addGroup(loggedMainLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainPanelTitle2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loggedMainLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(adminPanelBttn, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        loggedMainLayout.setVerticalGroup(
            loggedMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loggedMainLayout.createSequentialGroup()
                .addComponent(menuBar4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(mainPanelTitle2)
                .addGap(18, 18, 18)
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, 397, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(adminPanelBttn)
                .addContainerGap())
        );

        getContentPane().add(loggedMain, "loggedMain");

        userEditor.setBackground(new java.awt.Color(226, 121, 59));

        menuBar1.setBackground(new java.awt.Color(68, 51, 41));

        userEditorReturnBtn.setBackground(new java.awt.Color(68, 51, 41));
        userEditorReturnBtn.setFont(new java.awt.Font("Gadugi", 1, 20)); // NOI18N
        userEditorReturnBtn.setForeground(new java.awt.Color(254, 249, 217));
        userEditorReturnBtn.setText("Return");
        userEditorReturnBtn.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        userEditorReturnBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userEditorReturnBtnActionPerformed(evt);
            }
        });

        userEditorClearBtn.setBackground(new java.awt.Color(68, 51, 41));
        userEditorClearBtn.setFont(new java.awt.Font("Gadugi", 1, 20)); // NOI18N
        userEditorClearBtn.setForeground(new java.awt.Color(254, 249, 217));
        userEditorClearBtn.setText("Clear");
        userEditorClearBtn.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        userEditorClearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userEditorClearBtnActionPerformed(evt);
            }
        });

        userEditorSubmitBtn.setBackground(new java.awt.Color(68, 51, 41));
        userEditorSubmitBtn.setFont(new java.awt.Font("Gadugi", 1, 20)); // NOI18N
        userEditorSubmitBtn.setForeground(new java.awt.Color(254, 249, 217));
        userEditorSubmitBtn.setText("Submit");
        userEditorSubmitBtn.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        userEditorSubmitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userEditorSubmitBtnActionPerformed(evt);
            }
        });

        userEditorUsernameTag.setFont(new java.awt.Font("Gadugi", 1, 24)); // NOI18N
        userEditorUsernameTag.setForeground(new java.awt.Color(254, 249, 217));
        userEditorUsernameTag.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        userEditorUsernameTag.setText("Username");

        javax.swing.GroupLayout menuBar1Layout = new javax.swing.GroupLayout(menuBar1);
        menuBar1.setLayout(menuBar1Layout);
        menuBar1Layout.setHorizontalGroup(
            menuBar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuBar1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(userEditorReturnBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(userEditorClearBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(userEditorSubmitBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(198, 198, 198)
                .addComponent(userEditorUsernameTag, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        menuBar1Layout.setVerticalGroup(
            menuBar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuBar1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(menuBar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userEditorReturnBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(userEditorClearBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(userEditorSubmitBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(userEditorUsernameTag))
                .addContainerGap())
        );

        jLabel1.setBackground(new java.awt.Color(254, 249, 217));
        jLabel1.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(254, 249, 217));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel1.setText("Username");

        userEditorUsername.setBackground(new java.awt.Color(254, 249, 217));
        userEditorUsername.setFont(new java.awt.Font("Gadugi", 1, 12)); // NOI18N
        userEditorUsername.setForeground(new java.awt.Color(68, 51, 41));
        userEditorUsername.setToolTipText("");
        userEditorUsername.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(201, 198, 145), 4));
        userEditorUsername.setNextFocusableComponent(userEditorEmail);

        jLabel4.setBackground(new java.awt.Color(254, 249, 217));
        jLabel4.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(254, 249, 217));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel4.setText("E-mail address");

        userEditorEmail.setBackground(new java.awt.Color(254, 249, 217));
        userEditorEmail.setFont(new java.awt.Font("Gadugi", 1, 12)); // NOI18N
        userEditorEmail.setForeground(new java.awt.Color(68, 51, 41));
        userEditorEmail.setToolTipText("");
        userEditorEmail.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(201, 198, 145), 4));
        userEditorEmail.setNextFocusableComponent(userEditorPassword);

        jLabel2.setBackground(new java.awt.Color(254, 249, 217));
        jLabel2.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(254, 249, 217));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel2.setText("Password");

        userEditorPassword.setBackground(new java.awt.Color(254, 249, 217));
        userEditorPassword.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        userEditorPassword.setForeground(new java.awt.Color(68, 51, 41));
        userEditorPassword.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(201, 198, 145), 4));
        userEditorPassword.setNextFocusableComponent(userEditorPasswordConf);

        jLabel3.setBackground(new java.awt.Color(254, 249, 217));
        jLabel3.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(254, 249, 217));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel3.setText("Confirm password");

        userEditorPasswordConf.setBackground(new java.awt.Color(254, 249, 217));
        userEditorPasswordConf.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        userEditorPasswordConf.setForeground(new java.awt.Color(68, 51, 41));
        userEditorPasswordConf.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(201, 198, 145), 4));
        userEditorPasswordConf.setNextFocusableComponent(userEditorName);

        jLabel5.setBackground(new java.awt.Color(210, 235, 255));
        jLabel5.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(254, 249, 217));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel5.setText("Name");

        jLabel6.setBackground(new java.awt.Color(210, 235, 255));
        jLabel6.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(254, 249, 217));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel6.setText("Second Name");

        userEditorName.setBackground(new java.awt.Color(254, 249, 217));
        userEditorName.setFont(new java.awt.Font("Gadugi", 1, 12)); // NOI18N
        userEditorName.setForeground(new java.awt.Color(201, 198, 145));
        userEditorName.setToolTipText("");
        userEditorName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(201, 198, 145), 4));
        userEditorName.setNextFocusableComponent(userEditorSecondName);

        userEditorSecondName.setBackground(new java.awt.Color(254, 249, 217));
        userEditorSecondName.setFont(new java.awt.Font("Gadugi", 1, 12)); // NOI18N
        userEditorSecondName.setForeground(new java.awt.Color(201, 198, 145));
        userEditorSecondName.setToolTipText("");
        userEditorSecondName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(201, 198, 145), 4));
        userEditorSecondName.setNextFocusableComponent(userEditorSurname);

        userEditorGenderCB.setBackground(new java.awt.Color(254, 249, 217));
        userEditorGenderCB.setFont(new java.awt.Font("Gadugi", 1, 12)); // NOI18N
        userEditorGenderCB.setForeground(new java.awt.Color(201, 198, 145));
        userEditorGenderCB.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(201, 198, 145), 4));
        userEditorGenderCB.setNextFocusableComponent(userEditorPhone);

        jLabel12.setBackground(new java.awt.Color(210, 235, 255));
        jLabel12.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(254, 249, 217));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel12.setText("Gender");

        jLabel8.setBackground(new java.awt.Color(210, 235, 255));
        jLabel8.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(254, 249, 217));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel8.setText("Phone number");

        userEditorPhone.setBackground(new java.awt.Color(254, 249, 217));
        userEditorPhone.setFont(new java.awt.Font("Gadugi", 1, 12)); // NOI18N
        userEditorPhone.setForeground(new java.awt.Color(201, 198, 145));
        userEditorPhone.setToolTipText("");
        userEditorPhone.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(201, 198, 145), 4));
        userEditorPhone.setNextFocusableComponent(userEditorIdTypeCB);

        jLabel7.setBackground(new java.awt.Color(210, 235, 255));
        jLabel7.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(254, 249, 217));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel7.setText("Id type");

        jLabel9.setBackground(new java.awt.Color(210, 235, 255));
        jLabel9.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(254, 249, 217));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel9.setText("Id number");

        userEditorIdNum.setBackground(new java.awt.Color(254, 249, 217));
        userEditorIdNum.setFont(new java.awt.Font("Gadugi", 1, 12)); // NOI18N
        userEditorIdNum.setForeground(new java.awt.Color(201, 198, 145));
        userEditorIdNum.setToolTipText("");
        userEditorIdNum.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(201, 198, 145), 4));
        userEditorIdNum.setNextFocusableComponent(userEditorBirthdate);

        userEditorBirthdate.setBackground(new java.awt.Color(254, 249, 217));
        userEditorBirthdate.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(201, 198, 145), 4));
        userEditorBirthdate.setForeground(new java.awt.Color(201, 198, 145));
        userEditorBirthdate.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd/MM/yyyy"))));
        userEditorBirthdate.setToolTipText("DD/MM/YYYY");
        userEditorBirthdate.setNextFocusableComponent(userEditorCountryCB);

        jLabel10.setBackground(new java.awt.Color(210, 235, 255));
        jLabel10.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(254, 249, 217));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel10.setText("Birthdate");

        jLabel11.setBackground(new java.awt.Color(210, 235, 255));
        jLabel11.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(254, 249, 217));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel11.setText("Nationality");

        userEditorNationalityCB.setBackground(new java.awt.Color(254, 249, 217));
        userEditorNationalityCB.setFont(new java.awt.Font("Gadugi", 1, 12)); // NOI18N
        userEditorNationalityCB.setForeground(new java.awt.Color(201, 198, 145));
        userEditorNationalityCB.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(201, 198, 145), 4));
        userEditorNationalityCB.setNextFocusableComponent(userEditorRegionCB);

        userEditorCountryCB.setBackground(new java.awt.Color(254, 249, 217));
        userEditorCountryCB.setFont(new java.awt.Font("Gadugi", 1, 12)); // NOI18N
        userEditorCountryCB.setForeground(new java.awt.Color(201, 198, 145));
        userEditorCountryCB.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(201, 198, 145), 4));
        userEditorCountryCB.setNextFocusableComponent(userEditorNationalityCB);

        jLabel13.setBackground(new java.awt.Color(210, 235, 255));
        jLabel13.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(254, 249, 217));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel13.setText("Country");

        jLabel14.setBackground(new java.awt.Color(210, 235, 255));
        jLabel14.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(254, 249, 217));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel14.setText("Region");

        userEditorRegionCB.setBackground(new java.awt.Color(254, 249, 217));
        userEditorRegionCB.setFont(new java.awt.Font("Gadugi", 1, 12)); // NOI18N
        userEditorRegionCB.setForeground(new java.awt.Color(201, 198, 145));
        userEditorRegionCB.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(201, 198, 145), 4));
        userEditorRegionCB.setNextFocusableComponent(userEditorDistrictCB);

        userEditorDistrictCB.setBackground(new java.awt.Color(254, 249, 217));
        userEditorDistrictCB.setFont(new java.awt.Font("Gadugi", 1, 12)); // NOI18N
        userEditorDistrictCB.setForeground(new java.awt.Color(201, 198, 145));
        userEditorDistrictCB.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(201, 198, 145), 4));
        userEditorDistrictCB.setNextFocusableComponent(userEditorAddress);

        jLabel15.setBackground(new java.awt.Color(210, 235, 255));
        jLabel15.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(254, 249, 217));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel15.setText("District");

        jLabel16.setBackground(new java.awt.Color(210, 235, 255));
        jLabel16.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(254, 249, 217));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel16.setText("Address");

        userEditorAddress.setBackground(new java.awt.Color(254, 249, 217));
        userEditorAddress.setColumns(20);
        userEditorAddress.setFont(new java.awt.Font("Gadugi", 1, 12)); // NOI18N
        userEditorAddress.setForeground(new java.awt.Color(201, 198, 145));
        userEditorAddress.setLineWrap(true);
        userEditorAddress.setRows(5);
        userEditorAddress.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(201, 198, 145), 4));
        jScrollPane1.setViewportView(userEditorAddress);

        userEditorIdTypeCB.setBackground(new java.awt.Color(254, 249, 217));
        userEditorIdTypeCB.setFont(new java.awt.Font("Gadugi", 1, 12)); // NOI18N
        userEditorIdTypeCB.setForeground(new java.awt.Color(201, 198, 145));
        userEditorIdTypeCB.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(201, 198, 145), 4));
        userEditorIdTypeCB.setNextFocusableComponent(userEditorIdNum);

        userEditorClearPicBtn.setBackground(new java.awt.Color(68, 51, 41));
        userEditorClearPicBtn.setFont(new java.awt.Font("Gadugi", 1, 20)); // NOI18N
        userEditorClearPicBtn.setForeground(new java.awt.Color(254, 249, 217));
        userEditorClearPicBtn.setText("Clear Picture");
        userEditorClearPicBtn.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        userEditorClearPicBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userEditorClearPicBtnActionPerformed(evt);
            }
        });

        pfpLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pfpLbl.setText("pfp pic");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pfpLbl, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pfpLbl, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
        );

        userEditorSelectPicBtn.setBackground(new java.awt.Color(68, 51, 41));
        userEditorSelectPicBtn.setFont(new java.awt.Font("Gadugi", 1, 20)); // NOI18N
        userEditorSelectPicBtn.setForeground(new java.awt.Color(254, 249, 217));
        userEditorSelectPicBtn.setText("Select Picture");
        userEditorSelectPicBtn.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        userEditorSelectPicBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userEditorSelectPicBtnActionPerformed(evt);
            }
        });

        jLabel24.setBackground(new java.awt.Color(210, 235, 255));
        jLabel24.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(254, 249, 217));
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel24.setText("Surname");

        userEditorSurname.setBackground(new java.awt.Color(254, 249, 217));
        userEditorSurname.setFont(new java.awt.Font("Gadugi", 1, 12)); // NOI18N
        userEditorSurname.setForeground(new java.awt.Color(201, 198, 145));
        userEditorSurname.setToolTipText("");
        userEditorSurname.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(201, 198, 145), 4));
        userEditorSurname.setNextFocusableComponent(userEditorSecondSurname);

        jLabel25.setBackground(new java.awt.Color(210, 235, 255));
        jLabel25.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(254, 249, 217));
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel25.setText("Second Surname");

        userEditorSecondSurname.setBackground(new java.awt.Color(254, 249, 217));
        userEditorSecondSurname.setFont(new java.awt.Font("Gadugi", 1, 12)); // NOI18N
        userEditorSecondSurname.setForeground(new java.awt.Color(201, 198, 145));
        userEditorSecondSurname.setToolTipText("");
        userEditorSecondSurname.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(201, 198, 145), 4));
        userEditorSecondSurname.setNextFocusableComponent(userEditorGenderCB);

        jPanel20.setBackground(new java.awt.Color(226, 121, 59));
        jPanel20.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jCheckBox1.setBackground(new java.awt.Color(226, 121, 59));
        jCheckBox1.setFont(new java.awt.Font("Gadugi", 1, 18)); // NOI18N
        jCheckBox1.setForeground(new java.awt.Color(254, 249, 217));
        jCheckBox1.setText("Admin status");

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(224, Short.MAX_VALUE))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jCheckBox1, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout userEditorLayout = new javax.swing.GroupLayout(userEditor);
        userEditor.setLayout(userEditorLayout);
        userEditorLayout.setHorizontalGroup(
            userEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menuBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(userEditorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(userEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(userEditorLayout.createSequentialGroup()
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(userEditorSecondSurname))
                    .addGroup(userEditorLayout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(userEditorBirthdate))
                    .addGroup(userEditorLayout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(userEditorIdNum))
                    .addGroup(userEditorLayout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(userEditorGenderCB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(userEditorLayout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(userEditorPasswordConf))
                    .addGroup(userEditorLayout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(userEditorPassword))
                    .addGroup(userEditorLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(userEditorUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(userEditorLayout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(userEditorEmail))
                    .addGroup(userEditorLayout.createSequentialGroup()
                        .addGroup(userEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(userEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(userEditorPhone)
                            .addComponent(userEditorIdTypeCB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(userEditorLayout.createSequentialGroup()
                        .addGroup(userEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(userEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(userEditorName)
                            .addComponent(userEditorSecondName)))
                    .addGroup(userEditorLayout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(userEditorCountryCB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(userEditorLayout.createSequentialGroup()
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(userEditorSurname)))
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(userEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(userEditorLayout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addGroup(userEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(userEditorSelectPicBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE)
                            .addComponent(userEditorClearPicBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(userEditorLayout.createSequentialGroup()
                        .addGroup(userEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, userEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(userEditorLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addGroup(userEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(userEditorRegionCB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(userEditorDistrictCB, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(userEditorNationalityCB, 0, 411, Short.MAX_VALUE)
                            .addComponent(jScrollPane1))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        userEditorLayout.setVerticalGroup(
            userEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(userEditorLayout.createSequentialGroup()
                .addComponent(menuBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(userEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(userEditorLayout.createSequentialGroup()
                        .addGroup(userEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(userEditorUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(userEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(userEditorEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(userEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(userEditorPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(userEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(userEditorPasswordConf, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(userEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(userEditorName, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(userEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(userEditorSecondName, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(userEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(userEditorLayout.createSequentialGroup()
                            .addComponent(userEditorSelectPicBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(userEditorClearPicBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(userEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(userEditorLayout.createSequentialGroup()
                        .addGroup(userEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(userEditorNationalityCB, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(userEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(userEditorSurname, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(userEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(userEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(userEditorRegionCB, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(userEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(userEditorSecondSurname, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(userEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(userEditorLayout.createSequentialGroup()
                                .addComponent(userEditorDistrictCB, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(userEditorLayout.createSequentialGroup()
                                .addGap(0, 1, Short.MAX_VALUE)
                                .addGroup(userEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(userEditorGenderCB, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(userEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(userEditorPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(userEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(userEditorIdTypeCB, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(userEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(userEditorIdNum, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(userEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(userEditorBirthdate, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(userEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(userEditorCountryCB, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(userEditorLayout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        getContentPane().add(userEditor, "userEditor");

        productionEditor.setBackground(new java.awt.Color(226, 121, 59));

        menuBar3.setBackground(new java.awt.Color(68, 51, 41));

        personEditorReturnBtn1.setBackground(new java.awt.Color(68, 51, 41));
        personEditorReturnBtn1.setFont(new java.awt.Font("Gadugi", 1, 20)); // NOI18N
        personEditorReturnBtn1.setForeground(new java.awt.Color(254, 249, 217));
        personEditorReturnBtn1.setText("Return");
        personEditorReturnBtn1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        personEditorReturnBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                personEditorReturnBtn1ActionPerformed(evt);
            }
        });

        personEditorClearBtn1.setBackground(new java.awt.Color(68, 51, 41));
        personEditorClearBtn1.setFont(new java.awt.Font("Gadugi", 1, 20)); // NOI18N
        personEditorClearBtn1.setForeground(new java.awt.Color(254, 249, 217));
        personEditorClearBtn1.setText("Clear");
        personEditorClearBtn1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        personEditorClearBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                personEditorClearBtn1ActionPerformed(evt);
            }
        });

        personEditorSubmitBtn1.setBackground(new java.awt.Color(68, 51, 41));
        personEditorSubmitBtn1.setFont(new java.awt.Font("Gadugi", 1, 20)); // NOI18N
        personEditorSubmitBtn1.setForeground(new java.awt.Color(254, 249, 217));
        personEditorSubmitBtn1.setText("Submit");
        personEditorSubmitBtn1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        personEditorSubmitBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                personEditorSubmitBtn1ActionPerformed(evt);
            }
        });

        personEditorUsernameTag1.setFont(new java.awt.Font("Gadugi", 1, 24)); // NOI18N
        personEditorUsernameTag1.setForeground(new java.awt.Color(254, 249, 217));
        personEditorUsernameTag1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        personEditorUsernameTag1.setText("Username");

        javax.swing.GroupLayout menuBar3Layout = new javax.swing.GroupLayout(menuBar3);
        menuBar3.setLayout(menuBar3Layout);
        menuBar3Layout.setHorizontalGroup(
            menuBar3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuBar3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(personEditorReturnBtn1, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(personEditorClearBtn1, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(personEditorSubmitBtn1, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                .addGap(198, 198, 198)
                .addComponent(personEditorUsernameTag1, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        menuBar3Layout.setVerticalGroup(
            menuBar3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuBar3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(menuBar3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(personEditorReturnBtn1, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(personEditorClearBtn1, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(personEditorSubmitBtn1, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(personEditorUsernameTag1))
                .addContainerGap())
        );

        jLabel20.setBackground(new java.awt.Color(254, 249, 217));
        jLabel20.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(254, 249, 217));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel20.setText("Title");

        productionEditorName.setBackground(new java.awt.Color(254, 249, 217));
        productionEditorName.setFont(new java.awt.Font("Gadugi", 1, 12)); // NOI18N
        productionEditorName.setForeground(new java.awt.Color(68, 51, 41));
        productionEditorName.setToolTipText("");
        productionEditorName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(201, 198, 145), 4));

        jLabel39.setBackground(new java.awt.Color(210, 235, 255));
        jLabel39.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(254, 249, 217));
        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel39.setText("Storyline");

        productionEditorStoryline.setBackground(new java.awt.Color(254, 249, 217));
        productionEditorStoryline.setColumns(20);
        productionEditorStoryline.setFont(new java.awt.Font("Gadugi", 1, 12)); // NOI18N
        productionEditorStoryline.setForeground(new java.awt.Color(201, 198, 145));
        productionEditorStoryline.setLineWrap(true);
        productionEditorStoryline.setRows(5);
        productionEditorStoryline.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(201, 198, 145), 4));
        jScrollPane4.setViewportView(productionEditorStoryline);

        personEditorSelectBtn1.setBackground(new java.awt.Color(68, 51, 41));
        personEditorSelectBtn1.setFont(new java.awt.Font("Gadugi", 1, 20)); // NOI18N
        personEditorSelectBtn1.setForeground(new java.awt.Color(254, 249, 217));
        personEditorSelectBtn1.setText("Select Picture");
        personEditorSelectBtn1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        personEditorSelectBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                personEditorSelectBtn1ActionPerformed(evt);
            }
        });

        personEditorClearPicBtn1.setBackground(new java.awt.Color(68, 51, 41));
        personEditorClearPicBtn1.setFont(new java.awt.Font("Gadugi", 1, 20)); // NOI18N
        personEditorClearPicBtn1.setForeground(new java.awt.Color(254, 249, 217));
        personEditorClearPicBtn1.setText("Clear Picture");
        personEditorClearPicBtn1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        personEditorClearPicBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                personEditorClearPicBtn1ActionPerformed(evt);
            }
        });

        personEditorAddP1.setBackground(new java.awt.Color(68, 51, 41));
        personEditorAddP1.setFont(new java.awt.Font("Gadugi", 1, 20)); // NOI18N
        personEditorAddP1.setForeground(new java.awt.Color(254, 249, 217));
        personEditorAddP1.setText("Link person");
        personEditorAddP1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        personEditorAddP1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                personEditorAddP1ActionPerformed(evt);
            }
        });

        personEditorAddRel1.setBackground(new java.awt.Color(68, 51, 41));
        personEditorAddRel1.setFont(new java.awt.Font("Gadugi", 1, 20)); // NOI18N
        personEditorAddRel1.setForeground(new java.awt.Color(254, 249, 217));
        personEditorAddRel1.setText("Add review");
        personEditorAddRel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        personEditorAddTrivia1.setBackground(new java.awt.Color(68, 51, 41));
        personEditorAddTrivia1.setFont(new java.awt.Font("Gadugi", 1, 20)); // NOI18N
        personEditorAddTrivia1.setForeground(new java.awt.Color(254, 249, 217));
        personEditorAddTrivia1.setText("Add picture");
        personEditorAddTrivia1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel41.setBackground(new java.awt.Color(210, 235, 255));
        jLabel41.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(254, 249, 217));
        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel41.setText("Category");

        productionEditorCategoryCB.setBackground(new java.awt.Color(254, 249, 217));
        productionEditorCategoryCB.setFont(new java.awt.Font("Gadugi", 1, 12)); // NOI18N
        productionEditorCategoryCB.setForeground(new java.awt.Color(201, 198, 145));
        productionEditorCategoryCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Movie", "Series", "Documentary", "Other" }));
        productionEditorCategoryCB.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(201, 198, 145), 4));

        productionEditorGenreCB.setBackground(new java.awt.Color(254, 249, 217));
        productionEditorGenreCB.setFont(new java.awt.Font("Gadugi", 1, 12)); // NOI18N
        productionEditorGenreCB.setForeground(new java.awt.Color(201, 198, 145));
        productionEditorGenreCB.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(201, 198, 145), 4));

        jLabel42.setBackground(new java.awt.Color(210, 235, 255));
        jLabel42.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(254, 249, 217));
        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel42.setText("Genre");

        jLabel43.setBackground(new java.awt.Color(254, 249, 217));
        jLabel43.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(254, 249, 217));
        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel43.setText("Release date");

        personEditorAddP2.setBackground(new java.awt.Color(68, 51, 41));
        personEditorAddP2.setFont(new java.awt.Font("Gadugi", 1, 20)); // NOI18N
        personEditorAddP2.setForeground(new java.awt.Color(254, 249, 217));
        personEditorAddP2.setText("Add platform");
        personEditorAddP2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel44.setBackground(new java.awt.Color(254, 249, 217));
        jLabel44.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(254, 249, 217));
        jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel44.setText("Play time");

        productionEditorPlaytime.setBackground(new java.awt.Color(254, 249, 217));
        productionEditorPlaytime.setFont(new java.awt.Font("Gadugi", 1, 12)); // NOI18N
        productionEditorPlaytime.setForeground(new java.awt.Color(68, 51, 41));
        productionEditorPlaytime.setToolTipText("");
        productionEditorPlaytime.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(201, 198, 145), 4));

        productionEditorLinkTrailer.setBackground(new java.awt.Color(68, 51, 41));
        productionEditorLinkTrailer.setFont(new java.awt.Font("Gadugi", 1, 20)); // NOI18N
        productionEditorLinkTrailer.setForeground(new java.awt.Color(254, 249, 217));
        productionEditorLinkTrailer.setText("Link trailer");
        productionEditorLinkTrailer.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        productionEditorLinkTrailer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                productionEditorLinkTrailerActionPerformed(evt);
            }
        });

        productionEditorRelease.setBackground(new java.awt.Color(254, 249, 217));
        productionEditorRelease.setFont(new java.awt.Font("Gadugi", 1, 12)); // NOI18N
        productionEditorRelease.setForeground(new java.awt.Color(68, 51, 41));
        productionEditorRelease.setToolTipText("");
        productionEditorRelease.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(201, 198, 145), 4));

        filmPic1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        filmPic1.setText("pfp pic");

        javax.swing.GroupLayout productionEditorLayout = new javax.swing.GroupLayout(productionEditor);
        productionEditor.setLayout(productionEditorLayout);
        productionEditorLayout.setHorizontalGroup(
            productionEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menuBar3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, productionEditorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(productionEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(productionEditorLayout.createSequentialGroup()
                        .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(productionEditorGenreCB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(productionEditorLayout.createSequentialGroup()
                        .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(productionEditorCategoryCB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(productionEditorLayout.createSequentialGroup()
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(productionEditorName, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(productionEditorLayout.createSequentialGroup()
                        .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(productionEditorRelease, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(productionEditorLayout.createSequentialGroup()
                        .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(productionEditorPlaytime, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(productionEditorLayout.createSequentialGroup()
                        .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(productionEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(productionEditorLinkTrailer, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane4))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(productionEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(personEditorAddP2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(personEditorAddRel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(personEditorAddP1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(productionEditorLayout.createSequentialGroup()
                        .addGroup(productionEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(personEditorSelectBtn1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(personEditorClearPicBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(filmPic1, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(personEditorAddTrivia1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        productionEditorLayout.setVerticalGroup(
            productionEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(productionEditorLayout.createSequentialGroup()
                .addComponent(menuBar3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(productionEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(productionEditorLayout.createSequentialGroup()
                        .addGroup(productionEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(productionEditorLayout.createSequentialGroup()
                                .addComponent(personEditorSelectBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(personEditorClearPicBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(filmPic1, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(personEditorAddP1, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(personEditorAddP2, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(personEditorAddRel1, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(productionEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(personEditorAddTrivia1, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                            .addComponent(productionEditorLinkTrailer, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)))
                    .addGroup(productionEditorLayout.createSequentialGroup()
                        .addGroup(productionEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(productionEditorName, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(productionEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(productionEditorCategoryCB, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(productionEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(productionEditorGenreCB, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(productionEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(productionEditorRelease, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(productionEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(productionEditorPlaytime, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(productionEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(productionEditorLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(productionEditorLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(26, 26, 26))
        );

        getContentPane().add(productionEditor, "productionEditor");

        personEditor.setBackground(new java.awt.Color(226, 121, 59));

        menuBar2.setBackground(new java.awt.Color(68, 51, 41));

        personEditorReturnBtn.setBackground(new java.awt.Color(68, 51, 41));
        personEditorReturnBtn.setFont(new java.awt.Font("Gadugi", 1, 20)); // NOI18N
        personEditorReturnBtn.setForeground(new java.awt.Color(254, 249, 217));
        personEditorReturnBtn.setText("Return");
        personEditorReturnBtn.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        personEditorReturnBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                personEditorReturnBtnActionPerformed(evt);
            }
        });

        personEditorClearBtn.setBackground(new java.awt.Color(68, 51, 41));
        personEditorClearBtn.setFont(new java.awt.Font("Gadugi", 1, 20)); // NOI18N
        personEditorClearBtn.setForeground(new java.awt.Color(254, 249, 217));
        personEditorClearBtn.setText("Clear");
        personEditorClearBtn.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        personEditorSubmitBtn.setBackground(new java.awt.Color(68, 51, 41));
        personEditorSubmitBtn.setFont(new java.awt.Font("Gadugi", 1, 20)); // NOI18N
        personEditorSubmitBtn.setForeground(new java.awt.Color(254, 249, 217));
        personEditorSubmitBtn.setText("Submit");
        personEditorSubmitBtn.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        personEditorSubmitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                personEditorSubmitBtnActionPerformed(evt);
            }
        });

        personEditorUsernameTag.setFont(new java.awt.Font("Gadugi", 1, 24)); // NOI18N
        personEditorUsernameTag.setForeground(new java.awt.Color(254, 249, 217));
        personEditorUsernameTag.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        personEditorUsernameTag.setText("Username");

        javax.swing.GroupLayout menuBar2Layout = new javax.swing.GroupLayout(menuBar2);
        menuBar2.setLayout(menuBar2Layout);
        menuBar2Layout.setHorizontalGroup(
            menuBar2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuBar2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(personEditorReturnBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(personEditorClearBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(personEditorSubmitBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                .addGap(198, 198, 198)
                .addComponent(personEditorUsernameTag, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        menuBar2Layout.setVerticalGroup(
            menuBar2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuBar2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(menuBar2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(personEditorReturnBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(personEditorClearBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(personEditorSubmitBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(personEditorUsernameTag))
                .addContainerGap())
        );

        jLabel17.setBackground(new java.awt.Color(254, 249, 217));
        jLabel17.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(254, 249, 217));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel17.setText("Name");

        personEditorName.setBackground(new java.awt.Color(254, 249, 217));
        personEditorName.setFont(new java.awt.Font("Gadugi", 1, 12)); // NOI18N
        personEditorName.setForeground(new java.awt.Color(68, 51, 41));
        personEditorName.setToolTipText("");
        personEditorName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(201, 198, 145), 4));

        jLabel18.setBackground(new java.awt.Color(254, 249, 217));
        jLabel18.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(254, 249, 217));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel18.setText("Middle Name");

        personEditorMidName.setBackground(new java.awt.Color(254, 249, 217));
        personEditorMidName.setFont(new java.awt.Font("Gadugi", 1, 12)); // NOI18N
        personEditorMidName.setForeground(new java.awt.Color(68, 51, 41));
        personEditorMidName.setToolTipText("");
        personEditorMidName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(201, 198, 145), 4));

        jLabel19.setBackground(new java.awt.Color(254, 249, 217));
        jLabel19.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(254, 249, 217));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel19.setText("Surname");

        jLabel21.setBackground(new java.awt.Color(210, 235, 255));
        jLabel21.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(254, 249, 217));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel21.setText("Height");

        personEditorHeight.setBackground(new java.awt.Color(254, 249, 217));
        personEditorHeight.setFont(new java.awt.Font("Gadugi", 1, 12)); // NOI18N
        personEditorHeight.setForeground(new java.awt.Color(201, 198, 145));
        personEditorHeight.setToolTipText("");
        personEditorHeight.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(201, 198, 145), 4));

        personEditorGenderCB.setBackground(new java.awt.Color(254, 249, 217));
        personEditorGenderCB.setFont(new java.awt.Font("Gadugi", 1, 12)); // NOI18N
        personEditorGenderCB.setForeground(new java.awt.Color(201, 198, 145));
        personEditorGenderCB.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(201, 198, 145), 4));

        jLabel23.setBackground(new java.awt.Color(210, 235, 255));
        jLabel23.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(254, 249, 217));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel23.setText("Gender");

        personEditorBirthdate.setBackground(new java.awt.Color(254, 249, 217));
        personEditorBirthdate.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(201, 198, 145), 4));
        personEditorBirthdate.setForeground(new java.awt.Color(201, 198, 145));
        personEditorBirthdate.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd/MM/yyyy"))));
        personEditorBirthdate.setToolTipText("DD/MM/YY");

        jLabel27.setBackground(new java.awt.Color(210, 235, 255));
        jLabel27.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(254, 249, 217));
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel27.setText("Birthdate");

        jLabel28.setBackground(new java.awt.Color(210, 235, 255));
        jLabel28.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(254, 249, 217));
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel28.setText("Nationality");

        personEditorNationCB.setBackground(new java.awt.Color(254, 249, 217));
        personEditorNationCB.setFont(new java.awt.Font("Gadugi", 1, 12)); // NOI18N
        personEditorNationCB.setForeground(new java.awt.Color(201, 198, 145));
        personEditorNationCB.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(201, 198, 145), 4));

        personEditorBirthplaceCB.setBackground(new java.awt.Color(254, 249, 217));
        personEditorBirthplaceCB.setFont(new java.awt.Font("Gadugi", 1, 12)); // NOI18N
        personEditorBirthplaceCB.setForeground(new java.awt.Color(201, 198, 145));
        personEditorBirthplaceCB.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(201, 198, 145), 4));

        jLabel29.setBackground(new java.awt.Color(210, 235, 255));
        jLabel29.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(254, 249, 217));
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel29.setText("Birthplace");

        jLabel30.setBackground(new java.awt.Color(210, 235, 255));
        jLabel30.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(254, 249, 217));
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel30.setText("Region");

        personEditorRegionCB.setBackground(new java.awt.Color(254, 249, 217));
        personEditorRegionCB.setFont(new java.awt.Font("Gadugi", 1, 12)); // NOI18N
        personEditorRegionCB.setForeground(new java.awt.Color(201, 198, 145));
        personEditorRegionCB.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(201, 198, 145), 4));

        personEditorDistrictCB.setBackground(new java.awt.Color(254, 249, 217));
        personEditorDistrictCB.setFont(new java.awt.Font("Gadugi", 1, 12)); // NOI18N
        personEditorDistrictCB.setForeground(new java.awt.Color(201, 198, 145));
        personEditorDistrictCB.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(201, 198, 145), 4));

        jLabel31.setBackground(new java.awt.Color(210, 235, 255));
        jLabel31.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(254, 249, 217));
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel31.setText("District");

        jLabel32.setBackground(new java.awt.Color(210, 235, 255));
        jLabel32.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(254, 249, 217));
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel32.setText("Address");

        personEditorAddress.setBackground(new java.awt.Color(254, 249, 217));
        personEditorAddress.setColumns(20);
        personEditorAddress.setFont(new java.awt.Font("Gadugi", 1, 12)); // NOI18N
        personEditorAddress.setForeground(new java.awt.Color(201, 198, 145));
        personEditorAddress.setLineWrap(true);
        personEditorAddress.setRows(5);
        personEditorAddress.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(201, 198, 145), 4));
        jScrollPane2.setViewportView(personEditorAddress);

        personEditorSurname.setBackground(new java.awt.Color(254, 249, 217));
        personEditorSurname.setFont(new java.awt.Font("Gadugi", 1, 12)); // NOI18N
        personEditorSurname.setForeground(new java.awt.Color(68, 51, 41));
        personEditorSurname.setToolTipText("");
        personEditorSurname.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(201, 198, 145), 4));

        personEditorBio.setBackground(new java.awt.Color(254, 249, 217));
        personEditorBio.setColumns(20);
        personEditorBio.setFont(new java.awt.Font("Gadugi", 1, 12)); // NOI18N
        personEditorBio.setForeground(new java.awt.Color(201, 198, 145));
        personEditorBio.setLineWrap(true);
        personEditorBio.setRows(5);
        personEditorBio.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(201, 198, 145), 4));
        jScrollPane3.setViewportView(personEditorBio);

        jLabel33.setBackground(new java.awt.Color(210, 235, 255));
        jLabel33.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(254, 249, 217));
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel33.setText("Biography");

        personEditorSelectBtn.setBackground(new java.awt.Color(68, 51, 41));
        personEditorSelectBtn.setFont(new java.awt.Font("Gadugi", 1, 20)); // NOI18N
        personEditorSelectBtn.setForeground(new java.awt.Color(254, 249, 217));
        personEditorSelectBtn.setText("Select Picture");
        personEditorSelectBtn.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        personEditorSelectBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                personEditorSelectBtnActionPerformed(evt);
            }
        });

        personEditorClearPicBtn.setBackground(new java.awt.Color(68, 51, 41));
        personEditorClearPicBtn.setFont(new java.awt.Font("Gadugi", 1, 20)); // NOI18N
        personEditorClearPicBtn.setForeground(new java.awt.Color(254, 249, 217));
        personEditorClearPicBtn.setText("Clear Picture");
        personEditorClearPicBtn.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        personEditorClearPicBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                personEditorClearPicBtnActionPerformed(evt);
            }
        });

        personEditorAddP.setBackground(new java.awt.Color(68, 51, 41));
        personEditorAddP.setFont(new java.awt.Font("Gadugi", 1, 20)); // NOI18N
        personEditorAddP.setForeground(new java.awt.Color(254, 249, 217));
        personEditorAddP.setText("Add Production");
        personEditorAddP.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        personEditorAddRel.setBackground(new java.awt.Color(68, 51, 41));
        personEditorAddRel.setFont(new java.awt.Font("Gadugi", 1, 20)); // NOI18N
        personEditorAddRel.setForeground(new java.awt.Color(254, 249, 217));
        personEditorAddRel.setText("Add Relative");
        personEditorAddRel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        personEditorAddRel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                personEditorAddRelActionPerformed(evt);
            }
        });

        personEditorAddTrivia.setBackground(new java.awt.Color(68, 51, 41));
        personEditorAddTrivia.setFont(new java.awt.Font("Gadugi", 1, 20)); // NOI18N
        personEditorAddTrivia.setForeground(new java.awt.Color(254, 249, 217));
        personEditorAddTrivia.setText("Add Trivia");
        personEditorAddTrivia.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        personEditorSecondSurname.setBackground(new java.awt.Color(254, 249, 217));
        personEditorSecondSurname.setFont(new java.awt.Font("Gadugi", 1, 12)); // NOI18N
        personEditorSecondSurname.setForeground(new java.awt.Color(68, 51, 41));
        personEditorSecondSurname.setToolTipText("");
        personEditorSecondSurname.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(201, 198, 145), 4));

        jLabel22.setBackground(new java.awt.Color(254, 249, 217));
        jLabel22.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(254, 249, 217));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel22.setText("Second Surname");

        pfpLbl12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pfpLbl12.setText("pfp pic");

        javax.swing.GroupLayout personEditorLayout = new javax.swing.GroupLayout(personEditor);
        personEditor.setLayout(personEditorLayout);
        personEditorLayout.setHorizontalGroup(
            personEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menuBar2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(personEditorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(personEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, personEditorLayout.createSequentialGroup()
                        .addGroup(personEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(personEditorLayout.createSequentialGroup()
                                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(personEditorHeight))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, personEditorLayout.createSequentialGroup()
                                .addGroup(personEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(personEditorLayout.createSequentialGroup()
                                        .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jScrollPane2))
                                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(personEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(personEditorDistrictCB, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(personEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(personEditorLayout.createSequentialGroup()
                                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(personEditorName, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(personEditorLayout.createSequentialGroup()
                                                .addGroup(personEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(personEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(personEditorMidName)
                                                    .addComponent(personEditorSurname))))))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(personEditorLayout.createSequentialGroup()
                                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(personEditorGenderCB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(personEditorLayout.createSequentialGroup()
                        .addGroup(personEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, personEditorLayout.createSequentialGroup()
                                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(personEditorSecondSurname))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, personEditorLayout.createSequentialGroup()
                                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(personEditorRegionCB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, personEditorLayout.createSequentialGroup()
                                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(personEditorBirthplaceCB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, personEditorLayout.createSequentialGroup()
                                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(personEditorNationCB, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, personEditorLayout.createSequentialGroup()
                                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(personEditorBirthdate)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(7, 7, 7)
                .addGroup(personEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(personEditorLayout.createSequentialGroup()
                        .addGroup(personEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(personEditorSelectBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(personEditorClearPicBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(pfpLbl12, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3)
                    .addComponent(personEditorAddP, javax.swing.GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE)
                    .addComponent(personEditorAddRel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(personEditorAddTrivia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        personEditorLayout.setVerticalGroup(
            personEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(personEditorLayout.createSequentialGroup()
                .addComponent(menuBar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(personEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(personEditorLayout.createSequentialGroup()
                        .addGroup(personEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(personEditorLayout.createSequentialGroup()
                                .addGroup(personEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(personEditorName, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(7, 7, 7)
                                .addGroup(personEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(personEditorMidName, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(personEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(personEditorSurname, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(personEditorSelectBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(personEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(personEditorClearPicBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(personEditorLayout.createSequentialGroup()
                                .addGroup(personEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(personEditorSecondSurname, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(personEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(personEditorBirthdate, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(personEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(personEditorHeight, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addComponent(pfpLbl12, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(personEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(personEditorLayout.createSequentialGroup()
                        .addGroup(personEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(personEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(personEditorGenderCB, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                        .addGroup(personEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(personEditorNationCB, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(personEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(personEditorBirthplaceCB, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(personEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(personEditorRegionCB, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(personEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(personEditorDistrictCB, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(personEditorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(personEditorLayout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(personEditorAddP, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(personEditorAddRel, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(personEditorAddTrivia, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)))
                .addGap(26, 26, 26))
        );

        getContentPane().add(personEditor, "personEditor");

        searchResults.setBackground(new java.awt.Color(226, 121, 59));

        menuBar5.setBackground(new java.awt.Color(68, 51, 41));

        mainSearch2.setBackground(new java.awt.Color(254, 249, 217));
        mainSearch2.setFont(new java.awt.Font("Gadugi", 0, 14)); // NOI18N
        mainSearch2.setForeground(new java.awt.Color(201, 198, 145));
        mainSearch2.setText("jTextField1");
        mainSearch2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(201, 198, 145), 2));

        mainSearchButton2.setBackground(new java.awt.Color(254, 249, 217));
        mainSearchButton2.setForeground(new java.awt.Color(201, 198, 145));
        mainSearchButton2.setText("X");
        mainSearchButton2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(201, 198, 145), 2));

        mainLogin1.setBackground(new java.awt.Color(68, 51, 41));
        mainLogin1.setFont(new java.awt.Font("Gadugi", 1, 20)); // NOI18N
        mainLogin1.setForeground(new java.awt.Color(254, 249, 217));
        mainLogin1.setText("Log in");
        mainLogin1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mainLogin1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout menuBar5Layout = new javax.swing.GroupLayout(menuBar5);
        menuBar5.setLayout(menuBar5Layout);
        menuBar5Layout.setHorizontalGroup(
            menuBar5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuBar5Layout.createSequentialGroup()
                .addGap(170, 170, 170)
                .addComponent(mainSearch2, javax.swing.GroupLayout.PREFERRED_SIZE, 750, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mainSearchButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(212, Short.MAX_VALUE))
            .addGroup(menuBar5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuBar5Layout.createSequentialGroup()
                    .addContainerGap(1000, Short.MAX_VALUE)
                    .addComponent(mainLogin1, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );
        menuBar5Layout.setVerticalGroup(
            menuBar5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuBar5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(menuBar5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(mainSearch2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(mainSearchButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(menuBar5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuBar5Layout.createSequentialGroup()
                    .addContainerGap(8, Short.MAX_VALUE)
                    .addComponent(mainLogin1)
                    .addContainerGap(8, Short.MAX_VALUE)))
        );

        mainPanelTitle4.setFont(new java.awt.Font("Gadugi", 1, 48)); // NOI18N
        mainPanelTitle4.setForeground(new java.awt.Color(254, 249, 217));
        mainPanelTitle4.setText("Search results:");

        jPanel23.setBackground(new java.awt.Color(226, 121, 59));
        jPanel23.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Filters", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Gadugi", 1, 18), new java.awt.Color(254, 249, 217))); // NOI18N

        filterChB5.setText("jCheckBox1");

        filterChB6.setText("jCheckBox1");

        filterChB7.setText("jCheckBox1");

        filterChB8.setText("jCheckBox1");

        filterChB9.setText("jCheckBox1");

        FilmTitle17.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        FilmTitle17.setForeground(new java.awt.Color(254, 249, 217));
        FilmTitle17.setText("Released after");

        FilmTitle18.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        FilmTitle18.setForeground(new java.awt.Color(254, 249, 217));
        FilmTitle18.setText("Released before");

        FilmTitle19.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        FilmTitle19.setForeground(new java.awt.Color(254, 249, 217));
        FilmTitle19.setText("Max price");

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(filterChB5, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(filterChB6, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(filterChB7, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(filterChB8, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(filterChB9, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(FilmTitle18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(FilmTitle17, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(FilmTitle19, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jSpinner7)
                            .addComponent(jSpinner8)
                            .addComponent(jSpinner9, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addComponent(filterChB5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(filterChB6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(filterChB7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(filterChB8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(filterChB9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(FilmTitle19)
                    .addComponent(jSpinner7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(FilmTitle17)
                    .addComponent(jSpinner8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(FilmTitle18)
                    .addComponent(jSpinner9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel24.setBackground(new java.awt.Color(226, 121, 59));
        jPanel24.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Sort by", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Gadugi", 1, 18), new java.awt.Color(254, 249, 217))); // NOI18N

        personEditorGenderCB4.setBackground(new java.awt.Color(254, 249, 217));
        personEditorGenderCB4.setFont(new java.awt.Font("Gadugi", 1, 12)); // NOI18N
        personEditorGenderCB4.setForeground(new java.awt.Color(201, 198, 145));
        personEditorGenderCB4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        personEditorGenderCB4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(201, 198, 145), 4));

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(personEditorGenderCB4, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(personEditorGenderCB4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        seeProductionBttn.setBackground(new java.awt.Color(68, 51, 41));
        seeProductionBttn.setFont(new java.awt.Font("Gadugi", 1, 20)); // NOI18N
        seeProductionBttn.setForeground(new java.awt.Color(254, 249, 217));
        seeProductionBttn.setText("See production");
        seeProductionBttn.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        editProductionBttn.setBackground(new java.awt.Color(68, 51, 41));
        editProductionBttn.setFont(new java.awt.Font("Gadugi", 1, 20)); // NOI18N
        editProductionBttn.setForeground(new java.awt.Color(254, 249, 217));
        editProductionBttn.setText("Edit production");
        editProductionBttn.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        editProductionBttn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editProductionBttnActionPerformed(evt);
            }
        });

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "El Padrino" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane17.setViewportView(jList1);

        javax.swing.GroupLayout searchResultsLayout = new javax.swing.GroupLayout(searchResults);
        searchResults.setLayout(searchResultsLayout);
        searchResultsLayout.setHorizontalGroup(
            searchResultsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menuBar5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(searchResultsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(searchResultsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mainPanelTitle4)
                    .addGroup(searchResultsLayout.createSequentialGroup()
                        .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 865, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(searchResultsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(searchResultsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(seeProductionBttn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(editProductionBttn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        searchResultsLayout.setVerticalGroup(
            searchResultsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchResultsLayout.createSequentialGroup()
                .addComponent(menuBar5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(mainPanelTitle4)
                .addGap(18, 18, 18)
                .addGroup(searchResultsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(searchResultsLayout.createSequentialGroup()
                        .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(seeProductionBttn, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(editProductionBttn, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane17))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(searchResults, "searchResults");

        adminGUI.setBackground(new java.awt.Color(226, 121, 59));

        menuBar6.setBackground(new java.awt.Color(68, 51, 41));

        adminReturnBttn.setBackground(new java.awt.Color(68, 51, 41));
        adminReturnBttn.setFont(new java.awt.Font("Gadugi", 1, 20)); // NOI18N
        adminReturnBttn.setForeground(new java.awt.Color(254, 249, 217));
        adminReturnBttn.setText("Return");
        adminReturnBttn.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        adminReturnBttn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminReturnBttnActionPerformed(evt);
            }
        });

        adminUsernameTag.setFont(new java.awt.Font("Gadugi", 1, 24)); // NOI18N
        adminUsernameTag.setForeground(new java.awt.Color(254, 249, 217));
        adminUsernameTag.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        adminUsernameTag.setText("Username");

        javax.swing.GroupLayout menuBar6Layout = new javax.swing.GroupLayout(menuBar6);
        menuBar6.setLayout(menuBar6Layout);
        menuBar6Layout.setHorizontalGroup(
            menuBar6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuBar6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(adminReturnBttn, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                .addGap(600, 600, 600)
                .addComponent(adminUsernameTag, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        menuBar6Layout.setVerticalGroup(
            menuBar6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuBar6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(menuBar6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(adminReturnBttn, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(adminUsernameTag))
                .addContainerGap())
        );

        jPanel18.setBackground(new java.awt.Color(226, 121, 59));
        jPanel18.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        adminEditProductionBttn.setBackground(new java.awt.Color(68, 51, 41));
        adminEditProductionBttn.setFont(new java.awt.Font("Gadugi", 1, 20)); // NOI18N
        adminEditProductionBttn.setForeground(new java.awt.Color(254, 249, 217));
        adminEditProductionBttn.setText("Edit production");
        adminEditProductionBttn.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        adminCreateProductionBttn.setBackground(new java.awt.Color(68, 51, 41));
        adminCreateProductionBttn.setFont(new java.awt.Font("Gadugi", 1, 20)); // NOI18N
        adminCreateProductionBttn.setForeground(new java.awt.Color(254, 249, 217));
        adminCreateProductionBttn.setText("Create new production");
        adminCreateProductionBttn.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        adminCreateProductionBttn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminCreateProductionBttnActionPerformed(evt);
            }
        });

        adminEditPersonBttn.setBackground(new java.awt.Color(68, 51, 41));
        adminEditPersonBttn.setFont(new java.awt.Font("Gadugi", 1, 20)); // NOI18N
        adminEditPersonBttn.setForeground(new java.awt.Color(254, 249, 217));
        adminEditPersonBttn.setText("Edit person");
        adminEditPersonBttn.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        adminCreatePersonBttn.setBackground(new java.awt.Color(68, 51, 41));
        adminCreatePersonBttn.setFont(new java.awt.Font("Gadugi", 1, 20)); // NOI18N
        adminCreatePersonBttn.setForeground(new java.awt.Color(254, 249, 217));
        adminCreatePersonBttn.setText("Create new person");
        adminCreatePersonBttn.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        adminCreatePersonBttn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminCreatePersonBttnActionPerformed(evt);
            }
        });

        adminEditUserBttn.setBackground(new java.awt.Color(68, 51, 41));
        adminEditUserBttn.setFont(new java.awt.Font("Gadugi", 1, 20)); // NOI18N
        adminEditUserBttn.setForeground(new java.awt.Color(254, 249, 217));
        adminEditUserBttn.setText("Edit user");
        adminEditUserBttn.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        adminCreateBttn.setBackground(new java.awt.Color(68, 51, 41));
        adminCreateBttn.setFont(new java.awt.Font("Gadugi", 1, 20)); // NOI18N
        adminCreateBttn.setForeground(new java.awt.Color(254, 249, 217));
        adminCreateBttn.setText("Create new user");
        adminCreateBttn.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        adminCreateBttn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminCreateBttnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(adminCreateProductionBttn, javax.swing.GroupLayout.PREFERRED_SIZE, 486, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(adminEditPersonBttn, javax.swing.GroupLayout.PREFERRED_SIZE, 486, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(adminCreatePersonBttn, javax.swing.GroupLayout.PREFERRED_SIZE, 486, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(adminEditUserBttn, javax.swing.GroupLayout.PREFERRED_SIZE, 486, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(adminCreateBttn, javax.swing.GroupLayout.PREFERRED_SIZE, 486, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(adminEditProductionBttn, javax.swing.GroupLayout.PREFERRED_SIZE, 486, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(adminCreateBttn, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(adminEditUserBttn, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(adminCreatePersonBttn, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(adminEditPersonBttn, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(adminCreateProductionBttn, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(adminEditProductionBttn, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                .addGap(7, 7, 7))
        );

        jPanel19.setBackground(new java.awt.Color(226, 121, 59));
        jPanel19.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        adminStatisticsBttn.setBackground(new java.awt.Color(68, 51, 41));
        adminStatisticsBttn.setFont(new java.awt.Font("Gadugi", 1, 20)); // NOI18N
        adminStatisticsBttn.setForeground(new java.awt.Color(254, 249, 217));
        adminStatisticsBttn.setText("Visualise statistics");
        adminStatisticsBttn.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        adminStatisticsBttn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminStatisticsBttnActionPerformed(evt);
            }
        });

        jLabel34.setText("Espacio ocioso por si acaso. Si no hay nada ponemos a Bluey o algo lmao");

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(adminStatisticsBttn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(adminStatisticsBttn, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(106, 106, 106))
        );

        javax.swing.GroupLayout adminGUILayout = new javax.swing.GroupLayout(adminGUI);
        adminGUI.setLayout(adminGUILayout);
        adminGUILayout.setHorizontalGroup(
            adminGUILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menuBar6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(adminGUILayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        adminGUILayout.setVerticalGroup(
            adminGUILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(adminGUILayout.createSequentialGroup()
                .addComponent(menuBar6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(adminGUILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(adminGUILayout.createSequentialGroup()
                        .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        getContentPane().add(adminGUI, "adminGUI");

        productionVisualiser.setBackground(new java.awt.Color(226, 121, 59));

        menuBar7.setBackground(new java.awt.Color(68, 51, 41));

        prodVReturnBtn.setBackground(new java.awt.Color(68, 51, 41));
        prodVReturnBtn.setFont(new java.awt.Font("Gadugi", 1, 20)); // NOI18N
        prodVReturnBtn.setForeground(new java.awt.Color(254, 249, 217));
        prodVReturnBtn.setText("Return");
        prodVReturnBtn.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        prodVReturnBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prodVReturnBtnActionPerformed(evt);
            }
        });

        return2main.setBackground(new java.awt.Color(68, 51, 41));
        return2main.setFont(new java.awt.Font("Gadugi", 1, 20)); // NOI18N
        return2main.setForeground(new java.awt.Color(254, 249, 217));
        return2main.setText("Return to main");
        return2main.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        return2main.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                return2mainActionPerformed(evt);
            }
        });

        personEditorUsernameTag2.setFont(new java.awt.Font("Gadugi", 1, 24)); // NOI18N
        personEditorUsernameTag2.setForeground(new java.awt.Color(254, 249, 217));
        personEditorUsernameTag2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        personEditorUsernameTag2.setText("Username");

        javax.swing.GroupLayout menuBar7Layout = new javax.swing.GroupLayout(menuBar7);
        menuBar7.setLayout(menuBar7Layout);
        menuBar7Layout.setHorizontalGroup(
            menuBar7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuBar7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(prodVReturnBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(return2main, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                .addGap(412, 412, 412)
                .addComponent(personEditorUsernameTag2, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        menuBar7Layout.setVerticalGroup(
            menuBar7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuBar7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(menuBar7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(prodVReturnBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(return2main, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(personEditorUsernameTag2))
                .addContainerGap())
        );

        jLabel49.setBackground(new java.awt.Color(254, 249, 217));
        jLabel49.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(254, 249, 217));
        jLabel49.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel49.setText("Play time");

        personEditorAddRel3.setBackground(new java.awt.Color(68, 51, 41));
        personEditorAddRel3.setFont(new java.awt.Font("Gadugi", 1, 20)); // NOI18N
        personEditorAddRel3.setForeground(new java.awt.Color(254, 249, 217));
        personEditorAddRel3.setText("Add review");
        personEditorAddRel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        personEditorAddTrivia3.setBackground(new java.awt.Color(68, 51, 41));
        personEditorAddTrivia3.setFont(new java.awt.Font("Gadugi", 1, 20)); // NOI18N
        personEditorAddTrivia3.setForeground(new java.awt.Color(254, 249, 217));
        personEditorAddTrivia3.setText("Add to cart");
        personEditorAddTrivia3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        filmPic18.setBackground(new java.awt.Color(68, 51, 41));

        prodVPicLbl.setText("jLabel35");

        javax.swing.GroupLayout filmPic18Layout = new javax.swing.GroupLayout(filmPic18);
        filmPic18.setLayout(filmPic18Layout);
        filmPic18Layout.setHorizontalGroup(
            filmPic18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(prodVPicLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        filmPic18Layout.setVerticalGroup(
            filmPic18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(prodVPicLbl, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 409, Short.MAX_VALUE)
        );

        prodVTitleLbl.setBackground(new java.awt.Color(254, 249, 217));
        prodVTitleLbl.setFont(new java.awt.Font("Gadugi", 1, 48)); // NOI18N
        prodVTitleLbl.setForeground(new java.awt.Color(254, 249, 217));
        prodVTitleLbl.setText("Title");

        jLabel50.setBackground(new java.awt.Color(210, 235, 255));
        jLabel50.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(254, 249, 217));
        jLabel50.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel50.setText("Category");

        jLabel52.setBackground(new java.awt.Color(210, 235, 255));
        jLabel52.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(254, 249, 217));
        jLabel52.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel52.setText("Genre");

        jLabel53.setBackground(new java.awt.Color(254, 249, 217));
        jLabel53.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(254, 249, 217));
        jLabel53.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel53.setText("Release date");

        prodVCategoryLbl.setBackground(new java.awt.Color(210, 235, 255));
        prodVCategoryLbl.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        prodVCategoryLbl.setForeground(new java.awt.Color(254, 249, 217));
        prodVCategoryLbl.setText("Data field");

        prodVGenreLbl.setBackground(new java.awt.Color(210, 235, 255));
        prodVGenreLbl.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        prodVGenreLbl.setForeground(new java.awt.Color(254, 249, 217));
        prodVGenreLbl.setText("Data field");

        prodVRelease.setBackground(new java.awt.Color(210, 235, 255));
        prodVRelease.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        prodVRelease.setForeground(new java.awt.Color(254, 249, 217));
        prodVRelease.setText("Data field");

        prodVPlaytime.setBackground(new java.awt.Color(210, 235, 255));
        prodVPlaytime.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        prodVPlaytime.setForeground(new java.awt.Color(254, 249, 217));
        prodVPlaytime.setText("Data field");

        jPanel21.setBackground(new java.awt.Color(226, 121, 59));
        jPanel21.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Storyline", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(254, 249, 217))); // NOI18N

        jTextArea7.setBackground(new java.awt.Color(226, 121, 59));
        jTextArea7.setColumns(20);
        jTextArea7.setFont(new java.awt.Font("Gadugi", 1, 12)); // NOI18N
        jTextArea7.setForeground(new java.awt.Color(254, 249, 217));
        jTextArea7.setLineWrap(true);
        jTextArea7.setRows(5);
        jTextArea7.setText("for(int i = 0; i < productionEditorCategoryCB.getItemCount(); i++){             if(productionEditorCategoryCB.getItemAt(i).equals(category))                 productionEditorCategoryCB.setSelectedIndex(i);         }");
        jTextArea7.setBorder(null);
        jTextArea7.setFocusable(false);
        jScrollPane15.setViewportView(jTextArea7);

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane15, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane15, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
        );

        jScrollPane13.setBackground(new java.awt.Color(226, 121, 59));
        jScrollPane13.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Trivia", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(254, 249, 217))); // NOI18N
        jScrollPane13.setForeground(new java.awt.Color(226, 121, 59));
        jScrollPane13.setToolTipText("");

        jScrollPane14.setBackground(new java.awt.Color(226, 121, 59));
        jScrollPane14.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "People involved", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(254, 249, 217))); // NOI18N
        jScrollPane14.setForeground(new java.awt.Color(226, 121, 59));

        javax.swing.GroupLayout productionVisualiserLayout = new javax.swing.GroupLayout(productionVisualiser);
        productionVisualiser.setLayout(productionVisualiserLayout);
        productionVisualiserLayout.setHorizontalGroup(
            productionVisualiserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menuBar7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(productionVisualiserLayout.createSequentialGroup()
                .addGroup(productionVisualiserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(productionVisualiserLayout.createSequentialGroup()
                        .addGroup(productionVisualiserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel50, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(productionVisualiserLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(productionVisualiserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(prodVCategoryLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(prodVGenreLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(123, 123, 123)
                        .addGroup(productionVisualiserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(productionVisualiserLayout.createSequentialGroup()
                                .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(prodVRelease, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(productionVisualiserLayout.createSequentialGroup()
                                .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(prodVPlaytime, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(productionVisualiserLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(productionVisualiserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(productionVisualiserLayout.createSequentialGroup()
                                .addGroup(productionVisualiserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE)
                                    .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(prodVTitleLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 904, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(productionVisualiserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(personEditorAddRel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(filmPic18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(personEditorAddTrivia3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        productionVisualiserLayout.setVerticalGroup(
            productionVisualiserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(productionVisualiserLayout.createSequentialGroup()
                .addComponent(menuBar7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(productionVisualiserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(productionVisualiserLayout.createSequentialGroup()
                        .addComponent(filmPic18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(personEditorAddRel3, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                        .addComponent(personEditorAddTrivia3, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(productionVisualiserLayout.createSequentialGroup()
                        .addComponent(prodVTitleLbl)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(productionVisualiserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(productionVisualiserLayout.createSequentialGroup()
                                .addGroup(productionVisualiserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel50)
                                    .addComponent(prodVCategoryLbl))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(productionVisualiserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel52)
                                    .addComponent(prodVGenreLbl)))
                            .addGroup(productionVisualiserLayout.createSequentialGroup()
                                .addGroup(productionVisualiserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel53)
                                    .addComponent(prodVRelease))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(productionVisualiserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel49)
                                    .addComponent(prodVPlaytime))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(productionVisualiserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane14)
                            .addGroup(productionVisualiserLayout.createSequentialGroup()
                                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane13)))))
                .addContainerGap())
        );

        getContentPane().add(productionVisualiser, "productionVisualiser");

        personVisualiser.setBackground(new java.awt.Color(226, 121, 59));

        menuBar8.setBackground(new java.awt.Color(68, 51, 41));

        prodVReturnBtn1.setBackground(new java.awt.Color(68, 51, 41));
        prodVReturnBtn1.setFont(new java.awt.Font("Gadugi", 1, 20)); // NOI18N
        prodVReturnBtn1.setForeground(new java.awt.Color(254, 249, 217));
        prodVReturnBtn1.setText("Return");
        prodVReturnBtn1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        prodVReturnBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prodVReturnBtn1ActionPerformed(evt);
            }
        });

        return2main1.setBackground(new java.awt.Color(68, 51, 41));
        return2main1.setFont(new java.awt.Font("Gadugi", 1, 20)); // NOI18N
        return2main1.setForeground(new java.awt.Color(254, 249, 217));
        return2main1.setText("Return to main");
        return2main1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        return2main1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                return2main1ActionPerformed(evt);
            }
        });

        personEditorUsernameTag3.setFont(new java.awt.Font("Gadugi", 1, 24)); // NOI18N
        personEditorUsernameTag3.setForeground(new java.awt.Color(254, 249, 217));
        personEditorUsernameTag3.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        personEditorUsernameTag3.setText("Username");

        personVEditBttn.setBackground(new java.awt.Color(68, 51, 41));
        personVEditBttn.setFont(new java.awt.Font("Gadugi", 1, 20)); // NOI18N
        personVEditBttn.setForeground(new java.awt.Color(254, 249, 217));
        personVEditBttn.setText("Edit person");
        personVEditBttn.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout menuBar8Layout = new javax.swing.GroupLayout(menuBar8);
        menuBar8.setLayout(menuBar8Layout);
        menuBar8Layout.setHorizontalGroup(
            menuBar8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuBar8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(prodVReturnBtn1, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(return2main1, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(personVEditBttn, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(179, 179, 179)
                .addComponent(personEditorUsernameTag3, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        menuBar8Layout.setVerticalGroup(
            menuBar8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuBar8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(menuBar8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(personVEditBttn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(menuBar8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(prodVReturnBtn1, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                        .addComponent(return2main1, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                        .addComponent(personEditorUsernameTag3)))
                .addContainerGap())
        );

        jLabel51.setBackground(new java.awt.Color(254, 249, 217));
        jLabel51.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(254, 249, 217));
        jLabel51.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel51.setText("Play time");

        filmPic19.setBackground(new java.awt.Color(68, 51, 41));

        personVPicLbl.setText("jLabel35");

        javax.swing.GroupLayout filmPic19Layout = new javax.swing.GroupLayout(filmPic19);
        filmPic19.setLayout(filmPic19Layout);
        filmPic19Layout.setHorizontalGroup(
            filmPic19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(personVPicLbl, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        filmPic19Layout.setVerticalGroup(
            filmPic19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(personVPicLbl, javax.swing.GroupLayout.DEFAULT_SIZE, 493, Short.MAX_VALUE)
        );

        personVNameLbl.setBackground(new java.awt.Color(254, 249, 217));
        personVNameLbl.setFont(new java.awt.Font("Gadugi", 1, 48)); // NOI18N
        personVNameLbl.setForeground(new java.awt.Color(254, 249, 217));
        personVNameLbl.setText("Name");

        jLabel58.setBackground(new java.awt.Color(210, 235, 255));
        jLabel58.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        jLabel58.setForeground(new java.awt.Color(254, 249, 217));
        jLabel58.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel58.setText("Age");

        jLabel59.setBackground(new java.awt.Color(210, 235, 255));
        jLabel59.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        jLabel59.setForeground(new java.awt.Color(254, 249, 217));
        jLabel59.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel59.setText("Nationality");

        jLabel60.setBackground(new java.awt.Color(254, 249, 217));
        jLabel60.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        jLabel60.setForeground(new java.awt.Color(254, 249, 217));
        jLabel60.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel60.setText("Birthdate");

        personVCategoryLbl.setBackground(new java.awt.Color(210, 235, 255));
        personVCategoryLbl.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        personVCategoryLbl.setForeground(new java.awt.Color(254, 249, 217));
        personVCategoryLbl.setText("Data field");

        personVGenreLbl.setBackground(new java.awt.Color(210, 235, 255));
        personVGenreLbl.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        personVGenreLbl.setForeground(new java.awt.Color(254, 249, 217));
        personVGenreLbl.setText("Data field");

        personVReleaseLbl.setBackground(new java.awt.Color(210, 235, 255));
        personVReleaseLbl.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        personVReleaseLbl.setForeground(new java.awt.Color(254, 249, 217));
        personVReleaseLbl.setText("Data field");

        jLabel64.setBackground(new java.awt.Color(210, 235, 255));
        jLabel64.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        jLabel64.setForeground(new java.awt.Color(254, 249, 217));
        jLabel64.setText("Data field");

        personVProductionsScroll.setBackground(new java.awt.Color(226, 121, 59));
        personVProductionsScroll.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Trivia", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(254, 249, 217))); // NOI18N
        personVProductionsScroll.setForeground(new java.awt.Color(226, 121, 59));
        personVProductionsScroll.setToolTipText("");

        personVRelsScroll.setBackground(new java.awt.Color(226, 121, 59));
        personVRelsScroll.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Relatives", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(254, 249, 217))); // NOI18N
        personVRelsScroll.setForeground(new java.awt.Color(226, 121, 59));

        javax.swing.GroupLayout personVisualiserLayout = new javax.swing.GroupLayout(personVisualiser);
        personVisualiser.setLayout(personVisualiserLayout);
        personVisualiserLayout.setHorizontalGroup(
            personVisualiserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menuBar8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(personVisualiserLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(personVisualiserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(personVNameLbl, javax.swing.GroupLayout.DEFAULT_SIZE, 677, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, personVisualiserLayout.createSequentialGroup()
                        .addGroup(personVisualiserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(personVisualiserLayout.createSequentialGroup()
                                .addGroup(personVisualiserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel58, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(personVisualiserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(personVCategoryLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(personVGenreLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(personVisualiserLayout.createSequentialGroup()
                                .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(personVReleaseLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(personVisualiserLayout.createSequentialGroup()
                                .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                        .addComponent(personVRelsScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(personVProductionsScroll))
                .addGap(18, 18, 18)
                .addComponent(filmPic19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        personVisualiserLayout.setVerticalGroup(
            personVisualiserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(personVisualiserLayout.createSequentialGroup()
                .addComponent(menuBar8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(personVisualiserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(personVisualiserLayout.createSequentialGroup()
                        .addComponent(personVNameLbl)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(personVisualiserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(personVRelsScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(personVisualiserLayout.createSequentialGroup()
                                .addGroup(personVisualiserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel58)
                                    .addComponent(personVCategoryLbl))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(personVisualiserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel59)
                                    .addComponent(personVGenreLbl))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(personVisualiserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel60)
                                    .addComponent(personVReleaseLbl))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(personVisualiserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel51)
                                    .addComponent(jLabel64))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(personVProductionsScroll))
                    .addGroup(personVisualiserLayout.createSequentialGroup()
                        .addComponent(filmPic19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 70, Short.MAX_VALUE)))
                .addContainerGap())
        );

        getContentPane().add(personVisualiser, "personVisualiser");

        reviewWindow.setBackground(new java.awt.Color(226, 121, 59));

        menuBar9.setBackground(new java.awt.Color(68, 51, 41));

        personEditorReturnBtn2.setBackground(new java.awt.Color(68, 51, 41));
        personEditorReturnBtn2.setFont(new java.awt.Font("Gadugi", 1, 20)); // NOI18N
        personEditorReturnBtn2.setForeground(new java.awt.Color(254, 249, 217));
        personEditorReturnBtn2.setText("Return");
        personEditorReturnBtn2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        personEditorReturnBtn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                personEditorReturnBtn2ActionPerformed(evt);
            }
        });

        personEditorClearBtn2.setBackground(new java.awt.Color(68, 51, 41));
        personEditorClearBtn2.setFont(new java.awt.Font("Gadugi", 1, 20)); // NOI18N
        personEditorClearBtn2.setForeground(new java.awt.Color(254, 249, 217));
        personEditorClearBtn2.setText("Clear");
        personEditorClearBtn2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        personEditorClearBtn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                personEditorClearBtn2ActionPerformed(evt);
            }
        });

        personEditorSubmitBtn2.setBackground(new java.awt.Color(68, 51, 41));
        personEditorSubmitBtn2.setFont(new java.awt.Font("Gadugi", 1, 20)); // NOI18N
        personEditorSubmitBtn2.setForeground(new java.awt.Color(254, 249, 217));
        personEditorSubmitBtn2.setText("Submit");
        personEditorSubmitBtn2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        personEditorSubmitBtn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                personEditorSubmitBtn2ActionPerformed(evt);
            }
        });

        personEditorUsernameTag4.setFont(new java.awt.Font("Gadugi", 1, 24)); // NOI18N
        personEditorUsernameTag4.setForeground(new java.awt.Color(254, 249, 217));
        personEditorUsernameTag4.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        personEditorUsernameTag4.setText("Username");

        javax.swing.GroupLayout menuBar9Layout = new javax.swing.GroupLayout(menuBar9);
        menuBar9.setLayout(menuBar9Layout);
        menuBar9Layout.setHorizontalGroup(
            menuBar9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuBar9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(personEditorReturnBtn2, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(personEditorClearBtn2, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(personEditorSubmitBtn2, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                .addGap(198, 198, 198)
                .addComponent(personEditorUsernameTag4, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        menuBar9Layout.setVerticalGroup(
            menuBar9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuBar9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(menuBar9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(personEditorReturnBtn2, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(personEditorClearBtn2, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(personEditorSubmitBtn2, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(personEditorUsernameTag4))
                .addContainerGap())
        );

        jLabel40.setBackground(new java.awt.Color(210, 235, 255));
        jLabel40.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(254, 249, 217));
        jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel40.setText("Amount of stars");

        productionEditorStoryline1.setBackground(new java.awt.Color(254, 249, 217));
        productionEditorStoryline1.setColumns(20);
        productionEditorStoryline1.setFont(new java.awt.Font("Gadugi", 1, 18)); // NOI18N
        productionEditorStoryline1.setForeground(new java.awt.Color(201, 198, 145));
        productionEditorStoryline1.setLineWrap(true);
        productionEditorStoryline1.setRows(5);
        productionEditorStoryline1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(201, 198, 145), 4));
        jScrollPane16.setViewportView(productionEditorStoryline1);

        starsSpinner.setModel(new javax.swing.SpinnerNumberModel(1, 1, 5, 1));

        jLabel54.setBackground(new java.awt.Color(210, 235, 255));
        jLabel54.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
        jLabel54.setForeground(new java.awt.Color(254, 249, 217));
        jLabel54.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel54.setText("Review");

        javax.swing.GroupLayout reviewWindowLayout = new javax.swing.GroupLayout(reviewWindow);
        reviewWindow.setLayout(reviewWindowLayout);
        reviewWindowLayout.setHorizontalGroup(
            reviewWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menuBar9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(reviewWindowLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane16)
                .addContainerGap())
            .addGroup(reviewWindowLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(139, 139, 139)
                .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(starsSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        reviewWindowLayout.setVerticalGroup(
            reviewWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reviewWindowLayout.createSequentialGroup()
                .addComponent(menuBar9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(reviewWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(starsSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 416, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(70, Short.MAX_VALUE))
        );

        getContentPane().add(reviewWindow, "reviewWindow");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mainLogin1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mainLogin1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mainLogin1ActionPerformed

    private void personEditorSelectBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_personEditorSelectBtnActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Image Files", "jpg", "png", "gif", "jpeg"));
        int returnValue = fileChooser.showOpenDialog(this);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            ImageIcon imageIcon = new ImageIcon(selectedFile.getAbsolutePath());
            ImageIcon pfpIcon = new ImageIcon(imageIcon.getImage().getScaledInstance(223, 223, java.awt.Image.SCALE_DEFAULT));
            pfpLbl12.setText("");
            pfpLbl12.setIcon(pfpIcon);
        }
        
        
    }//GEN-LAST:event_personEditorSelectBtnActionPerformed

    private void personEditorClearPicBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_personEditorClearPicBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_personEditorClearPicBtnActionPerformed

    private void userEditorClearPicBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userEditorClearPicBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_userEditorClearPicBtnActionPerformed

    private void userEditorSelectPicBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userEditorSelectPicBtnActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Image Files", "jpg", "png", "gif", "jpeg"));
        int returnValue = fileChooser.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            ImageIcon imageIcon = new ImageIcon(selectedFile.getAbsolutePath());
            ImageIcon pfpIcon = new ImageIcon(imageIcon.getImage().getScaledInstance(175, 175, java.awt.Image.SCALE_DEFAULT));
            pfpLbl.setText("");
            pfpLbl.setIcon(pfpIcon);
        }
    }//GEN-LAST:event_userEditorSelectPicBtnActionPerformed

    private void personEditorSubmitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_personEditorSubmitBtnActionPerformed
    firstName = personEditorName.getText();
    middleName = personEditorMidName.getText();
    lastName = personEditorSurname.getText();
    secondSurname = personEditorSecondSurname.getText();
    biography = personEditorName.getText();
    trivia = personEditorName.getText();
    birthdate = personEditorBirthdate.getText();
    String heightText = personEditorHeight.getText();
    idDistrict = (int) personEditorDistrictCB.getSelectedIndex();
    idDistrict = idDistrict + 1;
    System.out.println("La distrito es" + idDistrict);
    
    Icon icon = pfpLbl.getIcon();
    byte[] photo = null;
        if (icon instanceof ImageIcon) {
            BufferedImage bufferedImage = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics g = bufferedImage.createGraphics();
            icon.paintIcon(null, g, 0, 0);
            g.dispose();

            try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
                ImageIO.write(bufferedImage, "jpg", baos);
                baos.flush();
                photo = baos.toByteArray();
            } catch (IOException ex) {
                ex.printStackTrace();
                }
            }
    
    
    
        if (firstName.isEmpty() || lastName.isEmpty() || biography.isEmpty() || trivia.isEmpty() || birthdate.isEmpty() || heightText.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor complete todos los campos.");
        } else {
            // Continuar con la inserción de persona
            try {
                int height = Integer.parseInt(heightText);
                ConnectDB.InsertPerson(firstName, middleName, lastName, secondSurname, biography, birthdate, height, photo, trivia, idDistrict);
                System.out.println("Persona insertada exitosamente");
            } catch (SQLException ex) {
                Logger.getLogger(mainInterface.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "La altura debe ser un número válido.");
            }
        }
    }//GEN-LAST:event_personEditorSubmitBtnActionPerformed

    private void popUpSearchBttnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_popUpSearchBttnActionPerformed

    }//GEN-LAST:event_popUpSearchBttnActionPerformed

    private void popUpSelectBttnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_popUpSelectBttnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_popUpSelectBttnActionPerformed

    private void adminCreateProductionBttnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminCreateProductionBttnActionPerformed
        javax.swing.JFrame newFrame = new mainInterface();
        java.awt.CardLayout mainLayout = (java.awt.CardLayout) this.getContentPane().getLayout();
        mainLayout.show(this.getContentPane(), "productionEditor");
    }//GEN-LAST:event_adminCreateProductionBttnActionPerformed

    private void adminCreatePersonBttnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminCreatePersonBttnActionPerformed
        javax.swing.JFrame newFrame = new mainInterface();
        java.awt.CardLayout mainLayout = (java.awt.CardLayout) this.getContentPane().getLayout();
        mainLayout.show(this.getContentPane(), "personEditor");
    }//GEN-LAST:event_adminCreatePersonBttnActionPerformed

    private void adminCreateBttnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminCreateBttnActionPerformed
        javax.swing.JFrame newFrame = new mainInterface();
        java.awt.CardLayout mainLayout = (java.awt.CardLayout) this.getContentPane().getLayout();
        mainLayout.show(this.getContentPane(), "userEditor");
    }//GEN-LAST:event_adminCreateBttnActionPerformed

    private void adminStatisticsBttnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminStatisticsBttnActionPerformed
        javax.swing.JFrame newFrame = new mainInterface();
        java.awt.CardLayout mainLayout = (java.awt.CardLayout) this.getContentPane().getLayout();
        mainLayout.show(this.getContentPane(), "productionVisualiser");
    }//GEN-LAST:event_adminStatisticsBttnActionPerformed

    private void mainLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mainLoginActionPerformed
        this.setVisible(false);
        new loginWindow().setVisible(true);
    }//GEN-LAST:event_mainLoginActionPerformed

    private void adminPanelBttnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminPanelBttnActionPerformed
        // TODO add your handling code here:
        javax.swing.JFrame newFrame = new mainInterface();
        java.awt.CardLayout mainLayout = (java.awt.CardLayout) this.getContentPane().getLayout();
        mainLayout.show(this.getContentPane(), "adminGUI");

    }//GEN-LAST:event_adminPanelBttnActionPerformed

    private void personEditorSubmitBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_personEditorSubmitBtn1ActionPerformed
        String productionType, title, synopsis;
        int idCategory, duration, releaseYear;
        
        productionType = (String) productionEditorCategoryCB.getSelectedItem();
        title = productionEditorName.getText();
        synopsis = productionEditorStoryline.getText();
        idCategory = (int) productionEditorGenreCB.getSelectedIndex() + 1;
        String durationText = productionEditorPlaytime.getText();
        String releaseText = productionEditorRelease.getText();
       
        if (productionType.isEmpty() || title.isEmpty() || synopsis.isEmpty() || durationText.isEmpty() || releaseText.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor complete todos los campos.");
        } else {
            // Continuar con la inserción de producción
            duration = Integer.parseInt(durationText);
            releaseYear = Integer.parseInt(releaseText);
            ConnectDB.insertProduction(productionType, idCategory, title, duration, synopsis, null, releaseYear, null);
            System.out.println("ingresado");
        }
    }//GEN-LAST:event_personEditorSubmitBtn1ActionPerformed

    private void LogOutBttn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LogOutBttn1ActionPerformed
        javax.swing.JFrame newFrame = new mainInterface();
        java.awt.CardLayout mainLayout = (java.awt.CardLayout) this.getContentPane().getLayout();
        mainLayout.show(this.getContentPane(), "mainPanel");
        currentUser = "";
    }//GEN-LAST:event_LogOutBttn1ActionPerformed

    private void adminReturnBttnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminReturnBttnActionPerformed
        javax.swing.JFrame newFrame = new mainInterface();
        java.awt.CardLayout mainLayout = (java.awt.CardLayout) this.getContentPane().getLayout();
        mainLayout.show(this.getContentPane(), "loggedMain");
    }//GEN-LAST:event_adminReturnBttnActionPerformed

    private void personEditorReturnBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_personEditorReturnBtn1ActionPerformed
        javax.swing.JFrame newFrame = new mainInterface();
        java.awt.CardLayout mainLayout = (java.awt.CardLayout) this.getContentPane().getLayout();
        mainLayout.show(this.getContentPane(), "adminGUI");
    }//GEN-LAST:event_personEditorReturnBtn1ActionPerformed

    private void userEditorReturnBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userEditorReturnBtnActionPerformed
        javax.swing.JFrame newFrame = new mainInterface();
        java.awt.CardLayout mainLayout = (java.awt.CardLayout) this.getContentPane().getLayout();
        mainLayout.show(this.getContentPane(), "adminGUI");
    }//GEN-LAST:event_userEditorReturnBtnActionPerformed

    private void personEditorReturnBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_personEditorReturnBtnActionPerformed
        javax.swing.JFrame newFrame = new mainInterface();
        java.awt.CardLayout mainLayout = (java.awt.CardLayout) this.getContentPane().getLayout();
        mainLayout.show(this.getContentPane(), "adminGUI");
    }//GEN-LAST:event_personEditorReturnBtnActionPerformed

    private void userEditorClearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userEditorClearBtnActionPerformed
        userEditorCleaner();
    }//GEN-LAST:event_userEditorClearBtnActionPerformed
    
    public boolean userExists(String Username) throws SQLException
    {
        if (!ConnectDB.checkUsernameAvailability(Username)){
        return true;
        } else{
            return false;
        }
    }
    
    private boolean check(){
        try {
            idNum = Integer.parseInt(userEditorIdNum.getText());
        } catch (Exception e) {
            userEditorIdNum.setText("Id Number field must be filled");
        }
        if(!userEditorPassword.getText().equals(userEditorPasswordConf.getText()))
        {
            jLabel36.setText("Passwords do not match ");
            return false;
            //jLabel5.setForeground(new java.awt.Color(226,122,55));    
        } else if(userEditorPassword.getText().equals("")
            || userEditorUsername.getText().equals("")
            || userEditorEmail.getText().equals("")
            || userEditorIdNum.getText().equals(""))
        {
            jLabel36.setText("No field can be left empty ");
            return false;
            //jLabel36.setForeground(new java.awt.Color(226,122,55));
           
        }
        try {
            if (userExists(userEditorUsername.getText())) {
                jLabel36.setText("Username already taken ");
                username = "";
                return false;
            } 
        } catch (SQLException ex) {
            // Manejar la excepciÃ³n
            ex.printStackTrace();
            jLabel36.setText("Error checking username availability");
            return false;
        }
        return true;
    }
    
    private void userEditorSubmitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userEditorSubmitBtnActionPerformed
        String idNumStr = userEditorIdNum.getText();
        String phoneStr = userEditorPhone.getText();

            jLabel36.setText("");
            if (!check()){
                JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios. Por favor, complete todos los campos antes de continuar.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                username = userEditorUsername.getText();
                nationality = (String) userEditorNationalityCB.getSelectedItem();
                country = (String) userEditorCountryCB.getSelectedItem();
                region = (String) userEditorRegionCB.getSelectedItem();
                district = (String) userEditorDistrictCB.getSelectedItem();
                address = userEditorAddress.getText();
                firstName = userEditorName.getText();
                lastName = userEditorSurname.getText();
                secondSurname = userEditorSecondSurname.getText();
                middleName = userEditorSecondName.getText();
                idNum = Integer.parseInt(userEditorIdNum.getText());
                birthdate = userEditorBirthdate.getText();
                String email = userEditorEmail.getText();
                int realgender = (int) userEditorGenderCB.getSelectedIndex();
                realgender = realgender + 1;
                int realidtype = (int) userEditorIdTypeCB.getSelectedIndex();
                realidtype = realidtype + 1;
                int phone = Integer.parseInt(userEditorPhone.getText());

                int realidDistrict = (int) userEditorDistrictCB.getSelectedIndex();
                realidDistrict = realidDistrict + 1;
                System.out.println("La distrito es" + realidDistrict);

                int realidnationality = (int) userEditorNationalityCB.getSelectedIndex();
                realidnationality = realidnationality + 1;
                System.out.println("La nacionanlidad es" + realidnationality);
                Icon icon = pfpLbl.getIcon();
                
                byte[] photo = null;
                if (icon instanceof ImageIcon) {
                    BufferedImage bufferedImage = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
                    Graphics g = bufferedImage.createGraphics();
                    icon.paintIcon(null, g, 0, 0);
                    g.dispose();

                    try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
                        ImageIO.write(bufferedImage, "jpg", baos);
                        baos.flush();
                        photo = baos.toByteArray();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }

                if (jCheckBox1.isSelected()){
                    try {
                        ConnectDB.InsertUserSysAdministrator(firstName, middleName, lastName, secondSurname, idNum, birthdate, photo, email, phone, username, password,
                                realidDistrict, realidnationality, realgender, realidtype);
                    } catch (SQLException ex) {
                        Logger.getLogger(mainInterface.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else{
                    try {
                        ConnectDB.InsertUserSys(firstName, middleName, lastName, secondSurname, idNum, birthdate, photo, email, phone, username, password,
                                realidDistrict, realidnationality, realgender, realidtype);
                    } catch (SQLException ex) {
                        Logger.getLogger(mainInterface.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            System.out.println("vista.mainInterface.userEditorSubmitBtnActionPerformed()");
            }
        
        
        
        
        
        
        
    }//GEN-LAST:event_userEditorSubmitBtnActionPerformed

    private void personEditorAddP1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_personEditorAddP1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_personEditorAddP1ActionPerformed

    private void productionEditorLinkTrailerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_productionEditorLinkTrailerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_productionEditorLinkTrailerActionPerformed

    private void personEditorSelectBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_personEditorSelectBtn1ActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Image Files", "jpg", "png", "gif", "jpeg"));
        int returnValue = fileChooser.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            ImageIcon imageIcon = new ImageIcon(selectedFile.getAbsolutePath());
            ImageIcon pfpIcon = new ImageIcon(imageIcon.getImage().getScaledInstance(222, 333, java.awt.Image.SCALE_DEFAULT));
            filmPic1.setText("");
            filmPic1.setIcon(pfpIcon);
        }
    }//GEN-LAST:event_personEditorSelectBtn1ActionPerformed

    private void personEditorClearPicBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_personEditorClearPicBtn1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_personEditorClearPicBtn1ActionPerformed

    private void prodVReturnBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prodVReturnBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_prodVReturnBtnActionPerformed

    private void prodVReturnBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prodVReturnBtn1ActionPerformed
        javax.swing.JFrame newFrame = new mainInterface();
        java.awt.CardLayout mainLayout = (java.awt.CardLayout) this.getContentPane().getLayout();
        mainLayout.show(this.getContentPane(), "adminGUI");
    }//GEN-LAST:event_prodVReturnBtn1ActionPerformed

    private void personEditorClearBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_personEditorClearBtn1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_personEditorClearBtn1ActionPerformed

    private void return2main1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_return2main1ActionPerformed
        // TODO add your handling code here:
        javax.swing.JFrame newFrame = new mainInterface();
        java.awt.CardLayout mainLayout = (java.awt.CardLayout) this.getContentPane().getLayout();
        mainLayout.show(this.getContentPane(), "loggedMain");
    }//GEN-LAST:event_return2main1ActionPerformed

    private void return2mainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_return2mainActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_return2mainActionPerformed

    private void personEditorReturnBtn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_personEditorReturnBtn2ActionPerformed
        javax.swing.JFrame newFrame = new mainInterface();
        java.awt.CardLayout mainLayout = (java.awt.CardLayout) this.getContentPane().getLayout();
        mainLayout.show(this.getContentPane(), "loggedMain");
    }//GEN-LAST:event_personEditorReturnBtn2ActionPerformed

    private void personEditorClearBtn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_personEditorClearBtn2ActionPerformed
        productionEditorStoryline1.setText("");
    }//GEN-LAST:event_personEditorClearBtn2ActionPerformed

    private void personEditorSubmitBtn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_personEditorSubmitBtn2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_personEditorSubmitBtn2ActionPerformed

    private void personEditorAddRelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_personEditorAddRelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_personEditorAddRelActionPerformed

    private void editProductionBttnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editProductionBttnActionPerformed
        ArrayList<String> datos = new ArrayList<String>();
        
        productionEditorSetter(datos.get(3), "Movie", datos.get(2), Integer.parseInt(datos.get(7)), Integer.parseInt(datos.get(4)), datos.get(5));
        
        
        javax.swing.JFrame newFrame = new mainInterface();
        java.awt.CardLayout mainLayout = (java.awt.CardLayout) this.getContentPane().getLayout();
        mainLayout.show(this.getContentPane(), "productionEditor");
    }//GEN-LAST:event_editProductionBttnActionPerformed

    private void mainSearchButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mainSearchButton1ActionPerformed
        javax.swing.JFrame newFrame = new mainInterface();
        java.awt.CardLayout mainLayout = (java.awt.CardLayout) this.getContentPane().getLayout();
        mainLayout.show(this.getContentPane(), "searchResults");
    }//GEN-LAST:event_mainSearchButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Metal".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(mainInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(mainInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(mainInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mainInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new mainInterface().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel FilmTitle1;
    private javax.swing.JLabel FilmTitle10;
    private javax.swing.JLabel FilmTitle17;
    private javax.swing.JLabel FilmTitle18;
    private javax.swing.JLabel FilmTitle19;
    private javax.swing.JLabel FilmTitle2;
    private javax.swing.JLabel FilmTitle20;
    private javax.swing.JLabel FilmTitle21;
    private javax.swing.JLabel FilmTitle22;
    private javax.swing.JLabel FilmTitle23;
    private javax.swing.JLabel FilmTitle24;
    private javax.swing.JLabel FilmTitle25;
    private javax.swing.JLabel FilmTitle26;
    private javax.swing.JLabel FilmTitle27;
    private javax.swing.JLabel FilmTitle28;
    private javax.swing.JLabel FilmTitle29;
    private javax.swing.JLabel FilmTitle3;
    private javax.swing.JLabel FilmTitle30;
    private javax.swing.JLabel FilmTitle31;
    private javax.swing.JLabel FilmTitle32;
    private javax.swing.JLabel FilmTitle33;
    private javax.swing.JLabel FilmTitle34;
    private javax.swing.JLabel FilmTitle35;
    private javax.swing.JLabel FilmTitle36;
    private javax.swing.JLabel FilmTitle37;
    private javax.swing.JLabel FilmTitle38;
    private javax.swing.JLabel FilmTitle39;
    private javax.swing.JLabel FilmTitle4;
    private javax.swing.JLabel FilmTitle40;
    private javax.swing.JLabel FilmTitle41;
    private javax.swing.JLabel FilmTitle42;
    private javax.swing.JLabel FilmTitle43;
    private javax.swing.JLabel FilmTitle44;
    private javax.swing.JLabel FilmTitle45;
    private javax.swing.JLabel FilmTitle46;
    private javax.swing.JLabel FilmTitle47;
    private javax.swing.JLabel FilmTitle48;
    private javax.swing.JLabel FilmTitle49;
    private javax.swing.JLabel FilmTitle5;
    private javax.swing.JLabel FilmTitle50;
    private javax.swing.JLabel FilmTitle51;
    private javax.swing.JLabel FilmTitle52;
    private javax.swing.JLabel FilmTitle53;
    private javax.swing.JLabel FilmTitle54;
    private javax.swing.JLabel FilmTitle55;
    private javax.swing.JLabel FilmTitle56;
    private javax.swing.JLabel FilmTitle57;
    private javax.swing.JLabel FilmTitle58;
    private javax.swing.JLabel FilmTitle59;
    private javax.swing.JLabel FilmTitle6;
    private javax.swing.JLabel FilmTitle60;
    private javax.swing.JLabel FilmTitle61;
    private javax.swing.JLabel FilmTitle62;
    private javax.swing.JLabel FilmTitle63;
    private javax.swing.JLabel FilmTitle64;
    private javax.swing.JLabel FilmTitle65;
    private javax.swing.JLabel FilmTitle66;
    private javax.swing.JLabel FilmTitle67;
    private javax.swing.JLabel FilmTitle7;
    private javax.swing.JLabel FilmTitle8;
    private javax.swing.JLabel FilmTitle9;
    private javax.swing.JLabel LblLoggedPic1;
    private javax.swing.JLabel LblLoggedPic2;
    private javax.swing.JLabel LblLoggedPic3;
    private javax.swing.JLabel LblLoggedPic4;
    private javax.swing.JLabel LblLoggedPic5;
    private javax.swing.JLabel LblTitlePic1;
    private javax.swing.JLabel LblTitlePic2;
    private javax.swing.JLabel LblTitlePic3;
    private javax.swing.JLabel LblTitlePic4;
    private javax.swing.JLabel LblTitlePic5;
    private javax.swing.JButton LogOutBttn1;
    private javax.swing.JButton adminCreateBttn;
    private javax.swing.JButton adminCreatePersonBttn;
    private javax.swing.JButton adminCreateProductionBttn;
    private javax.swing.JButton adminEditPersonBttn;
    private javax.swing.JButton adminEditProductionBttn;
    private javax.swing.JButton adminEditUserBttn;
    private javax.swing.JPanel adminGUI;
    private javax.swing.JButton adminPanelBttn;
    private javax.swing.JButton adminReturnBttn;
    private javax.swing.JButton adminStatisticsBttn;
    private javax.swing.JLabel adminUsernameTag;
    private javax.swing.JButton editProductionBttn;
    private javax.swing.JLabel f1s1;
    private javax.swing.JLabel f1s10;
    private javax.swing.JLabel f1s11;
    private javax.swing.JLabel f1s12;
    private javax.swing.JLabel f1s13;
    private javax.swing.JLabel f1s14;
    private javax.swing.JLabel f1s15;
    private javax.swing.JLabel f1s16;
    private javax.swing.JLabel f1s17;
    private javax.swing.JLabel f1s18;
    private javax.swing.JLabel f1s19;
    private javax.swing.JLabel f1s2;
    private javax.swing.JLabel f1s20;
    private javax.swing.JLabel f1s21;
    private javax.swing.JLabel f1s22;
    private javax.swing.JLabel f1s23;
    private javax.swing.JLabel f1s24;
    private javax.swing.JLabel f1s25;
    private javax.swing.JLabel f1s26;
    private javax.swing.JLabel f1s27;
    private javax.swing.JLabel f1s28;
    private javax.swing.JLabel f1s29;
    private javax.swing.JLabel f1s3;
    private javax.swing.JLabel f1s30;
    private javax.swing.JLabel f1s4;
    private javax.swing.JLabel f1s5;
    private javax.swing.JLabel f1s6;
    private javax.swing.JLabel f1s7;
    private javax.swing.JLabel f1s8;
    private javax.swing.JLabel f1s9;
    private javax.swing.JLabel f2s10;
    private javax.swing.JLabel f2s11;
    private javax.swing.JLabel f2s12;
    private javax.swing.JLabel f2s13;
    private javax.swing.JLabel f2s14;
    private javax.swing.JLabel f2s15;
    private javax.swing.JLabel f2s16;
    private javax.swing.JLabel f2s17;
    private javax.swing.JLabel f2s18;
    private javax.swing.JLabel f2s19;
    private javax.swing.JLabel f2s20;
    private javax.swing.JLabel f2s21;
    private javax.swing.JLabel f2s22;
    private javax.swing.JLabel f2s23;
    private javax.swing.JLabel f2s4;
    private javax.swing.JLabel f2s5;
    private javax.swing.JLabel f2s6;
    private javax.swing.JLabel f2s7;
    private javax.swing.JLabel f2s8;
    private javax.swing.JLabel f2s9;
    private javax.swing.JPanel filmPic;
    private javax.swing.JLabel filmPic1;
    private javax.swing.JPanel filmPic10;
    private javax.swing.JPanel filmPic11;
    private javax.swing.JPanel filmPic12;
    private javax.swing.JPanel filmPic13;
    private javax.swing.JPanel filmPic14;
    private javax.swing.JPanel filmPic15;
    private javax.swing.JPanel filmPic16;
    private javax.swing.JPanel filmPic18;
    private javax.swing.JPanel filmPic19;
    private javax.swing.JPanel filmPic2;
    private javax.swing.JPanel filmPic3;
    private javax.swing.JPanel filmPic4;
    private javax.swing.JPanel filmPic5;
    private javax.swing.JPanel filmPic6;
    private javax.swing.JPanel filmPic7;
    private javax.swing.JPanel filmPic8;
    private javax.swing.JPanel filmPic9;
    private javax.swing.JCheckBox filterChB5;
    private javax.swing.JCheckBox filterChB6;
    private javax.swing.JCheckBox filterChB7;
    private javax.swing.JCheckBox filterChB8;
    private javax.swing.JCheckBox filterChB9;
    private javax.swing.JFrame imageChooser;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSpinner jSpinner7;
    private javax.swing.JSpinner jSpinner8;
    private javax.swing.JSpinner jSpinner9;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextArea jTextArea3;
    private javax.swing.JTextArea jTextArea4;
    private javax.swing.JTextArea jTextArea5;
    private javax.swing.JTextArea jTextArea6;
    private javax.swing.JTextArea jTextArea7;
    private javax.swing.JPanel loggedMain;
    private javax.swing.JButton mainLogin;
    private javax.swing.JButton mainLogin1;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JLabel mainPanelTitle1;
    private javax.swing.JLabel mainPanelTitle2;
    private javax.swing.JLabel mainPanelTitle4;
    private javax.swing.JTextField mainSearch;
    private javax.swing.JTextField mainSearch1;
    private javax.swing.JTextField mainSearch2;
    private javax.swing.JButton mainSearchButton;
    private javax.swing.JButton mainSearchButton1;
    private javax.swing.JButton mainSearchButton2;
    private javax.swing.JLabel mainUserTag;
    private javax.swing.JPanel menuBar;
    private javax.swing.JPanel menuBar1;
    private javax.swing.JPanel menuBar2;
    private javax.swing.JPanel menuBar3;
    private javax.swing.JPanel menuBar4;
    private javax.swing.JPanel menuBar5;
    private javax.swing.JPanel menuBar6;
    private javax.swing.JPanel menuBar7;
    private javax.swing.JPanel menuBar8;
    private javax.swing.JPanel menuBar9;
    private javax.swing.JPanel personEditor;
    private javax.swing.JButton personEditorAddP;
    private javax.swing.JButton personEditorAddP1;
    private javax.swing.JButton personEditorAddP2;
    private javax.swing.JButton personEditorAddRel;
    private javax.swing.JButton personEditorAddRel1;
    private javax.swing.JButton personEditorAddRel3;
    private javax.swing.JButton personEditorAddTrivia;
    private javax.swing.JButton personEditorAddTrivia1;
    private javax.swing.JButton personEditorAddTrivia3;
    private javax.swing.JTextArea personEditorAddress;
    private javax.swing.JTextArea personEditorBio;
    private javax.swing.JFormattedTextField personEditorBirthdate;
    private javax.swing.JComboBox<String> personEditorBirthplaceCB;
    private javax.swing.JButton personEditorClearBtn;
    private javax.swing.JButton personEditorClearBtn1;
    private javax.swing.JButton personEditorClearBtn2;
    private javax.swing.JButton personEditorClearPicBtn;
    private javax.swing.JButton personEditorClearPicBtn1;
    private javax.swing.JComboBox<String> personEditorDistrictCB;
    private javax.swing.JComboBox<String> personEditorGenderCB;
    private javax.swing.JComboBox<String> personEditorGenderCB4;
    private javax.swing.JTextField personEditorHeight;
    private javax.swing.JTextField personEditorMidName;
    private javax.swing.JTextField personEditorName;
    private javax.swing.JComboBox<String> personEditorNationCB;
    private javax.swing.JComboBox<String> personEditorRegionCB;
    private javax.swing.JButton personEditorReturnBtn;
    private javax.swing.JButton personEditorReturnBtn1;
    private javax.swing.JButton personEditorReturnBtn2;
    private javax.swing.JTextField personEditorSecondSurname;
    private javax.swing.JButton personEditorSelectBtn;
    private javax.swing.JButton personEditorSelectBtn1;
    private javax.swing.JButton personEditorSubmitBtn;
    private javax.swing.JButton personEditorSubmitBtn1;
    private javax.swing.JButton personEditorSubmitBtn2;
    private javax.swing.JTextField personEditorSurname;
    private javax.swing.JLabel personEditorUsernameTag;
    private javax.swing.JLabel personEditorUsernameTag1;
    private javax.swing.JLabel personEditorUsernameTag2;
    private javax.swing.JLabel personEditorUsernameTag3;
    private javax.swing.JLabel personEditorUsernameTag4;
    private javax.swing.JFrame personPopUp;
    private javax.swing.JLabel personVCategoryLbl;
    private javax.swing.JButton personVEditBttn;
    private javax.swing.JLabel personVGenreLbl;
    private javax.swing.JLabel personVNameLbl;
    private javax.swing.JLabel personVPicLbl;
    private javax.swing.JScrollPane personVProductionsScroll;
    private javax.swing.JLabel personVReleaseLbl;
    private javax.swing.JScrollPane personVRelsScroll;
    private javax.swing.JPanel personVisualiser;
    private javax.swing.JLabel pfpLbl;
    private javax.swing.JLabel pfpLbl12;
    private javax.swing.JList<String> popUpList;
    private javax.swing.JComboBox<String> popUpRoleCB;
    private javax.swing.JButton popUpSearchBttn;
    private javax.swing.JTextField popUpSearchTxt;
    private javax.swing.JButton popUpSelectBttn;
    private javax.swing.JLabel prodVCategoryLbl;
    private javax.swing.JLabel prodVGenreLbl;
    private javax.swing.JLabel prodVPicLbl;
    private javax.swing.JLabel prodVPlaytime;
    private javax.swing.JLabel prodVRelease;
    private javax.swing.JButton prodVReturnBtn;
    private javax.swing.JButton prodVReturnBtn1;
    private javax.swing.JLabel prodVTitleLbl;
    private javax.swing.JPanel productionEditor;
    private javax.swing.JComboBox<String> productionEditorCategoryCB;
    private javax.swing.JComboBox<String> productionEditorGenreCB;
    private javax.swing.JButton productionEditorLinkTrailer;
    private javax.swing.JTextField productionEditorName;
    private javax.swing.JTextField productionEditorPlaytime;
    private javax.swing.JTextField productionEditorRelease;
    private javax.swing.JTextArea productionEditorStoryline;
    private javax.swing.JTextArea productionEditorStoryline1;
    private javax.swing.JPanel productionVisualiser;
    private javax.swing.JPanel resultPanel;
    private javax.swing.JPanel resultPanel1;
    private javax.swing.JPanel resultPanel2;
    private javax.swing.JPanel resultPanel3;
    private javax.swing.JPanel resultPanel4;
    private javax.swing.JPanel resultPanel5;
    private javax.swing.JButton return2main;
    private javax.swing.JButton return2main1;
    private javax.swing.JPanel reviewWindow;
    private javax.swing.JPanel searchResults;
    private javax.swing.JButton seeProductionBttn;
    private javax.swing.JSpinner starsSpinner;
    private javax.swing.JPanel userEditor;
    private javax.swing.JTextArea userEditorAddress;
    private javax.swing.JFormattedTextField userEditorBirthdate;
    private javax.swing.JButton userEditorClearBtn;
    private javax.swing.JButton userEditorClearPicBtn;
    private javax.swing.JComboBox<String> userEditorCountryCB;
    private javax.swing.JComboBox<String> userEditorDistrictCB;
    private javax.swing.JTextField userEditorEmail;
    private javax.swing.JComboBox<String> userEditorGenderCB;
    private javax.swing.JTextField userEditorIdNum;
    private javax.swing.JComboBox<String> userEditorIdTypeCB;
    private javax.swing.JTextField userEditorName;
    private javax.swing.JComboBox<String> userEditorNationalityCB;
    private javax.swing.JPasswordField userEditorPassword;
    private javax.swing.JPasswordField userEditorPasswordConf;
    private javax.swing.JTextField userEditorPhone;
    private javax.swing.JComboBox<String> userEditorRegionCB;
    private javax.swing.JButton userEditorReturnBtn;
    private javax.swing.JTextField userEditorSecondName;
    private javax.swing.JTextField userEditorSecondSurname;
    private javax.swing.JButton userEditorSelectPicBtn;
    private javax.swing.JButton userEditorSubmitBtn;
    private javax.swing.JTextField userEditorSurname;
    private javax.swing.JTextField userEditorUsername;
    private javax.swing.JLabel userEditorUsernameTag;
    // End of variables declaration//GEN-END:variables
}
