package com.pagos.dao;

import java.sql.*;
import java.util.List;
import com.pagos.database.Conexion;
import com.pagos.interfaces.crudUsuario;
import com.pagos.model.Usuario;
import com.pagos.security.Encriptador;
import java.util.ArrayList;

public class UsuarioDao implements crudUsuario {

    // Estancias necesarias para la lógica
    // Objeto para encriptar/desencriptar
    Encriptador enc = new Encriptador();
    // Objeto de la clase Usuario
    Usuario usuario = new Usuario();
    // Objeto para establecer la conexión a la base de datos
    Conexion cn = new Conexion();
    // Objeto para llamar a los procedimientos almacenados en la base de datos
    CallableStatement cs;
    // Objeto para mantener la conexión a la base de datos
    Connection con;
    // Objeto para almacenar el resultado de una consulta a la base de datos
    ResultSet rs;

    // Variables para los procedimientos almacenados
    // Llamada al procedimiento almacenado para obtener todos los usuarios
    String getAll = "CALL SeleccionarTodosUsuarios()";
    // Llamada al procedimiento almacenado para obtener un usuario por su ID
    String getById = "CALL SeleccionarUsuarioPorID(?)";
    // Llamada al procedimiento almacenado para agregar un nuevo usuario
    String add = "CALL CrearUsuario(?,?,?,?,?,?,?,?)";
    // Llamada al procedimiento almacenado para actualizar un usuario existente
    String update = "CALL ActualizarUsuario(?,?,?,?,?,?,?,?,?)";
    // Llamada al procedimiento almacenado para eliminar un usuario por su ID
    String delete = "CALL EliminarUsuario(?)";

    // Llamada al procedimiento almacenado para consultar el ID de un usuario por su usuUsuario (cedula o username)
    String getIdUsuario = "CALL getIdUsuario(?)";

    // Variable para almacenar el ID de un usuario
    Integer idUsuario;

    /* =================== LOGIN =================== */
    /**
     * Nos permite obtener el idUsuario a través del usuUsuario (cedula o
     * username)
     */
    @Override
    public Integer existIdUsuario(String USU_Usuario) {
        try {
            idUsuario = 0;
            con = (Connection) cn.getConexion();
            cs = con.prepareCall(getIdUsuario);
            cs.setString(1, USU_Usuario);
            rs = cs.executeQuery();
            while (rs.next()) {
                idUsuario = Integer.parseInt(rs.getString(1));
            }
        } catch (SQLException ex) {
            System.out.println("idUsuario no encontrado: " + ex);
            return 0;
        }
        return idUsuario;
    }

    // ==================== Métodos CRUD ====================
    // Método para obtener todos los usuarios
    @Override
    public List getAll() {
        // Se crea una lista para almacenar los objetos de Usuario recuperados de la base de datos
        ArrayList<Usuario> lista = new ArrayList<>();
        try {
            // Se obtiene una conexión a la base de datos utilizando el objeto de conexión cn
            con = (Connection) cn.getConexion();
            // Se crea un objeto CallableStatement para invocar el procedimiento almacenado getAll
            cs = con.prepareCall(getAll);
            // Se ejecuta la consulta y el resultado se almacena en el objeto ResultSet rs
            rs = cs.executeQuery();
            // Se itera a través de los resultados utilizando un bucle while
            while (rs.next()) {
                // Se crea un nuevo objeto Usuario para almacenar los datos de un usuario en particular
                Usuario usu = new Usuario();
                // Se obtienen los valores de las columnas correspondientes en el ResultSet rs y se asignan a las propiedades del objeto usu
                usu.setIdUsuario(rs.getInt(1));
                usu.setUsuIntento(rs.getInt(2));
                usu.setUsuUsuario(rs.getString(3));
                usu.setUsuClave(rs.getString(4));
                usu.setUsuRol(rs.getString(5));
                usu.setUsuNombres(rs.getString(6));
                usu.setUsuApellidos(rs.getString(7));
                usu.setUsuTelefono(rs.getString(8));
                usu.setUsuDireccion(rs.getString(9));
                // Se agrega el objeto usu a la lista de usuarios
                lista.add(usu);
            }
        } catch (SQLException ex) {
            // Si ocurre una excepción de tipo SQLException, se imprime un mensaje de error
            System.out.println("Error al listar: " + ex.getMessage());
        }
        // Se devuelve la lista que contiene todos los usuarios recuperados de la base de datos
        return lista;
    }

