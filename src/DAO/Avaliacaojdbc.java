package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Avaliacao;
import model.Ingrediente;

public class Avaliacaojdbc implements InterfaceAvaliacao {

    @Override
    public void cadastrarAvaliacao(Avaliacao avaliacao) {
        String sql = "INSERT INTO AvaliacaoUsuario (idUsuario, idReceita, avaliacao) VALUES (?, ?, ?)";
        PreparedStatement pst;
        Connection conexao;

        try {
            conexao = new ConnectionFactory().getConnection();
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, avaliacao.getIdUsuario());
            pst.setInt(2, avaliacao.getIdReceita());
            pst.setInt(3, avaliacao.getAvaliacao());
            pst.execute();
            pst.close();
            conexao.close();
        } catch (SQLException ex) {
            System.out.println("Erro ao inserir avaliação: ");
        }
    }

    @Override
    public ArrayList<Avaliacao> listarAvaliacoes() {
        String sql = "select * from avaliacaousuario";
        PreparedStatement pst;
        Connection conexao;
        ResultSet rs;
        ArrayList<Avaliacao> avaliacoes = null;
        try {
            conexao = new ConnectionFactory().getConnection();
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs != null) {
                avaliacoes = new ArrayList<Avaliacao>();
                while (rs.next()) {
                    Avaliacao a = new Avaliacao();
                    a.setIdUsuario(rs.getInt("idusuario"));
                    a.setIdReceita(rs.getInt("idreceita"));
                    a.setAvaliacao(rs.getInt("avaliacao"));
                    avaliacoes.add(a);
                }
            }
            rs.close();
            pst.close();
            conexao.close();
        } catch (SQLException ex) {
            System.out.println("Erro ao listar avaliacoes");
        }

        return avaliacoes;

    }

}