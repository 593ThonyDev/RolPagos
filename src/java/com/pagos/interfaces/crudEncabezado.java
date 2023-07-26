package com.pagos.interfaces;

import com.pagos.model.Encabezado;
import java.util.List;

public interface crudEncabezado {

    List<Encabezado> getAll();

    Encabezado getById(int id);

    String add(Encabezado encabezado);

    String updateById(Encabezado encabezado);

    String deleteById(int id);
}
