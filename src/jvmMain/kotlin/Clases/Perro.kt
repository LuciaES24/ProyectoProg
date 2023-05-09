package Clases

class Perro : Animal {
    var razaPerro = ""
    var ppp : Boolean

    constructor(name:String, fecha:String, s:String, raza:String, p:Boolean):super(name,fecha,s){
        razaPerro=raza
        ppp = p
    }
}