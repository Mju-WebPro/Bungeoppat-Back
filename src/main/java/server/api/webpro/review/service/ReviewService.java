package server.api.webpro.review.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import server.api.webpro.review.repository.ReviewRepository;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
}
