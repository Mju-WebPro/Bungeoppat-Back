package server.api.webpro.Order.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import server.api.webpro.Order.dto.OrderCreateRequest;
import server.api.webpro.Order.dto.OrderGetRequest;
import server.api.webpro.Order.entity.Order;
import server.api.webpro.Order.repository.OrderRepository;
import server.api.webpro.Order.dto.StatusContentResponse;
import server.api.webpro.Order.dto.OrderListResponse;
import server.api.webpro.payment.entity.Payment;
import server.api.webpro.payment.repository.PaymentRepository;
import server.api.webpro.review.entity.Review;
import server.api.webpro.review.repository.ReviewRepository;
import server.api.webpro.store.entity.Store;
import server.api.webpro.store.repository.StoreRepository;
import server.api.webpro.user.entity.User;
import server.api.webpro.user.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;
    private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepository;
    private final ReviewRepository reviewRepository;

    public StatusContentResponse createOrder(OrderCreateRequest request){
        User user = userRepository.findById(request.getUserId()).orElse(null);
        Store store = storeRepository.findById(request.getStoreId()).orElse(null);
        Review review = reviewRepository.findById(request.getReviewId()).orElse(null);
        Payment payment = paymentRepository.findById(request.getPaymentId()).orElse(null);

        if(user != null && store != null && review != null && payment != null){
            Order order = Order.builder()
                    .userId(user)
                    .storeId(store)
                    .reviewId(review)
                    .paymentId(payment)
                    .quantity(request.getQuantity())
                    .price(request.getPrice())
                    .purchaseDate(LocalDateTime.now())
                    .pickUpMinute(calculatorPickUp(request.getQuantity()))
                    .status(0)
                    .build();
            Order saveOrder = orderRepository.save(order);
            if(saveOrder != null) {return new StatusContentResponse(0, "success createOrder");}
            return new StatusContentResponse(2, "Unexpected Error : failed createdOrder");
        }
        return new StatusContentResponse(1, "request's values invalid");
    }

    public OrderListResponse getAllOrders(OrderGetRequest request){
        User user = userRepository.findById(request.getUserId()).orElse(null);
        if(user != null){
            List<Order> orders = orderRepository.findAllByUser(user);
            if(orders == null || orders.isEmpty()){
                return new OrderListResponse(0, null);
            }
            return new OrderListResponse(0, orders);
        }
        return new OrderListResponse(1, null);
    }

    public Integer calculatorPickUp(Integer quantity){
        if(quantity <= 10){return 10;}
        else if(quantity <= 20){return 20;}
        else {return 25;}
    }
}
