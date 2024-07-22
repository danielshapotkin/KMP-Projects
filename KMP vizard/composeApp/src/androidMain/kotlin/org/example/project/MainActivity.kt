package org.example.project

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
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
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class MainActivity : ComponentActivity() {
    private val API_KEY = "9cbbdbaf4f0aa3a2b5558f122d628b22"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val state = remember { mutableStateOf("Unknown") }
            LazyColumn (
                modifier = Modifier.fillMaxWidth()
            ) {
                itemsIndexed(
                    listOf(
                        Actor(R.drawable.zack, "Zack", "test dytsfaiwytg sytadfyjag sjydtfajytsf twghjkiwhgpewjiogloiesj"
                                + "bytufjuyjqkygcuih87353btyfqiuyfgbjauygbjydtfgvjutijytkjbkuygjytgukygkuygiuygy uyguygkuygkuyg"),
                        Actor(R.drawable.second, "Second", "test"),
                        Actor(R.drawable.tjhird, "Third", "test"),
                        Actor(R.drawable.fourth, "Fourth", "test")
                    )
                ) { index, item ->
                        Item(item, index)
                }
            }
            Box(contentAlignment = Alignment.BottomCenter, modifier = Modifier.fillMaxSize()){
                Text(text = state.value, modifier = Modifier.clickable {
                    getResult("Minsk", state, this@MainActivity)
                })
            }


        }
    }
private fun getResult(city: String, state:MutableState<String>, context: Context){
    val url = "https://api.openweathermap.org/data/2.5/weather?q=$city&appid=9cbbdbaf4f0aa3a2b5558f122d628b22"
    val queue = Volley.newRequestQueue(context)
    val stringRequest = StringRequest(
        Request.Method.GET,
        url,
        {
            response->
            val jsonObject = JSONObject(response)

            val mainObject = jsonObject.getJSONObject("main")

            val temp = mainObject.getDouble("temp")

            state.value = temp.toString()
        },
        {
            error->
        }
    )
    queue.add(stringRequest)
}
}

