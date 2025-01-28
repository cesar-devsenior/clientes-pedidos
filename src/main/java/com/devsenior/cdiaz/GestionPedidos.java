package com.devsenior.cdiaz;

import java.util.ArrayList;
import java.util.List;

public class GestionPedidos {
    private List<Pedido> pedidos;
    private GestionClientes clientes;

    public GestionPedidos(GestionClientes clientes) {
        this.clientes = clientes;
        pedidos = new ArrayList<>();
    }

    public void crearPedido(String clienteId, String producto, int cantidad) throws PedidoInvalidoException {
        // Cliente no existe
        try {
            var cliente = clientes.buscarCliente(clienteId);
        } catch (ClienteNoEncontradoException e) {
            throw new PedidoInvalidoException("El cliente no existe");
        }

        // Producto es vacio
        if (producto.isEmpty()) {
            throw new PedidoInvalidoException("El producto no puede ser vacio");
        }

        // Cantidad <= 0
        if (cantidad <= 0) {
            throw new PedidoInvalidoException("La cantidad no es válida");
        }

        var pedido = new Pedido(clienteId, producto, cantidad);
        pedidos.add(pedido);
    }

    public Pedido buscarPedido(String clienteId, String producto) {

        var cliente = clientes.buscarCliente(clienteId);    
        
        for (var pedido : pedidos) {
            if (pedido.getClienteId().equals(clienteId)
                    && pedido.getProducto().equals(producto)) {
                return pedido;
            }
        }
        
        return null;
    }
}
