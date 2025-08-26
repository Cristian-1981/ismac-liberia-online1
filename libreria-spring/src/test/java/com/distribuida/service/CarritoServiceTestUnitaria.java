package com.distribuida.service;

import com.distribuida.dao.CarritoItemRepository;
import com.distribuida.dao.CarritoRepository;
import com.distribuida.dao.ClienteRepository;
import com.distribuida.dao.LibroRepository;
import com.distribuida.model.Carrito;
import com.distribuida.model.CarritoItem;
import com.distribuida.model.Cliente;
import com.distribuida.model.Libro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static jdk.internal.org.objectweb.asm.util.CheckClassAdapter.verify;
import static jdk.jfr.internal.jfc.model.Constraint.any;
import static jdk.jfr.internal.jfc.model.Constraint.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.ExpectedCount.never;

@ExtendWith(MockitoExtension.class)
public class CarritoServiceTestUnitaria {

    @Mock
    private CarritoRepository carritoRepository;
    @Mock
    private CarritoItemRepository carritoItemRepository;
    @Mock
    private ClienteRepository clienteRepository;
    @Mock
    private LibroRepository libroRepository;

    @InjectMocks
    private CarritoServiceImpl carritoService;

    private Cliente cliente;
    private Libro libro;
    private Carrito carrito;
    private CarritoItem carritoItem;

    private static final int CLIENTE_ID = 1;
    private static final int LIBRO_ID = 1;
    private static final long CARRITO_ID = 1L;
    private static final long CARRITO_ITEM_ID = 1L;
    private static final String TOKEN = "test-token-456";

    @BeforeEach
    void setUp() {
        cliente = new Cliente();
        cliente.setIdCliente(CLIENTE_ID);
        cliente.setNombre("Carlos");

        libro = new Libro();
        libro.setIdLibro(LIBRO_ID);
        libro.setTitulo("El principito");
        libro.setPrecio(10.0);

        carrito = new Carrito();
        carrito.setIdCarrito(CARRITO_ID);
        carrito.setCliente(cliente);

        carritoItem = new CarritoItem();
        carritoItem.setIdCarritoItem(CARRITO_ITEM_ID);
        carritoItem.setCarrito(carrito);
        carritoItem.setLibro(libro);
        carritoItem.setCantidad(1);
        carritoItem.setPrecioUnitario(BigDecimal.valueOf(libro.getPrecio()));
    }

    @Test
    @DisplayName("Debe devolver un carrito existente para un cliente")
    void testFindOneExistente() {

        when(clienteRepository.findById(CLIENTE_ID)).thenReturn(Optional.of(cliente));
        when(carritoRepository.findByCliente(cliente)).thenReturn(Optional.of(carrito));

        Carrito resultado = carritoService.getOrCreateByClienteId(CLIENTE_ID, TOKEN);
        assertNotNull(resultado);
        assertEquals(CARRITO_ID, resultado.getIdCarrito());
        verify(carritoRepository, never()).save(any(Carrito.class));
    }

}
