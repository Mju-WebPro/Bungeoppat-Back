package server.api.webpro.user.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.api.webpro.common.security.TokenProvider;
import server.api.webpro.user.dto.LoginResponse;
import server.api.webpro.user.dto.TokenInfoResponse;
import server.api.webpro.user.entity.User;
import server.api.webpro.user.repository.UserRepository;
import server.api.webpro.user.state.Role;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static server.api.webpro.user.state.UserResponseType.*;
@Service
@Transactional
@RequiredArgsConstructor
public class UserAuthService {
    private final TokenProvider tokenProvider;
    private final UserRepository userRepository;
    private final UserGoogleService loginService;

    private String GOOGLE_TOKEN_URL = "https://oauth2.googleapis.com/token";

public String getAccessToken(String code) {
    String access_Token = "";
    try{
        URL url = new URL(GOOGLE_TOKEN_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
        bw.write(loginService.getOauthRedirectURL(code));
        bw.flush();

        int responseCode = conn.getResponseCode();
        System.out.println("responseCode : " + responseCode);
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line = "";
        String result = "";

        while ((line = br.readLine()) != null) {
            result += line;
        }
        System.out.println("response body : " + result);

        //Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(result);

        access_Token = element.getAsJsonObject().get("access_token").getAsString();
        System.out.println("access_token : " + access_Token);
        String refresh_Token = element.getAsJsonObject().get("id_token").getAsString();

        System.out.println("access_token : " + access_Token);
        System.out.println("refresh_token : " + refresh_Token);

        br.close();
        bw.close();

    }catch (IOException e) {
        e.printStackTrace();
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
    return access_Token;
    }

    public LoginResponse login(String token) {
        // access token 으로 사용자 정보 가져오기
        JsonObject memberInfo = loginService.connect("https://www.googleapis.com/oauth2/v1/userinfo", token);
        System.out.println(memberInfo.toString());
        User user = saveMember(loginService.getEmail(memberInfo));
        boolean isSignedUp = user.getEmail() != null;

        //2. 스프링 시큐리티 처리
        List<GrantedAuthority> authorities = initAuthorities();
        OAuth2User userDetails = createOAuth2UserByJson(authorities, loginService.getEmail(memberInfo));
        OAuth2AuthenticationToken auth = configureAuthentication(userDetails, authorities);

        //3. JWT 토큰 생성
        TokenInfoResponse tokenInfoResponse = tokenProvider.createToken(auth, isSignedUp, user.getId());
        return LoginResponse.from(tokenInfoResponse, isSignedUp ? LOGIN_SUCCESS.getMessage() : SIGN_UP_ING.getMessage(), user.getId());

    }

    public User saveMember(String email) {
        if (!userRepository.existsByEmail(email)) { userRepository.save(User.builder().email(email).name(email).build());}
        return userRepository.findByEmail(email).orElseThrow(()->new IllegalArgumentException("해당 유저가 없습니다."));
    }
    public List<GrantedAuthority> initAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(String.valueOf(Role.USER)));
        return authorities;
    }
    private OAuth2User createOAuth2UserByJson(List<GrantedAuthority> authorities, String email) {
        Map<String, Object> memberMap = new HashMap<>();
        memberMap.put("email", email);
        authorities.add(new SimpleGrantedAuthority(String.valueOf(Role.USER)));
        return new DefaultOAuth2User(authorities, memberMap, "email");
    }

    public OAuth2AuthenticationToken configureAuthentication(OAuth2User userDetails, List<GrantedAuthority> authorities) {
        OAuth2AuthenticationToken auth = new OAuth2AuthenticationToken(userDetails, authorities, "email");
        auth.setDetails(userDetails);
        SecurityContextHolder.getContext().setAuthentication(auth);
        return auth;
    }
}

