package Dao;

import java.util.ArrayList;

import model.IngredienteReceita;

public interface InterfaceIngredienteReceita {
    void cadastrarIngredienteReceita(IngredienteReceita IR);

    int salvarQuantidadeIngredienteReceita();

    ArrayList<IngredienteReceita> listarIngredienteReceitas();
}