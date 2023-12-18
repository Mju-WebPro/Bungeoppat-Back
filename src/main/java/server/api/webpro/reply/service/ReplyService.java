package server.api.webpro.reply.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import server.api.webpro.board.dto.ReplyRequest;
import server.api.webpro.board.entity.Board;
import server.api.webpro.board.service.BoardService;
import server.api.webpro.reply.dto.ReplyRetrieveResponse;
import server.api.webpro.reply.entity.Reply;
import server.api.webpro.reply.repository.ReplyRepository;
import server.api.webpro.reply.state.ReplyResponse;
import server.api.webpro.user.entity.User;
import server.api.webpro.user.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final BoardService boardService;
    private final UserService userService;

    public void createReply(ReplyRequest replyRequest) {
        Board targetBoard = boardService.findById(replyRequest.getBoardId());
        User replyUser = userService.getUserById(replyRequest.getUserId());
        replyRepository.save(Reply.of(replyRequest, targetBoard, replyUser));
    }

    public List<ReplyRetrieveResponse> retrieveReply(Long id) {
        return replyRepository.findAll().stream()
                .filter(reply -> reply.getBoard().getId().equals(id))
                .map(ReplyRetrieveResponse::of)
                .collect(Collectors.toList());
    }
}
