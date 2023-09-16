package controller.controllerTelas;

import model.Principal;
import model.Usuario;
import java.net.URL;
import java.util.ResourceBundle;
import data.UsuarioDados;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class TelaAlterarSenhaController implements Initializable {

  @FXML
  private Label alertaSenhaIncorreta, alertaSenhaForaDoPadrao, alertaSenhasNaoCorrespondem, alerta;
  @FXML
  private TextField campoSenhaAtual, campoNovaSenha, campoConfirmaSenha;

  @FXML
  public void enter(KeyEvent event) {
    // if (event.getCode().equals(KeyCode.ENTER))
    // botaoFinalizarCadastro();
  }

  @FXML
  public void botaoCancelarAlteracao() {
    campoSenhaAtual.clear();
    campoNovaSenha.clear();
    campoConfirmaSenha.clear();
  }

  @FXML
  public void botaoConfirmarAlteracao() {
    if (campoSenhaAtual.equals("") || campoNovaSenha.equals("") || campoConfirmaSenha.equals("")) {
      alerta.setText("Campos não podem ficar em branco");
      return;
    }

  }

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

  }

}