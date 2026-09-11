package com.doramgajo.coffeeshop

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdaptadorDos(var listaPedidos : ArrayList<Pedido>, private val mOnClickListener : InterfazEventos) : RecyclerView.Adapter<AdaptadorDos.ViewHolderPedido>() {
    inner class ViewHolderPedido(itemView : View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        var imagen : ImageView
        var cuadroNombre : TextView
        var cuadroTipo : TextView

        override fun onClick(view: View) {
            val posicion = absoluteAdapterPosition
            mOnClickListener.clickEnElemento(posicion)
        }

        init {
            imagen = itemView.findViewById(R.id.imagenLista)
            cuadroNombre = itemView.findViewById(R.id.textoLista)
            cuadroTipo = itemView.findViewById(R.id.tipoCafe)

            itemView.setOnClickListener(this)
            //añado la siguiente linea porque no se puede pulsar sobre el textview del nombre del cliente
            //en la lista, el resto de elementos si ejecutan el evento pero ese textview no
            itemView.findViewById<TextView>(R.id.textoLista).setOnClickListener(this)
        }



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderPedido {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.objeto_lista, parent, false)
        return ViewHolderPedido(item)
    }

    override fun onBindViewHolder(holder: ViewHolderPedido, position: Int) {
        val pedidoActual = listaPedidos[position]
        holder.cuadroNombre.text = pedidoActual.cliente
        holder.cuadroTipo.text = pedidoActual.tipo

        when (pedidoActual.tipo) {
            "latte" -> holder.imagen.setImageResource(R.drawable.latte)
            "espresso" -> holder.imagen.setImageResource(R.drawable.espresso)
            "machiatto" -> holder.imagen.setImageResource(R.drawable.machiatto)
        }

    }

    override fun getItemCount() = listaPedidos.size

}