package com.example.bipbipbopbop

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class test : AppCompatActivity() {
    private var fond = true

    override fun onCreate(savedInstanceState: Bundle?) {
        val mediaPlayer = MediaPlayer.create(this,R.raw.bipbipbopbop);
        super.onCreate(savedInstanceState)
        val monIntent : Intent =  Intent(this,MainActivity::class.java)
        mediaPlayer?.start()
        val handler = Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                fond()
                handler.postDelayed(this, 200)
            }
        }, 0)
        Handler().postDelayed({
            startActivity(monIntent)
        }, 2100)
    }

    private fun fond() {
        if (this.fond) {
            setContentView(R.layout.robot1)
            this.fond = false
        } else {
            setContentView(R.layout.robot2)
            this.fond = true
        }
    }
}
