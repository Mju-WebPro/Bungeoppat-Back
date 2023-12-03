package server.api.webpro.order.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.api.webpro.order.dto.OrderCreateRequest;
import server.api.webpro.order.dto.OrderGetRequest;
import server.api.webpro.order.service.OrderService;
import server.api.webpro.order.dto.StatusContentResponse;
import server.api.webpro.order.dto.OrderListResponse;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;

    @PostMapping()
    public ResponseEntity<StatusContentResponse> createOrder(@RequestBody OrderCreateRequest request){
        log.info("createOrder : storeId = {}, userId = {}, reviewId = {}, paymentId = {}, quantity = {}, price = {}",
                request.getStoreId(), request.getUserId(), request.getReviewId(), request.getPaymentId(),
                request.getQuantity(), request.getPrice());
        StatusContentResponse response = orderService.createOrder(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping()
    public ResponseEntity<OrderListResponse> getAllOrders(@RequestBody OrderGetRequest request){
        log.info("getAllOrders : userId = {} ", request.getUserId());
        OrderListResponse response = orderService.getAllOrders(request);
        return ResponseEntity.ok(response);
    }
}
