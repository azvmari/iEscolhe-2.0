package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Ingrediente;
import model.IngredienteReceita;

public class IngredienteReceitajdbc implements InterfaceIngredienteReceita {

    @Override
    public void cadastrarIngredienteReceita(IngredienteReceita IR) {
        String sql = "insert into ingrediente_receita (idingrediente, idreceita, quantidade) VALUES (?, ? , ?) ";
        PreparedStatement pst;
        Connection conexao;
        try {
            conexao = new ConnectionFactory().getConnection();
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, IR.getIdIngrediente());
            pst.setInt(2, IR.getIdReceita());
            pst.setString(3, IR.getQuantidade());
            pst.execute();
            pst.close();
        } catch (SQLException ex) {
            System.out.println("Erro ao inserir a  ingrediente_receita");
        }
    }

    @Override
    public int salvarQuantidadeIngredienteReceita() {
        String sql = "SELECT COUNT(*) AS total FROM ingrediente_receita";
        PreparedStatement pst;
        Connection conexao;
        ResultSet rs;
        int total = 0;

        try {
            conexao = new ConnectionFactory().getConnection();
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();

            if (rs != null && rs.next()) {
                total = rs.getInt("total");
            }

            rs.close();
            pst.close();
            conexao.close();
        } catch (SQLException ex) {
            System.out.println("Erro ao contar elementos");
        }

        return total;
    }

    @Override
    public ArrayList<IngredienteReceita> listarIngredienteReceitas() {
        String sql = "SELECT * FROM ingrediente_receita";
        PreparedStatement pst;
        Connection conexao;
        ResultSet rs;
        ArrayList<IngredienteReceita> ingredientes_receitas = null;
        try {
            conexao = new ConnectionFactory().getConnection();
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs != null) {
                ingredientes_receitas = new ArrayList<IngredienteReceita>();
                while (rs.next()) {
                    IngredienteReceita ir = new IngredienteReceita();
                    ir.setIdIngrediente(rs.getInt("idingrediente"));
                    ir.setIdReceita(rs.getInt("idreceita"));
                    ir.setQuantidade(rs.getString("quantidade"));
                    ingredientes_receitas.add(ir);
                }
            }
            rs.close();
            pst.close();
        }

        catch (SQLException e) {
            System.out.println("Erro ao listar ingrediente_Receita");
        }
        return ingredientes_receitas;
    }

}