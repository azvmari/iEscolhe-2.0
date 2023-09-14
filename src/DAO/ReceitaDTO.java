package Dao;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.util.ArrayList;

import controller.ControleAvaliacao;
import model.Avaliacao;
import model.Ingrediente;
import model.IngredienteReceita;

public class ReceitaDTO {
    public int identificador;
    public String nome;
    public String descricao;
    public String fonte;

    public ReceitaDTO(int identificador, String nome, String descricao) {
        this.identificador = identificador;
        this.nome = nome;
        this.descricao = descricao;
        this.fonte = "Tudo Gostoso";
    }

    public ReceitaDTO() {

    }

    public String getFonte() {
        return fonte;
    }

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    IngredienteReceitajdbc ird = new IngredienteReceitajdbc();

    public ArrayList<Integer> getIngredientesId() {
        ArrayList<Integer> ingredientes = new ArrayList<>();

        for (IngredienteReceita ir : ird.listarIngredienteReceitas()) {
            if (ir.getIdReceita() == this.identificador) {
                ingredientes.add(ir.getIdIngrediente());
            }
        }

        return ingredientes;
    }

    public ArrayList<String> getIngredientes() throws SQLException {
        ArrayList<String> ingredientes = new ArrayList<>();

        for (IngredienteReceita ir : ird.listarIngredienteReceitas()) {
            if (ir.getIdReceita() == this.identificador) {
                for (Ingrediente i : ii.listarIngredientes()) {
                    if (i.getIdentificador() == ir.getIdIngrediente()) { // Aqui nao esta encontrando esse
                                                                         // identificador, tem que mudar
                        ingredientes.add(i.getNome() + " - " + ir.getQuantidade());
                    }
                    // System.out.println(i.getIdentificador());
                }
            }
        }

        return ingredientes;
    }

    public double mediaAvaliacao() {
        ControleAvaliacao ca = new ControleAvaliacao();
        double media = 0;
        int quantidadeAvaliacoes = 0;
        for (Avaliacao a : ca.listarAvaliacoes()) {
            if (a.getIdReceita() == this.identificador) { // ve se nao da pra otimizar isso.....
                media += a.getAvaliacao();
                quantidadeAvaliacoes++;
            }
        }

        double mediaReceita = media / quantidadeAvaliacoes;
        BigDecimal bd = new BigDecimal(mediaReceita).setScale(1, RoundingMode.HALF_EVEN);
        return bd.doubleValue();
    }

    Receitajdbc re = new Receitajdbc();

    public String getModoDePreparo(String nomeReceita) {

        return re.getModoDePreparo(nomeReceita);

    }
    // IngredientesDados ii = new IngredientesDados();

    Ingredientejbdc ii = new Ingredientejbdc();

}