package server.api.webpro.board.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import server.api.webpro.board.dto.BoardRequest;
import server.api.webpro.board.dto.BoardRetrieveResponse;
import server.api.webpro.board.service.BoardService;
import server.api.webpro.board.state.BoardResponseType;
import server.api.webpro.common.utill.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping()
    private ApiResponse<Object> createBoard(@RequestBody BoardRequest boardRequest) {
        boardService.createBoard(boardRequest);
        return ApiResponse.of(BoardResponseType.CREATE_SUCCESS);
    }

    @GetMapping("/all")
    private ApiResponse<List<BoardRetrieveResponse>> retrieveAllBoard() {
        return ApiResponse.of(BoardResponseType.RETRIEVE_ALL, boardService.retrieveAllBoard());
    }

    @PutMapping()
    private ApiResponse<Object> updateBoard(@RequestBody BoardRequest boardRequest) {
        boardService.updateBoard(boardRequest);
        return ApiResponse.of(BoardResponseType.UPDATE_SUCCESS);
    }

}
