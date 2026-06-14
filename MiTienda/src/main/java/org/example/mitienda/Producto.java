package org.example.mitienda;

import javafx.beans.property.*;

public class Producto {

    private final StringProperty codigo;
    private final StringProperty nombre;
    private final StringProperty categoria;
    private final DoubleProperty precio;
    private final IntegerProperty stock;
    private final StringProperty estado;

    public Producto(String codigo, String nombre,
                    String categoria, double precio,
                    int stock, String estado) {

        this.codigo = new SimpleStringProperty(codigo);
        this.nombre = new SimpleStringProperty(nombre);
        this.categoria = new SimpleStringProperty(categoria);
        this.precio = new SimpleDoubleProperty(precio);
        this.stock = new SimpleIntegerProperty(stock);
        this.estado = new SimpleStringProperty(estado);
    }

    public String getCodigo() {
        return codigo.get();
    }

    public String getNombre() {
        return nombre.get();
    }

    public String getCategoria() {
        return categoria.get();
    }

    public double getPrecio() {
        return precio.get();
    }

    public int getStock() {
        return stock.get();
    }

    public String getEstado() {
        return estado.get();
    }

    public void setCodigo(String codigo) {
        this.codigo.set(codigo);
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public void setCategoria(String categoria) {
        this.categoria.set(categoria);
    }

    public void setPrecio(double precio) {
        this.precio.set(precio);
    }

    public void setStock(int stock) {
        this.stock.set(stock);
    }

    public void setEstado(String estado) {
        this.estado.set(estado);
    }
}