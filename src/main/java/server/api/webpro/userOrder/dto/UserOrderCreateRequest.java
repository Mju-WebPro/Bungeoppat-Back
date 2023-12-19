package server.api.webpro.userOrder.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class UserOrderCreateRequest {
    private Long storeId;
    private Long userId;
    private Long reviewId;
    private Long paymentId;
    private Integer quantity;
    private Integer price;
    private Integer pickUpDate;
}
