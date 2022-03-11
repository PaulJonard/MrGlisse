package com.example.mrglisse.model

import java.io.Serializable


abstract class Ski(
    open val brand:String,
    open val model: String,
    open val price: Double,
    open val size: Int
): Serializable {


    abstract fun showOverView():String

}