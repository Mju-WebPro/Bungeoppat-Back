package server.api.webpro.fishShapedBun.control;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import server.api.webpro.fishShapedBun.service.FishShapedBunService;

@RestController
@RequiredArgsConstructor
public class FishShapedBunControl {

    private final FishShapedBunService fishShapedBunService;
}
