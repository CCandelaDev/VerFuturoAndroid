package es.ilerna.vt3_uf1_verfuturo.bbdd_users_app

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.core.database.getIntOrNull

class UsuariosSQLite(context: Context) : SQLiteOpenHelper(context, "usuarios.db", null, 1) {


    override fun onCreate(dbUsers: SQLiteDatabase?) {
        //Creamos la Tabla usuarios
        val createTable = """
            CREATE TABLE usuarios (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT NOT NULL,
                age TEXT NOT NULL
                )
                 """.trimIndent() //Quitar espacios en blanco al principio y final

        if (dbUsers != null) {
            dbUsers.execSQL(createTable)
        }
    }

    override fun onUpgrade(dbUsers: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //Eliminar la Tabla si existe y volver a crearla
        val upgradeQuery = "DROP TABLE IF EXISTS usuarios"
        if (dbUsers != null) {
            dbUsers.execSQL(upgradeQuery)
        }
        onCreate(dbUsers) //La volvemos a crear
    }

    /**
     *Insertar un nuevo usuario
     * @param newUser Los datos del nuevo usuario
     * @return el nuevo registro
     */
    fun insertIntoDB(newUser: Usuarios): Long {
        val dbUsers = writableDatabase

        val values = ContentValues()
        values.put("name", newUser.nombre)
        values.put("age", newUser.edad)

        val newId = dbUsers.insert("usuarios", null, values)
        dbUsers.close()
        return newId
    }

    /**
     * Lee los datos de un Usuario(Clase usuario)
     * @param idUsuario la id del usuario de la BBDD
     * @return el objeto usuario con su informacion
     */
    fun read(idUsuario: Long): Usuarios {
        val dbUsers = readableDatabase

        val selectQuery = "SELECT * FROM usuarios WHERE id = ?"
        //Con un cursor recorremos la tabla pqra devolver la query
        val cursor: Cursor = dbUsers.rawQuery(selectQuery, arrayOf(idUsuario.toString()))
        var usuario = Usuarios(null,"", "") //donde almacenamos el resultado

        if (cursor.moveToFirst()) {
            val name = cursor.getString(cursor.getColumnIndexOrThrow("name"))
            val age = cursor.getString(cursor.getColumnIndexOrThrow("age"))
            usuario = Usuarios(null, name, age)
            // Actualizamos el ID del usuario
            usuario.id = idUsuario
        }
        cursor.close()
        dbUsers.close()
        return usuario

    }

    /**
     * Actualizar usuario con los datos de forma individual
     * @param idUsuario id del usuario en la BBDD
     * @param usuario objeto de la clase Usuarios con los datos a actualizar
     * @return el número de filas afectadas
     */
    fun update(idUsuario: Long, usuario: Usuarios): Int {
        val dbUsers = writableDatabase

        val values = ContentValues()
        values.put("name", usuario.nombre)
        values.put("age", usuario.edad)

        val affectedRows =
            dbUsers.update("usuarios", values, "id = ?", arrayOf(idUsuario.toString()))
        dbUsers.close()
        return affectedRows

    }

    /**
     * Borrar un Usuario de la BBDD
     * @param idUsuario id del usuario a borrar
     * @return el numero de filas afectadas en un Int
     */
    fun delete(idUsuario: Long): Int {
        val dbUsers = writableDatabase

        val affectedRows = dbUsers.delete("usuarios", "id = ?", arrayOf(idUsuario.toString()))
        dbUsers.close()
        return affectedRows
    }

    /**
     * Método para obtener el número de usuarios
     * @return el número de usuarios, si da error devuelve -1
     */
    fun getNumeroUsuarios(): Int {

        val dbUsers = readableDatabase

        val sqlQuery = "SELECT count(*) AS numUsuarios FROM usuarios"
        val cursor: Cursor = dbUsers.rawQuery(sqlQuery, null)
        var num = -1
        if (cursor.moveToFirst()) {
            num = cursor.getInt(cursor.getColumnIndexOrThrow("numUsuarios"))
        }

        cursor.close()
        dbUsers.close()
        return num

    }

    /**
     * Método para obtener una lista de los usuarios en la BBDD
     * @return List<Usuarios> la lista de usuarios BBDD
     */
    fun getListaUsuarios(): List<Usuarios> {
        //Creamos una lista modificable
        val usuariosList = mutableListOf<Usuarios>()
        val dbUsers = readableDatabase

        val sqlQuery = "SELECT * FROM usuarios"
        val cursor: Cursor = dbUsers.rawQuery(sqlQuery, null)

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getLong(cursor.getColumnIndexOrThrow("id"))
                val nombre = cursor.getString(cursor.getColumnIndexOrThrow("name"))
                val edad = cursor.getString(cursor.getColumnIndexOrThrow("age"))
                val usuario = Usuarios(id, nombre, edad)
                usuariosList.add(usuario)
            } while (cursor.moveToNext())
        }
        cursor.close()
        dbUsers.close()
        return usuariosList


    }

    /**
     *
     */
    //Metodo para verificar que el registro existe en la BD antes de eliminarlo
    fun verificarExisteRegistro(id: Long): Boolean {
        val dbUsers = readableDatabase

        val query = "SELECT count(*) FROM usuarios WHERE id=?"
        //Comprobar si existe en la bbdd ese id recibido por parametro
        val cursor: Cursor = dbUsers.rawQuery(query, arrayOf(id.toString()))

        cursor.moveToFirst()
        val count = cursor.getInt(0)
        cursor.close()
        dbUsers.close()

        return count > 0

    }
}