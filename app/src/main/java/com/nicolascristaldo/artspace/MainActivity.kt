package com.nicolascristaldo.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nicolascristaldo.artspace.ui.theme.ArtSpaceTheme
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceTheme {
                ArtSpaceApp()
            }
        }
    }
}

@Composable
fun ArtSpaceApp(modifier: Modifier = Modifier) {
    var imageNumber by remember { mutableIntStateOf(1) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(88.dp))
        ImageContainer(imageNumber,modifier = modifier.weight(1f))
        Spacer(modifier = Modifier.height(88.dp))
        ImageInformation(imageNumber,modifier = Modifier.padding(bottom = 36.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 36.dp)
        ) {
            Button(
                onClick = { imageNumber = returnNumber(imageNumber, false) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.button_color),
                    contentColor = colorResource(R.color.white)
                ),
                modifier = Modifier.width(120.dp)
            ) {
                Text(text = stringResource(R.string.button_previous_text))
            }
            Button(
                onClick = { imageNumber = returnNumber(imageNumber, true) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.button_color),
                    contentColor = colorResource(R.color.white)
                    ),
                modifier = Modifier.width(120.dp)
            ) {
                Text(text = stringResource(R.string.button_next_text))
            }
        }
    }
}

@Composable
fun ImageContainer(imageNumber: Int, modifier: Modifier = Modifier) {
    val image = when(imageNumber) {
        1 -> R.drawable.image_1
        2 -> R.drawable.image_2
        else -> R.drawable.image_3
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .shadow(8.dp)
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(image),
            contentDescription = null,
            modifier = Modifier.width(300.dp)
        )
    }
}

@Composable
fun ImageInformation(imageNumber: Int, modifier: Modifier = Modifier) {
    val imageTitle = when(imageNumber) {
        1 -> R.string.image_name_1
        2 -> R.string.image_name_2
        else -> R.string.image_name_3
    }
    val imageYear = when(imageNumber) {
        1 -> R.string.image_year_1
        2 -> R.string.image_year_2
        else -> R.string.image_year_3
    }
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(colorResource(id = R.color.information_card_background))
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(imageTitle),
            fontSize = 28.sp
        )

        Text(
            buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append(stringResource(id = R.string.image_author))
                }
                append(" (" + stringResource(imageYear) + ")")
            },
            fontSize = 14.sp
        )
    }
}

fun returnNumber(actualNumber: Int, isNextPressed: Boolean): Int {
    val number: Int

    if (isNextPressed) {
        if (actualNumber == 3) {
            number = 1
        }
        else {
            number = actualNumber + 1
        }
    }
    else {
        if (actualNumber == 1) {
            number = 3
        }
        else {
            number = actualNumber - 1
        }
    }

    return number
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArtSpaceTheme {
        ArtSpaceApp()
    }
}