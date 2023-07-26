package com.pagos.controller;

import com.pagos.dao.UsuarioDao;
import com.pagos.model.Usuario;
import com.pagos.security.Encriptador;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;

/**
 * Esta clase maneja la funcionalidad de inicio de sesión de usuario.
 */
public class Login extends HttpServlet {

    HttpSession session;
    Usuario usu = new Usuario();
    UsuarioDao dao = new UsuarioDao();
    Encriptador enc = new Encriptador();
    Integer idUsuario = 0;
    String INCIO = "Controlador?p=rol";
    String LOGIN = "index.jsp";
    String usuClaveBd;
    String usuUsuario;
    String usuClave;
    int usuIntento;

    /**
     * Maneja la solicitud POST para el inicio de sesión de usuario. Este método
     * verifica las credenciales del usuario y redirige al usuario a las páginas
     * apropiadas según su rol. Si el inicio de sesión falla, realiza un
     * seguimiento de los intentos de inicio de sesión y bloquea al usuario
     * después de un cierto número de intentos fallidos.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        session = request.getSession();
        usuUsuario = request.getParameter("usuUsuario");
        usuClave = request.getParameter("usuClave");

        // Desencriptar la contraseña almacenada en la base de datos del usuario que intenta iniciar sesión
        // Buscar el usuario en la base de datos por el nombre de usuario proporcionado
        idUsuario = dao.existIdUsuario(usuUsuario);
        usu = dao.getById(idUsuario);
        usuClaveBd = enc.desencriptar(usu.getUsuClave());

        // Verificar si el usuario existe o si el id de usuario es igual a cero (0)
        if (usu == null || idUsuario == 0) {
            request.setAttribute("errorSesion", "El usuario ingresado no está registrado!!");
            request.getRequestDispatcher(LOGIN).forward(request, response);
        }

        // Comparar la contraseña desencriptada con la contraseña proporcionada por el usuario
        // y verificar el número de intentos de inicio de sesión
        if (usuClaveBd.equals(usuClave) && usu.getUsuIntento() <= 5) {
            // El inicio de sesión es exitoso

            switch (usu.getUsuRol()) {
                case "Administrador":
                    // Establecer atributos de sesión para el usuario y redirigir a la página de inicio de administrador
                    session.setAttribute("idUsuario", usu.getIdUsuario());
                    session.setAttribute("usuUsuario", usuUsuario);
                    session.setAttribute("usuario", usu);
                    response.sendRedirect(INCIO);
                    usu.setUsuIntento(0);
                    usu.setUsuClave(usuClaveBd);
                    dao.updateById(usu);
                    break;
                case "Empleado":
                    // Establecer atributos de sesión para el usuario y redirigir a la página de inicio de empleado
                    session.setAttribute("idUsuario", usu.getIdUsuario());
                    session.setAttribute("usuUsuario", usuUsuario);
                    session.setAttribute("usuario", usu);
                    response.sendRedirect(INCIO);
                    usu.setUsuIntento(0);
                    usu.setUsuClave(usuClaveBd);
                    dao.updateById(usu);
                    break;
                default:
                    // El rol del usuario no es reconocido, mostrar mensaje de error y redirigir a la página de inicio de sesión
                    request.setAttribute("errorSesion", "Error en su petición!!");
                    request.getRequestDispatcher(LOGIN).forward(request, response);
            }
        } else {
            // El inicio de sesión falló debido a contraseña incorrecta o límite de intentos excedido

            usuIntento = usu.getUsuIntento() + 1;

            if (usuIntento >= 3) {
                // Si se excede el límite de intentos, bloquear al usuario y mostrar mensaje de error
                request.setAttribute("errorSesion", "Usuario bloqueado, limite de intentos exedidos!!");
                request.getRequestDispatcher(LOGIN).forward(request, response);
            }

            // Actualizar el número de intentos de inicio de sesión en la base de datos
            usu.setUsuClave(enc.desencriptar(usu.getUsuClave()));
            usu.setUsuIntento(usuIntento);
            dao.updateById(usu);

            // Mostrar mensaje de error y redirigir a la página de inicio de sesión
            request.setAttribute("errorSesion", "CONTRASEÑA INCORRECTA!!");
            request.getRequestDispatcher(LOGIN).forward(request, response);
        }
    }
}
