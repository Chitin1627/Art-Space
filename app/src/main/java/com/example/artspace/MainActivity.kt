package com.example.artspace

import android.graphics.drawable.shapes.OvalShape
import android.graphics.drawable.shapes.Shape
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpaceApp()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceApp(modifier: Modifier = Modifier) {
    ButtonFunction()
}

@Composable
fun ImageDetails(
    @DrawableRes painter: Int,
    @StringRes photoDescription: Int,
    @StringRes nameOfThePerson: Int,
    modifier: Modifier = Modifier) {
    Surface(
        shadowElevation = 5.dp,
        modifier = Modifier
            .height(500.dp)
            .width(350.dp)
            .padding(start = 10.dp, end = 10.dp)
    ) {
        Image(
            painter = painterResource(id = painter),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.background,
                )
                .padding(20.dp)

        )
    }

    Box(
        modifier = Modifier
            .padding(top = 20.dp)
            .background(
                Color(203, 195, 227),
                shape = RoundedCornerShape(16.dp)
            )
            .height(150.dp)
            .width(350.dp)

    ) {
        Column(
            modifier = Modifier.align(Alignment.Center)
        ) {
            Text(
                text = stringResource(id = photoDescription),
                fontSize = 25.sp,
                color = Color.Black,
                fontWeight = FontWeight.Light
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = stringResource(id = nameOfThePerson),
                fontWeight = FontWeight.ExtraBold,
                color = Color.Black
            )
        }
    }
}

@Composable
fun ButtonFunction(modifier: Modifier = Modifier) {
    var buttonClickNumber by remember { mutableStateOf(0) }
    var prevAllowed by remember { mutableStateOf(false) }
    var painter by remember { mutableStateOf(0) }
    var photoDescription by remember { mutableStateOf(0) }
    var nameOfThePerson by remember { mutableStateOf(0) }
    var nextAllowed by remember { mutableStateOf(true) }
    var darkTheme by remember { mutableStateOf(Color.White) }

    prevAllowed = when(buttonClickNumber) {
        0 -> false
        else -> true
    }

    nextAllowed = when(buttonClickNumber) {
        in 0..1 -> true
        else -> false
    }

    painter = when(buttonClickNumber) {
        0 -> R.drawable.messi
        1 -> R.drawable.ronaldo
        else -> R.drawable.neymar
    }

    photoDescription = when(buttonClickNumber) {
        0 -> R.string.messi_wc
        1 -> R.string.ronaldo_ucl
        else -> R.string.neymar_ucl
    }

    nameOfThePerson = when(buttonClickNumber) {
        0 -> R.string.leo_messi
        1 -> R.string.ronaldo
        else -> R.string.neymar
    }

    darkTheme = when(isSystemInDarkTheme()) {
        true -> Color.White
        else -> Color.Black
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ImageDetails(
            painter = painter,
            photoDescription = photoDescription,
            nameOfThePerson = nameOfThePerson)

        Row (
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.padding(top = 10.dp)
        ) {
            Button(
                onClick = { buttonClickNumber-- },
                modifier = Modifier
                    .padding(start = 20.dp, top = 10.dp, end = 20.dp)
                    .size(width = 80.dp, height = 40.dp)
                    .weight(1f),
                shape = CircleShape,
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 5.dp,
                    pressedElevation = 7.dp,
                    disabledElevation = 0.dp,
                    hoveredElevation = 5.dp,
                    focusedElevation = 5.dp
                ),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.background
                ),
                border = BorderStroke(1.dp, Color.White),
                enabled = prevAllowed
            ) {
                Text(
                    text = "Previous",
                    fontSize = 16.sp,
                    color = darkTheme
                )
            }

            Button(
                onClick = { buttonClickNumber++ },
                modifier = Modifier
                    //.align(Alignment.Start)
                    .padding(start = 20.dp, end = 20.dp, top = 10.dp)
                    .size(width = 80.dp, height = 40.dp)
                    .weight(1f),
                shape = CircleShape,
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 5.dp,
                    pressedElevation = 7.dp,
                    disabledElevation = 0.dp,
                    hoveredElevation = 5.dp,
                    focusedElevation = 5.dp
                ),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.background
                ),
                border = BorderStroke(1.dp, Color.White),
                enabled = nextAllowed
            ) {
                Text(
                    text = "Next",
                    fontSize = 16.sp,
                    color = darkTheme
                )
            }
        }



    }
}

@Preview(
    showBackground = true,
    showSystemUi = true)

@Composable
fun GreetingPreview() {
    ArtSpaceTheme {
        ArtSpaceApp()
    }
}