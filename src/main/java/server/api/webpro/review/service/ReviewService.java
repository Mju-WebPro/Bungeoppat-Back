package server.api.webpro.review.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import server.api.webpro.review.dto.StatusReviewResponse;
import server.api.webpro.userOrder.dto.StatusContentResponse;
import server.api.webpro.review.dto.ReviewWriteRequest;
import server.api.webpro.review.entity.Review;
import server.api.webpro.review.repository.ReviewRepository;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public StatusReviewResponse createReview(){
        Review review = Review.builder()
                .content(null)
                .starRating(null)
                .build();
        Review saveReview = reviewRepository.save(review);
        if(saveReview != null){return new StatusReviewResponse(0, saveReview);}
        return new StatusReviewResponse(2, null);
    }

    public StatusContentResponse writeReview(ReviewWriteRequest request){
        reviewRepository.writeReview(request.getReviewId(), request.getContent(), request.getStarRating());
        return new StatusContentResponse(0, "success WriteReview");
    }
}
