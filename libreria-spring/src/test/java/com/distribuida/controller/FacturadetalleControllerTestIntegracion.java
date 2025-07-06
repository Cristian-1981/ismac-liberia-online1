package com.distribuida.controller;

import com.distribuida.model.*;
import com.distribuida.service.FacturadetalleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SuppressWarnings("removal")
@WebMvcTest(FacturadetalleController.class)
public class FacturadetalleControllerTestIntegracion {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FacturadetalleService facturadetalleService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetFacturadetalles() throws Exception {
        Cliente cliente = new Cliente();
        Categoria categoria = new Categoria();
        Autor autor = new Autor();
        Factura factura = new Factura(1, "FAC-0001",new Date(),100.00,15.00,115.00,cliente);
        Libro libro = new Libro(1,"El Tesoro de Atahualpa","Editorial Luna",125,"Primera","Español",new Date(),"Trata sobre la busqueda del tesoro que escondio Atahualpa","Simple","KLIP",50,"Dibujada","Full color",100.00,categoria,autor);
        Facturadetalle facturadetalle = new Facturadetalle(1,5,50.25,factura,libro);


        Mockito.when(facturadetalleService.findAll()).thenReturn(List.of(facturadetalle));

        mockMvc.perform(get("/api/facturadetalles"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].cantidad").value(5));
    }

    @Test
    public void testPostFacturadetalle() throws Exception {
        Cliente cliente = new Cliente();
        Categoria categoria = new Categoria();
        Autor autor = new Autor();

        Factura factura = new Factura(1, "FAC-0001",new Date(),100.00,15.00,115.00,cliente);
        Libro libro = new Libro(1,"El Tesoro de Atahualpa","Editorial Luna",125,"Primera","Español",new Date(),"Trata sobre la busqueda del tesoro que escondio Atahualpa","Simple","KLIP",50,"Dibujada","Full color",100.00,categoria,autor);
        Facturadetalle facturadetalle = new Facturadetalle(1,5,50.25,factura,libro);

        Mockito.when(facturadetalleService.save(any(Facturadetalle.class))).thenReturn(facturadetalle);

        mockMvc.perform(post("/api/facturadetalles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(facturadetalle)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.cantidad").value(5));
    }


    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete("/api/facturadetalles/1")).andExpect(status().isNoContent());
    }

}
