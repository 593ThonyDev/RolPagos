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
import org.json.JSONObject;

public class RolPago extends HttpServlet {

    String LOGIN = "index.jsp";
    Detalle det = new Detalle();
    DetalleDao detDao = new DetalleDao();
    Encabezado enc = new Encabezado();
    EncabezadoDao encDao = new EncabezadoDao();
    JSONObject obj = new JSONObject();

    //     
    private int idEncabezado;
    private int fkUsuario;
    private String encFechaEmision;
    private String encFechaInicio;
    private String encFechaFin;
    private double encTotalPagar;
    //
    private Integer fkEncabezado;
    private String detFecha;
    private String detHoras;
    private Double detValor;
    private String detTipo = "PAGO";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

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
                    detHoras = request.getParameter("detHoras");
                    String detHoraValorStr = request.getParameter("detHoraValor");

                    // Verificar si detHoraValorStr es nulo antes de convertirlo a Double
                    if (detHoraValorStr != null && !detHoraValorStr.isEmpty()) {
                        try {
                            detValor = Double.parseDouble(detHoraValorStr);
                        } catch (NumberFormatException e) {
                            // Manejar el caso cuando el parámetro detHoraValor no es un número válido
                            // Por ejemplo, puedes asignarle un valor predeterminado o mostrar un mensaje de error.
                            // Aquí simplemente asignaremos 0.0 como valor predeterminado.
                            detValor = 0.0;
                        }
                    } else {
                        // Manejar el caso cuando el parámetro detHoraValor es nulo o está vacío
                        // Por ejemplo, puedes asignarle un valor predeterminado o mostrar un mensaje de error.
                        // Aquí simplemente asignaremos 0.0 como valor predeterminado.
                        detValor = 0.0;
                    }

                    det = new Detalle(fkEncabezado, detFecha, detHoras, detValor, detTipo);
                    fkUsuario = Integer.parseInt(request.getParameter("fkUsuario"));
                    encFechaEmision = request.getParameter("encEmision");
                    encFechaInicio = request.getParameter("detInicio");
                    encFechaFin = request.getParameter("detFin");

                    // Verificar si el totalPagarENC es nulo antes de convertirlo a Double
                    String totalPagarEncStr = request.getParameter("totalPagarENC");
                    if (totalPagarEncStr != null && !totalPagarEncStr.isEmpty()) {
                        try {
                            encTotalPagar = Double.parseDouble(totalPagarEncStr);
                        } catch (NumberFormatException e) {
                            // Manejar el caso cuando el parámetro totalPagarENC no es un número válido
                            // Por ejemplo, puedes asignarle un valor predeterminado o mostrar un mensaje de error.
                            // Aquí simplemente asignaremos 0.0 como valor predeterminado.
                            encTotalPagar = 0.0;
                        }
                    } else {
                        // Manejar el caso cuando el parámetro totalPagarENC es nulo o está vacío
                        // Por ejemplo, puedes asignarle un valor predeterminado o mostrar un mensaje de error.
                        // Aquí simplemente asignaremos 0.0 como valor predeterminado.
                        encTotalPagar = 0.0;
                    }

                    enc = new Encabezado(fkUsuario, encFechaEmision, encFechaInicio, encFechaFin, encTotalPagar);
                    encDao.add(enc);
                    detDao.add(det);
                    break;
                default:
                    acceso = LOGIN;
            }
            RequestDispatcher view = request.getRequestDispatcher(acceso);
            view.forward(request, response);
        }

    }

}
