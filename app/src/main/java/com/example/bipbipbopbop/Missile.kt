package com.example.bipbipbopbop

import android.graphics.drawable.Drawable

class Missile (damage: Int) {
    private val image = Drawable.createFromPath("R.drawable.missile")

    fun getImage (): Drawable? {
        return image;
    }

}