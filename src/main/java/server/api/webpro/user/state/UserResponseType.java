package server.api.webpro.user.state;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import server.api.webpro.common.utill.BaseResponseType;

@Getter
@AllArgsConstructor
public enum UserResponseType implements BaseResponseType {
    REGIST_SUCCESS(20001, "회원가입 성공", HttpStatus.OK),
    LOGIN_SUCCESS(20002, "로그인 성공", HttpStatus.OK),
    RETRIVE_SUCCESS(20003, "고객 정보 불러오기 성공", HttpStatus.OK),
    UPDATE_SUCCESS(20004, "고객 정보 업데이트 성공", HttpStatus.OK),
    DELETE_SUCCESS(20005, "고객 정보 삭제 성공", HttpStatus.OK),
    MYPAGE_LOAD_SUCCESS(20006, "마이페이지 정보 조회 완료", HttpStatus.OK),


    SIGN_UP_ING(40003,"회원가입 진행중", HttpStatus.BAD_REQUEST),

    CONNECT_ERROR(40001, "연결 에러", HttpStatus.BAD_REQUEST),
    NOT_FOUND_EMAIL(40002,"이메일을 찾을 수 없습니다.", HttpStatus.BAD_REQUEST),
    EXISTED_NICKNAME(40003, "이미 존재하는 닉네임입니다", HttpStatus.BAD_REQUEST),
    NOT_LOGGED_IN_USER(40004, "사용자 정보를 알 수 없습니다.", HttpStatus.BAD_REQUEST);
    private final Integer code;
    private final String message;
    private final HttpStatus httpStatus;
}
