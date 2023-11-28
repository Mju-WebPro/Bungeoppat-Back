package server.api.webpro.storeUserReview.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import server.api.webpro.storeUserReview.repository.StoreUserReviewRepository;

@Service
@RequiredArgsConstructor
public class StoreUserReviewService {
    private final StoreUserReviewRepository storeUserReviewRepository;
}
