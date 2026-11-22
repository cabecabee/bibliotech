/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.Livro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Usuario
 */

public class LivroDAO {
    public void criarTabela() throws SQLException{
        String sql = """
                     CREATE TABLE IF NOT EXISTS livros (
                     id INTEGER PRIMARY KEY,
                     titulo TEXT NOT NULL,
                     autor TEXT NOT NULL,
                     editora TEXT,
                     isbn TEXT,
                     publicacao INTEGER
                     )
                     """;
        
        try (Connection conexao = Conexao.conectar();
             PreparedStatement statement = conexao.prepareStatement(sql)) {

            statement.execute();
        }
    }
    
    public void inserir(Livro livro) throws SQLException {
        String sql = """
                     INSERT INTO livros (
                     id, titulo, autor, editora, isbn, publicacao
                     )
                     VALUES (?, ?, ?, ?, ?, ?)
                     """;
        try (Connection con = Conexao.conectar();
                
        PreparedStatement statement = con.prepareStatement(sql)) {
                statement.setInt(1, livro.getId());
                statement.setString(2, livro.getTitulo());
                statement.setString(3, livro.getAutor());
                statement.setString(4, livro.getEditora());
                statement.setString(5, livro.getIsbn());
                statement.setInt(6, livro.getPublicacao());
                statement.executeUpdate();
        }
    }
    
    public ArrayList<Livro> listar() throws SQLException {
        ArrayList<Livro> livros = new ArrayList<>();
        String sql = "SELECT * FROM livros";
        try(Connection conexao = Conexao.conectar();
            PreparedStatement statement = conexao.prepareStatement(sql);
            ResultSet rs = statement.executeQuery()){
            while(rs.next()){
                Livro livro = new Livro(rs.getInt("id"), rs.getString("titulo"), rs.getString("autor"), rs.getString("editora"), rs.getString("isbn"), rs.getInt("publicacao"));
                livros.add(livro);
            }
        }
        
        return livros;
    }
    
    public void atualizar(Livro livro, int idOriginal) throws SQLException{
        String sql = """
                     UPDATE livros
                     SET id = ?, titulo = ?, autor = ?, editora = ?, isbn = ?, publicacao = ?
                     WHERE id = ?
                     """;
        try(Connection conexao = Conexao.conectar();
            PreparedStatement statement = conexao.prepareStatement(sql)){
            
            statement.setInt(1, livro.getId());
            statement.setString(2, livro.getTitulo());
            statement.setString(3, livro.getAutor());
            statement.setString(4, livro.getEditora());
            statement.setString(5, livro.getIsbn());
            statement.setInt(6, livro.getPublicacao());
            statement.setInt(7, idOriginal);
            
            statement.executeUpdate();
        }
    }
    
    public void excluir(int id) throws SQLException{
        String sql = "DELETE FROM livros WHERE id = ?";
        try(Connection conexao = Conexao.conectar();
            PreparedStatement statement = conexao.prepareStatement(sql)){
            
            statement.setInt(1, id);
            
            statement.executeUpdate();
        }
    }
    
    public ArrayList<Livro> pesquisar(String termo, String campo) throws SQLException{

        ArrayList<Livro> livros = new ArrayList<>();

        String coluna;

        switch (campo) {
            case "Título":
                coluna = "titulo";
                break;
            case "Autor":
                coluna = "autor";
                break;
            case "ISBN":
                coluna = "isbn";
                break;
            default:
                coluna = null;
        }

        String sql;

        if (coluna != null) sql = "SELECT * FROM livros WHERE " + coluna + " LIKE ?";
        else {
            sql = """
                SELECT * FROM livros
                WHERE titulo LIKE ?
                OR autor LIKE ?
                OR isbn LIKE ?
                  """;
        }

        try (Connection conexao = Conexao.conectar();
        PreparedStatement statement = conexao.prepareStatement(sql)) {

            String busca = "%" + termo + "%";

            if (coluna != null) {
                statement.setString(1, busca);
            } else {
                statement.setString(1, busca);
                statement.setString(2, busca);
                statement.setString(3, busca);
            }

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Livro livro = new Livro(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getString("editora"),
                        rs.getString("isbn"),
                        rs.getInt("publicacao")
                    );

                    livros.add(livro);
                }
            }
        }

        return livros;
    }
}
