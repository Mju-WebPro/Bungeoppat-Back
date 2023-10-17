package server.api.webpro.user.dto;

import lombok.*;
import server.api.webpro.user.entity.User;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private Long id;
    private String name;
    private int age;

    public static UserResponse of(User user){
        return UserResponse.builder()
                .name(user.getName())
                .age(user.getAge())
                .build();
    }
}
