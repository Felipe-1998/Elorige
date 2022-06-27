package com.example.elorigen

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.elorigen.ui.theme.ElOrigenTheme

private val messages : List<MyMessage> = listOf(
    MyMessage("Felipe","Cuando un cliente entra a este sistema hace una conexión directa a uno de estos últimos, en donde recolecta y almacena toda la información y contenido disponible para compartir."),
    MyMessage("Felipe","Se trata entonces de un programa cuya función es la de conectar a los usuarios a través de una red sin servidores que facilita la descarga de música, películas, libros, fotos y software entre todos los otros usuarios, de manera gratuita."),
    MyMessage("Felipe","Estos archivos son compartidos “de computador a computador” por el solo hecho de tener acceso al sistema."),
    MyMessage("Felipe","Andres"),
    MyMessage("Felipe","Andres"),
    MyMessage("Felipe","Andres"),
    MyMessage("Felipe","Andres"),
    MyMessage("Felipe","Andres")
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ElOrigenTheme() {
                MyMessages(messages)
            }


        }
    }
}

data class MyMessage(val title:String,val body:String)

@Composable
fun MyMessages(messages : List<MyMessage>){
    LazyColumn {
        this.items(messages){ message ->
            MyComponent(message)
        }
    }
}
@Composable
fun MyComponent(message:MyMessage){
    Row(modifier = Modifier
        .background(MaterialTheme.colors.background)
        .padding(8.dp))
    {
        MyImages()
        MyTexts(message)
    }
}
@Composable
fun MyImages(){
    Image(painter = painterResource(id = R.drawable.ic_launcher_foreground ),
        contentDescription = "Prueba",
        modifier= Modifier
            .size(64.dp)
            .clip(CircleShape)
            .background(MaterialTheme.colors.primary)
             )

}

@Composable
fun MyTexts (message: MyMessage){
    var expanded by remember {mutableStateOf(false)}
    Column( modifier = Modifier.padding(start = 8.dp).clickable {
    expanded = !expanded
    }

    ){
        MyText(message.title,
            MaterialTheme.colors.primary,
        MaterialTheme.typography.subtitle1)
        Spacer(modifier = Modifier.height(16.dp))
        MyText(message.body,
            MaterialTheme.colors.onBackground,
            MaterialTheme.typography.subtitle2,
        if(expanded) {
            Int.MAX_VALUE
        } else {
            1
        }
        )
    }

}

@Composable
fun MyText (text:String, color : Color,style: TextStyle, lines: Int = Int.MAX_VALUE){
    Text(text, color = color,style = style, maxLines=lines)
}
@Preview(showSystemUi = true )
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewText(){
    ElOrigenTheme {
        MyMessages(messages)
    }

}

