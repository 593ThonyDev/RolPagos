package com.pagos.dao;

import java.sql.*;
import java.util.List;
import com.pagos.database.Conexion;
import com.pagos.interfaces.crudDetalle;
import com.pagos.model.Detalle;
import java.util.ArrayList;

public class DetalleDao implements crudDetalle {

    // Objeto de la clase Detalle
    Detalle detalle = new Detalle();
    // Objeto para establecer la conexión a la base de datos
    Conexion cn = new Conexion();
    // Objeto para llamar a los procedimientos almacenados en la base de datos
    CallableStatement cs;
    // Objeto para mantener la conexión a la base de datos
    Connection con;
    // Objeto para almacenar el resultado de una consulta a la base de datos
    ResultSet rs;

    // Variables para los procedimientos almacenados
    // Llamada al procedimiento almacenado para obtener todos los detalles
    String getAll = "CALL spLeerDetalles()";
    // Llamada al procedimiento almacenado para obtener un detalle por su ID
    String getById = "CALL spObtenerDetallePorID(?)";
    // Llamada al procedimiento almacenado para agregar un nuevo detalle
    String add = "CALL spCrearDetalle(?,?,?,?,?,?)";
    // Llamada al procedimiento almacenado para actualizar un detalle existente
    String update = "CALL spActualizarDetalle(?,?,?,?,?,?,?)";
    // Llamada al procedimiento almacenado para eliminar un detalle por su ID
    String delete = "CALL spEliminarDetalle(?)";

    // Método para obtener todos los detalles
    @Override
    public List<Detalle> getAll() {
        // Se crea una lista para almacenar los objetos de Detalle recuperados de la base de datos
        ArrayList<Detalle> lista = new ArrayList<>();
        try {
            // Se obtiene una conexión a la base de datos utilizando el objeto de conexión cn
            con = (Connection) cn.getConexion();
            // Se crea un objeto CallableStatement para invocar el procedimiento almacenado getAll
            cs = con.prepareCall(getAll);
            // Se ejecuta la consulta y el resultado se almacena en el objeto ResultSet rs
            rs = cs.executeQuery();
            // Se itera a través de los resultados utilizando un bucle while
            while (rs.next()) {
                // Se crea un nuevo objeto Detalle para almacenar los datos de un detalle en particular
                Detalle det = new Detalle();
                // Se obtienen los valores de las columnas correspondientes en el ResultSet rs y se asignan a las propiedades del objeto det
                det.setIdDetalle(rs.getInt(1));
                det.setFkEncabezado(rs.getInt(2));
                det.setDetFecha(rs.getString(3));
                det.setDetHoras(rs.getString(4));
                det.setDetValor(rs.getDouble(5));
                det.setDetTipo(rs.getString(6));
                // Se agrega el objeto det a la lista de detalles
                lista.add(det);
            }
        } catch (SQLException ex) {
            // Si ocurre una excepción de tipo SQLException, se imprime un mensaje de error
            System.out.println("Error al listar: " + ex.getMessage());
        }
        // Se devuelve la lista que contiene todos los detalles recuperados de la base de datos
        return lista;
    }

    // Método para obtener un detalle por su ID
    @Override
    public Detalle getById(int id) {
        try {
            // Se obtiene una conexión a la base de datos utilizando el objeto de conexión cn
            con = (Connection) cn.getConexion();
            // Se crea un objeto CallableStatement para invocar el procedimiento almacenado getById
            cs = con.prepareCall(getById);
            // Se establece el parámetro ID en el procedimiento almacenado mediante el método setInt()
            cs.setInt(1, id);
            // Se ejecuta la consulta y el resultado se almacena en el objeto ResultSet rs
            rs = cs.executeQuery();
            // Se itera a través de los resultados utilizando un bucle while
            while (rs.next()) {
                // Se establecen las propiedades del objeto detalle con los valores de las columnas correspondientes en el ResultSet rs
                detalle.setIdDetalle(rs.getInt(1));
                detalle.setFkEncabezado(rs.getInt(2));
                detalle.setDetFecha(rs.getString(3));
                detalle.setDetHoras(rs.getString(4));
                detalle.setDetValor(rs.getDouble(5));
                detalle.setDetTipo(rs.getString(6));
            }
        } catch (SQLException ex) {
            // Si ocurre una excepción de tipo SQLException, se imprime un mensaje de error
            System.out.println("Error al listar: " + ex.getMessage());
        }
        // Se devuelve el objeto detalle que contiene los datos del detalle recuperado de la base de datos
        return detalle;
    }

