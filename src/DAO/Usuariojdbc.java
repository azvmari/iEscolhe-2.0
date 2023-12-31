
package Dao;

import model.*;
import java.sql.*;
import java.util.*;
import Dao.ConnectionFactory;

public class Usuariojdbc implements InterfaceUsuarioDao {

    public static Usuario usuarioLogado = null;
    ConnectionFactory connectionFactory = new ConnectionFactory();

    public boolean checarUsuario(String usuario) {
        for (Usuario u : listarUsuarios()) {
            if (u.getUsuario().equals(usuario)) {
                return false;
            }
        }
        return true;
    }

    public boolean checarSenha(String senha) {
        boolean checagem_senha = true;
        if (senha.length() < 6) {
            checagem_senha = false;
        }
        return checagem_senha;

    }

    @Override
    public String cadastrarUsuario(Usuario usuario) {
        String resultado = "";

        if (checarUsuario(usuario.getUsuario()) == true && checarSenha(usuario.getSenha()) == true) {

            String sql = "insert into usuario (nome, usuario_nome, senha) values (?, ?, ?)";

            PreparedStatement pst;
            Connection conexao;
            try {
                conexao = connectionFactory.getConnection();
                pst = conexao.prepareStatement(sql);
                pst.setString(1, usuario.getNome());
                pst.setString(2, usuario.getUsuario());
                pst.setString(3, usuario.getSenha());
                pst.execute();
                pst.close();
            } catch (SQLException ex) {
                System.out.println("Erro ao inserir o usuario");
            }
            usuarioLogado = usuario;
            resultado = "";
        } else if (checarUsuario(usuario.getUsuario()) == false) {
            resultado = "Usuário já existe";
        } else if (checarSenha(usuario.getSenha()) == false) {
            resultado = "Senha deve ter pelo menos 6 caracteres";
        }

        return resultado;
    }

    public String fazerLoginUsuario(String usuario, String senha) {
        for (Usuario u : listarUsuarios()) {
            if (u.getUsuario().equals(usuario)) {
                if (u.getSenha().equals(senha)) {
                    usuarioLogado = u;
                    return "";
                } else
                    return "Senha incorreta";
            }
        }
        return "Usuário não encontrado";
    }

    public ArrayList<Usuario> listarUsuarios() {
        String sql = "select  * from vUsu";
        ArrayList<Usuario> usuarios = new ArrayList<>();

        // new ConnectionFactory();
        try (Connection conexao = connectionFactory.getConnection();
                PreparedStatement pst = conexao.prepareStatement(sql);
                ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Usuario c = new Usuario();
                c.setIdUsuario(rs.getInt("id"));
                c.setNome(rs.getString("nome"));
                c.setUsuario(rs.getString("usuario_nome"));
                c.setSenha(rs.getString("senha"));
                usuarios.add(c);
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao listar usuarios");
        }

        return usuarios;
    }

    @Override
    public void updateUsuario(int idUsuario, String senhaNova) {
        String sql = "update usuario set senha = ? where id = ?";
        PreparedStatement pst;
        Connection conexao;
        try {
            conexao = new ConnectionFactory().getConnection();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, senhaNova);
            pst.setInt(2, idUsuario);
            pst.execute();
            pst.close();
        } catch (SQLException ex) {
            System.out.println("Erro ao atualizar senha usuario");
        }
    }

    /*
     * @Override
     * public void deleteUsuario(Usuario Usuario) {
     * String sql = "delete from usuario where nome = ?";
     * PreparedStatement pst;
     * Connection conexao;
     * try {
     * conexao = new ConnectionFactory().getConnection();
     * pst = conexao.prepareStatement(sql);
     * pst.setString(1, Usuario.getNome());
     * pst.execute();
     * pst.close();
     * } catch (SQLException ex) {
     * System.out.println("Erro ao deletar usuario");
     * }
     * }
     */

}
