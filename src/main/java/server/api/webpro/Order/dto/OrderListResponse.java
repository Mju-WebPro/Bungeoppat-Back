package server.api.webpro.Order.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import server.api.webpro.Order.entity.Order;

import java.util.List;

@Getter
@AllArgsConstructor
public class OrderListResponse {
    private Integer status;
    private List<Order> orders;
}

