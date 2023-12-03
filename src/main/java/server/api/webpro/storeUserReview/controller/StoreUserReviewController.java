package server.api.webpro.storeUserReview.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import server.api.webpro.storeUserReview.service.StoreUserReviewService;

@RestController
@RequiredArgsConstructor
public class StoreUserReviewController {

    private final StoreUserReviewService storeUserReviewService;
}
