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

    public Categoria(int idcategoria, String categoria, String descripcion) {
        this.idcategoria = idcategoria;
        this.categoria = categoria;
        this.descripcion = descripcion;
    }

    public int getIdcategoria() {
        return idcategoria;
    }

    public void setIdcategoria(int idcategoria) {
        this.idcategoria = idcategoria;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
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
