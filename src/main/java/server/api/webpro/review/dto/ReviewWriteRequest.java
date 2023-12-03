package server.api.webpro.review.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ReviewWriteRequest {
    private Long id;
    private String content;
    private Float starRating;
}
