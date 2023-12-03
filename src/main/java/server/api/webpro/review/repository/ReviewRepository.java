package server.api.webpro.review.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import server.api.webpro.review.entity.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE Review r SET r.content = :content, r.starRating = :starRating WHERE r.id = :id")
    void writeReview(@Param("id") Long id, @Param("content") String content, @Param("starRating") Float starRating);
}
