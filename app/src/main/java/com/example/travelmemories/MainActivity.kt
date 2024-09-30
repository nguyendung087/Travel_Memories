package com.example.travelmemories

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.travelmemories.models.Destination
import com.example.travelmemories.models.DestinationList
import com.example.travelmemories.ui.theme.Shape
import com.example.travelmemories.ui.theme.TravelMemoriesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TravelMemoriesTheme {
                Surface {
                    TravelApp()
                }
            }
        }
    }
}

@Composable
fun TravelApp() {
    Scaffold(
        topBar = {
            TravelAppBar()
        },
    ) {
        TravelItemList(DestinationList.destinationList, it)

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TravelAppBar() {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.displayLarge
            )
        }
    )
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun TravelItemList(
    destinationList : List<Destination>,
    contentPadding : PaddingValues = PaddingValues(0.dp)
) {
    
    LazyColumn(contentPadding = contentPadding) {
            items(destinationList) { destinations /*, index*/ ->
                TravelItem(
                    destinations,
                )
            }

    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun TravelItem(
    destination: Destination,
    modifier: Modifier = Modifier
) {
    var expanded by remember {
        mutableStateOf(false)
    }
    val visibleState = remember {
        MutableTransitionState(false).apply {
            // Start the animation immediately.
            targetState = true
        }
    }
    AnimatedVisibility(
        visibleState = visibleState,
        enter = fadeIn(
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioNoBouncy
            ),

            ),
        exit = fadeOut(),
    ) {
        Card(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.tertiaryContainer)
                .padding(16.dp)
                .animateEnterExit(
                    enter = slideInHorizontally(
                        animationSpec = spring(
                            stiffness = Spring.StiffnessVeryLow,
                            dampingRatio = Spring.DampingRatioNoBouncy
                        ),
                        //                                initialOffsetY = {
                        //                                    it * (index + 1)
                        //                                }
                    )
                ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 5.dp,
            )
        ) {
            TravelInformation(
                isExpanded = expanded,
                onClick = {
                    expanded = !expanded
                },
                destination = destination
            )
        }
    }
}

@Composable
fun TravelInformation(
    destination: Destination,
    isExpanded : Boolean,
    onClick : () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = stringResource(R.string.day, destination.day),
            style = MaterialTheme.typography.displayLarge
        )
        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            OutlinedButton(
                onClick = onClick,
                modifier = Modifier
                    .padding(
                        top = 8.dp,
                        bottom = 8.dp
                    )
                    .fillMaxWidth()
            ) {
                Text(
                    text = stringResource(destination.name),
                    style = MaterialTheme.typography.headlineLarge,

                )
            }
            if (isExpanded) {
                Box(
                    modifier = Modifier
                        .padding(
                            top = 8.dp,
                        )

                        .clip(shape = Shape.small)
                ) {
                    Image(
                        painter = painterResource(destination.image),
                        contentDescription = stringResource(destination.name),
                    )
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TravelMemoriesTheme(darkTheme = true) {
        TravelApp()
    }
}

