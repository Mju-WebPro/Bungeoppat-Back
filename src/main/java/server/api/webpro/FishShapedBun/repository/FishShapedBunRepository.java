package server.api.webpro.FishShapedBun.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.api.webpro.FishShapedBun.entity.FishShapedBun;
import server.api.webpro.storeUserReview.entity.StoreUserReview;
@Repository
public interface FishShapedBunRepository extends JpaRepository<FishShapedBun, Long> {
}
