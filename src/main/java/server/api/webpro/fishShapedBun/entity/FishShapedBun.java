package server.api.webpro.fishShapedBun.entity;

import jakarta.persistence.*;
import lombok.Getter;
import server.api.webpro.store.entity.Store;

@Getter
@Entity
@Table(name = "FishShapedBun")
public class FishShapedBun {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Integer price;
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store_id;

}
