

import java.util.List;
import model.*;
import java.sql.*;
import java.util.*;
public class Usuariojdbc implements UsuarioDao{

    @Override
    public List<Usuario> getAllUsuarios() {
        String sql = "select * from cidade";
        PreparedStatement pst;
        Connection conexao;
        ResultSet rs;
        List<Usuario> usuarios = null;
        try {
            conexao = new ConnectionFactory ().getConnection();
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs != null) {
                usuarios = new ArrayList<Usuario>();
                while (rs.next()) {
                    Usuario c = new Usuario();
                    c.setUsuario(rs.getString("usuario"));
                    c.setNome(rs.getString("nome"));
                    c.setSenha(rs.getString("senha"));
                    usuarios.add(c);
                }
            }
            rs.close();
            pst.close();
            conexao.close();
        } catch (SQLException ex) {
            System.out.println("Erro ao listar usuarios");
        }
        return usuarios;
       
    }

    @Override
    public void createUsuario(Usuario usuario) {
        String sql = "insert into usuario (nome, usuario, senha) values (?, ?, ?)";
        PreparedStatement pst;
        Connection conexao;
        try{
            conexao = new ConnectionFactory().getConnection();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, usuario.getNome().toUpperCase());
            pst.setString(2, usuario.getUsuario().toUpperCase());
            pst.setString(3, usuario.getSenha().toUpperCase());
            pst.execute();
            pst.close();
        } catch (SQLException ex) {
            System.out.println("Erro ao inserir o usuario");
        }
    }

    @Override
    public Usuario readUsuario(long id) {
        String sql = "select * from usuario where nome = ?";
        PreparedStatement pst;
        Connection conexao;
        ResultSet rs;
        Usuario usuario = null;
        try {
            conexao = new ConnectionFactory().getConnection();
            pst = conexao.prepareStatement(sql);
            pst.setLong(3, id);
            rs = pst.executeQuery();
            if (rs != null){
                usuario = new Usuario();
                usuario.setNome(rs.getString("nome"));
                usuario.setUsuario(rs.getString("usuario"));
                usuario.setSenha(rs.getString("senha"));
            }
            rs.close();
            pst.close();
        } catch (SQLException ex) {
            System.out.println("Erro ao listar usuario");
        }
        return usuario;
    }

    @Override
    public void updateUsuario(Usuario usuario) {
        String sql = "update usuario set nome = ?, usuario = ?, senha = ?, where nome = ?";
        PreparedStatement pst;
        Connection conexao;
        try {
            conexao = new ConnectionFactory().getConnection();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, usuario.getNome().toUpperCase());
            pst.setString(2, usuario.getUsuario().toUpperCase());
            pst.setString(3, usuario.getSenha().toUpperCase());
            pst.execute();
            pst.close();
        } catch (SQLException ex) {
            System.out.println("Erro ao atualizar usuario");
        }
    }

    @Override
    public void deleteUsuario(Usuario Usuario) {
        String sql = "delete from usuario where nome = ?";
        PreparedStatement pst;
        Connection conexao;
        try {
            conexao = new ConnectionFactory().getConnection();
            pst = conexao.prepareStatement(sql);
            pst.setString(1, Usuario.getNome());
            pst.execute();
            pst.close();
        } catch (SQLException ex) {
            System.out.println("Erro ao deletar usuario");
        }
    }
    
}
