package server.api.webpro.FishShapedBun.entity;

import jakarta.persistence.*;
import lombok.Getter;

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
