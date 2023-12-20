package server.api.webpro.userOrder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import server.api.webpro.user.entity.User;
import server.api.webpro.userOrder.entity.UserOrder;

import java.util.List;

@Repository
public interface UserOrderRepository extends JpaRepository<UserOrder, Long> {

    @Query("SELECT o FROM UserOrder o WHERE o.userId = :userId ORDER BY o.purchaseDate DESC")
    List<UserOrder> findAllByUser(@Param("userId") User userId);
}
