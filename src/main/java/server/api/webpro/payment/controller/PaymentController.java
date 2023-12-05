package server.api.webpro.payment.controller;

import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import server.api.webpro.payment.dto.PaymentCreateRequest;
import server.api.webpro.payment.dto.StatusContentResponse;
import server.api.webpro.payment.service.PaymentService;

import java.io.IOException;

@RestController
@Slf4j
@RequiredArgsConstructor
public class PaymentController{
    private IamportClient iamportClient;
    private final PaymentService paymentService;

    @Value("${imp.api.key}")
    private String apiKey;

    @Value("${imp.api.secretkey}")
    private String secretKey;

    @PostConstruct
    public void init(){
        this.iamportClient = new IamportClient(apiKey, secretKey);
    }

    // 주문저장
    @PostMapping("/payment")
    public ResponseEntity<StatusContentResponse> createPayment(@RequestBody PaymentCreateRequest request){
        log.info("createPayment : paidAmount = {}, payMethod = {}, status = {}, imp_uid = {}", request.getPayAmount(), request.getPayMethod(), request.getStatus(), request.getImpUid());
        StatusContentResponse response = paymentService.createPayment(request);
        return ResponseEntity.ok(response);
    }

    // 주문검증
    @PostMapping("/payment/validation/{imp_uid}")
    public IamportResponse<Payment> validateIamport(@PathVariable String imp_uid) throws IamportResponseException, IOException {
        log.info("validateIamport : imp_uid = {}", imp_uid);
        IamportResponse<Payment> payment = iamportClient.paymentByImpUid(imp_uid);
        log.info("결제 요청 응답, 주문 번호 = {}", payment.getResponse().getMerchantUid());
        return payment;
    }
}
