package server.api.webpro.board.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;
import server.api.webpro.board.entity.Board;

import java.time.LocalDate;


@Setter
@Getter
@Builder
public class BoardDto {

    private String title;
    private LocalDate date;
    private MultipartFile multipartFile;
    private String content;

    public static BoardDto of(Board board) {
        return BoardDto.builder()
                .title(board.getTitle())
                .content(board.getContent())
                .build();
    }

}
