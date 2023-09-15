package controller;

import java.sql.SQLException;
import java.util.ArrayList;

import Dao.Avaliacaojdbc;
import data.AvaliacaoDados;
import model.Avaliacao;
import model.Interfaces.InterfaceAvaliacao;

public class ControleAvaliacao {

    // InterfaceAvaliacao ia = new AvaliacaoDados();
    Dao.InterfaceAvaliacao ia = new Avaliacaojdbc();

    public void cadastrarAvaliacao(int idUsuario, int avaliacao, int idReceita) {
        Avaliacao UA = new Avaliacao(idUsuario, avaliacao, idReceita);
        ia.cadastrarAvaliacao(UA);
    }

    public ArrayList<Avaliacao> listarAvaliacoes() throws SQLException {
        return ia.listarAvaliacoes();
    }

    public int quantidadeAvaliacoes() throws SQLException {
        return listarAvaliacoes().size();
    }

    public int somaAvaliacoes() throws SQLException {
        int soma = 0;
        for (Avaliacao a : listarAvaliacoes()) {
            soma += a.getAvaliacao();
        }
        return soma;
    }

}
