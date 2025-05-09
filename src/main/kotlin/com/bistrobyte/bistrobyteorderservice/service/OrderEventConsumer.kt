package com.bistrobyte.bistrobyteorderservice.service

import com.bistrobyte.bistrobyteorderservice.model.OrderEvent
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class OrderEventConsumer {

    private val objectMapper = jacksonObjectMapper()

    @KafkaListener(topics = ["order-status"], groupId = "order-service")
    fun listen(message: String) {
        try {
            val orderEvent: OrderEvent = objectMapper.readValue(message)
            println("Received order update: Order ID - ${orderEvent.orderId}, Status - ${orderEvent.status}")
        } catch (e: Exception) {
            println("Error processing message: $message")
        }
    }
}