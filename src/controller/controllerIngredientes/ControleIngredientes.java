package controller.controllerIngredientes;

import java.sql.SQLException;
import java.util.ArrayList;

import Dao.Ingredientejbdc;
import Dao.InterfaceIngredienteDAO;

import model.Ingrediente;
import model.Interfaces.*;

public class ControleIngredientes {

    // InterfaceIngrediente id = new IngredientesDados();

    InterfaceIngredienteDAO id = new Ingredientejbdc();

    public void cadastrarIngredientes(String nome, String categoria) {
        Ingrediente I = new Ingrediente(nome, categoria);
        id.cadastrarIngrediente(I);
        id.salvarQuantidadeIngredientes();
    }

    public ArrayList<Ingrediente> listarIngredientes() {

        return id.listarIngredientes();

    }

    public String imprimir() {
        String res = "";
        ArrayList<Ingrediente> disc;

        disc = listarIngredientes();

        for (int i = 0; i < disc.size(); i++) {
            res += disc.get(i).imprimir() + "\n---------\n";
        }
        return res;
    }
}
