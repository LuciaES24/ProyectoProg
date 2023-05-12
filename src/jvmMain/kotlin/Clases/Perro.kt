package Clases

class Perro : Animal {
    var razaPerro = ""
    var ppp = ""

    constructor(cod:Int, name:String, fecha:String, s:String, raza:String, p:String):super(cod,name,fecha,s){
        razaPerro=raza
        ppp = p
    }

    override fun toString(): String {
        return "Perro\n"+super.toString()+"\nRaza: $razaPerro\nPPP: $ppp"
    }
}