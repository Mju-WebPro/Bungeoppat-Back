package server.api.webpro.board.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;
import server.api.webpro.board.dto.BoardDto;
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
    private String content;

    private String fileName;
    private String imageUrl;

    public static Board of(BoardRequest boardRequest, MultipartFile multipartFile, String imageUrl, User boardUser) {
        return Board.builder()
                .user(boardUser)
                .title(boardRequest.getTitle())
                .date(LocalDate.now())
                .content(boardRequest.getContent())
                .fileName(multipartFile.getOriginalFilename())
                .imageUrl(imageUrl)
                .build();
    }

    public static Board notImage(BoardRequest boardRequest, User boardUser) {
        return Board.builder()
                .user(boardUser)
                .title(boardRequest.getTitle())
                .date(LocalDate.now())
                .content(boardRequest.getContent())
                .fileName("NONE")
                .imageUrl("NONE")
                .build();
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void updateImage(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
