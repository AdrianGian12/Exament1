package com.example.exament1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val dbHelper = DBHelper(this)  // Instanciamos la base de datos
        val usuario = findViewById<EditText>(R.id.Usuario)
        val contrasena = findViewById<EditText>(R.id.escontra)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val btnGoogle = findViewById<Button>(R.id.btnGoogle)
        val btnFacebook = findViewById<Button>(R.id.btnFacebook)
        val tvRegistro = findViewById<TextView>(R.id.tvRegistro)
        tvRegistro.setOnClickListener {
            val intent = Intent(this, RegistrarUsuario::class.java)
            startActivity(intent)
        }

      //validacion
        btnLogin.setOnClickListener {
            val userText = usuario.text.toString().trim()
            val passText = contrasena.text.toString().trim()
            //Verificar
            if (userText.isEmpty() || passText.isEmpty()) {
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
            } else {
                // Validar usuario con la base de datos
                val valido = dbHelper.validarUsuario(userText, passText)
                if (valido) {
                    //correcta, segunda actividad
                    Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, InicioActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
                }
            }
        }
        btnGoogle.setOnClickListener {
            Toast.makeText(this, "Iniciar sesión con Google aún no está implementado", Toast.LENGTH_SHORT).show()
        }
        btnFacebook.setOnClickListener {
            Toast.makeText(this, "Iniciar sesión con Facebook aún no está implementado", Toast.LENGTH_SHORT).show()
        }
    }
}

