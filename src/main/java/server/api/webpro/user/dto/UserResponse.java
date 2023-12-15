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
    private String email;

    public static UserResponse of(User user){
        return UserResponse.builder()
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }
}
