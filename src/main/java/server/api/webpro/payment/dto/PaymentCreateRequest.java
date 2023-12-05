package server.api.webpro.payment.dto;

import lombok.Getter;

@Getter
public class PaymentCreateRequest {
    private Long payAmount;
    private String payMethod;
    private String status;
    private String impUid;
}
