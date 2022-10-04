package com.hfad.simplecaloriecalculator

class Formatting {
    fun Double.format(scale: Int) = "%.${scale}f".format(this)
}