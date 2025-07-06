package com.distribuida.test;

import com.distribuida.entities.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ClienteTestUnitaria {

    private Cliente cliente;
    @BeforeEach
    public void setUp(){
        cliente = new Cliente(1,"1716699515","Cristian","Barreno","av. general","0984443705","correo@gmail.com");
    }
    @Test
    public void testClienteConstructorAndGetteres(){

        assertAll("Validar datos cliente",
                () -> assertEquals(1,cliente.getIdCliente()),
                () -> assertEquals("1716699515",cliente.getCedula()),
                () -> assertEquals("Cristian",cliente.getNombre()),
                () -> assertEquals("Barreno",cliente.getApellido()),
                () -> assertEquals("av. general",cliente.getDireccion()),
                () -> assertEquals("0984443705",cliente.getTelefono()),
                () -> assertEquals("correo@gmail.com",cliente.getCorreo())
        );
    }

    @Test
    public void testClienteSetters(){

        cliente = new Cliente();

        cliente.setIdCliente(2);
        cliente.setCedula("1701234562");
        cliente.setNombre("Juan2");
        cliente.setApellido("Taipe2");
        cliente.setDireccion("Tumbaco2");
        cliente.setTelefono("0987654322");
        cliente.setCorreo("jtaipe2@correo.com");

        assertAll( "Validar datos CLiente",
                () -> assertEquals(2,cliente.getIdCliente()),
                () -> assertEquals("1701234562",cliente.getCedula()),
                () -> assertEquals("Juan2",cliente.getNombre()),
                () -> assertEquals("Taipe2",cliente.getApellido()),
                () -> assertEquals("Tumbaco2",cliente.getDireccion()),
                () -> assertEquals("0987654322",cliente.getTelefono()),
                () -> assertEquals("jtaipe2@correo.com",cliente.getCorreo())
        );

    }

    @Test
    public void testToString(){

        String str = cliente.toString();
        assertAll( "Validar datos de CLiente",
                () -> assertTrue(str.contains("1")),
                () -> assertTrue(str.contains("1716699515")),
                () -> assertTrue(str.contains("Cristian")),
                () -> assertTrue(str.contains("Barreno")),
                () -> assertTrue(str.contains("av. general")),
                () -> assertTrue(str.contains("0984443705")),
                () -> assertTrue(str.contains("correo@gmail.com"))
        );

    }





}
