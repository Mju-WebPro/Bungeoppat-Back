package server.api.webpro.reply.state;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import server.api.webpro.common.utill.BaseResponseType;

@Getter
@AllArgsConstructor
public enum ReplyResponse implements BaseResponseType {

    CREATE_SUCCESS(200, "댓글 작성 성공", HttpStatus.OK),
    RETRIEVE_SUCCESS(200, "댓글 조회 성공", HttpStatus.OK);

    private final Integer code;
    private final String message;
    private final HttpStatus httpStatus;
}
