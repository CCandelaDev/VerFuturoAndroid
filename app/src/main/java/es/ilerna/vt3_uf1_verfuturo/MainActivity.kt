package es.ilerna.vt3_uf1_verfuturo

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Toast
import es.ilerna.vt3_uf1_verfuturo.bbdd_users_app.Usuarios
import es.ilerna.vt3_uf1_verfuturo.bbdd_users_app.UsuariosSQLite

class MainActivity : AppCompatActivity() {

    private lateinit var etName: EditText
    private lateinit var etAge: EditText
    private lateinit var dpDate: DatePicker
    private lateinit var btnNext: Button
    private lateinit var btnUsuariosBBDD: Button
    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initComponents()
        initListeners()


    }


    private fun initComponents() {
        etName = findViewById(R.id.etName)
        dpDate = findViewById((R.id.dpDate))
        btnNext = findViewById(R.id.btnNext)
        etAge = findViewById(R.id.etAge)
        btnUsuariosBBDD = findViewById(R.id.btnUsuariosBBDD)
    }

    private fun initListeners() {

        btnNext.setOnClickListener {

            val name = etName.text
            val age = etAge.text

            if (name.isNotEmpty() && age.isNotEmpty()) {

                //Insertamos los datos del usuario en la BBDD
                val usuario = Usuarios(null, name.toString(), age.toString())
                val bbddUsuario = UsuariosSQLite(this)
                bbddUsuario.insertIntoDB(usuario)

                //Mostramos por consola la lista de usuarios
                val listaUsuarios = bbddUsuario.getListaUsuarios()
                for (usuario in listaUsuarios) {
                    Log.i(
                        "LISTA DE USUARIOS BBDD",
                        "Nombre: ${usuario.nombre}  Edad: ${usuario.edad}"
                    )
                }

                //Recogemos la fecha indicada en el datePiker
                val day = dpDate.dayOfMonth
                val month = dpDate.month + 1 //Los meses comienzan en 0
                val year = dpDate.year

                Toast.makeText(
                    this,
                    "$name $age la Fecha Seleccionada es $day/$month/$year",
                    Toast.LENGTH_LONG
                ).show()

                //Enviamos los datos a ActivityVerFuturo
                val intent = Intent(this, ActivityVerFuturo::class.java)
                intent.putExtra("name", name.toString())
                intent.putExtra("day", day)
                intent.putExtra("month", month)
                startActivity(intent)
                //Log.e("oraculo", "CAMBIO DE ACTIVITY")

                //Limpiamos los editText
                etName.setText("")
                etAge.setText("")

                //Reproducir un sonido corto al pulsar el boton
                mediaPlayer = MediaPlayer.create(this, R.raw.sound)
                mediaPlayer.start()

            } else {
                Toast.makeText(this, "Introduce nombre y edad", Toast.LENGTH_LONG).show()
            }


        }

        btnUsuariosBBDD.setOnClickListener {
            val intent = Intent(this, ActivityUsuariosBBDD::class.java)
            startActivity(intent)

        }
    }

    //Libera recurso mediaPlayer para evitar perdidas de memoria
    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }


}