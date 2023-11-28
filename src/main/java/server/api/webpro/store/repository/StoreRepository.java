package server.api.webpro.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.api.webpro.review.entity.Review;

@Repository
public interface StoreRepository extends JpaRepository<Review, Long> {
}
