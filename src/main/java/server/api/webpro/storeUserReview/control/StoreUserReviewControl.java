package server.api.webpro.storeUserReview.control;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import server.api.webpro.storeUserReview.service.StoreUserReviewService;

@RestController
@RequiredArgsConstructor
public class StoreUserReviewControl {

    private final StoreUserReviewService storeUserReviewService;
}
