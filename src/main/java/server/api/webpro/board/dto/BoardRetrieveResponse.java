package server.api.webpro.board.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import server.api.webpro.board.entity.Board;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class BoardRetrieveResponse {

    private Long boardId;
    public Long userId;

    private String title;
    private LocalDate date;
    private String picture;
    private String content;

    public static BoardRetrieveResponse of(Board board) {
        return BoardRetrieveResponse.builder()
                .boardId(board.getId())
                .userId(board.getUser().getId())
                .title(board.getTitle())
                .date(board.getDate())
                .picture(board.getPicture())
                .content(board.getContent())
                .build();
    }
}
