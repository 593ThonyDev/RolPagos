package com.pagos.controller;

import com.pagos.dao.DetalleDao;
import com.pagos.dao.EncabezadoDao;
import com.pagos.model.Detalle;
import com.pagos.model.Encabezado;
import java.io.*;
import jakarta.servlet.http.*;
import jakarta.servlet.ServletException;
import org.json.JSONObject;

public class RolPago extends HttpServlet {

    Detalle det = new Detalle();
    DetalleDao detDao = new DetalleDao();
    Encabezado enc = new Encabezado();
    EncabezadoDao encDao = new EncabezadoDao();   
    JSONObject obj = new JSONObject();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

}
