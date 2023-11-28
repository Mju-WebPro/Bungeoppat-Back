package server.api.webpro.storeUserReview.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.api.webpro.storeUserReview.entity.StoreUserReview;

@Repository
public interface StoreUserReviewRepository extends JpaRepository<StoreUserReview, Long> {

}
