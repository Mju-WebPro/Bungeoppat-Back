package server.api.webpro.reply.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import server.api.webpro.board.dto.ReplyRequest;
import server.api.webpro.board.state.BoardResponseType;
import server.api.webpro.common.utill.ApiResponse;
import server.api.webpro.reply.dto.ReplyRetrieveResponse;
import server.api.webpro.reply.service.ReplyService;
import server.api.webpro.reply.state.ReplyResponse;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;

    @PostMapping("/reply")
    public ApiResponse<Object> createReply(@RequestBody ReplyRequest replyRequest) {
        replyService.createReply(replyRequest);
        return ApiResponse.of(ReplyResponse.CREATE_SUCCESS);
    }

    @GetMapping("/reply/{board_id}")
    public ApiResponse<List<ReplyRetrieveResponse>> retrieveReply(@PathVariable(name = "board_id") Long id) {
        return ApiResponse.of(ReplyResponse.RETRIEVE_SUCCESS, replyService.retrieveReply(id));
    }

}
