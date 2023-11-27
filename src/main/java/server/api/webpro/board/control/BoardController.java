package server.api.webpro.board.control;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import server.api.webpro.board.service.BoardService;

@Controller
@RequestMapping("board")
@NoArgsConstructor
public class BoardController {

    private BoardService boardService;

}
