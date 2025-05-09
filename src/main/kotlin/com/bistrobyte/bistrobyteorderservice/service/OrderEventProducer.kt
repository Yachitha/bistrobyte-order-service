package com.bistrobyte.bistrobyteorderservice.service

import com.bistrobyte.bistrobyteorderservice.model.OrderEvent
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

@Service
class OrderEventProducer(private val kafkaTemplate: KafkaTemplate<String, String>) {

    private val objectMapper = jacksonObjectMapper()

    fun sendOrderStatus(orderId: String, status: String) {
        val event = OrderEvent(orderId, status, System.currentTimeMillis())
        val message = objectMapper.writeValueAsString(event)
        kafkaTemplate.send("order-status", orderId, message)
        println("Order status sent to Kafka: $message")
    }
}
