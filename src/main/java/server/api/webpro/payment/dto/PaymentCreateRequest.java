package server.api.webpro.payment.dto;

import lombok.Getter;

@Getter
public class PaymentCreateRequest {
    private Long paidAmount;
    private String payMethod;
    private String status;
    private String impUid;
}
