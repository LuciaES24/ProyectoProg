package Clases

abstract class Animal {
    var codigo = 0
    var nombre = ""
    var fecha_nac = ""
    var sexo = ""


    constructor(name:String, fecha:String, s:String){
        nombre = name
        fecha_nac = fecha
        sexo = s
    }
}