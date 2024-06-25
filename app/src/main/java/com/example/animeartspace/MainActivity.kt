package com.example.animeartspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.currentRecomposeScope
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.animeartspace.R.color.blue_300
import com.example.animeartspace.ui.theme.AnimeArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimeArtSpaceTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    ArtMainScreen()
                }
            }
        }
    }
}

@Composable
fun ArtMainScreen(modifier: Modifier = Modifier) {

    val firstArtwork = R.drawable.girl_ai
    val secondArtwork = R.drawable.girl_in_autumn
    val thirdArtwork = R.drawable.girl_superhero
    val fourthArtwork = R.drawable.girl_under_water

    var title by remember { mutableStateOf(R.string.ai_girl) }
    var description by remember { mutableStateOf(R.string.ai_girl_description) }
    var currentArtwork by remember { mutableStateOf(firstArtwork) }

    Column(modifier = modifier) {
        ArtTextAndImage(
            titleImage = title,
            description = description,
            image = currentArtwork,
            modifier = Modifier.weight(1f)
        )
        Row(modifier = modifier.padding(horizontal = 16.dp)) {
            /** Previous Button */
            ArtButton(
                modifier = modifier.weight(0.4f),
                onClick = {
                    when (currentArtwork) {
                        firstArtwork -> {
                            currentArtwork = fourthArtwork
                            title = R.string.girl_under_water
                            description = R.string.girl_under_water_description
                        }

                        secondArtwork -> {
                            currentArtwork = firstArtwork
                            title = R.string.ai_girl
                            description = R.string.ai_girl_description
                        }

                        thirdArtwork -> {
                            currentArtwork = secondArtwork
                            title = R.string.girl_in_autumn
                            description = R.string.girl_in_autumn_description
                        }

                        else -> {
                            currentArtwork = thirdArtwork
                            title = R.string.girl_superhero
                            description = R.string.girl_superhero_description
                        }
                    }
                },
                text = "Previous"
            )
            Spacer(modifier = Modifier.size(16.dp))

            /** Next Button */
            ArtButton(modifier = modifier.weight(0.4f), onClick = {
                when (currentArtwork) {
                    firstArtwork -> {
                        currentArtwork = secondArtwork
                        title = R.string.girl_in_autumn
                        description = R.string.girl_in_autumn_description
                    }

                    secondArtwork -> {
                        currentArtwork = thirdArtwork
                        title = R.string.girl_superhero
                        description = R.string.girl_superhero_description
                    }

                    thirdArtwork -> {
                        currentArtwork = fourthArtwork
                        title = R.string.girl_under_water
                        description = R.string.girl_under_water_description
                    }

                    else -> {
                        currentArtwork = firstArtwork
                        title = R.string.ai_girl
                        description = R.string.ai_girl_description
                    }
                }
            }, text = "Next")

        }

    }
}

@Composable
fun ArtTextAndImage(@StringRes titleImage:Int, @StringRes description:Int, @DrawableRes image: Int, modifier: Modifier = Modifier) {
        Column(
            modifier = modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(image),
                contentDescription = stringResource(description),
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.5f),
                contentScale = ContentScale.Crop
            )
            Text(text = stringResource(description), fontWeight = FontWeight.Bold, fontSize = 20.sp, textAlign = TextAlign.Center)
            Text(text = stringResource(titleImage), fontWeight = FontWeight.Bold, fontSize = 16.sp)
    }
}

@Composable
fun ArtButton(modifier: Modifier, onClick: () -> Unit, text: String) {
    Button(
        modifier = modifier,
        onClick = onClick,
        border = BorderStroke(width = 2.dp, color = Color.Magenta),
        colors = ButtonDefaults.buttonColors(colorResource(id = blue_300)),
        shape = RoundedCornerShape(size = 15.dp),
        elevation = ButtonDefaults.elevatedButtonElevation(1.dp)
    ) {
        Text(
            text = text,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AnimeArtSpaceTheme {
        ArtMainScreen()
    }
}