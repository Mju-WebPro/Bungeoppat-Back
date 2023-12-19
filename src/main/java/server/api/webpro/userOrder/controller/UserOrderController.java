package server.api.webpro.userOrder.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.api.webpro.userOrder.dto.UserOrderCreateRequest;
import server.api.webpro.userOrder.dto.UserOrderGetRequest;
import server.api.webpro.userOrder.service.UserOrderService;
import server.api.webpro.userOrder.dto.StatusContentResponse;
import server.api.webpro.userOrder.dto.UserOrderListResponse;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserOrderController {

    private final UserOrderService orderService;

    @PostMapping("/userOrder/createOrder")
    public ResponseEntity<StatusContentResponse> createOrder(@RequestBody UserOrderCreateRequest request){
        log.info("createOrder : storeId = {}, userId = {}, reviewId = {}, paymentId = {}, quantity = {}, price = {}",
                request.getStoreId(), request.getUserId(), request.getReviewId(), request.getPaymentId(),
                request.getQuantity(), request.getPrice());
        StatusContentResponse response = orderService.createOrder(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/userOrder/getAllOrders")
    public ResponseEntity<UserOrderListResponse> getAllOrders(@RequestParam("userId") Long userId) {
        log.info("getAllOrders : userId = {} ", userId);
        UserOrderListResponse response = orderService.getAllOrders(userId);
        return ResponseEntity.ok(response);
    }
}
