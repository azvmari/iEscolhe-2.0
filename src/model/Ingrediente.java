package model;

import java.io.Serializable;

public class Ingrediente implements Serializable {
    private String nome;
    private int identificador;
    private String categoria;

    public Ingrediente(String nome, String categoria, int identificador) {
        this.nome = nome;
        this.identificador = identificador;
        this.categoria = categoria;
    }

    public Ingrediente(String nome, String categoria) {
        this.nome = nome;
        this.categoria = categoria;
    }

    public Ingrediente() {

    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getCategoria() {
        return categoria;
    }

    public String imprimir() {
        return "Nome: " + this.getNome() + " Id: " + this.getIdentificador() + " Categoria: " + this.getCategoria();
    }
}
