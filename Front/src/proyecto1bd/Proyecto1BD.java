/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto1bd;

import Connect.ConnectDB;

/**
 *
 * @author Leo
 */
public class Proyecto1BD {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //new vista.mainInterface().setVisible(true);
        //ConnectDB.insertProduction(1, "Título de la producción", 120, "Sinopsis de la producción", "URL del trailer", 2024, null);
        ConnectDB.getProductionInfo("El Padrino");
    }
    
}
