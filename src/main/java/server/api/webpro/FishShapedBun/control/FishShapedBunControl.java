package server.api.webpro.FishShapedBun.control;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import server.api.webpro.FishShapedBun.service.FishShapedBunService;
import server.api.webpro.storeUserReview.service.StoreUserReviewService;
@RestController
@RequiredArgsConstructor
public class FishShapedBunControl {

    private final FishShapedBunService fishShapedBunService;
}
