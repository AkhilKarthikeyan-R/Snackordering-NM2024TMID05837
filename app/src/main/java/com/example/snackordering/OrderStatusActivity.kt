package com.example.snackordering

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.snackordering.ui.theme.SnackOrderingTheme

class OrderStatusActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SnackOrderingTheme {
                OrderStatusScreen()
            }
        }
    }
}

@Composable
fun OrderStatusScreen() {
    var isDelivered by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        // Mock delay to simulate delivery
        kotlinx.coroutines.delay(5000)
        isDelivered = true
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (isDelivered) {
            Text(text = "Your order has been delivered!")
        } else {
            Text(text = "Your order is on the way...")
            Spacer(modifier = Modifier.height(16.dp))
            DeliveryAnimation()
        }
    }
}

@Composable
fun DeliveryAnimation() {
    val infiniteTransition = rememberInfiniteTransition()
    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.2f,
        animationSpec = infiniteRepeatable(
            animation = tween(800),
            repeatMode = RepeatMode.Reverse
        )
    )

    Image(
        painter = painterResource(id = R.drawable.delivery_bike),
        contentDescription = "Delivery Animation",
        modifier = Modifier
            .size(100.dp)
            .scale(scale)
    )
}
