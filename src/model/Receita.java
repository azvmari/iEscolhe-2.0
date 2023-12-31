package model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.util.ArrayList;

import Dao.IngredienteReceitajdbc;
import Dao.Ingredientejbdc;
import controller.ControleAvaliacao;

public class Receita implements Serializable {
    // Atributos
    private String nome;
    private int identificador;
    private int modoPreparo;
    private int avaliacao;
    private String fonte;
    private boolean favorito;

    // Construtor

    public Receita(String nome, int modoPreparo) {
        this.nome = nome;
        this.identificador = 1;
        this.modoPreparo = modoPreparo;
        this.fonte = "Tudo Gostoso";
        this.favorito = false;
    }

    // Metodos

    public double mediaAvaliacao() throws SQLException {
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

    // Getter & Setter
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdentificador() {
        return identificador;
    }

    public int getModoPreparo() {
        return modoPreparo;
    }

    public void setModoPreparo(int modoPreparo) {
        this.modoPreparo = modoPreparo;
    }

    public String getFonte() {
        return fonte;
    }

    public void setFonte(String fonte) {
        this.fonte = fonte;
    }

    public int getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(int avaliacao) {
        this.avaliacao = avaliacao;
    }

    public boolean isFavorito() {
        return favorito;
    }

    public void setFavorito(boolean favorito) {
        this.favorito = favorito;
    }

    // IngredienteReceitaDados ird = new IngredienteReceitaDados();

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

    public ArrayList<String> getIngredientes() {
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

    // IngredientesDados ii = new IngredientesDados();

    Ingredientejbdc ii = new Ingredientejbdc();

}