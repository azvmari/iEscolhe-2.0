package controller.controllerReceita;

import java.util.ArrayList;

import Dao.InterfaceReceita;
import Dao.ReceitaDTO;
import Dao.Receitajdbc;
import model.*;

public class ControleReceita {

    // InterfaceReceita rd = new ReceitaDados();

    InterfaceReceita rd = new Receitajdbc();

    public void cadastrarReceitas(String nome, int modoPreparo) {
        Receita R = new Receita(nome, modoPreparo);
        rd.cadastrarReceita(R);
        rd.salvarQuantidadeReceitas();
    }

    public ArrayList<ReceitaDTO> listarReceitas() {
        return rd.listarReceitas();
    }

}
