package com.doramgajo.coffeeshop

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    companion object {

        lateinit var adaptador: AdaptadorDos
        lateinit var recyclerView: RecyclerView
        lateinit var mediaPlayer : MediaPlayer

        fun notificarCreacion() {
            adaptador.notifyItemInserted(Pedido.pedidos.size)
            recyclerView.scrollToPosition(0)
        }
    }

    private val funcion = object : InterfazEventos {
        override fun clickEnElemento(pos: Int) {

            val intent = Intent(applicationContext, Detalles::class.java)
            intent.putExtra("posicion", pos)
            intent.putExtra("nombre", Pedido.pedidos.get(pos).cliente)
            intent.putExtra("tipo", Pedido.pedidos.get(pos).tipo)
            intent.putExtra("descafeinado", Pedido.pedidos.get(pos).descafeinado)
            intent.putExtra("azucar", Pedido.pedidos.get(pos).gramosAzucar)
            startActivity(intent)

        }
    }

    val funcionResultado = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        resultado ->
        if(resultado.resultCode == RESULT_OK) {
            val datos = resultado.data

            var nombre = datos?.getStringExtra("pedido")
            if (nombre.isNullOrBlank()) {
                nombre = "Paco"
            }
            var tipo = datos?.getStringExtra("tipo")
            if (tipo.isNullOrBlank()) {
                tipo = "latte"
            }
            var descafe = datos?.getBooleanExtra("descafeinado", true)
            if (descafe == null) {
                descafe = true
            }
            var azucar = datos?.getIntExtra("azucar", 0)
            if (azucar == null) {
                azucar = 0
            }
            Pedido.annadirPedido(Pedido(nombre, tipo, descafe, azucar))
            notificarCreacion()
        } else {
            Toast.makeText(this, "No se pudo añadir el pedido", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)


        mediaPlayer = MediaPlayer.create(this, R.raw.cup)

findViewById<Button>(R.id.botonCrear).setOnClickListener {
    val otroIntent = Intent(this, CrearPedido::class.java)

    funcionResultado.launch(otroIntent)
}

        findViewById<Button>(R.id.botonBorrar).setOnClickListener {

            if (Pedido.pedidos.size == 0) {
                Toast.makeText(this, "No hay pedidos que borrar.", Toast.LENGTH_SHORT).show()
            } else {
                Pedido.eliminarPedido()
                adaptador.notifyItemRemoved(0)
                recyclerView.scrollToPosition(0)
            }
        }

        adaptador = AdaptadorDos(Pedido.pedidos, funcion)

        recyclerView.layoutManager = LinearLayoutManager(this)

        recyclerView.addItemDecoration(
            DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL)
        )


        recyclerView.adapter = adaptador


    }
}