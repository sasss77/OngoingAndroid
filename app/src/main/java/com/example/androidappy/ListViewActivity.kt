package com.example.androidappy

import android.os.Bundle
import android.widget.GridView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androidappy.ui.theme.AndroidAppyTheme

class ListViewActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            listView()

        }
    }
}

@Composable
fun listView() {
        val images = listOf(R.drawable.image,
        (R.drawable.google),
        (R.drawable.gacebook),
        (R.drawable.man),
            (R.drawable.fb)
    )
    val name = listOf(
        "eye",
        "google",
        "Facebook",
        "Man",
        "fb2"
    )

    Scaffold()
    {

            innerPadding ->
        LazyColumn(modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()){
            item {

            LazyRow(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                items(images.size) {index ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center

                    ) {
                        Image(
                            painterResource(images[index]), contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(120.dp)
                                .clip(shape = CircleShape)
                        )
                        Text(name[index])

                    }
                }

            }


                Spacer(modifier = Modifier.height(20.dp))

                LazyVerticalGrid (modifier = Modifier.fillMaxWidth().height(400.dp),
                    columns = GridCells.Fixed(2),
                    ) {
                    items(images.size) {index ->
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center

                        ) {
                            Image(
                                painterResource(images[index]), contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(120.dp)
                            )
                            Text(name[index])

                        }
                    }

                }





            LazyRow(modifier = Modifier.fillMaxWidth()) {
                item {

                Box(
                    modifier = Modifier
                        .size(120.dp)
                        .background(color = Color.Blue)
                        .padding(10.dp)
                )
                Box(
                    modifier = Modifier
                        .size(120.dp)
                        .background(color = Color.Yellow)
                        .padding(10.dp)
                )
                Box(
                    modifier = Modifier
                        .size(120.dp)
                        .background(color = Color.Red)
                        .padding(10.dp)
                )
                Box(
                    modifier = Modifier
                        .size(120.dp)
                        .background(color = Color.Gray)
                        .padding(10.dp)
                )
            }

                }
            Box( modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()
                .padding(20.dp)
                .background(color = Color.Green))
            Box( modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()
                .padding(20.dp)
                .background(color = Color.Red))
            Box( modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()
                .padding(20.dp)
                .background(color = Color.Blue))
            Box( modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()
                .padding(20.dp)
                .background(color = Color.Gray))
            Box( modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()
                .padding(20.dp)
                .background(color = Color.Yellow))
            Box( modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()
                .padding(20.dp)
                .background(color = Color.Black))
            Box( modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()
                .padding(20.dp)
                .background(color = Color.Magenta))


        }
            }

    }
}


@Preview
@Composable
fun PreviewWin() {
    listView()
}


