package server.api.webpro.store.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import server.api.webpro.store.entity.Place;
import server.api.webpro.store.repository.StoreRepository;
import server.api.webpro.store.service.PlaceService;
import server.api.webpro.store.service.StoreService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/places")
public class PlaceController {
    private final PlaceService placeService;
    @GetMapping("/search")
    public List<Place> searchAndDisplayPlaces(@RequestParam("keyword") String keyword) {
        return placeService.searchAndSavePlaces(keyword);
    }
}
