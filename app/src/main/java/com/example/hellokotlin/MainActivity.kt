package com.example.hellokotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.widget.TextView
import java.util.*

class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    //texttospeech, el simbolo ? le avisa a la var que puede ser null
    var tts: TextToSpeech? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        tts = TextToSpeech(this,this)
        var message: String = findViewById<TextView>(R.id.tvStatus).text.toString()
        findViewById<TextView>(R.id.btnSpeak).setOnClickListener{speak()}
        Log.i("message txt", message)
    }

    private fun speak(){
        var message: String = findViewById<TextView>(R.id.et).text.toString()

        if (message.isEmpty()){

            message = "Escribe algo pos, pajaron";
        }
        tts!!.speak(message, TextToSpeech.QUEUE_FLUSH,null,"");

    }

    //
    override fun onInit(status: Int) {

        if(status == TextToSpeech.SUCCESS){
            findViewById<TextView>(R.id.tvStatus).text = "Welcome to Kotlin"
            //tts!!.setLanguage(Locale.US)
            tts!!.setLanguage(Locale("ES"))
        }else{
            findViewById<TextView>(R.id.tvStatus).text = "No disponible :C"
        }
    }

    override fun onDestroy() {
        if(tts != null){
            tts!!.stop()
            tts!!.shutdown()

        }
        super.onDestroy()
    }

        }