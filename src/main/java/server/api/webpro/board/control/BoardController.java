package server.api.webpro.board.control;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.api.webpro.board.dto.BoardDto;
import server.api.webpro.board.service.BoardService;

import java.io.IOException;

@RestController
@RequestMapping("board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping()
    public void createBoard(BoardDto boardDto) throws IOException {
        boardService.createBoard(boardDto);
    }

}
