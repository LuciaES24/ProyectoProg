package Clases

abstract class Animal {
    var codigo = 0
    var nombre = ""
    var fecha_nac = ""
    var sexo = ""

    companion object Animal{
        var numero = 100
    }

    constructor(name:String, fecha:String, s:String){
        codigo = numero
        Animal.numero++
        nombre = name
        fecha_nac = fecha
        sexo = s
    }
}