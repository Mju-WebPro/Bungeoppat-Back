package server.api.webpro.reply.dto;


import jakarta.persistence.Column;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReplyRequest {

    private Long boardId;
    private Long userId;
    private String replyContent;
}
