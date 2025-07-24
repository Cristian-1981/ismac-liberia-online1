package com.distribuida.model;

import jakarta.persistence.*;

@Entity
@Table(name = "categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private int idcategoria;
    @Column(name = "categoria")
    private String categoria;
    @Column(name = "descripcion")
    private String descripcion;

    public Categoria() {
    }

    public Categoria(int idCategoria, String categorias, String descripcion) {
        this.idcategoria = idCategoria;
        this.categoria = categorias;
        this.descripcion = descripcion;
    }

    public int getIdCategoria() {
        return idcategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idcategoria = idCategoria;
    }

    public String getCategorias() {
        return categoria;
    }

    public void setCategorias(String categorias) {
        this.categoria = categorias;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Categoria{" +
                "idcategoria=" + idcategoria +
                ", categoria='" + categoria + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }



}
