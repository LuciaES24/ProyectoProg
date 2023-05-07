package Clases

class Perro : Animal {
    var razaPerro = ""
    var ppp : Boolean

    constructor(name:String, dni:String, fecha:String, s:Char, raza:String, p:Boolean):super(name,dni,fecha,s){
        razaPerro=raza
        ppp = p
    }
}