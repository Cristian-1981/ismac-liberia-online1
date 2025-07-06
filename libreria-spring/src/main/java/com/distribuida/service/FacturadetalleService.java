package com.distribuida.service;

import com.distribuida.model.Facturadetalle;

import java.util.List;

public interface FacturadetalleService {

    public List<Facturadetalle> findAll();

    public Facturadetalle findOne(int id);

    public Facturadetalle save(Facturadetalle facturadetalle);

    public Facturadetalle update(int id, Facturadetalle facturadetalle);

    public void delete(int id);

}
