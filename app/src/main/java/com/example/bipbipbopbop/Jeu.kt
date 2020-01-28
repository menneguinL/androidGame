package com.example.bipbipbopbop


import android.animation.ValueAnimator
import android.os.Bundle
import android.os.Handler
import android.view.MotionEvent
import android.view.View
import android.view.ViewManager
import android.view.animation.AccelerateInterpolator
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import kotlin.random.Random
import android.R.*
import android.widget.TextView





class Jeu : AppCompatActivity() {

    var allMissile = ArrayList<ArrayList<Float>>()
    var index = 0
    var score = 0
    var timerMechant = 3000L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.jeu)
        val actionBar = supportActionBar
        actionBar!!.hide()
        val image = findViewById<ImageView>(R.id.imageRobot)
        val textView = findViewById<TextView>(R.id.score)
        textView.text = score.toString()
        val listener = View.OnTouchListener(function = {view, motionEvent ->

            if (motionEvent.action == MotionEvent.ACTION_MOVE) {

                view.y = motionEvent.rawY - view.height
                view.x = motionEvent.rawX - view.width/2

                addMissile(1,view.x,view.y,view.width,index)
                index += 1
            }
            true
        })
        image.run {
            setOnTouchListener(listener)
        }
        val handler = Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                addMechant()
                handler.postDelayed(this, timerMechant)
            }
        }, 0)

        handler.postDelayed(object : Runnable {
            override fun run() {
                val textView = findViewById<TextView>(R.id.score)
                textView.text = score.toString()
                handler.postDelayed(this, 10)
                if (timerMechant > 1000 ) {
                    timerMechant -= 1
                }
            }
        }, 0)

    }

    private fun addMissile(damage: Int, x: Float, y: Float, largeur: Int,index: Int) {
        val ier = index
        val constraintLayout = findViewById<ConstraintLayout>(R.id.truc)
        val imageView = ImageView(this)
        imageView.setImageResource(R.drawable.missile)
        imageView.x = x + (largeur / 2)
        imageView.y = y
        constraintLayout.addView(imageView)
        var coordonne = ArrayList<Float>()
        coordonne.add(imageView.y)
        coordonne.add(imageView.x)
        coordonne.add(damage.toFloat())
        allMissile.add(coordonne)
        val valueAnimator = ValueAnimator.ofFloat(imageView.y, 0F)
        valueAnimator.addUpdateListener {
            val value = it.animatedValue as Float
            imageView.translationY = value
            allMissile[ier][0] = value

        }
        valueAnimator.interpolator = AccelerateInterpolator(1.5f)
        valueAnimator.duration = 1000
        valueAnimator.start()


        Handler().postDelayed({
            allMissile[ier][0] = 2000F
            (imageView.parent as ViewManager).removeView(imageView)
        }, 1000)


    }

    private fun addMechant(){
        val constraintLayout = findViewById<ConstraintLayout>(R.id.truc)
        val imageViewEnemie = ImageView(this)
        var vie = 10
        imageViewEnemie.setImageResource(R.drawable.enemie)

        imageViewEnemie.x = Random.nextInt(-1, 670).toFloat();
        constraintLayout.addView(imageViewEnemie)
        val valueAnimator = ValueAnimator.ofFloat(imageViewEnemie.y, 1300F)
        valueAnimator.addUpdateListener {
            val value = it.animatedValue as Float
            imageViewEnemie.translationY = value
            var i = 0
            while (i < allMissile.size) {
                if (value.toInt() < allMissile[i][0].toInt() && value.toInt() + 50  > allMissile[i][0].toInt()) {
                    if (allMissile[i][1] > imageViewEnemie.x && allMissile[i][1] < imageViewEnemie.x + 50 ) {
                        vie -= allMissile[i][2].toInt()
                        if (vie == 0) {
                            imageViewEnemie.setImageResource(0)
                            score += 1
                        }
                    }
                }
                i += 1
            }
        }
        valueAnimator.interpolator = AccelerateInterpolator(1.5f)
        valueAnimator.duration = 3000
        valueAnimator.start()

        Handler().postDelayed({
            (imageViewEnemie.parent as ViewManager).removeView(imageViewEnemie)
        }, 3000)
    }

}

