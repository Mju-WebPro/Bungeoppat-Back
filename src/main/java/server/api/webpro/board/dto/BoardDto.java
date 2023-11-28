package server.api.webpro.board.dto;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardDto {

    private int boardId;

    private String title;
    private LocalDate date;
    private String picture;
    private String content;
}
