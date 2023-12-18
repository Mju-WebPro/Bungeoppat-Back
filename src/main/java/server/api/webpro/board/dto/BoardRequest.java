package server.api.webpro.board.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardRequest {

    private Long userId;
    private String title;
    private String content;
}
