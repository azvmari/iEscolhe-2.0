package controller.controllerTelas;

import model.Principal;
import model.Receita;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import controller.ReceitasSelecionadas;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class TelaReceitasCadastradasController implements Initializable {
  @FXML
  private VBox listaReceitas;

  @FXML
  private ScrollPane scroll;

  @FXML
  private MenuItem itemTodas, itemCafe, itemAlmoco, itemSobremesa;

  @FXML
  private MenuButton botaoMenu;

  @FXML
  public void botaoVoltar() {
    try {
      Principal.root = FXMLLoader.load(getClass().getResource("/view/" + Principal.telaMenuAnterior + ".fxml"));
      Scene scene = new Scene(Principal.root);
      Principal.palco.setScene(scene);
    } catch (Exception e) {
    }
  }

  @FXML
  public void filtraTodas() {
    botaoMenu.setText("TODAS");
  }

  @FXML
  public void filtraCafeDaManha() {
    botaoMenu.setText("CAFÉ DA MANHÃ");
  }

  @FXML
  public void filtraAlmocoJantar() {
    botaoMenu.setText("ALMOÇO/JANTAR");
  }

  @FXML
  public void filtraSobremesa() {
    botaoMenu.setText("SOBREMESA");
  }

  @FXML
  public void pesquisa() {

  }

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    preencherLista();
  }

  private void preencherLista() {
    listaReceitas.getChildren().clear();

    for (Receita receita : ReceitasSelecionadas.receitasPossiveis()) {
      HBox botaoReceita = new HBox();
      HBox hbox = new HBox();
      VBox vbox = new VBox();
      Label nomeReceita = new Label();
      Label avaliacao = new Label();
      Label ingredientes = new Label();

      Button editar = new Button();
      Button excluir = new Button();
      HBox botoesReceita = new HBox();

      ImageView iconeEditar = new ImageView(new Image("/view/images/icon-editar.png"));
      ImageView iconeExcluir = new ImageView(new Image("/view/images/icon-lixeira.png"));

      nomeReceita.setText(receita.getNome() + " ");
      avaliacao.setText(receita.mediaAvaliacao() + " ★");

      String ingredientesTexto = "INGREDIENTES:\n";
      for (String ingrediente : receita.getIngredientes()) {
        ingredientesTexto += "• " + ingrediente + "\n";
      }
      ingredientes.setText(ingredientesTexto);

      botaoReceita.getStyleClass().setAll("botaoReceita");
      vbox.getStyleClass().setAll("vbox");
      hbox.getStyleClass().setAll("hbox");
      editar.getStyleClass().setAll("botoesReceitas");
      excluir.getStyleClass().setAll("botoesReceitas");
      botoesReceita.setSpacing(10);
      iconeEditar.setFitHeight(28);
      iconeEditar.setFitWidth(28);
      iconeExcluir.setFitHeight(28);
      iconeExcluir.setFitWidth(28);
      nomeReceita.getStyleClass().setAll("nomeReceita");
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

      editar.setGraphic(iconeEditar);
      excluir.setGraphic(iconeExcluir);
      botoesReceita.getChildren().setAll(editar, excluir);
      hbox.getChildren().setAll(nomeReceita, botoesReceita);
      vbox.getChildren().setAll(hbox, ingredientes);
      botaoReceita.getChildren().setAll(vbox);
      listaReceitas.getChildren().add(botaoReceita);
    }
  }

}
