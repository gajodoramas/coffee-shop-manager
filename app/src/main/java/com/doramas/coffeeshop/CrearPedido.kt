package com.doramgajo.coffeeshop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.SeekBar
import android.widget.Spinner
import android.widget.TextView

class CrearPedido : AppCompatActivity() {


    lateinit var botonGuardar: TextView
    lateinit var campoCliente: TextView
    lateinit var spinner: Spinner
    lateinit var checkBox: CheckBox
    lateinit var seekBar: SeekBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_pedido)

        val intentDevuelto = Intent()

        botonGuardar = findViewById<Button>(R.id.botonGuardar)
        campoCliente = findViewById<EditText>(R.id.editTextTextPersonName)
        spinner = findViewById<Spinner>(R.id.spinner)
        checkBox = findViewById<CheckBox>(R.id.checkBox)
        seekBar = findViewById<SeekBar>(R.id.seekBar2)


        var listaOpciones = ArrayList<String>()

        listaOpciones.add("latte")
        listaOpciones.add("machiatto")
        listaOpciones.add("espresso")

        val adaptador =
            ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, listaOpciones)
        spinner.adapter = adaptador

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{

            override fun onProgressChanged(p0: SeekBar?, p1: Int, modificadoPorUsuario: Boolean) {
                findViewById<TextView>(R.id.gramosAzucar).text = "${seekBar.progress} gramos"
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {


            }
        })

        botonGuardar.setOnClickListener {

            if (campoCliente.text.toString().toLowerCase() == "ana botella" && spinner.selectedItem.toString() == "latte") {

                MainActivity.mediaPlayer.start()
            }
            val nombre = if (campoCliente.text.toString().isBlank()) "Anónimo" else campoCliente.text.toString()

            intentDevuelto.putExtra("pedido", nombre)
            intentDevuelto.putExtra("tipo", spinner.selectedItem.toString())
            intentDevuelto.putExtra("descafeinado", checkBox.isChecked)
            intentDevuelto.putExtra("azucar", seekBar.progress)
            setResult(RESULT_OK, intentDevuelto)

            finish()
        }

        findViewById<Button>(R.id.botonCancelar).setOnClickListener {
            finish()
        }

    }
}