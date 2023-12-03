package server.api.webpro.order.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StatusContentResponse {
    private Integer status;
    private String content;
}
