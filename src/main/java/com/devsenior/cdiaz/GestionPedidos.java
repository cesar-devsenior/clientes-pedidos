package com.devsenior.cdiaz;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GestionPedidos {

    private static final Logger LOG = LoggerFactory.getLogger(GestionPedidos.class);

    private List<Pedido> pedidos;
    private GestionClientes clientes;

    public GestionPedidos(GestionClientes clientes) {
        LOG.debug("Creando una instancia de Gestion de pedidos");
        this.clientes = clientes;
        pedidos = new ArrayList<>();
    }

    public void crearPedido(String clienteId, String producto, int cantidad) throws PedidoInvalidoException {
        LOG.trace("Llamando a crear pedido");
        LOG.debug("Los parametros son: {} {} {}", clienteId, producto, cantidad);
        // Cliente no existe
        try {
            var cliente = clientes.buscarCliente(clienteId);
            LOG.debug("El cliente del pedido es: {}", cliente);
        } catch (ClienteNoEncontradoException e) {
            LOG.warn("Cliente no existe: {}", clienteId);
            throw new PedidoInvalidoException("El cliente no existe");
        }

        // Producto es vacio
        if (producto.isEmpty()) {
            LOG.warn("Producto está vacio");
            throw new PedidoInvalidoException("El producto no puede ser vacio");
        }

        // Cantidad <= 0
        if (cantidad <= 0) {
            LOG.warn("La cantidad es menor que 0");
            throw new PedidoInvalidoException("La cantidad no es válida");
        }

        var pedido = new Pedido(clienteId, producto, cantidad);
        LOG.debug("Agregando el pedido: {}", pedido);
        pedidos.add(pedido);
        LOG.info("Pedido agregado con exito: {}", pedido);
    }

    public Pedido buscarPedido(String clienteId, String producto) {
        LOG.trace("Ingreso a buscar un pedido");
        LOG.debug("Ingresa con los datos {} {}", clienteId, producto);

        var cliente = clientes.buscarCliente(clienteId);

        for (var pedido : pedidos) {
            if (pedido.getClienteId().equals(clienteId)
                    && pedido.getProducto().equals(producto)) {
                return pedido;
            }
        }

        LOG.warn("El pedido no existe!");
        return null;
    }
}
