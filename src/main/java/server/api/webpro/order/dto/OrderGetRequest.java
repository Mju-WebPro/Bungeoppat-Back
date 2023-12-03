package server.api.webpro.order.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class OrderGetRequest {
    @NotNull
    private Long userId;
}
