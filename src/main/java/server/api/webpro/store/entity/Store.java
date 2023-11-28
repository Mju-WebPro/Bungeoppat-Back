package server.api.webpro.store.entity;

import jakarta.persistence.*;
import lombok.Getter;
import server.api.webpro.storeUserReview.entity.StoreUserReview;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Entity
@Table(name = "Store")
public class Store {
    @Id
    @Column(name = "Store_id")
    private Long id;
    private String name;
    private String location;
    private Long number;
    private Long openingHours;

    @OneToMany(mappedBy = "storeId", cascade = CascadeType.ALL)
    private List<StoreUserReview> storeUserReviews;
}
