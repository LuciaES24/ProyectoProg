package Clases

import ultimoGato

class Gato : Animal{
    var razaGato = ""

    constructor(cod:Int,name:String, fecha:String, s:String, raza:String):super(cod,name,fecha,s){
        razaGato=raza
    }

    override fun toString(): String {
        return "Gato\n"+ super.toString()+"\nRaza: $razaGato"
    }
}