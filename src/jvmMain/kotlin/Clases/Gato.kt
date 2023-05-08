package Clases

class Gato : Animal{
    var razaGato = ""

    constructor(name:String, fecha:String, s:String, raza:String):super(name,fecha,s){
        razaGato=raza
    }
}