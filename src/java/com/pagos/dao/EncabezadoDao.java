package com.pagos.dao;

import java.sql.*;
import java.util.List;
import com.pagos.database.Conexion;
import com.pagos.interfaces.crudEncabezado;
import com.pagos.model.Encabezado;
import java.util.ArrayList;

public class EncabezadoDao implements crudEncabezado {

    // Objeto de la clase Encabezado
    Encabezado encabezado = new Encabezado();
    // Objeto para establecer la conexión a la base de datos
    Conexion cn = new Conexion();
    // Objeto para llamar a los procedimientos almacenados en la base de datos
    CallableStatement cs;
    // Objeto para mantener la conexión a la base de datos
    Connection con;
    // Objeto para almacenar el resultado de una consulta a la base de datos
    ResultSet rs;

    // Variables para los procedimientos almacenados
    // Llamada al procedimiento almacenado para obtener todos los encabezados
    String getAll = "CALL spLeerEncabezados()";
    // Llamada al procedimiento almacenado para obtener un encabezado por su ID
    String getById = "CALL spObtenerEncabezadoPorID(?)";
    // Llamada al procedimiento almacenado para agregar un nuevo encabezado
    String add = "CALL CrearEncabezado(?,?,?,?,?)";
    // Llamada al procedimiento almacenado para actualizar un encabezado existente
    String update = "CALL spActualizarEncabezado(?,?,?,?,?,?)";
    // Llamada al procedimiento almacenado para eliminar un encabezado por su ID
    String delete = "CALL spEliminarEncabezado(?)";

    // Método para obtener todos los encabezados
    @Override
    public List<Encabezado> getAll() {
        // Se crea una lista para almacenar los objetos de Encabezado recuperados de la base de datos
        ArrayList<Encabezado> lista = new ArrayList<>();
        try {
            // Se obtiene una conexión a la base de datos utilizando el objeto de conexión cn
            con = (Connection) cn.getConexion();
            // Se crea un objeto CallableStatement para invocar el procedimiento almacenado getAll
            cs = con.prepareCall(getAll);
            // Se ejecuta la consulta y el resultado se almacena en el objeto ResultSet rs
            rs = cs.executeQuery();
            // Se itera a través de los resultados utilizando un bucle while
            while (rs.next()) {
                // Se crea un nuevo objeto Encabezado para almacenar los datos de un encabezado en particular
                Encabezado enc = new Encabezado();
                // Se obtienen los valores de las columnas correspondientes en el ResultSet rs y se asignan a las propiedades del objeto enc
                enc.setIdEncabezado(rs.getInt(1));
                enc.setFkUsuario(rs.getInt(2));
                enc.setEncFechaEmision(rs.getString(3));
                enc.setEncFechaInicio(rs.getString(4));
                enc.setEncFechaFin(rs.getString(5));
                enc.setEncTotalPagar(rs.getDouble(6));
                // Se agrega el objeto enc a la lista de encabezados
                lista.add(enc);
            }
        } catch (SQLException ex) {
            // Si ocurre una excepción de tipo SQLException, se imprime un mensaje de error
            System.out.println("Error al listar: " + ex.getMessage());
        }
        // Se devuelve la lista que contiene todos los encabezados recuperados de la base de datos
        return lista;
    }

    // Método para obtener un encabezado por su ID
    @Override
    public Encabezado getById(int id) {
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
                // Se establecen las propiedades del objeto encabezado con los valores de las columnas correspondientes en el ResultSet rs
                encabezado.setIdEncabezado(rs.getInt(1));
                encabezado.setFkUsuario(rs.getInt(2));
                encabezado.setEncFechaEmision(rs.getString(3));
                encabezado.setEncFechaInicio(rs.getString(4));
                encabezado.setEncFechaFin(rs.getString(5));
                encabezado.setEncTotalPagar(rs.getDouble(6));
            }
        } catch (SQLException ex) {
            // Si ocurre una excepción de tipo SQLException, se imprime un mensaje de error
            System.out.println("Error al listar: " + ex.getMessage());
        }
        // Se devuelve el objeto encabezado que contiene los datos del encabezado recuperado de la base de datos
        return encabezado;
    }
    
        public Integer getLastId() {
        int numero = 0;
        try {
            con = (Connection) cn.getConexion();
            cs = con.prepareCall("CALL obtenerUltimoIDEnc()");
            rs = cs.executeQuery();
            while (rs.next()) {
                numero = rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println("No hay id: " + ex.getMessage());
            return 0;
        }
        return numero;
    }

    // Método para agregar un nuevo encabezado
    @Override
    public String add(Encabezado enc) {
        try {
            // Se obtiene una conexión a la base de datos utilizando el objeto de conexión cn
            con = (Connection) cn.getConexion();
            // Se crea un objeto CallableStatement para invocar el procedimiento almacenado add
            cs = con.prepareCall(add);
            // Se establecen los parámetros del procedimiento almacenado mediante los métodos setInt() y setString()
            cs.setInt(1, enc.getFkUsuario());
            cs.setString(2, enc.getEncFechaEmision());
            cs.setString(3, enc.getEncFechaInicio());
            cs.setString(4, enc.getEncFechaFin());
            cs.setDouble(5, enc.getEncTotalPagar());
            // Se ejecuta el procedimiento almacenado para agregar el nuevo encabezado a la base de datos
            cs.execute();
        } catch (SQLException ex) {
            // Si ocurre una excepción de tipo SQLException, se imprime un mensaje de error y se devuelve un mensaje de "no creado"
            System.out.println("Error al crear: " + ex.getMessage());
            return "no creado";
        }
        // Si la operación fue exitosa, se devuelve un mensaje de "creado"
        return "creado";
    }

    // Método para actualizar un encabezado por su ID
    @Override
    public String updateById(Encabezado enc) {
        try {
            // Se obtiene una conexión a la base de datos utilizando el objeto de conexión cn
            con = (Connection) cn.getConexion();
            // Se crea un objeto CallableStatement para invocar el procedimiento almacenado update
            cs = con.prepareCall(update);
            // Se establecen los parámetros del procedimiento almacenado mediante los métodos setInt() y setString()
            cs.setInt(1, enc.getIdEncabezado());
            cs.setInt(2, enc.getFkUsuario());
            cs.setString(3, enc.getEncFechaEmision());
            cs.setString(4, enc.getEncFechaInicio());
            cs.setString(5, enc.getEncFechaFin());
            cs.setDouble(6, enc.getEncTotalPagar());
            // Se ejecuta el procedimiento almacenado para actualizar el encabezado en la base de datos
            cs.execute();
        } catch (SQLException ex) {
            // Si ocurre una excepción de tipo SQLException, se imprime un mensaje de error y se devuelve un mensaje de "no actualizado"
            System.out.println("Error al actualizar: " + ex);
            return "no actualizado";
        }
        // Si la operación fue exitosa, se devuelve un mensaje de "actualizado"
        return "actualizado";
    }

    // Método para eliminar un encabezado por su ID
    @Override
    public String deleteById(int id) {
        try {
            // Se obtiene una conexión a la base de datos utilizando el objeto de conexión cn
            con = (Connection) cn.getConexion();
            // Se crea un objeto CallableStatement para invocar el procedimiento almacenado delete
            cs = con.prepareCall(delete);
            // Se establece el parámetro ID en el procedimiento almacenado mediante el método setInt()
            cs.setInt(1, id);
            // Se ejecuta el procedimiento almacenado para eliminar el encabezado de la base de datos
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
