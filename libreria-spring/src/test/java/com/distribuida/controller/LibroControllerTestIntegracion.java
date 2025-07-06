package com.distribuida.controller;

import com.distribuida.model.*;
import com.distribuida.model.Libro;
import com.distribuida.service.LibroService;
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
@WebMvcTest(LibroController.class)
public class LibroControllerTestIntegracion {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LibroService libroService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetLibros() throws Exception {
        Categoria categoria = new Categoria(0, "Aventura","Busqueda de tesoros");
        Autor autor = new Autor(0, "Juan","Neruda","Colombia", "Av. Sebastian", "098565858","juanneruda@gmail.com");
        Libro libro = new Libro(1,"El Tesoro de Atahualpa","Editorial Luna",125,"Primera","Español",new Date(),"Trata sobre la busqueda del tesoro que escondio Atahualpa","Simple","KLIP",50,"Dibujada","Full color",100.00,categoria,autor);

        Mockito.when(libroService.findAll()).thenReturn(List.of(libro));

        mockMvc.perform(get("/api/libros"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].titulo").value("El Tesoro de Atahualpa"));
    }

    @Test
    public void testPostLibro() throws Exception {
        Categoria categoria = new Categoria(0, "Aventura","Busqueda de tesoros");
        Autor autor = new Autor(0, "Juan","Neruda","Colombia", "Av. Sebastian", "098565858","juanneruda@gmail.com");
        Libro libro = new Libro(1,"El Tesoro de Atahualpa","Editorial Luna",125,"Primera","Español",new Date(),"Trata sobre la busqueda del tesoro que escondio Atahualpa","Simple","KLIP",50,"Dibujada","Full color",100.00,categoria,autor);

        Mockito.when(libroService.save(any(Libro.class))).thenReturn(libro);

        mockMvc.perform(post("/api/libros")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(libro)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.titulo").value("El Tesoro de Atahualpa"));
    }


    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete("/api/libros/1")).andExpect(status().isNoContent());
    }

}
