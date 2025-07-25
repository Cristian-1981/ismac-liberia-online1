package com.distribuida.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "libro")

public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_libro")
    private int idLibro;
    @Column(name = "titulo")
    private String titulo;
    @Column(name = "editorial")
    private String editotial;
    @Column(name = "num_paginas")
    private int numpaginas;
    @Column(name = "edicion")
    private String edicion;
    @Column(name = "idioma")
    private String idioma;
    @Column(name = "fecha_publicacion")
    private Date fechapublicacion;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "tipo_pasta")
    private String tipopasta;
    @Column(name = "ISBN")
    private String isbn;
    @Column(name = "num_ejemplares")
    private int numejemplares;
    @Column(name = "portada")
    private String portada;
    @Column(name = "presentacion")
    private String presentacion;
    @Column(name = "precio")
    private Double precio;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name ="id_autor")
    private Autor autor;

    public Libro() {
    }

    public Libro(int idLibro, String titulo, String editotial, int numpaginas, String edicion, String idioma, Date fechapublicacion, String descripcion, String tipopasta, String isbn, int numejemplares, String portada, String presentacion, Double precio, Categoria categoria, Autor autor) {
        this.idLibro = idLibro;
        this.titulo = titulo;
        this.editotial = editotial;
        this.numpaginas = numpaginas;
        this.edicion = edicion;
        this.idioma = idioma;
        this.fechapublicacion = fechapublicacion;
        this.descripcion = descripcion;
        this.tipopasta = tipopasta;
        this.isbn = isbn;
        this.numejemplares = numejemplares;
        this.portada = portada;
        this.presentacion = presentacion;
        this.precio = precio;
        this.categoria = categoria;
        this.autor = autor;
    }

    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEditotial() {
        return editotial;
    }

    public void setEditotial(String editotial) {
        this.editotial = editotial;
    }

    public int getNumpaginas() {
        return numpaginas;
    }

    public void setNumpaginas(int numpaginas) {
        this.numpaginas = numpaginas;
    }

    public String getEdicion() {
        return edicion;
    }

    public void setEdicion(String edicion) {
        this.edicion = edicion;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Date getFechapublicacion() {
        return fechapublicacion;
    }

    public void setFechapublicacion(Date fechapublicacion) {
        this.fechapublicacion = fechapublicacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipopasta() {
        return tipopasta;
    }

    public void setTipopasta(String tipopasta) {
        this.tipopasta = tipopasta;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getNumejemplares() {
        return numejemplares;
    }

    public void setNumejemplares(int numejemplares) {
        this.numejemplares = numejemplares;
    }

    public String getPortada() {
        return portada;
    }

    public void setPortada(String portada) {
        this.portada = portada;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "idLibro=" + idLibro +
                ", titulo='" + titulo + '\'' +
                ", editotial='" + editotial + '\'' +
                ", numpaginas=" + numpaginas +
                ", edicion='" + edicion + '\'' +
                ", idioma='" + idioma + '\'' +
                ", fechapublicacion=" + fechapublicacion +
                ", descripcion='" + descripcion + '\'' +
                ", tipopasta='" + tipopasta + '\'' +
                ", isbn='" + isbn + '\'' +
                ", numejemplares=" + numejemplares +
                ", portada='" + portada + '\'' +
                ", presentacion='" + presentacion + '\'' +
                ", precio=" + precio +
                ", categoria=" + categoria +
                ", autor=" + autor +
                '}';
    }

}
