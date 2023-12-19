package server.api.webpro.userOrder.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import server.api.webpro.userOrder.dto.UserOrderCreateRequest;
import server.api.webpro.userOrder.dto.UserOrderGetRequest;
import server.api.webpro.userOrder.entity.UserOrder;
import server.api.webpro.userOrder.repository.UserOrderRepository;
import server.api.webpro.userOrder.dto.StatusContentResponse;
import server.api.webpro.userOrder.dto.UserOrderListResponse;
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
public class UserOrderService {
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;
    private final UserOrderRepository orderRepository;
    private final PaymentRepository paymentRepository;
    private final ReviewRepository reviewRepository;

    public StatusContentResponse createOrder(UserOrderCreateRequest request){
        User user = userRepository.findById(request.getUserId()).orElse(null);
        Store store = storeRepository.findById(request.getStoreId()).orElse(null);
        Review review = reviewRepository.findById(request.getReviewId()).orElse(null);
        Payment payment = paymentRepository.findById(request.getPaymentId()).orElse(null);

        if(user != null && store != null && review != null && payment != null){
            UserOrder order = UserOrder.builder()
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
            UserOrder saveOrder = orderRepository.save(order);
            if(saveOrder != null) {return new StatusContentResponse(0, "success createOrder");}
            return new StatusContentResponse(2, "Unexpected Error : failed createdOrder");
        }
        return new StatusContentResponse(1, "request's values invalid");
    }

    public UserOrderListResponse getAllOrders(Long userId){
        User user = userRepository.findById(userId).orElse(null);
        if(user != null){
            List<UserOrder> orders = orderRepository.findAllByUser(user);
            if(orders == null || orders.isEmpty()){
                return new UserOrderListResponse(0, null);
            }
            return new UserOrderListResponse(0, orders);
        }
        return new UserOrderListResponse(1, null);
    }

    public Integer calculatorPickUp(Integer quantity){
        if(quantity <= 10){return 10;}
        else if(quantity <= 20){return 20;}
        else {return 25;}
    }
}
