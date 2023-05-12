import Clases.Gato
import Clases.Perro
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application


@Composable
@Preview
fun App() {
    var visible by remember { mutableStateOf(0) }
    var accion by remember { mutableStateOf(0) }
    var animal by remember { mutableStateOf("") }
    val url = "jdbc:oracle:thin:@localhost:1521:xe"
    val usuario = "PROYECTO"
    val contrasena = "PROYECTO"
    Class.forName("oracle.jdbc.driver.OracleDriver")

    MaterialTheme {
        if (visible == 0){
            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color(245,255,250)),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ){
                Button(shape = RoundedCornerShape(50), colors = ButtonDefaults.buttonColors(backgroundColor = Color(60,179,113)), onClick = {
                    visible=1
                }) {
                    Text("Comenzar")
                }
            }
        }
        if (visible == 1) {
            if (accion == 0){
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = Color(245,255,250)),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(shape = RoundedCornerShape(50), colors = ButtonDefaults.buttonColors(backgroundColor = Color(60,179,113)), onClick = {
                        accion = 1
                    }) {
                        Text("Registrar animal")
                    }
                    Button(shape = RoundedCornerShape(50), colors = ButtonDefaults.buttonColors(backgroundColor = Color(60,179,113)), onClick = {
                        accion = 2
                    }) {
                        Text("Mostrar un animal")
                    }
                    Button(shape = RoundedCornerShape(50), colors = ButtonDefaults.buttonColors(backgroundColor = Color(60,179,113)), onClick = {
                        accion = 3
                    }) {
                        Text("Mostrar todos los animales")
                    }
                    Button(shape = RoundedCornerShape(50), colors = ButtonDefaults.buttonColors(backgroundColor = Color(60,179,113)), onClick = {
                        visible=0
                    }) {
                        Text("Atras")
                    }
                }
            }
            if (accion == 1){
                var codigo by remember { mutableStateOf("") }
                var nombre by remember { mutableStateOf("") }
                var fecha by remember { mutableStateOf("") }
                var sexo by remember { mutableStateOf("") }
                if (animal == ""){
                    Column (
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = Color(245,255,250)),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceEvenly
                            ){
                        Row (
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            TextButton(onClick = {
                                animal = "Perro"
                            }){
                                Text("Perro")
                            }
                            TextButton(onClick = {
                                animal = "Gato"
                            }){
                                Text("Gato")
                            }
                        }
                        Button(shape = RoundedCornerShape(50), colors = ButtonDefaults.buttonColors(backgroundColor = Color(60,179,113)), onClick = {
                            accion=0
                        }){
                            Text("Atras")
                        }
                    }
                }
                if (animal=="Perro"){
                    var raza by remember { mutableStateOf("") }
                    var ppp by remember { mutableStateOf("") }
                    Column (
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = Color(245,255,250)),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceEvenly
                    ){
                        Text("Perro", style = TextStyle(fontSize = 30.sp))
                        TextField(
                            value = codigo,
                            onValueChange = {codigo=it},
                            label = { Text("Codigo identificador (nnn)") }
                        )
                        TextField(
                            value = nombre,
                            onValueChange = {nombre=it},
                            label = { Text("Nombre del animal") }
                        )
                        TextField(
                            value = fecha,
                            onValueChange = {fecha=it},
                            label = { Text("Fecha de nacimiento (dd/mm/aa)") }
                        )
                        TextField(
                            value = sexo,
                            onValueChange = {sexo=it},
                            label = { Text("Sexo (M/H)") }
                        )
                        TextField(
                            value = raza,
                            onValueChange = {raza=it},
                            label = { Text("Raza") }
                        )
                        Text("¿Es considerado PPP?")
                        Row (
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            TextButton(onClick = {
                                ppp="SI"
                            }){
                                Text("Si")
                            }
                            TextButton(onClick = {
                                ppp="NO"
                            }){
                                Text("No")
                            }
                        }
                        Button(shape = RoundedCornerShape(50), colors = ButtonDefaults.buttonColors(backgroundColor = Color(60,179,113)), onClick = {
                            val perro = Perro(codigo.toInt(),nombre,fecha,sexo,raza,ppp)
                            insertarPerro(perro)
                            nombre = ""
                            fecha = ""
                            sexo = ""
                            raza = ""
                            ppp = ""
                        }, enabled = codigo.length != 0 &&  nombre.length != 0 && fecha.length != 0 && sexo.length!=0 && raza.length != 0 && ppp.length != 0){
                            Text("Registrar")
                        }
                        Button(shape = RoundedCornerShape(50), colors = ButtonDefaults.buttonColors(backgroundColor = Color(60,179,113)), onClick = {
                            animal=""
                            nombre = ""
                            fecha = ""
                            sexo = ""
                            raza = ""
                            ppp = ""
                        }){
                            Text("Atras")
                        }
                    }
                }
                if (animal=="Gato"){
                    var raza by remember { mutableStateOf("") }
                    Column (
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = Color(245,255,250)),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceEvenly
                    ){
                        Text("Gato", style = TextStyle(fontSize = 30.sp))
                        TextField(
                            value = codigo,
                            onValueChange = {codigo=it},
                            label = { Text("Codigo identificador (nnn)") }
                        )
                        TextField(
                            value = nombre,
                            onValueChange = {nombre=it},
                            label = { Text("Nombre del animal") }
                        )
                        TextField(
                            value = fecha,
                            onValueChange = {fecha=it},
                            label = { Text("Fecha de nacimiento (dd/mm/aa)") }
                        )
                        TextField(
                            value = sexo,
                            onValueChange = {sexo=it},
                            label = { Text("Sexo (M/H)") }
                        )
                        TextField(
                            value = raza,
                            onValueChange = {raza=it},
                            label = { Text("Raza") }
                        )
                        Button(shape = RoundedCornerShape(50), colors = ButtonDefaults.buttonColors(backgroundColor = Color(60,179,113)), onClick = {
                            val gato = Gato(codigo.toInt(),nombre,fecha,sexo,raza)
                            insertarGato(gato)
                            nombre = ""
                            fecha = ""
                            sexo = ""
                            raza = ""
                        }, enabled = codigo.length != 0 && nombre.length!=0 && fecha.length!=0 && sexo.length!=0 && raza.length!=0){
                            Text("Registrar")
                        }
                        Button(shape = RoundedCornerShape(50), colors = ButtonDefaults.buttonColors(backgroundColor = Color(60,179,113)), onClick = {
                            animal=""
                            nombre = ""
                            fecha = ""
                            sexo = ""
                            raza = ""
                        }){
                            Text("Atras")
                        }
                    }
                }
            }
            if (accion == 2){
                var conectado by remember { mutableStateOf("") }
                Column (
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = Color(245,255,250)),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text("¿Qué animal estás buscando?", style = TextStyle(fontSize = 30.sp))
                    Row (
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        TextButton(onClick = {
                            animal = "Perro"
                        }){
                            Text("Perro")
                        }
                        TextButton(onClick = {
                            animal = "Gato"
                        }){
                            Text("Gato")
                        }
                    }
                    Button(shape = RoundedCornerShape(50), colors = ButtonDefaults.buttonColors(backgroundColor = Color(60,179,113)), onClick = {
                        accion = 0
                    }){
                        Text("Atras")
                    }
                }
                if (animal == "Perro"){
                    var codigoPerro by remember { mutableStateOf("") }
                    Column (
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = Color(245,255,250)),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceEvenly
                    ){
                        TextField(
                            value = codigoPerro,
                            onValueChange = {codigoPerro=it},
                            label = { Text("Codigo del perro") }
                        )
                        Button(shape = RoundedCornerShape(50), colors = ButtonDefaults.buttonColors(backgroundColor = Color(60,179,113)), onClick = {
                            conectado = "Si"
                        }){
                            Text("Buscar")
                        }
                        Button(shape = RoundedCornerShape(50), colors = ButtonDefaults.buttonColors(backgroundColor = Color(60,179,113)), onClick = {
                            animal = ""
                        }){
                            Text("Atras")
                        }
                    }
                    if (conectado=="Si"){
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(color = Color(245,255,250)),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.SpaceEvenly
                        ) {
                            val perroEncontrado = buscarPerro(codigoPerro.toInt())
                            Box(
                                modifier = Modifier
                                    .align(Alignment.CenterHorizontally)
                                    .border(BorderStroke(3.dp, Color.Black))
                            ){
                                Text(perroEncontrado.toString())
                            }
                            Button(shape = RoundedCornerShape(50), colors = ButtonDefaults.buttonColors(backgroundColor = Color(60,179,113)), onClick = {
                                conectado = ""
                                codigoPerro = ""
                            }){
                                Text("Atras")
                            }
                        }
                    }
                }
                if (animal == "Gato"){
                    var codigoGato by remember { mutableStateOf("") }
                    Column (
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = Color(245,255,250)),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceEvenly
                    ){
                        TextField(
                            value = codigoGato,
                            onValueChange = {codigoGato=it},
                            label = { Text("Codigo del gato") }
                        )
                        Button(shape = RoundedCornerShape(50), colors = ButtonDefaults.buttonColors(backgroundColor = Color(60,179,113)), onClick = {
                            conectado = "Si"
                        }){
                            Text("Buscar")
                        }
                        Button(shape = RoundedCornerShape(50), colors = ButtonDefaults.buttonColors(backgroundColor = Color(60,179,113)), onClick = {
                            animal = ""
                        }){
                            Text("Atras")
                        }
                    }
                    if (conectado=="Si"){
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(color = Color(245,255,250)),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.SpaceEvenly
                        ) {
                            val gatoEncontrado = buscarGato(codigoGato.toInt())
                            Box(
                                modifier = Modifier
                                    .align(Alignment.CenterHorizontally)
                                    .border(BorderStroke(3.dp, Color.Black))
                            ){
                                Text(gatoEncontrado.toString())
                            }
                            Button(shape = RoundedCornerShape(50), colors = ButtonDefaults.buttonColors(backgroundColor = Color(60,179,113)), onClick = {
                                conectado = ""
                                codigoGato = ""
                            }){
                                Text("Atras")
                            }
                        }
                    }
                }
            }
            if (accion==3){
                Column (
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = Color(245,255,250))
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly
                ){
                    val lista = buscarTodos()
                    for (animal in lista){
                        Box(
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .border(BorderStroke(3.dp, Color.Black))
                        ){
                            Text(animal.toString())
                        }
                        Spacer(modifier = Modifier.size(5.dp))
                    }
                    Button(shape = RoundedCornerShape(50), colors = ButtonDefaults.buttonColors(backgroundColor = Color(60,179,113)), onClick = {
                        accion = 0
                    }){
                        Text("Atras")
                    }
                }
            }
        }
    }
}


fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
