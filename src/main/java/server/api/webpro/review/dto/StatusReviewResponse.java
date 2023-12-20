package server.api.webpro.review.dto;

import lombok.Data;
import server.api.webpro.review.entity.Review;

@Data
public class StatusReviewResponse {
    private Integer status;
    private Review review;

    public StatusReviewResponse(int status, Review review) {
        this.status = status;
        this.review = review;
    }
}
