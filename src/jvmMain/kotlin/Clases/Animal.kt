package Clases

abstract class Animal {
    var codigo = 0
    var nombre = ""
    var dniDueno = ""
    var fecha_nac = ""
    var sexo : Char

    companion object Animal{
        var numero = 100
    }

    constructor(name:String, dni:String, fecha:String, s:Char){
        codigo = numero
        Animal.numero++
        nombre = name
        dniDueno = dni
        fecha_nac = fecha
        sexo = s
    }
}