package br.com.alura.aluvery.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.alura.aluvery.R
import br.com.alura.aluvery.ui.theme.AluveryTheme
import br.com.alura.aluvery.ui.theme.Pink40
import br.com.alura.aluvery.ui.theme.PurpleGrey40

@Composable
fun MyFirstComposable() {
    Text(text = "teste")
}

@Preview(
    name = "textPreview",
    heightDp = 200,
    widthDp = 300,
    showBackground = true,
    backgroundColor = 0xFFFF1144,
    showSystemUi = true
)
@Composable
fun MyFirstComposablePreview() {
    AluveryTheme {
        Surface {
            MyFirstComposable()
        }
    }
}


@Preview(showBackground = true)
@Composable
fun testTeacher(){
    Row(
        Modifier
            .fillMaxWidth()
            .height(150.dp)
    ) {
        Box(
            Modifier
                .fillMaxHeight()
                .width(100.dp)
                .background(Color.Blue)
        )
        Column {
            Text(text = "Test 1",
                Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFededed))
                    .padding(16.dp)
            )
            Text(text = "Test 2", Modifier.padding(16.dp))
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ChallengeComposable() {
    Surface(Modifier.padding(8.dp), shape = RoundedCornerShape(8.dp), shadowElevation = 4.dp) {
        Row(
            Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) {
            val imageSize = 100.dp
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(imageSize)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Pink40, PurpleGrey40
                            )
                        )
                    )
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = null,
                    Modifier
                        .size(imageSize)
                        .align(Alignment.Center)
                        .offset(x = (imageSize / 2))
                        .clip(shape = CircleShape)
                        .border(2.dp, Brush.verticalGradient(listOf(
                            Color.Blue, Color.Cyan
                        )), shape = CircleShape)
                )
            }
            Spacer(modifier = Modifier.width(imageSize / 2))
            Box(
                Modifier
                    .padding(16.dp)
                    .fillMaxHeight()
                    .padding(16.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Column{
                    Text(
                        text = LoremIpsum(20).values.first(),
                        lineHeight = 20.sp,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}

@Composable
fun ChallengeComposable2(description: String = "") {
    Surface(
        shape = RoundedCornerShape(15.dp),
        shadowElevation = 4.dp
    ) {
        Column(
            Modifier
                .heightIn(250.dp, 260.dp)
                .width(200.dp)
                .verticalScroll(rememberScrollState())
        ) {
            val imageSize = 100.dp
            Box(
                modifier = Modifier
                    .height(imageSize)
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                Color.Cyan, Color.Blue
                            )
                        )
                    )
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = null,
                    Modifier
                        .size(imageSize)
                        .offset(y = imageSize / 2)
                        .clip(shape = CircleShape)
                        .align(Alignment.BottomCenter)
                )
            }
            Spacer(modifier = Modifier.height(imageSize / 2))
            Column(Modifier.padding(16.dp)) {
                Text(
                    text = LoremIpsum(50).values.first(),
                    fontSize = 18.sp,
                    fontWeight = FontWeight(700),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "R$ 14,99",
                    Modifier.padding(top = 8.dp),
                    fontSize = 14.sp,
                    fontWeight = FontWeight(400)
                )
            }
            if (description.isNotBlank()) {
                Text(
                    text = description,
                    Modifier
                        .background(MaterialTheme.colorScheme.primary)
                        .padding(
                            start = 16.dp,
                            end = 16.dp,
                            bottom = 16.dp,
                            top = 8.dp
                        ),
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}

@Preview
@Composable
fun ChallengeComposablePreview(){
    ChallengeComposable2()
}

@Preview
@Composable
fun ChallengeComposableDescriptionPreview() {
    ChallengeComposable2(LoremIpsum(50).values.first())
}
