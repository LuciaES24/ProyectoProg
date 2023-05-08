import java.sql.*

fun conectarBase(): Connection? {
    val url = "jdbc:oracle:thin:@localhost:1521:xe"
    val usuario = "PROYECTO"
    val contrasena = "PROYECTO"
    Class.forName("oracle.jdbc.driver.OracleDriver")
    val conexion = DriverManager.getConnection(url, usuario, contrasena)
    return conexion
}

fun insertarPerro(cod:Int, name:String,fec:String, s:String, ra:String, p:Boolean){
    val stmt = conectarBase()?.prepareStatement("INSERT INTO PERROS  (COD_ANIMAL , NOMBRE , FECHA_NAC , SEXO , RAZA , PPP ) VALUES (?, ?, ?, ?, ?, ?)")
    stmt?.setInt(1, cod)
    stmt?.setString(2, name)
    stmt?.setString(3, fec)
    stmt?.setString(4, s)
    stmt?.setString(5, ra)
    stmt?.setBoolean(5, p)
    stmt?.executeUpdate()
}

fun insertarGato(cod:Int, name:String,fec:String, s:String, ra:String){
    val stmt = conectarBase()?.prepareStatement("INSERT INTO GATOS  (COD_ANIMAL , NOMBRE , FECHA_NAC , SEXO , RAZA ) VALUES (?, ?, ?, ?, ?)")
    stmt?.setInt(1, cod)
    stmt?.setString(2, name)
    stmt?.setString(3, fec)
    stmt?.setString(4, s)
    stmt?.setString(5, ra)
    stmt?.executeUpdate()
}

fun buscarrPerro(cod:Int): List<String?> {
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

