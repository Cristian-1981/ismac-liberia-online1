package com.distribuida.dao;


import com.distribuida.model.Carrito;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@Rollback(value = false)

public class CarritoRepositoryTestIntegracion {

    @Autowired
    private CarritoRepository carritoRepository;

    // Inyectamos ClienteRepository para poder obtener un cliente existente
    // y asociarlo a un nuevo carrito.
    @Autowired
    private ClienteRepository clienteRepository;

    @Test
    public void findAll(){
        List<Carrito> carritos = carritoRepository.findAll();
        assertNotNull(carritos);
        assertTrue(carritos.size() > 0);
        for(Carrito item: carritos){
            System.out.println(item.toString());
        }
    }

}
