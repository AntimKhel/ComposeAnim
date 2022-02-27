package com.local.composeanimations.animations

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun SuspendingAnimationApi() {
    Text(
        text = "Suspending Apis",
        style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.ExtraBold),
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center
    )
    BallSyncAnimateApi()
}

@Composable
fun BallSyncAnimateApi() {
    Text(
        text = "animate()",
        style = MaterialTheme.typography.caption.copy(fontWeight = FontWeight.Bold, color = Color.DarkGray),
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