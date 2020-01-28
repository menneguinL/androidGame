package com.example.bipbipbopbop

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.postAtTime
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.concurrent.fixedRateTimer
import kotlin.system.exitProcess


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        val jeu : Intent =  Intent(this,Jeu::class.java)
        val bouttonJeu = findViewById<Button>(R.id.buttonJeu)
        bouttonJeu.setOnClickListener {
            startActivity(jeu)
        }
        val bipbipbopbop : Intent =  Intent(this,test::class.java)
        fab.setOnClickListener {
            startActivity(bipbipbopbop)
        }

    }
}
