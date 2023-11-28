package server.api.webpro.store.control;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import server.api.webpro.review.service.ReviewService;

@RestController
@RequiredArgsConstructor
public class StoreControl {
    private final StoreControl storeControl;
}
