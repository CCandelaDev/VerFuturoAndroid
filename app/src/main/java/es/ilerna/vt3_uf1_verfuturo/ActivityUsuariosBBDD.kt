package es.ilerna.vt3_uf1_verfuturo

import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import es.ilerna.vt3_uf1_verfuturo.bbdd_users_app.UsuariosSQLite
import java.lang.StringBuilder

class ActivityUsuariosBBDD : AppCompatActivity() {

    private lateinit var tvListaUsuarios: TextView
    private lateinit var btnMainReturn: Button
    private lateinit var btnCloseApp: Button
    private lateinit var tvMostrarNumReg: TextView
    private lateinit var etNumBorrarReg: EditText
    private lateinit var btnBorrarReg: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_usuarios_bbdd)

        initComponents()
        initListeners()


        //Mostramos los usuarios en la BBDD
        val bbddUsuarios = UsuariosSQLite(this)
        val listaUsuarios = bbddUsuarios.getListaUsuarios()

        // Inicializamos un StringBuilder para ir concatenando la información de cada usuario
        val usuariosBBDD = StringBuilder()
        var num = 1
        //Vamos añadiendo informacion al Stingbuilder
        for (usuario in listaUsuarios) {
            usuariosBBDD.append("${num}. ID: ${usuario.id}  Nombre: ${usuario.nombre}  Edad: ${usuario.edad}\n\n")
            num++
        }
        //Lo mostramos en el textView
        tvListaUsuarios.text = usuariosBBDD.toString()


        //Mostramos el número de registros de la BBDD
        tvMostrarNumReg.text = bbddUsuarios.getNumeroUsuarios().toString()


    }

    private fun initComponents() {
        tvListaUsuarios = findViewById(R.id.tvListaUsuarios)
        btnMainReturn = findViewById(R.id.btnMainReturn)
        btnCloseApp = findViewById(R.id.btnCloseApp)
        tvMostrarNumReg = findViewById(R.id.tvMostrarNumReg)
        etNumBorrarReg = findViewById(R.id.etNumBorrarReg)
        btnBorrarReg = findViewById((R.id.btnBorrarReg))

    }


    private fun initListeners() {

        btnBorrarReg.setOnClickListener {
            val bbddUsuarios = UsuariosSQLite(this)
            val numRegistroStr = etNumBorrarReg.text.toString()
            val numRegistroLong = numRegistroStr.toLongOrNull()

            //Verificar que se introduzca un registro
            if (numRegistroLong != null) {

                //Verificar si el registro existe---> metodo creado en UsuariosSQLite
                val registroExiste = bbddUsuarios.verificarExisteRegistro(numRegistroLong)

                if (registroExiste) {
                    bbddUsuarios.delete(numRegistroLong)
                    etNumBorrarReg.setText("")
                    Toast.makeText(this, "Registro ID $numRegistroStr borrado", Toast.LENGTH_LONG)
                        .show()

                    //Reiniciamos la activity---No es lo mas  recomendado así
                    val intent = Intent(this, ActivityUsuariosBBDD::class.java)
                    finish()
                    startActivity(intent)

                } else {
                    Toast.makeText(
                        this,
                        "El registro no existe en la base de datos",
                        Toast.LENGTH_LONG
                    ).show()
                }


            } else {
                Toast.makeText(this, "La entrada no es válida", Toast.LENGTH_LONG).show()
            }


        }


        btnMainReturn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)


        }

        btnCloseApp.setOnClickListener {
            finishAffinity()
        }

    }


}