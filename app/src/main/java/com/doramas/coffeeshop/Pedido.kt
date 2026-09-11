package com.doramgajo.coffeeshop

data class Pedido (var cliente : String, var tipo : String,
                   var descafeinado : Boolean, var gramosAzucar : Int) {

    companion object {
        var pedidos : ArrayList<Pedido> = ArrayList()

        fun annadirPedido(pedido : Pedido) {
            pedidos.add(pedido)
        }

        fun eliminarPedido() {
            pedidos.remove(pedidos[0])
        }
    }



}