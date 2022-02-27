package com.local.composeanimations

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.local.composeanimations.animations.AnimationsWithVisibilityApi
import com.local.composeanimations.animations.TransitionAnimationApi
import com.local.composeanimations.ui.theme.ComposeAnimationsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeAnimationsTheme {
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background) {
                    AnimationScreenContent()
                }
            }
        }
    }
}

@Composable
fun AnimationScreenContent() {
    Scaffold(
        modifier = Modifier,
        topBar = {
            TopAppBar(
                content = {
                    Text(
                        text = "Animations",
                        style = typography.h5.copy(fontWeight = FontWeight.ExtraBold),
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                },
                backgroundColor = Color.White,
                elevation = 8.dp
            )
        },
        content = {
            AnimationScreen()
        }
    )
}

@Composable
fun AnimationScreen() {
    LazyColumn(
        state = rememberLazyListState(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item { Spacer(modifier = Modifier.padding(4.dp)) }
        item { AnimationsWithVisibilityApi() }
        item { Spacer(modifier = Modifier.padding(4.dp)) }
        item { Divider(thickness = 24.dp) }
        item { Spacer(modifier = Modifier.padding(4.dp)) }
        item { TransitionAnimationApi() }
        item { Spacer(modifier = Modifier.padding(4.dp)) }
        item { Divider(thickness = 24.dp) }
        item { Spacer(modifier = Modifier.padding(4.dp)) }
        item { Spacer(modifier = Modifier.padding(100.dp)) }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeAnimationsTheme {
        AnimationScreenContent()
    }
}