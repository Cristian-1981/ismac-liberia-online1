package com.distribuida.service.util;

import com.distribuida.model.Carrito;
import com.distribuida.model.CarritoItem;
import com.distribuida.model.Factura;
import com.distribuida.model.Facturadetalle;

import java.util.Date;

public class CheckoutMapper {

    private CheckoutMapper(){}

    public static Factura construirFacturaDesdeCarrito(Carrito carrito, String numFactura, double tasaIva){

        Factura f = new Factura();
        f.setNumFactura(numFactura);
        f.setFecha(new Date());
        f.setCliente(carrito.getCliente());

        double subtotal = carrito.getItems().stream() // Stream<CarritoItem>
                .mapToDouble(carritoItem -> carritoItem.getTotal().doubleValue()) // DoubleStream
                .sum();

        double iva = Math.max(0, subtotal) * tasaIva;
        double total = subtotal + iva;

        f.setTotalNeto(subtotal);
        f.setIva(iva);
        f.setTotal(total);

        return f;
    }


    public static Facturadetalle construirDetalle(Factura factura, CarritoItem carritoItem) {
        Facturadetalle d = new Facturadetalle();
        d.setFactura(factura);
        d.setLibro(carritoItem.getLibro());
        d.setCantidad(carritoItem.getCantidad());
        d.setSubtotal(carritoItem.getTotal().doubleValue());
        return d;
    }
}
