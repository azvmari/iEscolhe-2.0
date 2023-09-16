package Dao;

import java.util.ArrayList;

import model.Favorito;

public interface InterfaceFavorito {
    void cadastrarFavorito(int idUsuario, int idReceita);

    void removerFavorito(int idUsuario, int idReceita);

    ArrayList<Favorito> listarFavoritos();

    ArrayList<Integer> listarReceitasFavoritas(int idUsuario);

}