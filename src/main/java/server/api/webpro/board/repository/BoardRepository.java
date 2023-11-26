package server.api.webpro.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.api.webpro.board.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Integer> {
}
