package server.api.webpro.board.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardRequest {
    private String userId;
    private String title;
    private String content;
}
