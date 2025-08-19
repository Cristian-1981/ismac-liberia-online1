package com.distribuida.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CarritoItemTestUnitaria {

    private CarritoItem carritoItem;
    private Carrito carrito;
    private Libro libro;

    @BeforeEach
    public void setUp() {

        carrito = new Carrito();
        carrito.setIdCarrito(1L);

        libro = new Libro();
        libro.setIdLibro(10);
        libro.setTitulo("El Señor de los Anillos");
        libro.setPrecio(25.00);

        carritoItem = new CarritoItem();
        carritoItem.setIdCarritoItem(1L);
        carritoItem.setCarrito(carrito);
        carritoItem.setLibro(libro);
        carritoItem.setCantidad(2);
        carritoItem.setPrecioUnitario(new BigDecimal("25.00"));
    }

    @Test
    public void testCarritoItemSetters() {
        // Creamos un nuevo item y le asignamos valores con los setters
        CarritoItem nuevoItem = new CarritoItem();

        Carrito nuevoCarrito = new Carrito();
        nuevoCarrito.setIdCarrito(2L);

        Libro nuevoLibro = new Libro();
        nuevoLibro.setIdLibro(20);

        nuevoItem.setIdCarritoItem(2L);
        nuevoItem.setCarrito(nuevoCarrito);
        nuevoItem.setLibro(nuevoLibro);
        nuevoItem.setCantidad(5);
        nuevoItem.setPrecioUnitario(new BigDecimal("12.50"));

        // Verificamos que los setters funcionaron correctamente
        assertAll("Validar datos asignados por setters",
                () -> assertEquals(2L, nuevoItem.getIdCarritoItem()),
                () -> assertEquals(2L, nuevoItem.getCarrito().getIdCarrito()),
                () -> assertEquals(20, nuevoItem.getLibro().getIdLibro()),
                () -> assertEquals(5, nuevoItem.getCantidad()),
                () -> assertEquals(0, new BigDecimal("12.50").compareTo(nuevoItem.getPrecioUnitario()))
        );
    }
}
