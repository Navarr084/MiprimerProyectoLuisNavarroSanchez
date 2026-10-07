package com.example.retouno

import android.os.Bundle
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var cbAndroid: CheckBox
    private lateinit var cbReact: CheckBox
    private lateinit var cbFlutter: CheckBox
    private lateinit var resultado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        cbAndroid = findViewById(R.id.cbAndroid)
        cbReact = findViewById(R.id.cbReact)
        cbFlutter = findViewById(R.id.cbFlutter)
        resultado = findViewById(R.id.resultado)

        val listener = CompoundButton.OnCheckedChangeListener { _, _ ->
            actualizarTecnologias()
        }

        cbAndroid.setOnCheckedChangeListener(listener)
        cbReact.setOnCheckedChangeListener(listener)
        cbFlutter.setOnCheckedChangeListener(listener)
    }

    private fun actualizarTecnologias(){
        val seleccionadas = mutableListOf<String>()

        if (cbAndroid.isClickable) seleccionadas.add(cbAndroid.text.toString())
        if (cbReact.isClickable) seleccionadas.add(cbReact.text.toString())
        if (cbFlutter.isClickable) seleccionadas.add(cbFlutter.text.toString())

        resultado.text = if (seleccionadas.isEmpty()) {
            "No has seleccionado ninguna tecnología"
        } else {
            "Tecnologías: " + seleccionadas.joinToString(", ")
        }
    }
}