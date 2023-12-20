package server.api.webpro.board.entity;

import jakarta.persistence.*;
import lombok.*;
import server.api.webpro.board.dto.BoardRequest;
import server.api.webpro.reply.entity.Reply;
import server.api.webpro.user.entity.User;

import java.time.LocalDate;
import java.util.List;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Board {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "board_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String title;
    private LocalDate date;
    private String content;

    @OneToMany
    private List<Reply> replies;

    public static Board of(BoardRequest boardRequest, User boardUser) {
        return Board.builder()
                .user(boardUser)
                .title(boardRequest.getTitle())
                .date(LocalDate.now())
                .content(boardRequest.getContent())
                .build();
    }

    public static Board notImage(BoardRequest boardRequest, User boardUser) {
        return Board.builder()
                .user(boardUser)
                .title(boardRequest.getTitle())
                .date(LocalDate.now())
                .content(boardRequest.getContent())
                .build();
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
