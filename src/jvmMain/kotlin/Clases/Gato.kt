package Clases

class Gato : Animal{
    var razaGato = ""

    constructor(name:String, dni:String, fecha:String, s:Char, raza:String):super(name,dni,fecha,s){
        razaGato=raza
    }
}