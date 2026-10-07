package com.example.retouno

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.RadioButton
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var cbAndroid: CheckBox
    private lateinit var cbReact: CheckBox
    private lateinit var cbFlutter: CheckBox

    private lateinit var rbPresencia: RadioButton

    private lateinit var rbOnline: RadioButton

    private lateinit var rbMixta: RadioButton
    private lateinit var resultadoTec: TextView
    private lateinit var resultadoMod: TextView
    private lateinit var spinnerCurso: Spinner

    private lateinit var resultadoCurso: TextView

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
        resultadoTec = findViewById(R.id.resultadoTec)
        rbPresencia = findViewById(R.id.rbPresencial)
        rbOnline = findViewById(R.id.rbOnline)
        rbMixta = findViewById(R.id.rbMixta)
        resultadoMod = findViewById(R.id.resultadoMod)
        spinnerCurso = findViewById(R.id.spinnerCurso)
        resultadoCurso = findViewById(R.id.resultadoCurso)

        val listener = CompoundButton.OnCheckedChangeListener { _, _ ->
            actualizarTecnologias()
        }

        val listenerMod = CompoundButton.OnCheckedChangeListener { _, _ ->
            actualizarModalidad()
        }

        cbAndroid.setOnCheckedChangeListener(listener)
        cbReact.setOnCheckedChangeListener(listener)
        cbFlutter.setOnCheckedChangeListener(listener)
        rbPresencia.setOnCheckedChangeListener(listenerMod)
        rbOnline.setOnCheckedChangeListener(listenerMod)
        rbMixta.setOnCheckedChangeListener(listenerMod)

        spinnerCurso.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                actualizarCurso()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        actualizarTecnologias()
        actualizarModalidad()
    }

    private fun actualizarTecnologias() {
        val seleccionadas = mutableListOf<String>()

        if (cbAndroid.isChecked) seleccionadas.add(cbAndroid.text.toString())
        if (cbReact.isChecked) seleccionadas.add(cbReact.text.toString())
        if (cbFlutter.isChecked) seleccionadas.add(cbFlutter.text.toString())

        resultadoTec.text = if (seleccionadas.isEmpty()) {
            "No has seleccionado ninguna tecnología"
        } else {
            "Las tecnologías seleccionadas: " + seleccionadas.joinToString(", ")
        }
    }

    private fun actualizarModalidad() {
        val seleccionada = mutableListOf<String>()

        if (rbPresencia.isChecked) seleccionada.add(rbPresencia.text.toString())
        if (rbOnline.isChecked) seleccionada.add(rbOnline.text.toString())
        if (rbMixta.isChecked) seleccionada.add(rbMixta.text.toString())

        resultadoMod.text = if (seleccionada.isEmpty()) {
            "No se ha seleccionado ninguna modalidad"
        } else {
            "La modalidad seleccionada: " + seleccionada.joinToString(", ")
        }
    }

    private fun actualizarCurso() {
        val seleccionCurso = mutableListOf<String>()

        if (spinnerCurso.isClickable) seleccionCurso.add(spinnerCurso.selectedItem.toString())

        resultadoCurso.text = if (seleccionCurso.isEmpty()) {
            "No se ha seleccionado ningún curso"
        } else {
            "El curso seleccionado: " + seleccionCurso.joinToString(", ")
        }
    }
}