package server.api.webpro.userOrder.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderListDTO {
    private Long storeId;
    private Long userId;
    private Long reviewId;
    private Long paymentId;
    private Integer quantity;
    private Integer price;
    private LocalDateTime purchaseDate;
    private Integer pickUpMinute;
    private Integer status;
    private String storeName;
    private String content;
    private Float starRating;

    public OrderListDTO(Long storeId, Long userId, Long reviewId, Long paymentId, Integer quantity, Integer price,
                        LocalDateTime purchaseDate, Integer pickUpMinute, Integer status, String storeName,
                        String content, Float starRating) {
        this.storeId = storeId;
        this.userId = userId;
        this.reviewId = reviewId;
        this.paymentId = paymentId;
        this.quantity = quantity;
        this.price = price;
        this.purchaseDate = purchaseDate;
        this.pickUpMinute = pickUpMinute;
        this.status = status;
        this.storeName = storeName;
        this.content = content;
        this.starRating = starRating;
    }
}
