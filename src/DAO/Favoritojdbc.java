package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Avaliacao;
import model.Favorito;
import model.Ingrediente;

public class Favoritojdbc implements InterfaceFavorito {

    @Override
    public void cadastrarFavorito(int idUsuario, int idReceita) {
        for (Favorito f : listarFavoritos()) {
            if (f.getIdReceita() == idReceita && f.getIdUsuario() == idUsuario) {
                return;
            }
        }
        String sql = "INSERT INTO receitafavoritas (idreceita, idusuario) VALUES (?, ?)";
        PreparedStatement pst;
        Connection conexao;
        try {
            conexao = new ConnectionFactory().getConnection();
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, idReceita);
            pst.setInt(2, idUsuario);
            pst.execute();
            pst.close();
            conexao.close();
        } catch (SQLException ex) {
            System.out.println("Erro ao inserir receita favorita: ");
        }
    }

    @Override
    public void removerFavorito(int idUsuario, int idReceita) {
        String sql = "delete from receitafavoritas where idreceita = ? AND idusuario = ?";
        PreparedStatement pst;
        Connection conexao;
        try {
            conexao = new ConnectionFactory().getConnection();
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, idReceita);
            pst.setInt(2, idUsuario);
            pst.execute();
            pst.close();
        } catch (SQLException ex) {
            System.out.println("Erro ao deletar receita favorita");
        }
    }

    @Override
    public ArrayList<Favorito> listarFavoritos() {
        String sql = "select * from receitafavoritas";
        PreparedStatement pst;
        Connection conexao;
        ResultSet rs;
        ArrayList<Favorito> favoritos = null;
        try {
            conexao = new ConnectionFactory().getConnection();
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs != null) {
                favoritos = new ArrayList<Favorito>();
                while (rs.next()) {
                    Favorito f = new Favorito();
                    f.setIdReceita(rs.getInt("idreceita"));
                    f.setIdUsuario(rs.getInt("idusuario"));
                    favoritos.add(f);
                }
            }
            rs.close();
            pst.close();
            conexao.close();
        } catch (SQLException ex) {
            System.out.println("Erro ao listar favoritos");
        }

        return favoritos;

    }

    @Override
    public ArrayList<Integer> listarReceitasFavoritas(int idUsuario) {
        String sql = "select * from receitafavoritas where idusuario = ?";
        PreparedStatement pst;
        Connection conexao;
        ResultSet rs;
        ArrayList<Integer> lista = null;
        try {
            conexao = new ConnectionFactory().getConnection();
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, idUsuario);
            rs = pst.executeQuery();
            if (rs != null) {
                lista = new ArrayList<Integer>();
                while (rs.next()) {
                    int i = rs.getInt("idreceita");
                    lista.add(i);
                }
            }
            rs.close();
            pst.close();
            conexao.close();
        } catch (SQLException ex) {
            System.out.println("Erro ao listar favoritos");
        }

        return lista;
    }

    public int QuantidadeReceitasFavoritas(int idUsuario) {
        String sql = "SELECT COUNT(*) AS total FROM ingrediente";
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

}
