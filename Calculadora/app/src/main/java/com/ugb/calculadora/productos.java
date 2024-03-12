package com.ugb.calculadora;


public class productos {
    String codigoProducto;
    String nombre;
    String descripcion;
    String marca;
    String presentacion;
    String precio;

    public productos(String idProducto, String nombre, String descripcion, String marca, String presentacion, String precio) {
        this.codigoProducto = idProducto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.marca = marca;
        this.presentacion = presentacion;
        this.precio = precio;
    }

    public String getCodigoProducto() {
        return codigoProducto;
    }

    public void setIdProducto(String idProducto) {
        this.codigoProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String direccion) {
        this.descripcion = direccion;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String telefono) {
        this.marca = telefono;
    }

        public String getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(String email) {
        this.presentacion = email;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String dui) {
        this.precio = dui;
    }
}