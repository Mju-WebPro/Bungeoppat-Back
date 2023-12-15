package server.api.webpro.fishShapedBun.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.api.webpro.fishShapedBun.entity.FishShapedBun;

@Repository
public interface FishShapedBunRepository extends JpaRepository<FishShapedBun, Long> {
}
