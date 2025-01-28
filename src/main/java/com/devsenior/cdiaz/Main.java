package com.devsenior.cdiaz;

public class Main {
    public static void main(String[] args) {
        

        var clientes = new GestionClientes();
        clientes.registrarCliente("1", "Andres");
        clientes.registrarCliente("2", "Cristina");
        clientes.registrarCliente("3", "Fredy");

        var pedidos = new GestionPedidos(clientes);
        try {
            pedidos.crearPedido("1", "Panela", 2);
            pedidos.crearPedido("2", "Arroz", 5);
            pedidos.crearPedido("5", "Panela", 10);
        } catch (PedidoInvalidoException e) {
            System.err.println("Ha ocurrido un error al gestionar el pedido\n\t" + e.getMessage());
        }

        System.out.println("Gracias por usar nuestro servicio");
    }
}