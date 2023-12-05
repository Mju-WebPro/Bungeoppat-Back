package server.api.webpro.payment.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import server.api.webpro.payment.dto.PaymentCreateRequest;
import server.api.webpro.payment.dto.StatusContentResponse;
import server.api.webpro.payment.entity.Payment;
import server.api.webpro.payment.repository.PaymentRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;

    public StatusContentResponse createPayment(PaymentCreateRequest request){
        Payment payment = Payment.builder()
                .paidAmount(request.getPaidAmount())
                .payMethod(request.getPayMethod())
                .status(request.getStatus())
                .impUid(request.getImpUid())
                .build();
        Payment savePayment = paymentRepository.save(payment);
        if(savePayment != null){return new StatusContentResponse(0, "success createPayment");}
        return new StatusContentResponse(2, "Unexpected Error : failed createPayment");
    }
}