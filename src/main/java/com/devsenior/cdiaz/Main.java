package com.devsenior.cdiaz;

import org.apache.logging.log4j.LogManager;

public class Main {
    public static void main(String[] args) {
        var log = LogManager.getLogger(Main.class);

        log.info("Inicia la ejecucion del programa");
        var clientes = new GestionClientes();
        
        log.debug("Registrando cliente (1, Andres)");
        clientes.registrarCliente("1", "Andres");
        log.info("Se creo el cliente Andres con el id 1");

        log.debug("Registrando cliente (2, Cristina)");
        clientes.registrarCliente("2", "Cristina");
        log.info("Se creo el cliente Cristina con el id 2");

        log.debug("Registrando cliente (3, Fredy)");
        clientes.registrarCliente("3", "Fredy");
        log.info("Se creo el cliente Fredy con el id 3");

        var pedidos = new GestionPedidos(clientes);
        try {
            log.debug("Creando el pedido (1, Panela, 2)");
            pedidos.crearPedido("1", "Panela", 2);


            log.debug("Creando el pedido (2, Arroz, 5)");
            pedidos.crearPedido("2", "Arroz", 5);
            
            log.debug("Creando el pedido (5, Panela, 10)");
            pedidos.crearPedido("5", "Panela", 10);
        } catch (PedidoInvalidoException e) {
            log.warn("No se pudo agregar uno de los pedidos");
            System.err.println("Ha ocurrido un error al gestionar el pedido\n\t" + e.getMessage());
        }

        System.out.println("Gracias por usar nuestro servicio");
        log.info("Salio de la aplicacion");
    }
}