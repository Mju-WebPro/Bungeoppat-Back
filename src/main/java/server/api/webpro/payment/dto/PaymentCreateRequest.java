package server.api.webpro.payment.dto;

import lombok.Getter;

@Getter
public class PaymentCreateRequest {
    private Long storeId;
    private Long popFishNum;
    private Long suFishNum;
}
