package server.api.webpro.review.control;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import server.api.webpro.review.service.ReviewService;

@RestController
@RequiredArgsConstructor
public class ReviewControl {
    private final ReviewService reviewService;
}
