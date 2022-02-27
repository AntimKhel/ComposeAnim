package com.local.composeanimations.animations

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.local.composeanimations.R

@Composable
fun TransitionAnimationApi() {
    Text(
        text = "Transition Apis",
        style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.ExtraBold),
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center
    )
    ComposeLogoComponent()
}

@Composable
fun ComposeLogoComponent() {
    Text(
        text = "rememberInfiniteTransition()",
        style = MaterialTheme.typography.caption.copy(fontWeight = FontWeight.Bold, color = Color.DarkGray),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp),
        textAlign = TextAlign.Center
    )

    val infiniteTransition = rememberInfiniteTransition()
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 800,
                easing = FastOutLinearInEasing,
            ),
        )
    )

    Box(
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.img),
            modifier = Modifier.size(60.dp),
            tint = Color.Red,
            contentDescription = null
        )
        Canvas(modifier = Modifier.size(68.dp)) {
            drawArc(
                color = Color.Blue,
                topLeft = Offset(1f, 1f),
                startAngle = rotation,
                sweepAngle = 150f,
                useCenter = false,
                style = Stroke(12f)
            )
        }
    }
}