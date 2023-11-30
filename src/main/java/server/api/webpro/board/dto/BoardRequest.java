package server.api.webpro.board.dto;

import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardRequest {

    private Long boardId;
    private Long userId;

    private String title;
    private LocalDate date;
    private String picture;
    private String content;
}
