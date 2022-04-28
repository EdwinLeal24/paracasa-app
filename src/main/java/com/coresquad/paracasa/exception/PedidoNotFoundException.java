package com.coresquad.paracasa.exception;

public class PedidoNotFoundException extends RuntimeException{
    public PedidoNotFoundException(Integer id)  {
        super("Pedido con id:  " + id + " no encontrado");
    }
}
