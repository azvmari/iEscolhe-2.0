
import model.*;

import java.sql.*;
import java.util.*;

public class Ingredientejbdc implements IngredienteDAO{

    @Override
    public List<Ingrediente> getAllIngredientes() {
    

        String sql = "select * from ingrediente";
        PreparedStatement pst;
        Connection conexao;
        ResultSet rs;
        List<Ingrediente> ingredientes = null;
        try {
            conexao = new ConnectionFactory ().getConnection();
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs != null) {
                ingredientes = new ArrayList<Ingrediente>();
                while (rs.next()) {
                  Ingrediente c = new Ingrediente();
                    // c.set(rs.getInt("id"));
                    c.setNome(rs.getString("nome"));
                    c.setCategoria(rs.getString("categoria"));
                    ingredientes.add(c);
                }
            }
            rs.close();
            pst.close();
            conexao.close();
        } catch (SQLException ex) {
            System.out.println("Erro ao listar ");
        }
        return ingredientes;
       
    }

    @Override
    public void createIngrediente(Ingrediente ingredientes) {
        String sql = "insert into ingredientes (nome, categoria)";
        PreparedStatement pst;
        Connection conexao;
        try{
            conexao = new ConnectionFactory().getConnection();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, ingredientes.getNome().toUpperCase());
            pst.setString(2, ingredientes.getCategoria().toUpperCase());
            pst.execute();
            pst.close();
        } catch (SQLException ex) {
            System.out.println("Erro ao inserir a ingrediente");
        }
    }

    @Override
public Ingrediente readIngrediente(long id) {
    String sql = "select * from ingredientes where id = ?";
    PreparedStatement pst;
    Connection conexao;
    ResultSet rs;
    Ingrediente ingredientes = null;
    try {
        conexao = new ConnectionFactory().getConnection();
        pst = conexao.prepareStatement(sql);
        pst.setLong(1, id);  // Corrigido o índice do parâmetro
        rs = pst.executeQuery();
        if (rs.next()) {  // Use rs.next() para mover para o primeiro resultado
            Ingrediente i = new Ingrediente();
            // i.setCategoria(rs.getInt("id"));
            i.setNome(rs.getString("nome"));
            i.setCategoria(rs.getString("categoria"));
            ingredientes = i;  // Atribua o objeto i ao ingredientes se houver resultado
        }
        rs.close();
        pst.close();
        conexao.close();  // Não esqueça de fechar a conexão
    } catch (SQLException ex) {
        System.out.println("Erro ao listar ingredientes");
        ex.printStackTrace(); // Melhorar o tratamento de exceção para depuração
    }
    return ingredientes;
}


    @Override
    public void updateIngrediente(Ingrediente ingredientes) {
        String sql = "update ingrediente set nome = ?, categoria = ?, where id = ?";
        PreparedStatement pst;
        Connection conexao;
        try {
            conexao = new ConnectionFactory().getConnection();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, ingredientes.getNome().toUpperCase());
            pst.setString(2, ingredientes.getCategoria().toUpperCase());
            pst.setLong(3, ingredientes.getIdentificador());
            pst.execute();
            pst.close();
        } catch (SQLException ex) {
            System.out.println("Erro ao atualizar ingrediente");
        }
    }

    @Override
    public void deleteIngrediente(Ingrediente ingredientes) {
        String sql = "delete from ingrediente where id = ?";
        PreparedStatement pst;
        Connection conexao;
        try {
            conexao = new ConnectionFactory().getConnection();
            pst = conexao.prepareStatement(sql);
            pst.setLong(1, ingredientes.getIdentificador());
            pst.execute();
            pst.close();
        } catch (SQLException ex) {
            System.out.println("Erro ao deletar ingredinte");
        }
    }

  

    
    

}
