package com.local.composeanimations.animations

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColor
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.runtime.*
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
    AnimatedState()
    LikeButton()
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

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedState() {
    Text(
        text = "animate*AsState()",
        style = MaterialTheme.typography.caption.copy(fontWeight = FontWeight.Bold, color = Color.DarkGray),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp, bottom = 4.dp),
        textAlign = TextAlign.Center
    )
    var paid by remember {
        mutableStateOf(false)
    }
    val width = animateDpAsState(
        targetValue = if (paid) 40.dp else 120.dp
    )
    val color = animateColorAsState(
        targetValue = if (paid) Color.Green else Color.Blue
    )

    Card(
        modifier = Modifier
            .padding(vertical = 4.dp, horizontal = 16.dp)
            .size(width = width.value, height = 40.dp),
        shape = CircleShape,
        backgroundColor = color.value
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .clickable {
                    paid = !paid
                },
        contentAlignment = Alignment.Center) {
            AnimatedVisibility(visible = paid.not()) {
                Text(
                    text = "Pay",
                    style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.Bold, color = Color.White),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            AnimatedVisibility(visible = paid) {
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(40.dp)
                )
            }
        }
    }
}

private enum class LikedState { Liked, Unliked }

private const val LikedLabel = "liked"

@Composable
fun LikeButton(
    modifier: Modifier = Modifier
) {
    Text(
        text = "updateTransition()",
        style = MaterialTheme.typography.caption.copy(fontWeight = FontWeight.Bold, color = Color.DarkGray),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp, bottom = 4.dp),
        textAlign = TextAlign.Center
    )

    var isLiked by remember {
        mutableStateOf(false)
    }
    val likedState = if (isLiked) LikedState.Liked else LikedState.Unliked
    val transition = updateTransition(likedState, label = LikedLabel)
    val colour by transition.animateColor(label = LikedLabel) { state ->
        when (state) {
            LikedState.Liked -> Color.Red
            LikedState.Unliked -> Color.Gray
        }
    }
    val size by transition.animateDp(label = LikedLabel) { state ->
        when (state) {
            LikedState.Liked -> 36.dp
            LikedState.Unliked -> 24.dp
        }
    }

    IconToggleButton(
        checked = isLiked,
        onCheckedChange = { isLiked = !isLiked },
        modifier = modifier
    ) {
        Icon(
            imageVector = Icons.Outlined.Favorite,
            contentDescription = "",
            tint = colour,
            modifier = Modifier.size(size)
        )
    }
}