package server.api.webpro.Order.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class OrderGetRequest {
    @NotNull
    private Long userId;
}
