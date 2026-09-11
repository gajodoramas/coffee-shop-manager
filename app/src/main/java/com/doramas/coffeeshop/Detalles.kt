package com.doramgajo.coffeeshop

import android.graphics.text.LineBreaker
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi

class Detalles : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles)

        val imagenCafe = findViewById<ImageView>(R.id.imagenCafe)
        val textoLargo = findViewById<TextView>(R.id.textoLargo)

        textoLargo.justificationMode = LineBreaker.JUSTIFICATION_MODE_INTER_WORD

        val intentRecibido = intent

        findViewById<TextView>(R.id.textoTipo).text = intentRecibido.getStringExtra("tipo")

        when (intentRecibido.getStringExtra("tipo")) {
            "latte" -> imagenCafe.setImageResource(R.drawable.latte)
            "espresso" -> imagenCafe.setImageResource(R.drawable.espresso)
            "machiatto" -> imagenCafe.setImageResource(R.drawable.machiatto)
        }

        when (intentRecibido.getStringExtra("tipo")) {
            "latte" -> textoLargo.text = getString(R.string.latte)
            "espresso" -> textoLargo.text = getString(R.string.espresso)
            "machiatto" -> textoLargo.text = getString(R.string.machiatto)
        }


            findViewById<TextView>(R.id.textoClienteNombre).text = intentRecibido.getStringExtra("nombre")
        findViewById<TextView>(R.id.textoPosicion).text = "Posición: ${intentRecibido.getIntExtra("posicion", 0)}"

        if (intentRecibido.getBooleanExtra("descafeinado", true)) {
            findViewById<TextView>(R.id.textoCafeina).text = "Descafeinado"
        } else {
            findViewById<TextView>(R.id.textoCafeina).text = "Con cafeina"
        }

        if (intentRecibido.getIntExtra("azucar", 0) == 0) {
            findViewById<TextView>(R.id.textoGramosAzucar).text = "Sin azúcar"
        } else {
            findViewById<TextView>(R.id.textoGramosAzucar).text = "${intentRecibido.getIntExtra("azucar", 0)} gramos de azúcar"
        }



    }
}