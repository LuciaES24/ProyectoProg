import Clases.Gato
import Clases.Perro
import java.sql.*

fun conectarBase(): Connection? {
    val url = "jdbc:oracle:thin:@localhost:1521:xe"
    val usuario = "PROYECTO"
    val contrasena = "PROYECTO"
    Class.forName("oracle.jdbc.driver.OracleDriver")
    val conexion = DriverManager.getConnection(url, usuario, contrasena)
    return conexion
}

fun ultimoPerro() : String{
    val stmt = conectarBase()?.prepareStatement("SELECT MAX(COD_ANIMAL) FROM PERROS ")
    val result = stmt?.executeQuery()
    val codigo = result?.getInt("COD_ANIMAL")
    return codigo.toString()
}

fun ultimoGato() : String{
    val stmt = conectarBase()?.prepareStatement("SELECT MAX(COD_ANIMAL) FROM GATOS ")
    val result = stmt?.executeQuery()
    val codigo = result?.getInt("COD_ANIMAL")
    return codigo.toString()
}

fun insertarPerro(perro:Perro){
    val stmt = conectarBase()?.prepareStatement("INSERT INTO PERROS (COD_ANIMAL , NOMBRE , FECHA_NAC , SEXO , RAZA , PPP ) VALUES (?, ?, ?, ?, ?, ?)")
    stmt?.setInt(1, perro.codigo)
    stmt?.setString(2, perro.nombre)
    stmt?.setString(3, perro.fecha_nac)
    stmt?.setString(4, perro.sexo)
    stmt?.setString(5, perro.razaPerro)
    stmt?.setString(5, perro.ppp)
    stmt?.executeUpdate()
}

fun insertarGato(gato:Gato){
    val stmt = conectarBase()?.prepareStatement("INSERT INTO GATOS  (COD_ANIMAL , NOMBRE , FECHA_NAC , SEXO , RAZA ) VALUES (?, ?, ?, ?, ?)")
    stmt?.setInt(1, gato.codigo)
    stmt?.setString(2, gato.nombre)
    stmt?.setString(3, gato.fecha_nac)
    stmt?.setString(4, gato.sexo)
    stmt?.setString(5, gato.razaGato)
    stmt?.executeUpdate()
}

fun buscarPerro(cod:Int): List<String?> {
    val stmt = conectarBase()?.prepareStatement("SELECT COD_ANIMAL , NOMBRE , FECHA_NAC , SEXO , RAZA , PPP FROM PERROS WHERE COD_ANIMAL = ?")
    stmt?.setInt(1,cod)
    val result = stmt?.executeQuery()
    val codigo = result?.getInt("COD_ANIMAL")
    val name = result?.getString("NOMBRE")
    val fec = result?.getString("FECHA_NAC")
    val s = result?.getString("SEXO")
    val ra = result?.getString("RAZA")
    val p = result?.getBoolean("PPP")
    val listaFinal = listOf(codigo.toString(),name,fec,ra,s,p.toString())
    return listaFinal
}

fun buscarGato(cod:Int): List<String?> {
    val stmt = conectarBase()?.prepareStatement("SELECT COD_ANIMAL , NOMBRE , FECHA_NAC , SEXO , RAZA FROM PERROS WHERE COD_ANIMAL = ?")
    stmt?.setInt(1,cod)
    val result = stmt?.executeQuery()
    val codigo = result?.getInt("COD_ANIMAL")
    val name = result?.getString("NOMBRE")
    val fec = result?.getString("FECHA_NAC")
    val s = result?.getString("SEXO")
    val ra = result?.getString("RAZA")
    val listaFinal = listOf(codigo.toString(),name,fec,ra,s)
    return listaFinal
}

