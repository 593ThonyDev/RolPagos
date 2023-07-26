package com.pagos.interfaces;

import com.pagos.model.Usuario;
import java.util.List;

public interface crudUsuario {

    List getAll();

    Usuario getById(Integer id);

    String add(Usuario Usuario);

    String updateById(Usuario Usuario);

    String deleteById(Integer id);

    //Login
    Integer existIdUsuario(String USU_Usuario);

}
