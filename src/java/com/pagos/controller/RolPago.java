package com.pagos.controller;

import com.pagos.dao.DetalleDao;
import com.pagos.dao.EncabezadoDao;
import com.pagos.model.Detalle;
import com.pagos.model.Encabezado;
import com.pagos.model.Usuario;
import jakarta.servlet.RequestDispatcher;
import java.io.*;
import jakarta.servlet.http.*;
import jakarta.servlet.ServletException;

public class RolPago extends HttpServlet {

    String LOGIN = "index.jsp";
    String ROLES = "views/Roles.jsp";
    Detalle det = new Detalle();
    DetalleDao detDao = new DetalleDao();
    Encabezado enc = new Encabezado();
    EncabezadoDao encDao = new EncabezadoDao();

    //     
    private int fkUsuario;
    private String encFechaEmision;
    private String encFechaInicio;
    private String encFechaFin;
    private Double encTotalPagar;
    //
    private Integer fkEncabezado;
    private String detFecha;
    private Integer detHoras;
    private Double detValor;
    private String detTipo = "PAGO";


    @Override
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
                    fkEncabezado = (encDao.getLastId() != 0 ? encDao.getLastId() + 1 : encDao.getLastId() + 1);
                    detFecha = request.getParameter("encEmision");
                    detHoras = Integer.parseInt(request.getParameter("detHoras"));
                    detValor = Double.parseDouble(request.getParameter("detHoraValor"));
                    det = new Detalle(fkEncabezado, detFecha, detHoras, detValor, detTipo);
                    fkUsuario = Integer.parseInt(request.getParameter("fkUsuario"));
                    encFechaEmision = request.getParameter("encEmision");
                    encFechaInicio = request.getParameter("detInicio");
                    encFechaFin = request.getParameter("detFin");
                    encTotalPagar = detHoras * detValor;
                    enc = new Encabezado(fkUsuario, encFechaEmision, encFechaInicio, encFechaFin, encTotalPagar);
                    encDao.add(enc);
                    detDao.add(det);
                    acceso = ROLES;
                    break;
                default:
                    acceso = LOGIN;
            }
            RequestDispatcher view = request.getRequestDispatcher(acceso);
            view.forward(request, response);
        }

    }

}
