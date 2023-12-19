package server.api.webpro.payment.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import server.api.webpro.fishShapedBun.entity.FishShapedBun;
import server.api.webpro.fishShapedBun.repository.FishShapedBunRepository;
import server.api.webpro.payment.dto.PaymentCreateRequest;
import server.api.webpro.payment.dto.StatusContentResponse;
import server.api.webpro.payment.entity.Payment;
import server.api.webpro.payment.repository.PaymentRepository;
import server.api.webpro.store.entity.Store;
import server.api.webpro.store.repository.StoreRepository;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final StoreRepository storeRepository;
    private final FishShapedBunRepository fishShapedBunRepository;

    public StatusContentResponse createPayment(PaymentCreateRequest request){
        Store store = storeRepository.findById(request.getStoreId()).orElse(null);
        if(store != null){
            List<FishShapedBun> fishShapedBunList = fishShapedBunRepository.findByStoreId(store);
            if(fishShapedBunList.isEmpty() || fishShapedBunList != null){
                int sum = 0;
                for(FishShapedBun fish : fishShapedBunList){
                    if(fish.getName().equals("팥 붕어빵")){
                        sum = (int) (sum + (request.getPopFishNum() * fish.getPrice()));
                    }else{
                        sum = (int) (sum + (request.getSuFishNum() * fish.getPrice()));
                    }
                }

                Payment payment = Payment.builder()
                        .amount(sum)
                        .build();
                Payment savePayment = paymentRepository.save(payment);
                if(savePayment != null){return new StatusContentResponse(0, "success createPayment");}
                return new StatusContentResponse(2, "Unexpected Error : failed createPayment");
            }
        }
        return new StatusContentResponse(1, "request invalid");
    }
}