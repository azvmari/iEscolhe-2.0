
package Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Usuario;

public interface InterfaceUsuarioDao {

    public boolean checarUsuario(String usuario);

    public boolean checarSenha(String usuario);

    public String cadastrarUsuario(Usuario usuario);

    public String fazerLoginUsuario(String usuario, String senha);

    public ArrayList<Usuario> listarUsuarios() throws SQLException;

    public void updateUsuario(int idUsuario, String senhaNova);
}

// public Usuario readUsuario(long id);
// public void deleteUsuario(Usuario usuario);
