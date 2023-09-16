package controller.controllerTelas;

import model.Principal;
import model.Receita;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Dao.Favoritojdbc;
import Dao.ReceitaDTO;
import Dao.Receitajdbc;
import Dao.Usuariojdbc;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class TelaFavoritosController implements Initializable {

    @FXML
    private VBox listaReceitas;

    @FXML
    private Label mensagemFavoritos;

    @FXML
    private ScrollPane scroll;

    @FXML
    public void botaoVoltar() {
        try {
            Principal.root = FXMLLoader.load(getClass().getResource("/view/TelaMenuDeOpcoes.fxml"));
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
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void preencherLista() throws SQLException {
        Receitajdbc rd = new Receitajdbc();
        // ReceitaDados rd = new ReceitaDados();
        Favoritojdbc fd = new Favoritojdbc();
        // FavoritoDados fd = new FavoritoDados();
        listaReceitas.getChildren().clear();

        if (rd.listarReceitas().size() == 0) {
            mensagemFavoritos.setText("Você não favoritou nenhuma receita!");
            scroll.setVbarPolicy(ScrollBarPolicy.NEVER);
        }

        for (ReceitaDTO receita : rd.listarReceitas()) {
            if (fd.listarReceitasFavoritas(Usuariojdbc.usuarioLogado.getIdUsuario())
                    .contains(receita.getIdentificador())) {
                HBox botaoReceita = new HBox();
                HBox hbox = new HBox();
                VBox vbox = new VBox();
                Label nomeReceita = new Label();
                Label avaliacao = new Label();
                Label ingredientes = new Label();

                nomeReceita.setText(receita.getNome() + " ");
                avaliacao.setText(receita.mediaAvaliacao() + " ★"); // mudei de getAvaliacao pra mediaAvaliacao

                String ingredientesTexto = "Ingredientes:\n";
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
                        Principal.telaAnterior = "TelaFavoritos";

                        Principal.root = FXMLLoader.load(getClass().getResource("/view/TelaReceitaEscolhida.fxml"));
                        Scene scene = new Scene(Principal.root);
                        Principal.palco.setScene(scene);
                    } catch (Exception e) {
                    }
                });

                hbox.getChildren().setAll(nomeReceita, avaliacao);
                vbox.getChildren().setAll(hbox, ingredientes);
                botaoReceita.getChildren().setAll(vbox);
                listaReceitas.getChildren().add(botaoReceita);
            }
        }
    }
}
