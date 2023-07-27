package com.pagos.model;

public class Detalle extends Encabezado{
    private Integer idDetalle; 
    private Integer fkEncabezado; 
    private String detFecha;
    private String detHoras;
    private Double detValor;
    private String detTipo;

    public Detalle() {
    }

    public Detalle(Integer idDetalle, Integer fkEncabezado, String detFecha, String detHoras, Double detValor, String detTipo) {
        this.idDetalle = idDetalle;
        this.fkEncabezado = fkEncabezado;
        this.detFecha = detFecha;
        this.detHoras = detHoras;
        this.detValor = detValor;
        this.detTipo = detTipo;
    }

    public Detalle(Integer fkEncabezado, String detFecha, String detHoras, Double detValor, String detTipo) {
        this.fkEncabezado = fkEncabezado;
        this.detFecha = detFecha;
        this.detHoras = detHoras;
        this.detValor = detValor;
        this.detTipo = detTipo;
    }

    public Integer getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(Integer idDetalle) {
        this.idDetalle = idDetalle;
    }

    public Integer getFkEncabezado() {
        return fkEncabezado;
    }

    public void setFkEncabezado(Integer fkEncabezado) {
        this.fkEncabezado = fkEncabezado;
    }

    public String getDetFecha() {
        return detFecha;
    }

    public void setDetFecha(String detFecha) {
        this.detFecha = detFecha;
    }

    public String getDetHoras() {
        return detHoras;
    }

    public void setDetHoras(String detHoras) {
        this.detHoras = detHoras;
    }

    public Double getDetValor() {
        return detValor;
    }

    public void setDetValor(Double detValor) {
        this.detValor = detValor;
    }

    public String getDetTipo() {
        return detTipo;
    }

    public void setDetTipo(String detTipo) {
        this.detTipo = detTipo;
    }

   
    
    
}
