package server.api.webpro.userOrder.entity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import server.api.webpro.payment.entity.Payment;
import server.api.webpro.review.entity.Review;
import server.api.webpro.store.entity.Store;
import server.api.webpro.user.entity.User;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "userOrder")
@IdClass(UserOrderId.class)
public class UserOrder {
    @Id
    @ManyToOne
    @JoinColumn(name = "Store_id")
    private Store storeId;

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;

    @Id
    @OneToOne
    @JoinColumn(name = "review_id")
    private Review reviewId;

    @Id
    @OneToOne
    @JoinColumn(name = "payment_id")
    private Payment paymentId;

    @Column(nullable = false)
    private Integer quantity;
    @Column(nullable = false)
    private Integer price;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private LocalDateTime purchaseDate;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Integer pickUpMinute;
    @Column(nullable = false)
    private Integer status;

    @Builder
    public UserOrder(Store storeId, User userId, Review reviewId, Payment paymentId, Integer quantity, Integer price,
                     LocalDateTime purchaseDate, Integer pickUpMinute, Integer status){
        this.storeId = storeId;
        this.userId = userId;
        this.reviewId = reviewId;
        this.paymentId = paymentId;
        this.quantity = quantity;
        this.price = price;
        this.purchaseDate = purchaseDate;
        this.pickUpMinute = pickUpMinute;
        this.status = status;
    }
}