package server.api.webpro.storeUserReview.entity;

import jakarta.persistence.*;
import lombok.Getter;
import server.api.webpro.review.entity.Review;
import server.api.webpro.store.entity.Store;
import server.api.webpro.user.entity.User;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "StoreUserView")
@IdClass(StoreUserReviewId.class)
public class StoreUserReview {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Store_id")
    private Store storeId;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User userId;

    @Id
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    private Review reviewId;

    @Column(nullable = false)
    private Integer quantity;
    @Column(nullable = false)
    private Integer price;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private LocalDateTime purchaseDate;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private LocalDateTime pickUpdDate;
    @Column(nullable = false)
    private Integer status;
}