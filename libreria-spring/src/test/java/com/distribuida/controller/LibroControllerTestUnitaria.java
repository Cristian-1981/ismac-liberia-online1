package com.distribuida.controller;

import com.distribuida.model.*;
import com.distribuida.model.Libro;
import com.distribuida.service.LibroService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class LibroControllerTestUnitaria {

    @InjectMocks
    private LibroController libroController;

    @Mock
    private LibroService libroService;

    private Libro libro;
    private Categoria categoria;
    private Autor autor;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        categoria = new Categoria(0, "Aventura","Busqueda de tesoros");
        autor = new Autor(0, "Juan","Neruda","Colombia", "Av. Sebastian", "098565858","juanneruda@gmail.com");
        libro = new Libro(1,"El Tesoro de Atahualpa","Editorial Luna",125,"Primera","Español",new Date(),"Trata sobre la busqueda del tesoro que escondio Atahualpa","Simple","KLIP",50,"Dibujada","Full color",100.00,categoria,autor);
        //libro = new Libro();
        //libro.setIdLibro(1);
       // libro.setCantidad(5);
       // libro.setSubtotal(50.25);
        //libro.setFactura(factura);
        ///libro.setLibro(libro);

    }

    @Test
    public void testFindAll(){
        when(libroService.findAll()).thenReturn(List.of(libro));
        ResponseEntity<List<Libro>> respuesta = libroController.findAll();
        assertEquals(200, respuesta.getStatusCodeValue());
        assertEquals(1,respuesta.getBody().size());
    }

    @Test
    public void testFindOneExistente(){
        when(libroService.findOne(1)).thenReturn(libro);
        ResponseEntity<Libro> respuesta = libroController.findOne(1);
        assertEquals(200, respuesta.getStatusCodeValue());
        assertEquals(libro.getTitulo(), respuesta.getBody().getTitulo());
    }

    @Test
    public void testFindOneNoExistente(){
        when(libroService.findOne(2)).thenReturn(null);
        ResponseEntity<Libro> respuesta = libroController.findOne(2);
        assertEquals(404, respuesta.getStatusCodeValue());
    }

    @Test
    public void testSave(){
        when(libroService.save(any(Libro.class))).thenReturn(libro);
        ResponseEntity<Libro> respuesta = libroController.save(libro);
        assertEquals(200, respuesta.getStatusCodeValue());
        assertEquals("El Tesoro de Atahualpa", respuesta.getBody().getTitulo());
    }


    @Test
    public void testUpdateExistente(){
        when(libroService.update(eq(1),any(Libro.class))).thenReturn(libro);
        ResponseEntity<Libro> respuesta = libroController.update(1,libro);
        assertEquals(200, respuesta.getStatusCodeValue());
    }

    @Test
    public void testUpdateNoExistente(){
        when(libroService.update(eq(2),any(Libro.class))).thenReturn(null);
        ResponseEntity<Libro> respuesta = libroController.update(2,libro);
        assertEquals(404,respuesta.getStatusCodeValue());
    }

    @Test
    public void testDelete(){
        doNothing().when(libroService).delete(1);
        ResponseEntity<Void> respuesta = libroController.delete(1);
        assertEquals(204, respuesta.getStatusCodeValue());
        verify(libroService, times(1)).delete(1);
    }
}
