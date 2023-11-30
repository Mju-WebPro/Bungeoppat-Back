package server.api.webpro.board.entity;

import jakarta.persistence.*;
import lombok.*;
import server.api.webpro.board.dto.BoardRequest;
import server.api.webpro.user.entity.User;

import java.time.LocalDate;

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
    // 이미지 주소로 저장(반환할 때 사용할 주소)
    private String picture;
    private String content;


    public static Board of(BoardRequest boardRequest, User writer) {
        return Board.builder()
                .id(boardRequest.getBoardId())
                .user(writer)
                .title(boardRequest.getTitle())
                .date(LocalDate.now())
                .picture(boardRequest.getPicture())
                .content(boardRequest.getContent())
                .build();
    }
}
