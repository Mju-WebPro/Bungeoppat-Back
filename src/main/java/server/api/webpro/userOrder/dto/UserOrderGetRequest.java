package server.api.webpro.userOrder.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class UserOrderGetRequest {
    @NotNull
    private Long userId;
}
