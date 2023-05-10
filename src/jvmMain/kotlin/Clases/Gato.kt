package Clases

import ultimoGato

class Gato : Animal{
    var razaGato = ""

    constructor(name:String, fecha:String, s:String, raza:String):super(name,fecha,s){
        codigo = ultimoGato().toInt()+1
        razaGato=raza
    }
}