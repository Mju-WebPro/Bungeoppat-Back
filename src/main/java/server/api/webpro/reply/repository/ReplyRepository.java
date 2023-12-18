package server.api.webpro.reply.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.api.webpro.reply.entity.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
}
