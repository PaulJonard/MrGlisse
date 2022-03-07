package com.example.mrglisse.model

abstract class Ski(
    private val brand:String,
    private val model: String,
    private val price: Double,
    private val size: Int
) {

    abstract fun showOverView():String

}