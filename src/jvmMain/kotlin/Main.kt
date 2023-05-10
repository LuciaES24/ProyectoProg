import Clases.Gato
import Clases.Perro
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key.Companion.C
import androidx.compose.ui.input.key.Key.Companion.R
import androidx.compose.ui.res.loadSvgPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

@Composable
@Preview
fun App() {
    var text by remember { mutableStateOf("") }
    var visible by remember { mutableStateOf(0) }
    var accion by remember { mutableStateOf(0) }
    var animal by remember { mutableStateOf("") }

    MaterialTheme {
        if (visible == 0){
            Column (
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize()
                    .background(color = Color(245,255,250)),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                Button(shape = RoundedCornerShape(50), colors = ButtonDefaults.buttonColors(backgroundColor = Color(60,179,113)), onClick = {
                    visible=1
                    conectarBase()
                }) {
                    Text("Comenzar")
                }
            }
        }
        if (visible == 1) {
            if (accion == 0){
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
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
                var nombre by remember { mutableStateOf("") }
                var fecha by remember { mutableStateOf("") }
                var sexo by remember { mutableStateOf("") }
                if (animal == ""){
                    Column (
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
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
                        .padding(16.dp)
                        .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center){
                        Text("Perro", style = TextStyle(fontSize = 30.sp))
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
                            val perro = Perro(nombre,fecha,sexo,raza,ppp)
                            insertarPerro(perro)
                        }, enabled = nombre.length != 0 && fecha.length != 0 && sexo.length!=0 && raza.length != 0 && ppp.length != 0){
                            Text("Registrar")
                        }
                        Button(shape = RoundedCornerShape(50), colors = ButtonDefaults.buttonColors(backgroundColor = Color(60,179,113)), onClick = {
                            animal=""
                        }){
                            Text("Atras")
                        }
                    }
                }
                if (animal=="Gato"){
                    var raza by remember { mutableStateOf("") }
                    Column (
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center){
                        Text("Gato", style = TextStyle(fontSize = 30.sp))
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
                            val gato = Gato(nombre,fecha,sexo,raza)
                            insertarGato(gato)
                        }, enabled = nombre.length!=0 || fecha.length!=0||sexo.length!=0||raza.length!=0){
                            Text("Registrar")
                        }
                        Button(shape = RoundedCornerShape(50), colors = ButtonDefaults.buttonColors(backgroundColor = Color(60,179,113)), onClick = {
                            animal=""
                        }){
                            Text("Atras")
                        }
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
