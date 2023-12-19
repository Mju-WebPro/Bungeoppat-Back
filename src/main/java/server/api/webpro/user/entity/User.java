package server.api.webpro.user.entity;

import jakarta.persistence.*;
import lombok.*;
import net.minidev.json.annotate.JsonIgnore;
import server.api.webpro.reply.entity.Reply;
import server.api.webpro.userOrder.entity.UserOrder;


import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    private String email;
    private String name;
    private Integer age;
    private double region;

    @JsonIgnore
    @OneToMany(mappedBy = "userId")
    private List<UserOrder> userList = new ArrayList<>();

    @OneToMany
    private List<Reply> replies;
}
