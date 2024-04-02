package es.ilerna.vt3_uf1_verfuturo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class ActivityVerFuturo : AppCompatActivity() {

    private lateinit var ivZodiacSign: ImageView
    private lateinit var tvFutureText: TextView
    private lateinit var btnReturn: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_futuro)
        initComponents()
        initListeners()

        //Colección de pares clave-valor, donde cada clave es única y tiene asociado un valor.
        val resourcesSigns = mapOf(
            "aries" to R.drawable.aries,
            "tauro" to R.drawable.tauro,
            "geminis" to R.drawable.geminis,
            "cancer" to R.drawable.cancer,
            "leo" to R.drawable.leo,
            "virgo" to R.drawable.virgo,
            "libra" to R.drawable.libra,
            "escorpio" to R.drawable.scorpio,
            "sagitario" to R.drawable.sagitario,
            "capricornio" to R.drawable.capricornio,
            "acuario" to R.drawable.acuario,
            "piscis" to R.drawable.piscis,
        )
        //Recogemos nombre y la fecha que envia la Activity principal
        val name = intent.getStringExtra("name")?: "Name"
        val birthDay = intent.getIntExtra("day", -1)
        val birthMonth = intent.getIntExtra("month", -1)

        //Creamos objeto de la clase oraculo y le pasamos dia y mes
        val oraculo = Oraculo(birthDay, birthMonth)
        //Creamos la prediccion
        val prediction = oraculo.getPrediction(name)

        //Mostramos la predicción en la UI y la imagen en el ImageView
        tvFutureText.text = prediction
        val signID = resourcesSigns[oraculo.sign] ?:0
        val newDrawable = getDrawable(signID)
        ivZodiacSign.setImageDrawable(newDrawable)


    }

    private fun initComponents() {
        ivZodiacSign = findViewById(R.id.ivZodiacSign)
        tvFutureText = findViewById(R.id.tvFutureText)
        btnReturn = findViewById(R.id.btnReturn)
    }

    private fun initListeners() {
        btnReturn.setOnClickListener {

            finish()
        }
    }

}