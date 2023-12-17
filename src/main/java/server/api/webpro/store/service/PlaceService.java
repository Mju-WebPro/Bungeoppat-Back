package server.api.webpro.store.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import server.api.webpro.store.entity.Place;
import server.api.webpro.store.repository.PlaceRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PlaceService {
    private final PlaceRepository placeRepository;

    public List<Place> searchAndSavePlaces(String keyword) {
        List<Place> places = new ArrayList<>();

        String apiUrl = "https://dapi.kakao.com/v2/local/search/keyword.json?query=" + keyword;

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "KakaoAK d309db78b46ed9baa5e4381087afce06");
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.GET, entity, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                JsonNode rootNode = objectMapper.readTree(response.getBody());
                if (rootNode != null && rootNode.has("documents")) {
                    JsonNode documents = rootNode.get("documents");
                    for (JsonNode document : documents) {
                        String placeName = document.get("place_name").asText();
                        double latitude = document.get("y").asDouble();
                        double longitude = document.get("x").asDouble();

                        Place place = new Place();
                        place.setPlaceName(placeName);
                        place.setLatitude(latitude);
                        place.setLongitude(longitude);

                        places.add(place); // 장소를 리스트에 추가
                    }
                }
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return places;
    }
}
