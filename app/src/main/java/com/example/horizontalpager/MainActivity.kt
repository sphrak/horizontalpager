package com.example.horizontalpager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.horizontalpager.ui.theme.HorizontalpagerTheme
import kotlin.math.abs

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HorizontalpagerTheme {
                PagerScreen()
            }
        }
    }
}


enum class Card {
    First,
    Second,
    Third,
    Fourth,
    Fifth,
    Sixth,
    Seventh,
    Eight,
    Ninth,
    Tenth
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PagerScreen(
    modifier: Modifier = Modifier,
) {

    val list: List<Card> = remember {
        Card.entries.toList()
    }

    val pagerState = rememberPagerState {
        list.size
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier,
                title = {
                    Text(text = "Pager")
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            Text(text = "Content A")
            HorizontalPager(
                state = pagerState
            ) {
                Page(
                    modifier = Modifier,
                    card = list[it]
                )
            }
            Text(text = "Content B")
        }
    }
}

inline val Card.stringRes @Composable get() = when (this) {
    Card.First -> "1"
    Card.Second -> "2"
    Card.Third -> "3"
    Card.Fourth -> "4"
    Card.Fifth -> "5"
    Card.Sixth -> "6"
    Card.Seventh -> "7"
    Card.Eight -> "8"
    Card.Ninth -> "9"
    Card.Tenth -> "10"
}


@Composable
private fun Page(
    modifier: Modifier = Modifier,
    card: Card
) {
    Box(
        modifier = modifier
            .semantics {
                contentDescription = "item ${card.name}"
            }
            .fillMaxWidth()
            .background(colorForValue(card.stringRes))
            .height(300.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "${card.stringRes}", fontSize = 30.sp)
    }
}

fun colorForValue(value: String): Color {
    val palette = listOf(
        Color(0xFFC62828), // Red
        Color(0xFFAD1457), // Pink
        Color(0xFF6A1B9A), // Purple
        Color(0xFF283593), // Indigo
        Color(0xFF0277BD), // Blue
        Color(0xFF00838F), // Cyan
        Color(0xFF2E7D32), // Green
        //Color(0xFFF9A825), // Yellow
        //Color(0xFFEF6C00), // Orange
    )
    val index = abs(value.hashCode()) % palette.size
    return palette[index].copy(0.48f)
}
