package controller;

import java.util.ArrayList;

import Dao.Favoritojdbc;
import Dao.InterfaceFavorito;
import model.Favorito;

public class ControleFavorito {

    // InterfaceFavorito fd = new FavoritoDados();

    InterfaceFavorito fd = new Favoritojdbc();

    public void cadastrarFavorito(int idUsuario, int idReceita) {
        fd.cadastrarFavorito(idUsuario, idReceita);
    }

    public ArrayList<Favorito> listarFavoritos() {
        return fd.listarFavoritos();
    }

    public String imprimir() {
        String res = "";
        ArrayList<Favorito> disc = listarFavoritos();
        for (int i = 0; i < disc.size(); i++) {
            res += disc.get(i).imprimir() + "\n---------\n";
        }
        return res;
    }

}
