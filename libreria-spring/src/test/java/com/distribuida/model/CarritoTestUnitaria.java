package com.distribuida.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CarritoTestUnitaria {

    private Carrito carrito;
    private Cliente cliente;

    @BeforeEach
    public void setUp(){

        cliente = new Cliente(1,"1716699515","Cristian", "Barreno", "av. general", "0984443705", "correo@gmail.com");

        CarritoItem item = new CarritoItem();
        item.setIdCarritoItem(1L);
        carrito = new Carrito();
        carrito.setIdCarrito(1L);
        carrito.setCliente(cliente);
        carrito.setToken("token-123");
        //carrito.setItems(List.of(item));
        carrito.setSubtotal(new BigDecimal("50.00"));
        carrito.setImpuestos(new BigDecimal("6.00"));
        carrito.setTotal(new BigDecimal("56.00"));
        carrito.setActualizadoEn(LocalDateTime.now());

    }

    @Test
    public void testCarritoGetters() {
        // Validamos los getters de Carrito
        assertAll("Validar datos del carrito",
                () -> assertEquals(1L, carrito.getIdCarrito()),
                () -> assertNotNull(carrito.getCliente()),
                () -> assertEquals(1, carrito.getCliente().getIdCliente()),
                () -> assertEquals("token-123", carrito.getToken()),
                () -> assertEquals(0, new BigDecimal("50.00").compareTo(carrito.getSubtotal())),
                () -> assertEquals(0, new BigDecimal("6.00").compareTo(carrito.getImpuestos())),
                () -> assertEquals(0, new BigDecimal("56.00").compareTo(carrito.getTotal())),
                () -> assertNotNull(carrito.getActualizadoEn())
        );
    }

}
