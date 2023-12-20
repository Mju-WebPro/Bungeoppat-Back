package server.api.webpro.review.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.api.webpro.review.dto.StatusReviewResponse;
import server.api.webpro.userOrder.dto.StatusContentResponse;
import server.api.webpro.review.dto.ReviewWriteRequest;
import server.api.webpro.review.service.ReviewService;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;
    @PostMapping("/review/createReview")
    public ResponseEntity<StatusReviewResponse> createReview(){
        log.info("review/createReview");
        StatusReviewResponse response = reviewService.createReview();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/review/write")
    public ResponseEntity<StatusContentResponse> writeReview(@RequestBody ReviewWriteRequest request){
        log.info("writeReview : id = {}, content = {}, starRating = {}", request.getReviewId(), request.getContent(), request.getStarRating());
        StatusContentResponse response = reviewService.writeReview(request);
        return ResponseEntity.ok(response);
    }
}
