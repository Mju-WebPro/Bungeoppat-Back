package server.api.webpro.userOrder.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
@NotNull
public class UserOrderCreateRequest {
    @NotNull
    private Long storeId;
    @NotNull
    private Long userId;
    @NotNull
    private Long reviewId;
    @NotNull
    private Long paymentId;
    @NotNull
    private Integer quantity;
    @NotNull
    private Integer price;
}
