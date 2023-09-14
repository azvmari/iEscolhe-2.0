package controller;

import java.sql.SQLException;
import java.util.ArrayList;

import Dao.ReceitaDTO;
import controller.controllerReceita.ControleReceita;
import model.Receita;

public class ReceitasSelecionadas {

    static ControleReceita cr = new ControleReceita();

    public static ArrayList<Integer> ingredientesSelecionadosID = new ArrayList<>();
    static ArrayList<ReceitaDTO> receitas = cr.listarReceitas();

    public static ArrayList<ReceitaDTO> receitasPossiveis() throws SQLException {
        ArrayList<ReceitaDTO> result = new ArrayList<>();

        for (ReceitaDTO r : receitas) {
            int count = 0;
            for (int idIngrediente : r.getIngredientesId()) {
                if (ingredientesSelecionadosID.contains(idIngrediente)) {
                    count++;
                }
            }

            if (count == r.getIngredientes().size()) {
                result.add(r);
            }

        }

        return result;
    }
}
