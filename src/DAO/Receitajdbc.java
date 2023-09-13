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
        String sql = "SELECT COUNT(*) AS quantidade_itens\r\n" +
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
        String sql = "SELECT r.id AS idReceita, r.nome AS nome, " +
                " m.descricao from receita AS r " +
                "INNER JOIN mododepreparo AS m  ON " +
                "r.id_mododepreparo = m.id ";
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
                    r.setIdentificador(rs.getInt("id"));
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
}