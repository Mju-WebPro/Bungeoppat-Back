package server.api.webpro.user.control;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import server.api.webpro.common.utill.ApiResponse;
import server.api.webpro.user.dto.UserCreateRequest;
import server.api.webpro.user.dto.UserResponse;
import server.api.webpro.user.dto.UserUpdateRequest;
import server.api.webpro.user.service.UserService;
import server.api.webpro.user.state.UserResponseType;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "User 컨트롤러", description = "User API입니다.")
public class UserControl {
    private final UserService userService;
    @PostMapping("/regist")
    public ApiResponse<String> saveUser(@RequestBody UserCreateRequest userCreateRequest) {
        userService.saveUser(userCreateRequest);
    return ApiResponse.of(UserResponseType.REGIST_SUCCESS);}
    @GetMapping("/getUser")
    public ApiResponse<List<UserResponse>> retrieveAll() {
        return ApiResponse.of(UserResponseType.RETRIVE_SUCCESS
                ,userService.getUser());}
    @PutMapping("/user/update")
    public ApiResponse<String> updateUser(@RequestBody UserUpdateRequest request) {
        userService.updateUser(request);
        return ApiResponse.of(UserResponseType.UPDATE_SUCCESS);
    }
    @DeleteMapping("/user/delete")
    public ApiResponse<String> deleteUser(@RequestParam String name) {
        userService.deleteUser(name);
        return ApiResponse.of(UserResponseType.UPDATE_SUCCESS);
    }
}