package com.example.calculodeaposentadoria

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val spnSexo = findViewById<Spinner>(R.id.spn_Sexo)
        val txtIdade = findViewById<EditText>(R.id.txt_Idade)
        val btnCalcular = findViewById<Button>(R.id.btn_Calcular)
        val txtResultado = findViewById<TextView>(R.id.txt_Resultado)

        spnSexo.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            listOf("Masculino", "Feminino")
        )

        btnCalcular.setOnClickListener {
            val sexo = spnSexo.selectedItem as String
            val idadeText = txtIdade.text.toString()

            if (idadeText.isEmpty()) {
                txtResultado.text = "Por favor, insira sua idade."
                return@setOnClickListener
            }

            val idade = idadeText.toInt()
            val resultado: Int
            resultado = if (sexo == "Masculino") {
                63 - idade // Limite de idade masculino conforme a nova Previdência
            } else {
                58 - idade // Limite de idade feminino conforme a nova Previdência
            }
            if (resultado <= 0) {
                txtResultado.text = "Você já pode se aposentar."
            } else {
                txtResultado.text = "Faltam $resultado anos para você se aposentar"
            }
        }
    }
}
