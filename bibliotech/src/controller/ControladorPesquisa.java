/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.LivroDAO;
import model.Livro;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Usuario
 */
public class ControladorPesquisa {
    
    private LivroDAO livroDAO = new LivroDAO();

    private JTextField jTextFieldPesquisar;
    private JComboBox<String> jComboBoxPesquisa;
    private JTable jTableResultados;

    public ControladorPesquisa(JTextField jTextFieldPesquisar, JComboBox<String> jComboBoxPesquisa, JTable jTableResultados) {
        this.jTextFieldPesquisar = jTextFieldPesquisar;
        this.jComboBoxPesquisa = jComboBoxPesquisa;
        this.jTableResultados = jTableResultados;
    }
    
    public void pesquisar() {
        String termo = jTextFieldPesquisar.getText();
        String campo = (String) jComboBoxPesquisa.getSelectedItem();

        try {
            ArrayList<Livro> livros = livroDAO.pesquisar(termo, campo);

            DefaultTableModel modelo = new DefaultTableModel(
                new Object[]{
                    "ID", "Título", "Autor",
                    "Ano", "Editora", "ISBN"
                }, 0
            ) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            for (Livro livro : livros) {
                modelo.addRow(new Object[]{
                    livro.getId(),
                    livro.getTitulo(),
                    livro.getAutor(),
                    livro.getPublicacao(),
                    livro.getEditora(),
                    livro.getIsbn()
                });
            }

            jTableResultados.setModel(modelo);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                "Erro ao pesquisar: " + e.getMessage());
        }
    }
}
