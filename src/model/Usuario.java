package model;

import java.io.Serializable;

public class Usuario implements Serializable {
    private int idUsuario;
    private String nome;
    private String senha;
    private String usuario;

    public Usuario(String nome, String usuario, String senha) {
        this.nome = nome;
        this.usuario = usuario;
        this.senha = senha;

    }

    public Usuario(int idUsuario, String nome, String usuario, String senha) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.usuario = usuario;
        this.senha = senha;

    }

    public Usuario() {

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String toString() {
        return "Nome: " + nome + "\n Usuario: " + usuario;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

}
