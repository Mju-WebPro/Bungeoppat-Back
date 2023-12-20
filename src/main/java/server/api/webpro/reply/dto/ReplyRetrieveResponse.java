package server.api.webpro.reply.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import server.api.webpro.reply.entity.Reply;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class ReplyRetrieveResponse {

    private String writer;
    private String replyContent;
    private LocalDate time;

    public static ReplyRetrieveResponse of(Reply reply) {
        return ReplyRetrieveResponse.builder()
                .writer(reply.getUser().getName())
                .replyContent(reply.getReplyContent())
                .time(reply.getDate())
                .build();
    }

}
