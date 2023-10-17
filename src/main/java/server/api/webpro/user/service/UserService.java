package server.api.webpro.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.api.webpro.user.dto.UserCreateRequest;
import server.api.webpro.user.dto.UserResponse;
import server.api.webpro.user.dto.UserUpdateRequest;
import server.api.webpro.user.repository.UserRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void saveUser(UserCreateRequest request) {
        userRepository.save(request.getName(),request.getAge());
    }

    public List<UserResponse> getUser() {return userRepository.get();}

    public void updateUser(UserUpdateRequest request) {
        if(userRepository.isUserNotExistById(request.getId())){throw new IllegalArgumentException();}
        userRepository.update(request.getName(), request.getId());
    }

    public void deleteUser(String name) {
        if(userRepository.isUserNotExistByName(name)){ throw new IllegalArgumentException();}
        userRepository.delete(name);
    }
}

//        userRepository.save(User.builder()
//                .name(request.getName())
//                .age(request.getAge())
//                .build());