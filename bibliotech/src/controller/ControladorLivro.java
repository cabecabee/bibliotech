/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTextField;
import model.Livro;
import dao.LivroDAO;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class ControladorLivro {
    JTextField jTextFieldId, jTextFieldTitulo, jTextFieldAutor, jTextFieldEditora, jTextFieldIsbn, jTextFieldPublicacao;
    JButton jButtonSalvarEditar;
    JList<String> jListLivros;
    DefaultListModel defaultListModel = new DefaultListModel();
    private final LivroDAO livroDAO = new LivroDAO();
    private ArrayList<Livro> listaLivros = new ArrayList<>();
    private int idOriginal;

    public ControladorLivro(JTextField jTextFieldId, JTextField jTextFieldTitulo, JTextField jTextFieldAutor, JTextField jTextFieldEditora, JTextField jTextFieldIsbn, JTextField jTextFieldPublicacao, JButton jButtonSalvarEditar, JList<String> jListLivros) {
        this.jTextFieldId = jTextFieldId;
        this.jTextFieldTitulo = jTextFieldTitulo;
        this.jTextFieldAutor = jTextFieldAutor;
        this.jTextFieldEditora = jTextFieldEditora;
        this.jTextFieldIsbn = jTextFieldIsbn;
        this.jTextFieldPublicacao = jTextFieldPublicacao;
        this.jButtonSalvarEditar = jButtonSalvarEditar;
        this.jListLivros = jListLivros;
        try {
            livroDAO.criarTabela();
            carregarLista();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao inicializar banco de dados: " + e.getMessage());
        }
    }
    
    public void limpar(){
        jTextFieldId.setText("");
        jTextFieldTitulo.setText("");
        jTextFieldAutor.setText("");
        jTextFieldEditora.setText("");
        jTextFieldIsbn.setText("");
        jTextFieldPublicacao.setText("");
        jButtonSalvarEditar.setText("Salvar");
    }
    
    public void carregarLista() {
        try {
           listaLivros = livroDAO.listar();

           DefaultListModel<String> modelo = new DefaultListModel<>();
        
           for (Livro livro : listaLivros) {
                modelo.addElement(
                   livro.getId() + " - " +
                   livro.getTitulo() + " - " +
                   livro.getAutor() + " - " +
                   livro.getPublicacao()
               );
           }
        
            jListLivros.setModel(modelo);
        
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                "Erro ao carregar livros: " + e.getMessage());
        }
    }
    
    public void salvar() {
        try {
            int id = Integer.parseInt(jTextFieldId.getText());
            String titulo = jTextFieldTitulo.getText();
            String autor = jTextFieldAutor.getText();
            String editora = jTextFieldEditora.getText();
            String isbn = jTextFieldIsbn.getText();
            int publicacao = Integer.parseInt(jTextFieldPublicacao.getText());

            Livro l = new Livro(id, titulo, autor, editora, isbn, publicacao);

            if (jButtonSalvarEditar.getText().equalsIgnoreCase("Salvar")) {

            livroDAO.inserir(l);

        } else {
            livroDAO.atualizar(l, idOriginal);
        }

            carregarLista();
            limpar();

        } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null,
            "Verifique os campos numéricos!");

        } catch (SQLException e) {
        JOptionPane.showMessageDialog(null,
            "Erro ao salvar livro: " + e.getMessage());
        }
    }
    
    public void editar() {
        int index = jListLivros.getSelectedIndex();

        if (index >= 0) {
            Livro l = listaLivros.get(index);

            idOriginal = l.getId();

            jTextFieldId.setText(String.valueOf(l.getId()));
            jTextFieldTitulo.setText(l.getTitulo());
            jTextFieldAutor.setText(l.getAutor());
            jTextFieldEditora.setText(l.getEditora());
            jTextFieldIsbn.setText(l.getIsbn());
            jTextFieldPublicacao.setText(String.valueOf(l.getPublicacao()));

            jButtonSalvarEditar.setText("Editar");
        }
    }
    
    public void excluir() {
        int index = jListLivros.getSelectedIndex();

        if (index >= 0) {
            Livro l = listaLivros.get(index);

            try {
                livroDAO.excluir(l.getId());

                carregarLista();
                limpar();

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,
                    "Erro ao excluir livro: " + e.getMessage());
            }
        }
    }
}
