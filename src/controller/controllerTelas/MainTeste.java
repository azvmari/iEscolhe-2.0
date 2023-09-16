package controller.controllerTelas;

import java.sql.SQLException;

import Dao.MelhoresReceitasjdbc;
import Dao.ReceitaDTO;
import controller.ControllerMelhoresReceitas;

public class MainTeste {
    public static void main(String[] args) throws SQLException {
        chamar();

    }

    public static void chamar() throws SQLException {
        ControllerMelhoresReceitas mr = new ControllerMelhoresReceitas();
        for (ReceitaDTO receita : mr.listarMelhoresReceitas()) {
            System.out.println(receita.getNome() + " ");
            System.out.println(receita.mediaAvaliacao() + " ★"); // mudei de getAvaliacao pra mediaAvaliacao

            String ingredientesTexto = "Ingredientes:\n";
            for (String ingrediente : receita.getIngredientes()) {
                ingredientesTexto += "• " + ingrediente + "\n";
            }
            System.out.println(ingredientesTexto);
        }
    }
}