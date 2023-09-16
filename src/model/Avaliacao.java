package model;

import java.io.Serializable;

public class Avaliacao implements Serializable {

    // Atributos
    private int idusuario;
    private int avaliacao;
    private int idReceita;

    public Avaliacao() {

    }

    // Construtor
    public Avaliacao(int idusuario, int avaliacao, int idReceita) {
        this.idusuario = idusuario;
        this.avaliacao = avaliacao;
        this.idReceita = idReceita;
    }

    // Metodos

    public void adicionarAvaliacao(int avaliacao) {
        this.avaliacao += avaliacao;
    }

    // Getter & Setter

    public int getIdUsuario() {
        return idusuario;
    }

    public void setIdUsuario(int Idusuario) {
        this.idusuario = Idusuario;
    }

    public int getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(int avaliacao) {
        this.avaliacao = avaliacao;
    }

    public int getIdReceita() {
        return idReceita;
    }

    public void setIdReceita(int idReceita) {
        this.idReceita = idReceita;
    }
}
