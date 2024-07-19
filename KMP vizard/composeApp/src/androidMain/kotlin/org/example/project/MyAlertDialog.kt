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


class MyAlertDialog{
    @Composable
    fun MyAlertDialog(
        showDialog: MutableState<Boolean>,
        onDismiss: () -> Unit,
        index:Int
    ) {
        if (showDialog.value) {
            AlertDialog(
                onDismissRequest = {
                    showDialog.value = false
                    onDismiss()
                },
                title = { Text(text = "Actor Info") },
                text = { Text("") },
                confirmButton = {
                    Button(onClick = {
                        showDialog.value = false
                        onDismiss()
                    }) {
                        Text("OK")
                    }
                },
                dismissButton = {
                    Button(onClick = {
                        showDialog.value = false
                        onDismiss()
                    }) {
                        Text("Cancel")
                    }
                }
            )
        }
    }

}
