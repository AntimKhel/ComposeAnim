package com.local.composeanimations.animations

import androidx.compose.animation.core.*
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.horizontalDrag
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.local.composeanimations.R
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SuspendingAnimationApi() {
    Text(
        text = "Suspending Apis",
        style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.ExtraBold),
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center
    )
    BallSyncAnimateApi()
    DraggableCardWithAnimate()
}

@Composable
fun BallSyncAnimateApi() {
    Text(
        text = "animate()",
        style = MaterialTheme.typography.caption.copy(fontWeight = FontWeight.Bold,
            color = Color.DarkGray),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp, bottom = 4.dp),
        textAlign = TextAlign.Center
    )

    val animationValues = (1..3).map { index ->
        var animatedValue by remember { mutableStateOf(0f) }
        LaunchedEffect(key1 = Unit) {
            delay(70L * index)
            animate(
                initialValue = 0f,
                targetValue = 12f,
                animationSpec = infiniteRepeatable(
                    animation = tween(durationMillis = 300),
                    repeatMode = RepeatMode.Reverse,
                )
            ) { value, _ -> animatedValue = value }
        }
        animatedValue
    }
    Row {
        animationValues.forEach { animatedValue ->
            Icon(
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .offset(y = animatedValue.dp),
                imageVector = Icons.Default.ThumbUp,
                contentDescription = ""
            )
        }
    }
}

@Composable
fun DraggableCardWithAnimate() {
    Text(
        text = "Animatable()",
        style = MaterialTheme.typography.caption.copy(fontWeight = FontWeight.Bold,
            color = Color.DarkGray),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp, bottom = 4.dp),
        textAlign = TextAlign.Center
    )
    val pointerAnimatableX = remember { Animatable(0f) }
    pointerAnimatableX.updateBounds(((-(LocalConfiguration.current.screenWidthDp.toFloat() / 2)) + 50f),
        (LocalConfiguration.current.screenWidthDp.toFloat() / 2) - 50f)

    val modifier = Modifier.pointerInput(Unit) {
        coroutineScope {
            while (true) {
                val firstDownId = awaitPointerEventScope {
                    awaitFirstDown().id
                }
                awaitPointerEventScope {
                    horizontalDrag(firstDownId, onDrag = {
                        launch {
                            pointerAnimatableX.animateTo(it.position.x)
                            delay(1000L)
                            pointerAnimatableX.snapTo(0f)
                        }
                    })
                }
            }
        }
    }
    Icon(
        painter = painterResource(id = R.drawable.img),
        modifier = modifier
            .size(50.dp)
            .offset(x = pointerAnimatableX.value.dp),
        contentDescription = ""
    )
}