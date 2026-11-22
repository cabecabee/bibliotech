/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author Usuario
 */
public class Conexao {
    private static final String URL = "jdbc:sqlite:biblioteca.db";

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}
