package com.example.exament1
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class InicioActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.inicioactivity)
        // Cargar el primer fragmento al iniciar la actividad
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.frame_container, primerfragmento()) // Usa el ID correcto
                .commit()
        }
        // Configurar el BottomNavigationView
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.setOnItemSelectedListener { item ->
            val fragment = when (item.itemId) {
                R.id.primerfragmento -> primerfragmento()
                R.id.segundofragmento -> segundofragmento()
                R.id.tercerofragmento -> tercerofragmento()
                R.id.cuartofragmento -> cuartofragmento()
                else -> null
            }
            fragment?.let {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame_container, it)
                    .commit()
            }
            true
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.bottom_navigation, menu)
        return true
    }
}