package com.pagos.model;

public class Usuario {

    private Integer idUsuario;
    private Integer usuIntento;
    private String usuUsuario;
    private String usuClave;
    private String usuRol;
    private String usuNombres;
    private String usuApellidos;
    private String usuTelefono;
    private String usuDireccion;

    public Usuario() {
    }

    public Usuario(Integer idUsuario, Integer usuIntento, String usuUsuario, String usuClave, String usuRol, String usuNombres, String usuApellidos, String usuTelefono, String usuDireccion) {
        this.idUsuario = idUsuario;
        this.usuIntento = usuIntento;
        this.usuUsuario = usuUsuario;
        this.usuClave = usuClave;
        this.usuRol = usuRol;
        this.usuNombres = usuNombres;
        this.usuApellidos = usuApellidos;
        this.usuTelefono = usuTelefono;
        this.usuDireccion = usuDireccion;
    }

    public Usuario(Integer usuIntento, String usuUsuario, String usuClave, String usuRol, String usuNombres, String usuApellidos, String usuTelefono, String usuDireccion) {
        this.usuIntento = usuIntento;
        this.usuUsuario = usuUsuario;
        this.usuClave = usuClave;
        this.usuRol = usuRol;
        this.usuNombres = usuNombres;
        this.usuApellidos = usuApellidos;
        this.usuTelefono = usuTelefono;
        this.usuDireccion = usuDireccion;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getUsuIntento() {
        return usuIntento;
    }

    public void setUsuIntento(Integer usuIntento) {
        this.usuIntento = usuIntento;
    }

    public String getUsuUsuario() {
        return usuUsuario;
    }

    public void setUsuUsuario(String usuUsuario) {
        this.usuUsuario = usuUsuario;
    }

    public String getUsuClave() {
        return usuClave;
    }

    public void setUsuClave(String usuClave) {
        this.usuClave = usuClave;
    }

    public String getUsuRol() {
        return usuRol;
    }

    public void setUsuRol(String usuRol) {
        this.usuRol = usuRol;
    }

    public String getUsuNombres() {
        return usuNombres;
    }

    public void setUsuNombres(String usuNombres) {
        this.usuNombres = usuNombres;
    }

    public String getUsuApellidos() {
        return usuApellidos;
    }

    public void setUsuApellidos(String usuApellidos) {
        this.usuApellidos = usuApellidos;
    }

    public String getUsuTelefono() {
        return usuTelefono;
    }

    public void setUsuTelefono(String usuTelefono) {
        this.usuTelefono = usuTelefono;
    }

    public String getUsuDireccion() {
        return usuDireccion;
    }

    public void setUsuDireccion(String usuDireccion) {
        this.usuDireccion = usuDireccion;
    }

}
