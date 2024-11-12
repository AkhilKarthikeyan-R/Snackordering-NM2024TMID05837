package com.example.snackordering

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.snackordering.ui.theme.SnackOrderingTheme

class CartActivity : ComponentActivity() {
    private lateinit var orderDatabaseHelper: OrderDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        orderDatabaseHelper = OrderDatabaseHelper(this)

        setContent {
            SnackOrderingTheme {
                CartScreen(this, orderDatabaseHelper)
            }
        }
    }
}

@Composable
fun CartScreen(context: Context, orderDatabaseHelper: OrderDatabaseHelper) {
    var orders by remember { mutableStateOf(orderDatabaseHelper.getAllOrders()) }
    var totalAmount by remember { mutableStateOf(orders.sumOf { it.quantity!!.toInt() * 10 }) } // Assuming each snack costs 10 units

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Cart", modifier = Modifier.padding(16.dp))

        LazyColumn {
            items(orders) { order ->
                Text(text = "${order.quantity} x Snack at ${order.address}")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Total: $totalAmount")

        Button(onClick = {
            val intent = Intent(context, OrderStatusActivity::class.java)
            context.startActivity(intent)
        }) {
            Text("Place Order")
        }
    }
}
