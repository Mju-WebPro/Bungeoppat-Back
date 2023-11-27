package server.api.webpro.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.api.webpro.review.entity.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
}
