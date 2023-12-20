package server.api.webpro.store.entity;

import jakarta.persistence.*;
import lombok.*;
import net.minidev.json.annotate.JsonIgnore;
import server.api.webpro.userOrder.entity.UserOrder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_Id")
    private Long id;

    private String storeName;
    private String operateTime;
    private String menu;

    @JsonIgnore
    @OneToMany(mappedBy = "storeId")
    private List<UserOrder> userList = new ArrayList<>();

    @OneToOne(mappedBy = "store", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true) //FK가 없는 쪽에 mappedBy 사용을 추천
    private Place place = new Place();
}
