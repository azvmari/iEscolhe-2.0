


import java.util.List;
import model.Ingrediente;

public interface IngredienteDAO {
    public List<Ingrediente> getAllIngredientes();
    public void createIngrediente(Ingrediente ingredientes);
    public Ingrediente readIngrediente(long id);
    public void updateIngrediente(Ingrediente ingredientes);
    public void deleteIngrediente(Ingrediente ingredientes);
}