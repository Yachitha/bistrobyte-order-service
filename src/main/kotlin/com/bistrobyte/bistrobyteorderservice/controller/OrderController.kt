package com.bistrobyte.bistrobyteorderservice.controller

import com.bistrobyte.bistrobyteorderservice.service.OrderEventProducer
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/orders")
class OrderController(private val orderEventProducer: OrderEventProducer) {

    @PostMapping("/{id}/status")
    fun updateOrderStatus(@PathVariable id: String, @RequestParam status: String): String {
        orderEventProducer.sendOrderStatus(id, status)
        return "Order $id status updated to $status"
    }
}