    // Método para obtener un usuario por su ID
    @Override
    public Usuario getById(Integer id) {
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
                // Se establecen las propiedades del objeto usuario con los valores de las columnas correspondientes en el ResultSet rs
                usuario.setIdUsuario(rs.getInt(1));
                usuario.setUsuIntento(rs.getInt(2));
                usuario.setUsuUsuario(rs.getString(3));
                usuario.setUsuClave(rs.getString(4));
                usuario.setUsuRol(rs.getString(5));
                usuario.setUsuNombres(rs.getString(6));
                usuario.setUsuApellidos(rs.getString(7));
                usuario.setUsuTelefono(rs.getString(8));
                usuario.setUsuDireccion(rs.getString(9));
            }
        } catch (SQLException ex) {
            // Si ocurre una excepción de tipo SQLException, se imprime un mensaje de error
            System.out.println("Error al listar: " + ex.getMessage());
        }
        // Se devuelve el objeto usuario que contiene los datos del usuario recuperado de la base de datos
        return usuario;
    }

    // Método para agregar un nuevo usuario
    @Override
    public String add(Usuario usu) {
        try {
            // Se obtiene una conexión a la base de datos utilizando el objeto de conexión cn
            con = (Connection) cn.getConexion();
            // Se crea un objeto CallableStatement para invocar el procedimiento almacenado add
            cs = con.prepareCall(add);
            // Se establecen los parámetros del procedimiento almacenado mediante los métodos setInt() y setString()
            cs.setInt(1, usu.getUsuIntento());
            cs.setString(2, usu.getUsuUsuario());
            cs.setString(3, enc.encriptar(usu.getUsuClave()));
            cs.setString(4, usu.getUsuRol());
            cs.setString(5, usu.getUsuNombres());
            cs.setString(6, usu.getUsuApellidos());
            cs.setString(7, usu.getUsuTelefono());
            cs.setString(8, usu.getUsuDireccion());
            // Se ejecuta el procedimiento almacenado para agregar el nuevo usuario a la base de datos
            cs.execute();
        } catch (SQLException ex) {
            // Si ocurre una excepción de tipo SQLException, se imprime un mensaje de error y se devuelve un mensaje de "no creado"
            System.out.println("Error al crear: " + ex.getMessage());
            return "no creado";
        }
        // Si la operación fue exitosa, se devuelve un mensaje de "creado"
        return "creado";
    }

    // Método para actualizar un usuario por su ID
    @Override
    public String updateById(Usuario usu) {
        try {
            // Se obtiene una conexión a la base de datos utilizando el objeto de conexión cn
            con = (Connection) cn.getConexion();
            // Se crea un objeto CallableStatement para invocar el procedimiento almacenado update
            cs = con.prepareCall(update);
            // Se establecen los parámetros del procedimiento almacenado mediante los métodos setInt() y setString()
            cs.setInt(1, usu.getIdUsuario());
            cs.setInt(2, usu.getUsuIntento());
            cs.setString(3, usu.getUsuUsuario());
            cs.setString(4, enc.encriptar(usu.getUsuClave()));
            cs.setString(5, usu.getUsuRol());
            cs.setString(6, usu.getUsuNombres());
            cs.setString(7, usu.getUsuApellidos());
            cs.setString(8, usu.getUsuTelefono());
            cs.setString(9, usu.getUsuDireccion());
            // Se ejecuta el procedimiento almacenado para actualizar el usuario en la base de datos
            cs.execute();
        } catch (SQLException ex) {
            // Si ocurre una excepción de tipo SQLException, se imprime un mensaje de error y se devuelve un mensaje de "no actualizado"
            System.out.println("Error al actualizar: " + ex);
            return "no actualizado";
        }
        // Si la operación fue exitosa, se devuelve un mensaje de "actualizado"
        return "actualizado";
    }

    // Método para eliminar un usuario por su ID
    @Override
    public String deleteById(Integer id) {
        try {
            // Se obtiene una conexión a la base de datos utilizando el objeto de conexión cn
            con = (Connection) cn.getConexion();
            // Se crea un objeto CallableStatement para invocar el procedimiento almacenado delete
            cs = con.prepareCall(delete);
            // Se establece el parámetro ID en el procedimiento almacenado mediante el método setInt()
            cs.setInt(1, id);
            // Se ejecuta el procedimiento almacenado para eliminar el usuario de la base de datos
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
