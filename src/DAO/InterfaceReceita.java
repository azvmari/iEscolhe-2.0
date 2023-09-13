package Dao;

import java.util.ArrayList;

import model.Receita;

public interface InterfaceReceita {

    void cadastrarReceita(Receita R);

    int salvarQuantidadeReceitas();

    ArrayList<ReceitaDTO> listarReceitas();
}