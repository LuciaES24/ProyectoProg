package Clases

import ultimoPerro

class Perro : Animal {
    var razaPerro = ""
    var ppp = ""

    constructor(name:String, fecha:String, s:String, raza:String, p:String):super(name,fecha,s){
        codigo = ultimoPerro().toInt()+1
        razaPerro=raza
        ppp = p
    }
}