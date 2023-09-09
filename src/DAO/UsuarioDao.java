

import java.util.List;
import model.Usuario;

public interface UsuarioDao {
    public List<Usuario> getAllUsuarios();
    public void createUsuario (Usuario usuario);
    public Usuario readUsuario(long id);
    public void updateUsuario(Usuario usuario);
    public void deleteUsuario(Usuario usuario);
}