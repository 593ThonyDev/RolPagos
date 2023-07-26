package com.pagos.interfaces;

import com.pagos.model.Detalle;
import java.util.List;

public interface crudDetalle {

    List<Detalle> getAll();

    Detalle getById(int id);

    String add(Detalle detalle);

    String updateById(Detalle detalle);

    String deleteById(int id);
}
