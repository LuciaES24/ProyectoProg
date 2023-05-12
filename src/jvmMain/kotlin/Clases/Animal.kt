package Clases

abstract class Animal {
    var codigo = 0
    var nombre = ""
    var fecha_nac = ""
    var sexo = ""


    constructor(cod:Int,name:String, fecha:String, s:String){
        codigo = cod
        nombre = name
        fecha_nac = fecha
        sexo = s
    }

    override fun toString(): String {
        return "Codigo: $codigo\nNombre: $nombre\nFecha de nacimiento: $fecha_nac\nSexo: $sexo"
    }


}