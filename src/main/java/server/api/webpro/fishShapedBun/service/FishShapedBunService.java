package server.api.webpro.fishShapedBun.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import server.api.webpro.fishShapedBun.repository.FishShapedBunRepository;

@Service
@RequiredArgsConstructor
public class FishShapedBunService {

    private final FishShapedBunRepository fishShapedBunRepository;
}
