package com.example.exament1

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class DBHelper(context: Context) : SQLiteOpenHelper(context, "Usuarios.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        val crearTabla = """
            CREATE TABLE usuarios (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre TEXT,
                apellido TEXT,
                usuario TEXT UNIQUE,
                email TEXT,
                contrasena TEXT
            )
        """.trimIndent()
        db.execSQL(crearTabla)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS usuarios")
        onCreate(db)
    }

    fun insertarUsuario(nombre: String, apellido: String, usuario: String, email: String, contrasena: String, context: Context): Boolean {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put("nombre", nombre)
            put("apellido", apellido)
            put("usuario", usuario)
            put("email", email)
            put("contrasena", contrasena)
        }
        val resultado = db.insert("usuarios", null, values)
        return if (resultado == -1L) {
            Toast.makeText(context, "Error al registrar usuario (puede estar duplicado)", Toast.LENGTH_SHORT).show()
            false
        } else {
            true
        }
    }

    fun validarUsuario(usuario: String, contrasena: String): Boolean {
        val db = this.readableDatabase
        val query = "SELECT * FROM usuarios WHERE usuario = ? AND contrasena = ?"
        val cursor = db.rawQuery(query, arrayOf(usuario, contrasena))
        val existe = cursor.count > 0
        cursor.close()
        return existe
    }
}
