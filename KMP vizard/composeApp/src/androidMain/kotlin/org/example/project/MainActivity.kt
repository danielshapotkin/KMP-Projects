package org.example.project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val sharedFlow = MutableSharedFlow<People>()
    private val mutableState = MutableStateFlow(People(1, "unknown"))

    private val flow = flow {
        for (i in 1..10) {
            delay(1000)
            emit(People(i, "Daniel $i"))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        // Set Content
        setContent {
            var showContent by remember { mutableStateOf(false) }
            LaunchedEffect(Unit) {
                delay(3000) // Delay to show the content after 6 seconds
                showContent = true
            }

            // Display either the loading screen or the main content
            if (showContent) {
                MainScreen()
            }
        }
        // Collect values from Flow and update commonFlow
        lifecycleScope.launch {
            flow.collect { value ->
                mutableState.value = value
            }
        }

        // Emit values into SharedFlow
        lifecycleScope.launch {
            for (i in 1..10) {
                delay(1000)
                sharedFlow.emit(People(i, "Daniel"))
            }
        }
    }

    @Composable
    fun MainScreen() {
        val sharedFlowValues by sharedFlow.collectAsState(initial = People(0, "Initial"))
        val flowValues by flow.collectAsState(initial = People(0, "Initial"))

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Box(
                Modifier
                    .size(120.dp, 120.dp)
                    .background(Color.Red),
                contentAlignment = Alignment.Center
            ) {
                Button(
                    onClick = {

                    }
                ) {
                    Text("Toggle Color")
                }
            }

            // Display flow values
            Text(
                text = "Flow Values: ${flowValues.name} - ${flowValues.age}",
                modifier = Modifier.padding(16.dp)
            )

            // Display sharedFlow values
            Text(
                text = "SharedFlow Values: ${sharedFlowValues.name} - ${sharedFlowValues.age}",
                modifier = Modifier.padding(16.dp)
            )

        }
    }
}

data class People(
    val age: Int,
    val name: String
)
