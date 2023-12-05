package server.api.webpro.fishShapedBun.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import server.api.webpro.fishShapedBun.service.FishShapedBunService;

@RestController
@RequiredArgsConstructor
public class FishShapedBunController {

    private final FishShapedBunService fishShapedBunService;
}
