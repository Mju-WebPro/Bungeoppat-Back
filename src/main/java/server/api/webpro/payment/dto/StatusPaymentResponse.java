package server.api.webpro.payment.dto;

import lombok.Data;
import server.api.webpro.payment.entity.Payment;

@Data
public class StatusPaymentResponse {
    private Integer status;
    private Payment payment;

    public StatusPaymentResponse(int status, Payment payment) {
        this.status = status;
        this.payment = payment;
    }
}
