package controller;

import java.sql.SQLException;
import java.util.ArrayList;

import Dao.InterfaceUsuarioDao;
import Dao.Usuariojdbc;
import model.*;
import model.Interfaces.*;

public class ControleUsuario {
    // InterfaceUsuario ud = new UsuarioDados();

    InterfaceUsuarioDao ud = new Usuariojdbc();

    public String cadastrarUsuario(String nome, String usuario, String senha) {
        Usuario u = new Usuario(nome, usuario, senha);
        return ud.cadastrarUsuario(u);
    }

    public String fazerLoginUsuario(String usuario, String senha) {
        return ud.fazerLoginUsuario(usuario, senha);
    }

    public ArrayList<Usuario> listarUsuarios() throws SQLException {

        return ud.listarUsuarios();
    }

}