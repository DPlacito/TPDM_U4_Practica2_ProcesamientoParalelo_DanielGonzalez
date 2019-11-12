package mx.edu.ittepic.tpdm_u4_practica2_procesamientoparalelo_danielgonzalez

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var segundos = 0
    private var estaCorriendo = false
    private var etiqueta: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etiqueta = findViewById(R.id.etiqueta_Cronometro)
        correrCronometro()
    }

    override fun onStart() {
        super.onStart()
        verificarTiempo()
    }

    override fun onPause() {
        super.onPause()

        if (estaCorriendo)
            estaCorriendo = false
    }

    override fun onResume() {
        super.onResume()
        verificarTiempo()
        reiniciarCronometro()
    }

    override fun onStop() {
        super.onStop()

        if (estaCorriendo)
            estaCorriendo = false
    }

    override fun onRestart() {
        super.onRestart()

        if (!estaCorriendo)
            estaCorriendo = true
    }


    fun Iniciar(view: View) {
        estaCorriendo = true
    }

    fun Pausar(view: View) {
        estaCorriendo = false
    }

    fun Reiniciar(view: View) {
        segundos = 0
        estaCorriendo = false
    }


    private fun correrCronometro() {
        val handler = Handler()

        handler.post(object : Runnable {
            override fun run() {
                val horas = segundos / 3600
                val minutos = segundos % 3600 / 60
                val segundo = segundos % 60

                etiqueta?.text = getString(R.string.formato_cronometro, horas, minutos, segundo)

                if (estaCorriendo)
                    segundos++

                handler.postDelayed(this, 1000)
            }
        })
    }

    private fun verificarTiempo() {
        if (etiqueta?.text != getString(R.string.tiempo) && !estaCorriendo)
            estaCorriendo = true
    }



    private fun reiniciarCronometro() {
        if (segundos > 0) {
            var horas = 0
            var minutos = 0
            var segundo = 0

            for (i in 0..segundos) {
                horas= segundos / 3600
                minutos = segundos % 3600 / 60
                segundo = segundos % 60
            }

            etiqueta?.text = getString(R.string.formato_cronometro, horas, minutos, segundo)
            estaCorriendo = true
        }
    }

}
