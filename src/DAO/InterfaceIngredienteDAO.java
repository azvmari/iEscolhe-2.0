
package Dao;

import java.util.ArrayList;
import java.util.List;
import model.Ingrediente;

public interface InterfaceIngredienteDAO {
    public ArrayList<Ingrediente> listarIngredientes();

    public void cadastrarIngrediente(Ingrediente ingredientes);

    public Ingrediente readIngrediente(long id);

    public void updateIngrediente(Ingrediente ingredientes);

    public void deleteIngrediente(Ingrediente ingredientes);

    public int salvarQuantidadeIngredientes();
}