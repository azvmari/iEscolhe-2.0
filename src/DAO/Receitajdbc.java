package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Receita;

public class Receitajdbc implements InterfaceReceita {

    @Override
    public void cadastrarReceita(Receita R) {
        String sql = "INSERT INTO receita(id_mododepreparo, nome)";
        PreparedStatement pst;
        Connection conexao;
        try {
            conexao = new ConnectionFactory().getConnection();
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, R.getModoPreparo());
            pst.setString(2, R.getNome());
            pst.execute();
            pst.close();
        } catch (Exception e) {
            System.out.println("Erro ao inserir Receita");
        }
    }

    @Override
    public int salvarQuantidadeReceitas() {
        String sql = "SELECT COUNT(*) AS quantidade_itens" +
                "FROM receita;";
        PreparedStatement pst;
        Connection conexao;
        ResultSet rs;
        int quantidade_itens = 0;

        try {
            conexao = new ConnectionFactory().getConnection();
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();

            if (rs != null && rs.next()) {
                quantidade_itens = rs.getInt("quantidade_itens");
            }

            rs.close();
            pst.close();
            conexao.close();
        } catch (SQLException ex) {
            System.out.println("Erro ao contar elementos");
        }

        return quantidade_itens;
    }

    @Override
    public ArrayList<ReceitaDTO> listarReceitas() {
        String sql = " select * from Vreceita";
        PreparedStatement pst;
        Connection conexao;
        ResultSet rs;
        ArrayList<ReceitaDTO> receitas = null;
        try {
            conexao = new ConnectionFactory().getConnection();
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs != null) {
                receitas = new ArrayList<ReceitaDTO>();
                while (rs.next()) {
                    ReceitaDTO r = new ReceitaDTO();
                    r.setIdentificador(rs.getInt("idReceita"));
                    r.setNome(rs.getString("nome"));
                    r.setDescricao(rs.getString("descricao"));
                    receitas.add(r);
                }
            }
            rs.close();
            pst.close();
            conexao.close();
        } catch (SQLException e) {
            System.out.println("Erro ao listas Receitas");
        }
        return receitas;

    }

    public String getModoDePreparo(String nomeReceita) {
        String sql = "SELECT mp.descricao FROM mododepreparo AS mp INNER JOIN receita AS r ON mp.id = r.id_mododepreparo WHERE r.nome = ?";
        PreparedStatement pst;
        Connection conexao;
        ResultSet rs;
        String modoDePreparo = "";
        try {
            conexao = new ConnectionFactory().getConnection();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, nomeReceita);
            rs = pst.executeQuery();
            if (rs != null) {
                modoDePreparo = rs.getString("descricao");
            }
            rs.close();
            pst.close();
        } catch (SQLException e) {
            System.out.println("Erro ao listar modo de preparo");
        }
        return modoDePreparo;
    }
}