package server.api.webpro.board.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import server.api.webpro.board.dto.BoardDto;
import server.api.webpro.board.dto.BoardRequest;
import server.api.webpro.board.dto.BoardRetrieveResponse;
import server.api.webpro.board.dto.BoardUpdateRequest;
import server.api.webpro.board.entity.Board;
import server.api.webpro.board.service.BoardService;
import server.api.webpro.board.state.BoardResponseType;
import server.api.webpro.common.utill.ApiResponse;

import java.io.IOException;
import java.util.List;

@RestController
//@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/board")
    private ApiResponse<Object> createBoard(@RequestBody BoardRequest boardRequest,
                                            @RequestParam("multipartFile") MultipartFile multipartFile) throws IOException {
        boardService.createBoard(boardRequest, multipartFile);
        return ApiResponse.of(BoardResponseType.CREATE_SUCCESS);
    }

    @GetMapping("/board/all")
    public ApiResponse<List<BoardRetrieveResponse>> retrieveAllBoard() {
        return ApiResponse.of(BoardResponseType.RETRIEVEALL_SUCCESS, boardService.retrieveBoard());
    }

    @GetMapping("/board/one")
    private ApiResponse<BoardDto> retrieveBoardById(@RequestParam Long id) {
        return ApiResponse.of(BoardResponseType.RETRIEVEBYID_SUCCESS, boardService.retrieveBoardById(id));
    }

    @PutMapping("/board/{board_id}")
    private ApiResponse<Object> updateBoard(@PathVariable(value = "board_id") Long id,
                                            @ModelAttribute BoardUpdateRequest boardUpdateRequest) throws IOException {
        boardService.updateBoard(id, boardUpdateRequest);
        return ApiResponse.of(BoardResponseType.UPDATE_SUCCESS);
    }

    @DeleteMapping()
    private ApiResponse<Object> deleteBoard(@RequestParam("board_id") Long id) {
        boardService.deleteBoard(id);
        return ApiResponse.of(BoardResponseType.DELETE_SUCCESS);
    }

}
