package com.pagos.controller;

import com.pagos.model.Usuario;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.Date;

public class Controlador extends HttpServlet {

    String LOGIN = "index.jsp";
    String USUARIOS = "views/Usuarios.jsp";
    String ROLES = "views/Roles.jsp";
    String ROLES_LISTA = "views/RolesListar.jsp";
    String acceso = "";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = request.getParameter("p");
        //========================================================================================== GLOBALES
        HttpSession session = request.getSession();
        Usuario usu = (Usuario) session.getAttribute("usuario");
        if (usu == null) {
            // si no existe un Usuario activo en el servidor se redirige al login
            request.setAttribute("errorSesion", "Debes iniciar sesion, para acceder al contenido!!");
            request.getRequestDispatcher(LOGIN).forward(request, response);
        } else {
            switch (page) {
                case "empleados":
                    acceso = USUARIOS;
                    break;
                case "rol":
                    acceso = ROLES;
                    break;
                case "lista-rol":
                    acceso = ROLES_LISTA;
                    break;
                default:
                    acceso = LOGIN;
            }
            RequestDispatcher view = request.getRequestDispatcher(acceso);
            view.forward(request, response);
        }
    }

}
