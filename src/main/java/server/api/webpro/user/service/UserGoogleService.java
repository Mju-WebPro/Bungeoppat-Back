package server.api.webpro.user.service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.api.webpro.common.exception.BizException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static server.api.webpro.user.state.UserResponseType.*;


@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserGoogleService{
    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String GOOGLE_SNS_CLIENT_ID;
    @Value("${spring.security.oauth2.client.registration.google.client-secret}")
    private String GOOGLE_SNS_CLIENT_SECRET;
    @Value("${spring.security.oauth2.client.registration.google.redirect-uri}")
    private String GOOGLE_SNS_REDIRECT_URL;

    public String getOauthRedirectURL(String code) {
        StringBuilder sb = new StringBuilder();
        sb.append("grant_type=authorization_code");
        sb.append("&client_id="+GOOGLE_SNS_CLIENT_ID);
        sb.append("&redirect_uri="+GOOGLE_SNS_REDIRECT_URL);
        sb.append("&client_secret="+GOOGLE_SNS_CLIENT_SECRET);
        sb.append("&code=" + code);
        sb.append("&access_type=offline");


        return sb.toString();
    }

    public JsonObject connect(String reqURL, String token) {
        try {
            System.out.println(reqURL);
            System.out.println(token);
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Authorization", "Bearer " + token);

            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuffer response = new StringBuffer();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            JsonObject json = JsonParser.parseString(response.toString()).getAsJsonObject();
            return json;
        } catch (IOException e) {
            log.info(CONNECT_ERROR.getMessage());
            throw new BizException(CONNECT_ERROR);
        }

    }
    public String getEmail(JsonObject userInfo) {
        if (userInfo.getAsJsonObject().has("email")) {
            return userInfo.getAsJsonObject().get("email").getAsString();
        }
        throw new BizException(NOT_FOUND_EMAIL);
    }


}
