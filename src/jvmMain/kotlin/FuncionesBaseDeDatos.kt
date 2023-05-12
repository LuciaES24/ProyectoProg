import Clases.Animal
import Clases.Gato
import Clases.Perro
import java.sql.*

fun ultimoPerro() : String{
    val url = "jdbc:oracle:thin:@localhost:1521:xe"
    val usuario = "PROYECTO"
    val contrasena = "PROYECTO"
    Class.forName("oracle.jdbc.driver.OracleDriver")
    val conexion = DriverManager.getConnection(url, usuario, contrasena)
    val stmt = conexion.prepareStatement("SELECT MAX(COD_ANIMAL) FROM PERROS ")
    val result = stmt?.executeQuery()
    val codigo = result?.getInt("COD_ANIMAL")
    return codigo.toString()
}

fun ultimoGato() : String{
    val url = "jdbc:oracle:thin:@localhost:1521:xe"
    val usuario = "PROYECTO"
    val contrasena = "PROYECTO"
    var codigoFinal = ""
    try {
        Class.forName("oracle.jdbc.driver.OracleDriver")
        val conexion = DriverManager.getConnection(url, usuario, contrasena)
        val stmt = conexion.prepareStatement("SELECT MAX(COD_ANIMAL) FROM GATOS ")
        val result = stmt.executeQuery()
        val codigo = result.getInt("COD_ANIMAL")
        codigoFinal = codigo.toString()
    }catch (e: SQLException) {
        println("Error en la conexión: ${e.message}")
    } catch (e: ClassNotFoundException) {
        println("No se encontró el driver JDBC: ${e.message}")
    }
    return codigoFinal
}

fun insertarPerro(perro:Perro){
    val url = "jdbc:oracle:thin:@localhost:1521:xe"
    val usuario = "PROYECTO"
    val contrasena = "PROYECTO"
    try {
        Class.forName("oracle.jdbc.driver.OracleDriver")
        val conexion = DriverManager.getConnection(url, usuario, contrasena)
        val stmt = conexion.prepareStatement("INSERT INTO PERROS (COD_ANIMAL , NOMBRE , FECHA_NAC , SEXO , RAZA , PPP ) VALUES (?, ?, ?, ?, ?, ?)")
        stmt.setInt(1, perro.codigo)
        stmt.setString(2, perro.nombre)
        stmt.setString(3, perro.fecha_nac)
        stmt.setString(4, perro.sexo)
        stmt.setString(5, perro.razaPerro)
        stmt.setString(5, perro.ppp)
        stmt.executeUpdate()
    }catch (e: SQLException) {
        println("Error en la conexión: ${e.message}")
    } catch (e: ClassNotFoundException) {
        println("No se encontró el driver JDBC: ${e.message}")
    }
}

fun insertarGato(gato:Gato){
    val url = "jdbc:oracle:thin:@localhost:1521:xe"
    val usuario = "PROYECTO"
    val contrasena = "PROYECTO"
    try {
        Class.forName("oracle.jdbc.driver.OracleDriver")
        val conexion = DriverManager.getConnection(url, usuario, contrasena)
        val stmt =
            conexion.prepareStatement("INSERT INTO GATOS  (COD_ANIMAL , NOMBRE , FECHA_NAC , SEXO , RAZA ) VALUES (?, ?, ?, ?, ?)")
        stmt.setInt(1, gato.codigo)
        stmt.setString(2, gato.nombre)
        stmt.setString(3, gato.fecha_nac)
        stmt.setString(4, gato.sexo)
        stmt.setString(5, gato.razaGato)
        stmt.executeUpdate()
    }catch (e: SQLException) {
        println("Error en la conexión: ${e.message}")
    } catch (e: ClassNotFoundException) {
        println("No se encontró el driver JDBC: ${e.message}")
    }

}

