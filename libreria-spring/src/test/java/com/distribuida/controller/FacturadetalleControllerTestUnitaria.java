package com.distribuida.controller;

import com.distribuida.model.*;
import com.distribuida.service.FacturadetalleService;
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

public class FacturadetalleControllerTestUnitaria {

    @InjectMocks
    private FacturadetalleController facturadetalleController;

    @Mock
    private FacturadetalleService facturadetalleService;

    private Facturadetalle facturadetalle;
    private Factura factura;
    private Libro libro;
    private Cliente cliente;
    private Categoria categoria;
    private Autor autor;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        factura = new Factura(1, "FAC-0001",new Date(),100.00,15.00,115.00,cliente);
        libro = new Libro(1,"El Tesoro de Atahualpa","Editorial Luna",125,"Primera","Español",new Date(),"Trata sobre la busqueda del tesoro que escondio Atahualpa","Simple","KLIP",50,"Dibujada","Full color",100.00,categoria,autor);
        facturadetalle = new Facturadetalle();
        facturadetalle.setIdFacturadetalle(1);
        facturadetalle.setCantidad(5);
        facturadetalle.setSubtotal(50.25);
        facturadetalle.setFactura(factura);
        facturadetalle.setLibro(libro);

    }

    @Test
    public void testFindAll(){
        when(facturadetalleService.findAll()).thenReturn(List.of(facturadetalle));
        ResponseEntity<List<Facturadetalle>> respuesta = facturadetalleController.findAll();
        assertEquals(200, respuesta.getStatusCodeValue());
        assertEquals(1,respuesta.getBody().size());
    }

    @Test
    public void testFindOneExistente(){
        when(facturadetalleService.findOne(1)).thenReturn(facturadetalle);
        ResponseEntity<Facturadetalle> respuesta = facturadetalleController.findOne(1);
        assertEquals(200, respuesta.getStatusCodeValue());
        assertEquals(facturadetalle.getCantidad(), respuesta.getBody().getCantidad());
    }

    @Test
    public void testFindOneNoExistente(){
        when(facturadetalleService.findOne(2)).thenReturn(null);
        ResponseEntity<Facturadetalle> respuesta = facturadetalleController.findOne(2);
        assertEquals(404, respuesta.getStatusCodeValue());
    }

    @Test
    public void testSave(){
        when(facturadetalleService.save(any(Facturadetalle.class))).thenReturn(facturadetalle);
        ResponseEntity<Facturadetalle> respuesta = facturadetalleController.save(facturadetalle);
        assertEquals(200, respuesta.getStatusCodeValue());
        assertEquals(5, respuesta.getBody().getCantidad());
    }

    @Test
    public void testUpdateExistente(){
        when(facturadetalleService.update(eq(1),any(Facturadetalle.class))).thenReturn(facturadetalle);
        ResponseEntity<Facturadetalle> respuesta = facturadetalleController.update(1,facturadetalle);
        assertEquals(200, respuesta.getStatusCodeValue());
    }

    @Test
    public void testUpdateNoExistente(){
        when(facturadetalleService.update(eq(2),any(Facturadetalle.class))).thenReturn(null);
        ResponseEntity<Facturadetalle> respuesta = facturadetalleController.update(2,facturadetalle);
        assertEquals(404,respuesta.getStatusCodeValue());
    }

    @Test
    public void testDelete(){
        doNothing().when(facturadetalleService).delete(1);
        ResponseEntity<Void> respuesta = facturadetalleController.delete(1);
        assertEquals(204, respuesta.getStatusCodeValue());
        verify(facturadetalleService, times(1)).delete(1);
    }
}
