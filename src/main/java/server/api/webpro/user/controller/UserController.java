package server.api.webpro.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import server.api.webpro.common.utill.ApiResponse;
import server.api.webpro.user.dto.MyIdResponse;
import server.api.webpro.user.dto.UserCreateRequest;
import server.api.webpro.user.dto.UserResponse;
import server.api.webpro.user.service.UserAuthService;
import server.api.webpro.user.service.UserService;
import server.api.webpro.user.state.UserResponseType;

import java.text.ParseException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "User 컨트롤러", description = "User API입니다.")
@Slf4j
public class UserController {
    private final UserService userService;
    private final UserAuthService userAuthService;

    @PostMapping("/regist")
    public ApiResponse<String> saveUser(@RequestBody UserCreateRequest userCreateRequest) {
        userService.saveUser(userCreateRequest);
        return ApiResponse.of(UserResponseType.REGIST_SUCCESS);
    }


    @GetMapping("/v1/get-user")
    public ApiResponse<List<UserResponse>> retrieveAll() {
        return ApiResponse.of(UserResponseType.RETRIVE_SUCCESS
                , userService.getAllUser());
    }

    @Operation(summary = "구글 연동 로그인 및 계정 등록", description = " https://accounts.google.com/o/oauth2/v2/auth?scope=profile%20email&response_type=code&redirect_uri=http://localhost:8080/login/oauth2/code/google&client_id=535321350238-hah6c37spl3eua2bujvvoug3ql237nns.apps.googleusercontent.com")
    @PostMapping(value = {"/login/oauth2/code/google"})
    public ApiResponse<Object> googleLogin(@RequestParam String token) {
        log.info(token);
        return ApiResponse.of(UserResponseType.LOGIN_SUCCESS,
                userAuthService.login(token));
    }

    @GetMapping(value = {"/login/oauth2/code/google"})
    public ApiResponse<Object> callBack(@RequestParam String code) {
        log.info(code);
        return ApiResponse.of(UserResponseType.LOGIN_SUCCESS,
                userAuthService.getAccessToken(code));
    }

    @GetMapping(value = {"/myid"})
    public ApiResponse<MyIdResponse> myId() throws ParseException {
        return ApiResponse.of(UserResponseType.MYPAGE_LOAD_SUCCESS, userService.myId());

}
