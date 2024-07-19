package org.example.project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen() {
    val showDialog = remember { mutableStateOf(false) }
    val selectedActorIndex = remember { mutableStateOf(-1) }
    val actorImages = listOf(
        R.drawable.zack, // Путь к изображению Zac
        R.drawable.second, // Путь к изображению Jap
        R.drawable.tjhird,  // Путь к изображению Pi
        R.drawable.fourth // Путь к изображению Bred
    )

    Column {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            itemsIndexed(
                listOf("Zac", "Jap", "Pi", "Bred")
            ) { index, item ->
                Card(
                    elevation = 5.dp,
                    shape = RoundedCornerShape(15.dp),
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth()
                        .clickable {
                            selectedActorIndex.value = index
                            showDialog.value = true
                        }
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            modifier = Modifier
                                .size(64.dp)
                                .padding(5.dp)
                                .clip(CircleShape),
                            painter = painterResource(actorImages[index]),
                            contentDescription = "image",
                            contentScale = ContentScale.Crop
                        )
                        Column {
                            Text(text = item, fontSize = 20.sp)
                            Text(text = "Actor")
                        }
                    }
                }
            }
        }

        // Вызов диалога
        if (showDialog.value) {
            MyAlertDialog(
                onDismiss = { showDialog.value = false },
                actorName = listOf("Zac", "Jap", "Pi", "Bred")[selectedActorIndex.value]
            )
        }
    }
}

@Composable
fun MyAlertDialog(
    onDismiss: () -> Unit,
    actorName: String
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Dialog for $actorName") },
        text = { Text("This is a simple AlertDialog example for $actorName.") },
        confirmButton = {
            Button(onClick = onDismiss) {
                Text("OK")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}
