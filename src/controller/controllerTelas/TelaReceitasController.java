package controller.controllerTelas;

import model.Principal;
import model.Receita;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Dao.ReceitaDTO;
import controller.ReceitasSelecionadas;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class TelaReceitasController implements Initializable {

    @FXML
    private VBox listaReceitas;

    @FXML
    private Label mensagemReceita;

    @FXML
    private ScrollPane scroll;

    @FXML
    public void botaoVoltar() {
        try {
            Principal.root = FXMLLoader.load(getClass().getResource("/view/" + Principal.telaMenuAnterior + ".fxml"));
            Scene scene = new Scene(Principal.root);
            Principal.palco.setScene(scene);
        } catch (Exception e) {
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            preencherLista();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void preencherLista() throws SQLException {
        listaReceitas.getChildren().clear();

        if (ReceitasSelecionadas.receitasPossiveis().size() == 0) {
            mensagemReceita.setText(" Não há receitas disponíveis para esses ingredientes :(");
            scroll.setVbarPolicy(ScrollBarPolicy.NEVER);
        }

        else if (ReceitasSelecionadas.receitasPossiveis().size() == 1) {
            mensagemReceita.setText(" Você pode fazer 1 receita:");
        }

        else {
            mensagemReceita
                    .setText(" Você pode fazer " + ReceitasSelecionadas.receitasPossiveis().size() + " receitas:");
        }

        for (ReceitaDTO receita : ReceitasSelecionadas.receitasPossiveis()) {
            HBox botaoReceita = new HBox();
            HBox hbox = new HBox();
            VBox vbox = new VBox();
            Label nomeReceita = new Label();
            Label avaliacao = new Label();
            Label ingredientes = new Label();

            nomeReceita.setText(receita.getNome() + " ");
            avaliacao.setText(receita.mediaAvaliacao() + " ★"); // mudei de getAvaliacao pra mediaAvaliacao

            String ingredientesTexto = "INGREDIENTES:\n";
            for (String ingrediente : receita.getIngredientes()) {
                ingredientesTexto += "• " + ingrediente + "\n";
            }
            ingredientes.setText(ingredientesTexto);

            botaoReceita.getStyleClass().setAll("botaoReceita");
            vbox.getStyleClass().setAll("vbox");
            hbox.getStyleClass().setAll("hbox");
            nomeReceita.getStyleClass().setAll("nomeReceita");
            avaliacao.getStyleClass().setAll("avaliacao");
            ingredientes.getStyleClass().setAll("ingredientes");

            botaoReceita.setOnMouseClicked(event -> {
                try {
                    TelaReceitaEscolhidaController.receitaEscolhida = receita;
                    Principal.telaAnterior = "TelaReceitas";

                    Principal.root = FXMLLoader.load(getClass().getResource("/view/TelaReceitaEscolhida.fxml"));
                    Scene scene = new Scene(Principal.root);
                    Principal.palco.setScene(scene);
                } catch (Exception e) {
                    System.out.print(e);
                }
            });

            hbox.getChildren().setAll(nomeReceita, avaliacao);
            vbox.getChildren().setAll(hbox, ingredientes);
            botaoReceita.getChildren().setAll(vbox);
            listaReceitas.getChildren().add(botaoReceita);
        }
    }

}
