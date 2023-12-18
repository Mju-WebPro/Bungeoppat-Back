package server.api.webpro.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import server.api.webpro.store.dto.StoreRequest;
import server.api.webpro.store.entity.Store;

import java.util.List;


@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
    @Query("select new server.api.webpro.store.dto.StoreRequest(sc.id, sc.storeName, sc.operateTime,sc.menu,sc.place.latitude,sc.place.longitude) " +
            "from Store sc ")
    List<StoreRequest> findAllStore();


    @Query("select new server.api.webpro.store.dto.StoreRequest(sc.id, sc.storeName, sc.operateTime,sc.menu,sc.place.latitude,sc.place.longitude) " +
            "from Store sc " +
            "where sc.place.latitude between :latitude - 0.0015 and :latitude + 0.0015 " +
            "and sc.place.longitude between :longitude - 0.0015 and :longitude + 0.0015")
    List<StoreRequest> findNearbyStore(double latitude,double longitude);
}
