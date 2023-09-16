package controller.controllerTelas;

import model.Avaliacao;
import model.Favorito;
import model.Principal;
import model.Receita;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Dao.Avaliacaojdbc;
import Dao.Favoritojdbc;
import Dao.ReceitaDTO;
import Dao.Usuariojdbc;
import controller.ControleAvaliacao;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TelaReceitaEscolhidaController implements Initializable {

    public static ReceitaDTO receitaEscolhida = null;

    Image imagemEstrelaVazia = new Image(getClass().getResourceAsStream("/view/images/icon-estrela-vazia.png"));
    Image imagemEstrelaPreenchida = new Image(getClass().getResourceAsStream("/view/images/icon-estrela.png"));
    Image imagemCoracaoVazio = new Image(getClass().getResourceAsStream("/view/images/icon-favoritos-amarelo.png"));
    Image imagemCoracaoPreenchido = new Image(
            getClass().getResourceAsStream("/view/images/icon-favoritos-amarelo-preenchido.png"));

    @FXML
    private ImageView favoritar, estrela1, estrela2, estrela3, estrela4, estrela5;

    @FXML
    private Label nomeReceita, avaliacao, fonte, modoDePreparo, mensagemFavoritos, avaliacaoEnviada;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private Button avaliar, b1estrela, b2estrela, b3estrela, b4estrela, b5estrela;

    private boolean favoritado = false;

    private boolean avaliado = false;

    private int nota = 0;

    @FXML
    public void botaoTelaPrincipal() {
        try {
            Principal.root = FXMLLoader.load(getClass().getResource("/view/TelaMenu.fxml"));
            Scene scene = new Scene(Principal.root);
            Principal.palco.setScene(scene);
        } catch (Exception e) {
        }
    }

    public void limparEstrelas() {
        estrela1.setImage(imagemEstrelaVazia);
        estrela2.setImage(imagemEstrelaVazia);
        estrela3.setImage(imagemEstrelaVazia);
        estrela4.setImage(imagemEstrelaVazia);
        estrela5.setImage(imagemEstrelaVazia);
        nota = 0;
    }

    @FXML
    public void botaoFavoritar() {
        // FavoritoDados fd = new FavoritoDados();

        Favoritojdbc fd = new Favoritojdbc();

        if (favoritado) {
            favoritar.setImage(imagemCoracaoVazio);
            mensagemFavoritos.setText("");
            fd.removerFavorito(Usuariojdbc.usuarioLogado.getIdUsuario(), receitaEscolhida.getIdentificador());
            System.out.println("removido");
        } else {
            favoritar.setImage(imagemCoracaoPreenchido);
            fd.cadastrarFavorito(Usuariojdbc.usuarioLogado.getIdUsuario(), receitaEscolhida.getIdentificador());
            mensagemFavoritos.setText("Receita adicionada às favoritas!");
        }
        favoritado = !favoritado;
    }

    @FXML
    public void botao1estrela() {
        if (!avaliado) {
            limparEstrelas();
            estrela1.setImage(imagemEstrelaPreenchida);
            nota = 1;
        }
    }

    @FXML
    public void botao2estrela() {
        if (!avaliado) {
            limparEstrelas();
            estrela1.setImage(imagemEstrelaPreenchida);
            estrela2.setImage(imagemEstrelaPreenchida);
            nota = 2;
        }
    }

    @FXML
    public void botao3estrela() {
        if (!avaliado) {
            limparEstrelas();
            estrela1.setImage(imagemEstrelaPreenchida);
            estrela2.setImage(imagemEstrelaPreenchida);
            estrela3.setImage(imagemEstrelaPreenchida);
            nota = 3;
        }
    }

    @FXML
    public void botao4estrela() {
        if (!avaliado) {
            limparEstrelas();
            estrela1.setImage(imagemEstrelaPreenchida);
            estrela2.setImage(imagemEstrelaPreenchida);
            estrela3.setImage(imagemEstrelaPreenchida);
            estrela4.setImage(imagemEstrelaPreenchida);
            nota = 4;
        }
    }

    @FXML
    public void botao5estrela() {
        if (!avaliado) {
            estrela1.setImage(imagemEstrelaPreenchida);
            estrela2.setImage(imagemEstrelaPreenchida);
            estrela3.setImage(imagemEstrelaPreenchida);
            estrela4.setImage(imagemEstrelaPreenchida);
            estrela5.setImage(imagemEstrelaPreenchida);
            nota = 5;
        }
    }

    @FXML
    public void botaoVoltarParaReceitas() {
        try {
            Principal.root = FXMLLoader.load(getClass().getResource("/view/" + Principal.telaAnterior + ".fxml"));
            Scene scene = new Scene(Principal.root);
            Principal.palco.setScene(scene);
        } catch (Exception e) {
        }
    }

    @FXML
    public void botaoEnviarAvaliacao() {
        // fazer coisa aqui!
        ControleAvaliacao ca = new ControleAvaliacao();
        ca.cadastrarAvaliacao(Usuariojdbc.usuarioLogado.getIdUsuario(), nota, receitaEscolhida.getIdentificador());
        avaliacaoEnviada.setText("Receita avaliada!");
        avaliar.setVisible(false);
        b1estrela.setDisable(true);
        b2estrela.setDisable(true);
        b3estrela.setDisable(true);
        b4estrela.setDisable(true);
        b5estrela.setDisable(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            preencherReceita();
            verificarFavoritado();
            verificarAvaliacao();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void preencherReceita() throws SQLException {
        if (receitaEscolhida != null) {
            nomeReceita.setText("→ " + receitaEscolhida.getNome() + ":");
            avaliacao.setText(receitaEscolhida.mediaAvaliacao() + " ★");

            String modoDePreparoTexto = "";
            modoDePreparoTexto += "INGREDIENTES:";
            for (String ingrediente : receitaEscolhida.getIngredientes()) {
                modoDePreparoTexto += "\n• " + ingrediente;
            }
            modoDePreparoTexto += "\n\nMODO DE PREPARO:\n";
            modoDePreparoTexto += receitaEscolhida.descricao + "\n\n";
            modoDePreparo.setText(modoDePreparoTexto);
            fonte.setText("Fonte: " + receitaEscolhida.getFonte());
        }
    }

    private void verificarFavoritado() {
        Favoritojdbc fd = new Favoritojdbc();
        for (Favorito f : fd.listarFavoritos()) {
            if (f.getIdUsuario() == Usuariojdbc.usuarioLogado.getIdUsuario()
                    && f.getIdReceita() == receitaEscolhida.getIdentificador()) {
                favoritado = true;
                favoritar.setImage(imagemCoracaoPreenchido);
                break;
            }
        }
    }

    private void verificarAvaliacao() {
        // AvaliacaoDados ad = new AvaliacaoDados();
        Avaliacaojdbc ad = new Avaliacaojdbc();
        for (Avaliacao a : ad.listarAvaliacoes()) {
            if (a.getIdUsuario() == Usuariojdbc.usuarioLogado.getIdUsuario()
                    && a.getIdReceita() == receitaEscolhida.getIdentificador()) {
                avaliar.setVisible(false);
                avaliacaoEnviada.setText("Receita avaliada!");

                switch (a.getAvaliacao()) {
                    case 1: {
                        botao1estrela();
                        break;
                    }
                    case 2: {
                        botao2estrela();
                        break;
                    }
                    case 3: {
                        botao3estrela();
                        break;
                    }
                    case 4: {
                        botao4estrela();
                        break;
                    }
                    case 5: {
                        botao5estrela();
                        break;
                    }
                }
                avaliado = true;
            }
        }
    }
}