fun buscarPerro(cod:Int): List<Animal> {
    val url = "jdbc:oracle:thin:@localhost:1521:xe"
    val usuario = "PROYECTO"
    val contrasena = "PROYECTO"
    var listaFinal = mutableListOf<Animal>()
    try {
        Class.forName("oracle.jdbc.driver.OracleDriver")
        val conexion = DriverManager.getConnection(url, usuario, contrasena)
        val stmt = conexion.prepareStatement("SELECT COD_ANIMAL , NOMBRE , FECHA_NAC , SEXO , RAZA , PPP FROM PERROS WHERE COD_ANIMAL = ?")
        stmt.setInt(1, cod)
        val listaAnimales = mutableListOf<Animal>()
        val result = stmt.executeQuery()
        while (result.next()) {
            val codigo = result.getInt("COD_ANIMAL")
            val name = result.getString("NOMBRE")
            val fec = result.getString("FECHA_NAC")
            val s = result.getString("SEXO")
            val ra = result.getString("RAZA")
            val p = result.getString("PPP")
            listaAnimales.add(Perro(codigo,name,fec,s,ra,p))
        }
        listaFinal = listaAnimales
    }catch (e: SQLException) {
        println("Error en la conexión: ${e.message}")
    } catch (e: ClassNotFoundException) {
        println("No se encontró el driver JDBC: ${e.message}")
    }
    return listaFinal
}

fun buscarTodos() : List<Animal>{
    val url = "jdbc:oracle:thin:@localhost:1521:xe"
    val usuario = "PROYECTO"
    val contrasena = "PROYECTO"
    var listaFinal = mutableListOf<Animal>()
    try {
        Class.forName("oracle.jdbc.driver.OracleDriver")
        val conexion = DriverManager.getConnection(url, usuario, contrasena)
        val stmt = conexion.prepareStatement("SELECT COD_ANIMAL , NOMBRE , FECHA_NAC , SEXO , RAZA , PPP FROM PERROS")
        val listaAnimales = mutableListOf<Animal>()
        val result1 = stmt.executeQuery()
        while (result1.next()){
            val codigo = result1.getInt("COD_ANIMAL")
            val name = result1.getString("NOMBRE")
            val fec = result1.getString("FECHA_NAC")
            val s = result1.getString("SEXO")
            val ra = result1.getString("RAZA")
            val p = result1.getString("PPP")
            listaAnimales.add(Perro(codigo,name,fec,s,ra,p))
        }
        stmt.close()
        conexion.close()
        val conexion2 = DriverManager.getConnection(url, usuario, contrasena)
        val stmt2 = conexion2.prepareStatement("SELECT COD_ANIMAL , NOMBRE , FECHA_NAC , SEXO , RAZA FROM GATOS")
        val result2 = stmt2.executeQuery()
        while (result2.next()){
            val codigo2 = result2.getInt("COD_ANIMAL")
            val name2 = result2.getString("NOMBRE")
            val fec2 = result2.getString("FECHA_NAC")
            val s2 = result2.getString("SEXO")
            val ra2 = result2.getString("RAZA")
            listaAnimales.add(Gato(codigo2,name2,fec2,s2,ra2))
        }
        listaFinal = listaAnimales
    }catch (e: SQLException) {
        println("Error en la conexión: ${e.message}")
    } catch (e: ClassNotFoundException) {
        println("No se encontró el driver JDBC: ${e.message}")
    }
    return listaFinal
}

fun buscarGato(cod:Int): List<Animal> {
    val url = "jdbc:oracle:thin:@localhost:1521:xe"
    val usuario = "PROYECTO"
    val contrasena = "PROYECTO"
    var listaFinal = mutableListOf<Animal>()
    try {
        Class.forName("oracle.jdbc.driver.OracleDriver")
        val conexion = DriverManager.getConnection(url, usuario, contrasena)
        val stmt = conexion.prepareStatement("SELECT COD_ANIMAL , NOMBRE , FECHA_NAC , SEXO , RAZA FROM GATOS WHERE COD_ANIMAL = ?")
        stmt.setInt(1, cod)
        val listaAnimales = mutableListOf<Animal>()
        val result = stmt.executeQuery()
        while (result.next()) {
            val codigo = result.getInt("COD_ANIMAL")
            val name = result.getString("NOMBRE")
            val fec = result.getString("FECHA_NAC")
            val s = result.getString("SEXO")
            val ra = result.getString("RAZA")
            listaAnimales.add(Gato(codigo,name,fec,s,ra))
        }
        listaFinal = listaAnimales
    }catch (e: SQLException) {
        println("Error en la conexión: ${e.message}")
    } catch (e: ClassNotFoundException) {
        println("No se encontró el driver JDBC: ${e.message}")
    }
    return listaFinal
}

