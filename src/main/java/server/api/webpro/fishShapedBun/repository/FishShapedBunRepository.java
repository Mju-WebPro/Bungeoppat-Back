package server.api.webpro.fishShapedBun.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import server.api.webpro.fishShapedBun.entity.FishShapedBun;
import server.api.webpro.store.entity.Store;

import java.util.List;

@Repository
public interface FishShapedBunRepository extends JpaRepository<FishShapedBun, Long> {

    @Query("SELECT f FROM FishShapedBun f WHERE f.storeId = :storeId")
    List<FishShapedBun> findByStoreId(@Param("storeId") Store storeId);
}
