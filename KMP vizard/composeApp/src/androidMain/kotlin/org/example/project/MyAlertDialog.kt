package org.example.project

import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun MyAlertDialog(
    onDismiss: () -> Unit,
    actorIndex: Int
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Info") },
        text = { Text(text = Info().getInfo(actorIndex + 1)) },
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