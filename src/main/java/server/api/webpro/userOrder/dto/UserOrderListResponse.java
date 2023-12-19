package server.api.webpro.userOrder.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import server.api.webpro.userOrder.entity.UserOrder;

import java.util.List;

@Getter
@AllArgsConstructor
public class UserOrderListResponse {
    private Integer status;
    private List<OrderListDTO> orders;
}

