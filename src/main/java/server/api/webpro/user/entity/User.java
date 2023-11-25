package server.api.webpro.user.entity;

import jakarta.persistence.*;
import lombok.*;
import server.api.webpro.user.state.Role;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "User_ID")
    private Long id;
    private String email;
    private String name;
    private Integer age;
    @Enumerated(EnumType.STRING)
    private Role role;
}
