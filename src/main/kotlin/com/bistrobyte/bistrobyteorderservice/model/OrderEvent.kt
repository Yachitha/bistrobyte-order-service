package com.bistrobyte.bistrobyteorderservice.model

data class OrderEvent (
    val orderId: String,
    val status: String,
    val timestamp: Long
)