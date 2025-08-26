package com.distribuida.service;

import com.distribuida.dao.CarritoRepository;
import com.distribuida.dao.FacturaRepository;
import com.distribuida.dao.FacturadetalleRepository;
import com.distribuida.dao.LibroRepository;
import com.distribuida.model.Factura;
import com.distribuida.service.util.CheckoutMapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class GuestCheckoutServiceImpl implements GuestCheckoutService{

    private final CarritoRepository carritoRepository;
    private final FacturaRepository facturaRepository;
    private final FacturadetalleRepository facturaDetalleRepository;
    private final LibroRepository libroRepository;

    private static final double IVA = 0.15d;

    public GuestCheckoutServiceImpl(CarritoRepository carritoRepository,
                                    FacturaRepository facturaRepository,
                                    FacturadetalleRepository facturaDetalleRepository,
                                    LibroRepository libroRepository
                        ){
        this.carritoRepository = carritoRepository;
        this.facturaRepository = facturaRepository;
        this.facturaDetalleRepository = facturaDetalleRepository;
        this.libroRepository = libroRepository;
    }

    @Override
    @Transactional
    public Factura checkoutByToken(String token) {
        var carrito = carritoRepository.findByToken(token)
                .orElseThrow(() -> new IllegalStateException("No existe carrito para el token"));

        if(carrito.getItems() == null || carrito.getItems().isEmpty()){
            throw new IllegalStateException("El carrito está vacío");
        }

        for(var item : carrito.getItems()){
            var libro = item.getLibro();
            if(libro.getNumejemplares() < item.getCantidad()){
                throw new IllegalStateException("Stock insuficiente para : " + libro.getTitulo());
            }
        }

        for(var item : carrito.getItems()){
            var libro = item.getLibro();
            libro.setNumejemplares(libro.getNumejemplares() - item.getCantidad());
            libroRepository.save(libro);
        }

        String numFactura = "F-" + DateTimeFormatter.ofPattern("yyMMddHHmmss")
                .format(LocalDateTime.now());

        var factura = CheckoutMapper.construirFacturaDesdeCarrito(carrito, numFactura, IVA);

        factura = facturaRepository.save(factura);

        for(var item: carrito.getItems()){
            var det = CheckoutMapper.construirDetalle(factura, item);
            facturaDetalleRepository.save(det);
        }

        carrito.getItems().clear();
        carritoRepository.save(carrito);
        return factura;

    }
}
