package com.example.mrglisse.model

abstract class Ski(
    open val brand:String,
    open val model: String,
    open val price: Double,
    open val size: Int
) {


    abstract fun showOverView():String

}