package model;

import java.io.Serializable;

public class Favorito implements Serializable {
    // Atributos
    private int idUsuario;
    private int idReceita;

    // Construtor
    public Favorito(int idUsuario, int idReceita) {
        this.idUsuario = idUsuario;
        this.idReceita = idReceita;
    }

    public Favorito() {

    }
    // Metodos

    // Getter & Setter

    public int getIdReceita() {
        return idReceita;
    }

    public void setIdReceita(int idReceita) {
        this.idReceita = idReceita;
    }

    public String imprimir() {
        return "Id Usuario: " + getIdUsuario() + "\n Id receita:" + getIdReceita();
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

}
