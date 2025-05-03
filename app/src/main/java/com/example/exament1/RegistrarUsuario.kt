package com.example.exament1
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegistrarUsuario : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registrarusuario)

        val dbHelper = DBHelper(this)

        val nombre = findViewById<EditText>(R.id.renombre)
        val apellido = findViewById<EditText>(R.id.reapellido)
        val usuario = findViewById<EditText>(R.id.ruse)
        val email = findViewById<EditText>(R.id.rmail)
        val contrasena = findViewById<EditText>(R.id.rcontrasena)
        val btnCrearCuenta = findViewById<Button>(R.id.btncrearcuenta)
        val btnAtras = findViewById<Button>(R.id.btnatras)

        btnAtras.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
        btnCrearCuenta.setOnClickListener {
            val nombreText = nombre.text.toString().trim()
            val apellidoText = apellido.text.toString().trim()
            val usuarioText = usuario.text.toString().trim()
            val emailText = email.text.toString().trim()
            val contrasenaText = contrasena.text.toString().trim()

            if (nombreText.isEmpty() || apellidoText.isEmpty() || usuarioText.isEmpty() ||
                emailText.isEmpty() || contrasenaText.isEmpty()) {
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
            } else {
                // Registrar el usuario
                dbHelper.insertarUsuario(
                    nombreText,
                    apellidoText,
                    usuarioText,
                    emailText,
                    contrasenaText,
                    this
                )
                // Limpiar los campos
                nombre.text.clear()
                apellido.text.clear()
                usuario.text.clear()
                email.text.clear()
                contrasena.text.clear()

                // Mensaje y redirigir al LoginActivity
                Toast.makeText(this, "Cuenta creada exitosamente", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()

            }
        }
    }
}
