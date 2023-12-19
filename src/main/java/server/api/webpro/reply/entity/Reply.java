package server.api.webpro.reply.entity;

import jakarta.persistence.*;
import lombok.*;
import server.api.webpro.reply.dto.ReplyRequest;
import server.api.webpro.board.entity.Board;
import server.api.webpro.user.entity.User;

import java.time.LocalDate;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Reply {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "reply_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String replyContent;
    private LocalDate date;

    public static Reply of(ReplyRequest replyRequest, Board targetBoard, User replyUser) {
        return Reply.builder()
                .board(targetBoard)
                .user(replyUser)
                .replyContent(replyRequest.getReplyContent())
                .date(LocalDate.now())
                .build();
    }
}
