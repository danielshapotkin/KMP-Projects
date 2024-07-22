package org.example.project

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Item (actor: Actor, index: Int){
    val showDialog = remember { mutableStateOf(false) }
    val selectedActorIndex = remember { mutableStateOf(0) }
    var isExpanded by remember {
        mutableStateOf(false)
    }
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
                painter = painterResource(id = actor.imageID),
                contentDescription = "image",
                contentScale = ContentScale.Crop
            )
            Column {
                Text(text = actor.title, fontSize = 20.sp)
                Text(modifier = Modifier.clickable {
                    isExpanded = !isExpanded
                }, maxLines = if (isExpanded)10 else 1, text = actor.content)
            }
        }
    }

    if (showDialog.value) {
        MyAlertDialog(
            onDismiss = { showDialog.value = false },
            actorIndex = selectedActorIndex.value
        )
    }
}