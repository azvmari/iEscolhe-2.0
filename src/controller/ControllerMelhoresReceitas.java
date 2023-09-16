package controller;

import java.sql.SQLException;
import java.util.ArrayList;

import Dao.InterfaceMelhoresReceitas;
import Dao.MelhoresReceitasjdbc;
import Dao.ReceitaDTO;

public class ControllerMelhoresReceitas {
    InterfaceMelhoresReceitas mr = new MelhoresReceitasjdbc();

    public ArrayList<ReceitaDTO> listarMelhoresReceitas() throws SQLException {
        return mr.listarMelhoresReceitas();
    }
}