    // Método para agregar un nuevo detalle
    @Override
    public String add(Detalle det) {
        try {
            // Se obtiene una conexión a la base de datos utilizando el objeto de conexión cn
            con = (Connection) cn.getConexion();
            // Se crea un objeto CallableStatement para invocar el procedimiento almacenado add
            cs = con.prepareCall(add);
            // Se establecen los parámetros del procedimiento almacenado mediante los métodos setInt() y setString()
            cs.setInt(1, det.getFkEncabezado());
            cs.setString(2, det.getDetFecha());
            cs.setString(3, det.getDetHoras());
            cs.setDouble(4, det.getDetValor());
            cs.setString(5, det.getDetTipo());
            // Se ejecuta el procedimiento almacenado para agregar el nuevo detalle a la base de datos
            cs.execute();
        } catch (SQLException ex) {
            // Si ocurre una excepción de tipo SQLException, se imprime un mensaje de error y se devuelve un mensaje de "no creado"
            System.out.println("Error al crear: " + ex.getMessage());
            return "no creado";
        }
        // Si la operación fue exitosa, se devuelve un mensaje de "creado"
        return "creado";
    }

    // Método para actualizar un detalle por su ID
    @Override
    public String updateById(Detalle det) {
        try {
            // Se obtiene una conexión a la base de datos utilizando el objeto de conexión cn
            con = (Connection) cn.getConexion();
            // Se crea un objeto CallableStatement para invocar el procedimiento almacenado update
            cs = con.prepareCall(update);
            // Se establecen los parámetros del procedimiento almacenado mediante los métodos setInt() y setString()
            cs.setInt(1, det.getIdDetalle());
            cs.setInt(2, det.getFkEncabezado());
            cs.setString(3, det.getDetFecha());
            cs.setString(4, det.getDetHoras());
            cs.setDouble(5, det.getDetValor());
            cs.setString(6, det.getDetTipo());
            // Se ejecuta el procedimiento almacenado para actualizar el detalle en la base de datos
            cs.execute();
        } catch (SQLException ex) {
            // Si ocurre una excepción de tipo SQLException, se imprime un mensaje de error y se devuelve un mensaje de "no actualizado"
            System.out.println("Error al actualizar: " + ex);
            return "no actualizado";
        }
        // Si la operación fue exitosa, se devuelve un mensaje de "actualizado"
        return "actualizado";
    }

    // Método para eliminar un detalle por su ID
    @Override
    public String deleteById(int id) {
        try {
            // Se obtiene una conexión a la base de datos utilizando el objeto de conexión cn
            con = (Connection) cn.getConexion();
            // Se crea un objeto CallableStatement para invocar el procedimiento almacenado delete
            cs = con.prepareCall(delete);
            // Se establece el parámetro ID en el procedimiento almacenado mediante el método setInt()
            cs.setInt(1, id);
            // Se ejecuta el procedimiento almacenado para eliminar el detalle de la base de datos
            cs.execute();
        } catch (SQLException ex) {
            // Si ocurre una excepción de tipo SQLException, se imprime un mensaje de error y se devuelve un mensaje de "no eliminado"
            System.out.println("Error al eliminar: " + ex.getMessage());
            return "no eliminado";
        }
        // Si la operación fue exitosa, se devuelve un mensaje de "eliminado"
        return "eliminado";
    }
}
