package com.pagos.controller;

import com.pagos.dao.UsuarioDao;
import com.pagos.model.Usuario;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;

public class Usuarios extends HttpServlet {

    String LOGIN = "index.jsp";
    String CREAR = "views/UsuariosCrear.jsp";
    String EDITAR = "views/UsuariosActualizar.jsp";
    String REGISTROS = "views/Usuarios.jsp";

    //Objetos
    Usuario usu = new Usuario();
    UsuarioDao dao = new UsuarioDao();
    //
    Integer idUsuario = 0;
    Integer usuIntento = 0;
    String usuUsuario, usuClave, usuRol, usuNombres, usuApellidos, usuTelefono, usuDireccion;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        Usuario usu = (Usuario) session.getAttribute("usuario");
        if (usu == null) {
            request.getRequestDispatcher(LOGIN).forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("accion");
        String acceso = "";
        //========================================================================================== GLOBALES
        HttpSession session = request.getSession();
        Usuario usu = (Usuario) session.getAttribute("usuario");
        if (usu == null) {
            // si no existe un usuario activo en el servidor se redirige al login
            request.setAttribute("errorSesion", "Debes iniciar sesion, para acceder al contenido!!");
            request.getRequestDispatcher(LOGIN).forward(request, response);
        } else {
            switch (action) {
                case "guardar":
                    usuIntento = 0;
                    usuUsuario = request.getParameter("usuUsuario");
                    usuClave = request.getParameter("usuClave");
                    usuRol = "Empleado";
                    usuNombres = request.getParameter("usuNombres");
                    usuApellidos = request.getParameter("usuApellidos");
                    usuTelefono = request.getParameter("usuTelefono");
                    usuDireccion = request.getParameter("usuDireccion");
                    usu = new Usuario(usuIntento, usuUsuario, usuClave, usuRol, usuNombres, usuApellidos, usuTelefono, usuDireccion);
                    dao.add(usu);
                    acceso = REGISTROS;
                    break;
                case "actualizar":
                    idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
                    usuUsuario = request.getParameter("usuUsuario");
                    usuClave = request.getParameter("usuClave");
                    usuRol = "Empleado";
                    usuNombres = request.getParameter("usuNombres");
                    usuApellidos = request.getParameter("usuApellidos");
                    usuTelefono = request.getParameter("usuTelefono");
                    usuDireccion = request.getParameter("usuDireccion");
                    usu = new Usuario(idUsuario, usuIntento, usuUsuario, usuClave, usuRol, usuNombres, usuApellidos, usuTelefono, usuDireccion);
                    dao.updateById(usu);
                    System.out.println(dao.updateById(usu));
                    acceso = REGISTROS;
                    break;
                default:
                    acceso = LOGIN;
            }
            RequestDispatcher view = request.getRequestDispatcher(acceso);
            view.forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("accion");
        String acceso = "";
        //========================================================================================== GLOBALES
        HttpSession session = request.getSession();
        Usuario usu = (Usuario) session.getAttribute("usuario");
        if (usu == null) {
            // si no existe un usuario activo en el servidor se redirige al login
            request.setAttribute("errorSesion", "Debes iniciar sesion, para acceder al contenido!!");
            request.getRequestDispatcher(LOGIN).forward(request, response);
        } else {
            switch (action) {
                case "nuevo":
                    acceso = CREAR;
                    break;
                case "editar":
                    request.setAttribute("idUsuario", request.getParameter("idUsuario"));
                    acceso = EDITAR;
                    break;
                case "eliminar":
                    idUsuario = Integer.parseInt((String) request.getParameter("idUsuario"));
                    /* ========== ENVIO EL OBJETO A LA DB=========*/
                    dao.deleteById(idUsuario);
                    acceso = REGISTROS;
                    break;

                default:
                    acceso = LOGIN;
            }
            RequestDispatcher view = request.getRequestDispatcher(acceso);
            view.forward(request, response);
        }
    }

}
