package Dao;

import java.util.ArrayList;

import model.Avaliacao;

public interface InterfaceAvaliacao {
    void cadastrarAvaliacao(Avaliacao avaliacao);

    ArrayList<Avaliacao> listarAvaliacoes();

}