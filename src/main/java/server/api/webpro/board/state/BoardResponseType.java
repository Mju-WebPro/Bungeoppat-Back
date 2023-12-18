package server.api.webpro.board.state;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import server.api.webpro.common.utill.BaseResponseType;

@Getter
@AllArgsConstructor
public enum BoardResponseType implements BaseResponseType {

    CREATE_SUCCESS(200, "게시판 생성 성공", HttpStatus.OK),
    RETRIEVEALL_SUCCESS(200, "게시판 전체 조회 성공", HttpStatus.OK),
    UPDATE_SUCCESS(200, "게시판 수정 성공", HttpStatus.OK),
    RETRIEVEBYID_SUCCESS(200, "게시판 조회 성공 By ID", HttpStatus.OK),
    DELETE_SUCCESS(200, "게시판 삭제 성공", HttpStatus.OK),
    REPLY_SUCCESS(200, "게시판 댓글 작성 성공", HttpStatus.OK);

    private final Integer code;
    private final String message;
    private final HttpStatus httpStatus;

}
