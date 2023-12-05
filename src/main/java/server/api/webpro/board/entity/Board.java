package server.api.webpro.board.entity;

import jakarta.persistence.*;
import lombok.*;
import server.api.webpro.board.dto.BoardDto;
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
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String title;
    private LocalDate date;
    private String content;

    private String imageUrl;

    public static Board of(BoardDto boardDto, String imageUrl) {
        return Board.builder()
                .title(boardDto.getTitle())
                .date(LocalDate.now())
                .content(boardDto.getContent())
                .imageUrl(imageUrl)
                .build();
    }
}
