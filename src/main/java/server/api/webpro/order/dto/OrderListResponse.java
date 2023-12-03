package server.api.webpro.order.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import server.api.webpro.order.entity.Order;

import java.util.List;

@Getter
@AllArgsConstructor
public class OrderListResponse {
    private Integer status;
    private List<Order> orders;
}

