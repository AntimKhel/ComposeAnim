package com.local.composeanimations.animations

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.local.composeanimations.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun AnimationsWithVisibilityApi() {
    Text(
        text = "Visibility Apis",
        style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.ExtraBold),
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center
    )
    AnimateVisibilityAnim()
    CrossFadeAnim()
}


@OptIn(ExperimentalMaterialApi::class, ExperimentalAnimationApi::class)
@Composable
fun AnimateVisibilityAnim() {
    Text(
        text = "AnimateVisibility() and animateContentSize()",
        style = MaterialTheme.typography.caption.copy(fontWeight = FontWeight.Bold, color = Color.DarkGray),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp),
        textAlign = TextAlign.Center
    )
    var expanded by remember { mutableStateOf(false) }
    var sText by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = expanded, key2 = sText) {
        delay(1000)
        if (expanded or sText) {
            expanded = false
            sText = false
        }
    }
    Card(
        onClick = {
            expanded = !expanded
            scope.launch {
                delay(1000)
                sText = true
            }
        },
        modifier = Modifier
            .padding(8.dp)
            .animateContentSize(),
        elevation = 12.dp,
        backgroundColor = Color.LightGray
    ) {
        Row(Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = R.drawable.jiologo),
                modifier = Modifier.size(100.dp),
                tint = Color.Unspecified,
                contentDescription = null
            )
            Column {
                AnimatedVisibility(
                    expanded,
                    modifier = Modifier
                ) {
                    Text(
                        modifier = Modifier.padding(start = 8.dp), text = "Introducing",
                        style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold, color = Color.DarkGray)
                    )
                }
                AnimatedVisibility(
                    sText,
                    modifier = Modifier
                ) {
                    Text(
                        modifier = Modifier.padding(start = 8.dp), text = "JioMeet",
                        style = MaterialTheme.typography.body2.copy(fontWeight = FontWeight.Bold, color = Color.DarkGray)
                    )
                }
            }
        }
    }
}

@Composable
fun CrossFadeAnim() {
    Text(
        text = "Crossfade()",
        style = MaterialTheme.typography.caption.copy(fontWeight = FontWeight.Bold, color = Color.DarkGray),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp),
        textAlign = TextAlign.Center
    )

    val color = listOf(Color.Red, Color.Green, Color.Blue, Color.Yellow, Color.Cyan, Color.Magenta, Color.DarkGray)
    var tint by remember {
        mutableStateOf(Color.Red)
    }

    LaunchedEffect(key1 = tint) {
        delay(1500)
        tint = color.filter {
            it != tint
        }.random()
    }

    Crossfade(
        targetState = tint,
        animationSpec = tween(
            durationMillis = 1000
        )
    ) {
        Box(modifier = Modifier) {
            Icon(
                painter = painterResource(id = R.drawable.img),
                modifier = Modifier.size(100.dp),
                tint = it,
                contentDescription = null
            )
        }
    }
}

