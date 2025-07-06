package com.distribuida.service;

import com.distribuida.dao.FacturadetalleRepository;
import com.distribuida.model.Facturadetalle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacturadetalleServiceImpl implements FacturadetalleService{

    @Autowired
    private FacturadetalleRepository facturadetalleRepository;

    @Override
    public List<Facturadetalle> findAll() {
        return facturadetalleRepository.findAll();
    }

    @Override
    public Facturadetalle findOne(int id) {
        Optional<Facturadetalle> facturadetalle = facturadetalleRepository.findById(id);
        return facturadetalle.orElse(null);
    }

    @Override
    public Facturadetalle save(Facturadetalle facturadetalle) {
        return facturadetalleRepository.save(facturadetalle);
    }

    @Override
    public Facturadetalle update(int id, Facturadetalle facturadetalle) {
        Facturadetalle facturadetalleExistente = findOne(id);

        if(facturadetalleExistente == null){
            return null;
        }

        facturadetalleExistente.setCantidad(facturadetalle.getCantidad());
        facturadetalleExistente.setSubtotal(facturadetalle.getSubtotal());
        facturadetalleExistente.setFactura(facturadetalle.getFactura());
        facturadetalleExistente.setLibro(facturadetalle.getLibro());

        return facturadetalleRepository.save(facturadetalleExistente);
    }

    @Override
    public void delete(int id) {
        if(facturadetalleRepository.existsById(id)){
            facturadetalleRepository.deleteById(id);
        }
    }
}
