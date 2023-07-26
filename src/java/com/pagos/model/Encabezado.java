package com.pagos.model;

public class Encabezado extends Usuario{

    private int idEncabezado;
    private int fkUsuario;
    private String encFechaEmision;
    private String encFechaInicio;
    private String encFechaFin;
    private double encTotalPagar;

    public Encabezado() {
    }

    public Encabezado(Integer idEncabezado, Integer fkUsuario, String encFechaEmision, String encFechaInicio, String encFechaFin, Double encTotalPagar) {
        this.idEncabezado = idEncabezado;
        this.fkUsuario = fkUsuario;
        this.encFechaEmision = encFechaEmision;
        this.encFechaInicio = encFechaInicio;
        this.encFechaFin = encFechaFin;
        this.encTotalPagar = encTotalPagar;
    }

    public Encabezado(Integer fkUsuario, String encFechaEmision, String encFechaInicio, String encFechaFin, Double encTotalPagar) {
        this.fkUsuario = fkUsuario;
        this.encFechaEmision = encFechaEmision;
        this.encFechaInicio = encFechaInicio;
        this.encFechaFin = encFechaFin;
        this.encTotalPagar = encTotalPagar;
    }

    public Integer getIdEncabezado() {
        return idEncabezado;
    }

    public void setIdEncabezado(Integer idEncabezado) {
        this.idEncabezado = idEncabezado;
    }

    public Integer getFkUsuario() {
        return fkUsuario;
    }

    public void setFkUsuario(Integer fkUsuario) {
        this.fkUsuario = fkUsuario;
    }

    public String getEncFechaEmision() {
        return encFechaEmision;
    }

    public void setEncFechaEmision(String encFechaEmision) {
        this.encFechaEmision = encFechaEmision;
    }

    public String getEncFechaInicio() {
        return encFechaInicio;
    }

    public void setEncFechaInicio(String encFechaInicio) {
        this.encFechaInicio = encFechaInicio;
    }

    public String getEncFechaFin() {
        return encFechaFin;
    }

    public void setEncFechaFin(String encFechaFin) {
        this.encFechaFin = encFechaFin;
    }

    public Double getEncTotalPagar() {
        return encTotalPagar;
    }

    public void setEncTotalPagar(Double encTotalPagar) {
        this.encTotalPagar = encTotalPagar;
    }

}
