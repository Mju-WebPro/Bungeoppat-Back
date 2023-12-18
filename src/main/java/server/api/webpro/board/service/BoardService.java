package server.api.webpro.board.service;

import com.amazonaws.services.kms.model.NotFoundException;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import server.api.webpro.board.dto.*;
import server.api.webpro.board.entity.Board;
import server.api.webpro.board.repository.BoardRepository;
import server.api.webpro.user.entity.User;
import server.api.webpro.user.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserService userService;


    public void createBoard(BoardRequest boardRequest) {
        checkDuplicateBoard(boardRequest);
        User boardUser = userService.getUserById(boardRequest.getUserId());
        boardRepository.save(Board.notImage(boardRequest, boardUser));
    }

//    public void createReply(ReplyRequest replyRequest) {
//        Board targetBoard = boardRepository.getReferenceById(replyRequest.getBoardId());
//        User replyUser = userService.getUserById(replyRequest.getUserId());
//        targetBoard.setReply(replyRequest.getReply());
//    }

    public List<BoardRetrieveResponse> retrieveBoard() {
        return boardRepository.findAll()
                .stream().map(BoardRetrieveResponse::of).collect(Collectors.toList());
    }

    public void deleteBoard(Long id) {
        checkBoardExist(id);
        Board targetBoard = boardRepository.getReferenceById(id);
        boardRepository.delete(targetBoard);
    }

    public BoardDto retrieveBoardById(Long id) {
        checkBoardExist(id);
        Board targetBoard = boardRepository.getReferenceById(id);
        return BoardDto.of(targetBoard);
    }

    public void updateBoard(Long id, BoardUpdateRequest boardUpdateRequest) {
        checkBoardExist(id);
        Board targetBoard = boardRepository.getReferenceById(id);
        targetBoard.update(boardUpdateRequest.getTitle(), boardUpdateRequest.getContent());
    }

    private void checkDuplicateBoard(BoardRequest boardRequest) {
//        if(boardRepository.existsBy)
    }

    private void checkBoardExist(Long id) {
        boardRepository.findById(id).orElseThrow(() -> new NotFoundException("Board Not Found!"));
    }


}
