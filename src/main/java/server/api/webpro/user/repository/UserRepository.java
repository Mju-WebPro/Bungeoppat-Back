package server.api.webpro.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import server.api.webpro.user.entity.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
//    @Query("select m from User m where m.nickname = :nickname and m.isDeleted = false")
//    Optional<User> findNotDeletedByNickname(@Param("nickname") String nickname);
    @Query("select m from User m where m.email = :email")
    Optional<User> findByEmailonToken(@Param("email") String email);

    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);
}
