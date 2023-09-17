package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controller.ControllerMelhoresReceitas;

import model.IngredienteReceita;

public class MelhoresReceitasjdbc implements InterfaceMelhoresReceitas {

    @Override
    public ArrayList<ReceitaDTO> listarMelhoresReceitas() {

        String sql = "SELECT r.id AS id , r.nome AS nome, m.descricao AS descricao, AVG(a.avaliacao) as media_avaliacao "
                +
                " FROM receita AS r" +
                " INNER JOIN (" +
                " SELECT idReceita" +
                " FROM avaliacaousuario" +
                " GROUP BY idReceita" +
                " ORDER BY AVG(avaliacao) DESC" +
                " LIMIT 10" +
                ") AS top_receitas ON r.id = top_receitas.idReceita" +
                " INNER JOIN mododepreparo AS m ON r.id_mododepreparo = m.id" +
                " LEFT JOIN avaliacaousuario AS a ON r.id = a.idReceita" +
                " GROUP BY r.id, r.nome, m.descricao" +
                " ORDER BY media_avaliacao DESC; ";
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
            System.out.println("Erro ao listas Melhores Receitas");
        }

        return receitas;

    }

}
