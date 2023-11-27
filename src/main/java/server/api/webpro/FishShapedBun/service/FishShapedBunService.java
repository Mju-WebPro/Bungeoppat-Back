package server.api.webpro.FishShapedBun.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import server.api.webpro.FishShapedBun.repository.FishShapedBunRepository;
import server.api.webpro.storeUserReview.repository.StoreUserReviewRepository;
@Service
@RequiredArgsConstructor
public class FishShapedBunService {

    private final FishShapedBunRepository fishShapedBunRepository;
}
