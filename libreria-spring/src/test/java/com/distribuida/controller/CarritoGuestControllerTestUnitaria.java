package com.distribuida.controller;

import com.distribuida.model.Carrito;
import com.distribuida.service.CarritoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class CarritoGuestControllerTestUnitaria {

    @Mock
    private CarritoService carritoService;

    @InjectMocks
    private CarritoGuestController carritoGuestController;

    private Carrito carrito;
    private static final String TOKEN = "test-token-123";

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        carrito = new Carrito();
        carrito.setIdCarrito(1L);
        carrito.setToken(TOKEN);
    }

    @Test
    public void testFindOneExistente() {
        when(carritoService.getOrCreateByToken(anyString())).thenReturn(carrito);

        ResponseEntity<Carrito> response = carritoGuestController.createOrGet(TOKEN);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(TOKEN, response.getBody().getToken());
        verify(carritoService, times(1)).getOrCreateByToken(TOKEN);
    }

    @Test
    public void testSave() {
        when(carritoService.addItem(anyString(), anyInt(), anyInt())).thenReturn(carrito);
        Map<String, Integer> body = new HashMap<>();
        body.put("libroId", 1);
        body.put("cantidad", 2);

        ResponseEntity<Carrito> response = carritoGuestController.addItem(TOKEN, body);

        assertEquals(200, response.getStatusCodeValue());
        verify(carritoService, times(1)).addItem(TOKEN, 1, 2);
    }
}
