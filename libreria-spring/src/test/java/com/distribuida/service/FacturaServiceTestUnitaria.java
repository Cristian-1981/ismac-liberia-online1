package com.distribuida.service;

import com.distribuida.dao.FacturaRepository;
import com.distribuida.model.Cliente;
import com.distribuida.model.Factura;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FacturaServiceTestUnitaria {

    @Mock
    private FacturaRepository facturaRepository;


    @InjectMocks
    private FacturaServiceImpl facturaService;

    private Factura factura;
    private Cliente cliente;

    @BeforeEach
    public void setUp(){
        factura = new Factura();
        cliente = new Cliente(1,"1701234567","Juan","Taipe", "Av. por ahi", "0987654321","jtaipe@correo.com");
        factura.setIdFactura(1);
        factura.setNumFactura("FAC-0001");
        factura.setFecha(new Date());
        factura.setTotalNeto(100.00);
        factura.setIva(15.00);
        factura.setTotal(115.00);
        factura.setCliente(cliente);

    }

    @Test
    public void findAll(){
        when(facturaRepository.findAll()).thenReturn(List.of(factura));
        List<Factura> facturas = facturaService.findAll();
        assertEquals(1, facturas.size());
        assertNotNull(facturas);
        verify(facturaRepository, times(1)).findAll();
    }

    @Test
    public void findOneExistente(){
        when(facturaRepository.findById(1)).thenReturn(Optional.ofNullable(factura));
        Factura factura = facturaService.findOne(1);
        assertNotNull(factura);
        assertEquals("FAC-0001", factura.getNumFactura());
    }

    @Test
    public void findOneNoExistente(){
        when(facturaRepository.findById(2)).thenReturn(Optional.empty());
        Factura factura = facturaService.findOne(2);
        assertNull(factura);
    }

    @Test
    public void save(){
        when(facturaRepository.save(factura)).thenReturn(factura);
        Factura factura1 = facturaService.save(factura);
        assertEquals("FAC-0001", factura1.getNumFactura());
        assertEquals(115.0, factura1.getTotal());
    }

    @Test
    public void updateExistente(){
        Factura facturaNueva = new Factura();

        Cliente cliente = new Cliente(2,"1701234562","Juan2","Taipe2", "Av. por ahi2", "0987654322","jtaipe2@correo.com");
        facturaNueva.setNumFactura("FAC-0002");
        facturaNueva.setFecha(new Date());
        facturaNueva.setTotalNeto(200.00);
        facturaNueva.setIva(30.00);
        facturaNueva.setTotal(230.00);
        facturaNueva.setCliente(cliente);

        when(facturaRepository.findById(1)).thenReturn(Optional.ofNullable(factura));
        when(facturaRepository.save(factura)).thenReturn(factura);

        Factura facturaRespuesta = facturaService.update(1, facturaNueva);
        assertNotNull(facturaRespuesta);
        assertEquals("FAC-0002", facturaRespuesta.getNumFactura());
        assertEquals(230.0, facturaRespuesta.getTotal());
        verify(facturaRepository, times(1)).save(factura);
    }

    @Test
    public void updateNoExistente(){
        Factura factura = new Factura();
        when(facturaRepository.findById(999)).thenReturn(Optional.empty());
        Factura factura1 = facturaService.update(999, factura);
        assertNull(factura1);
        verify(facturaRepository, times(0)).save(any());
    }

    @Test
    public void delete(){
        when(facturaRepository.existsById(1)).thenReturn(true);
        facturaService.delete(1);
        verify(facturaRepository, times(1)).deleteById(1);
    }
}
