package server.api.webpro.board.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.api.webpro.board.dto.BoardRequest;
import server.api.webpro.board.dto.BoardRetrieveResponse;
import server.api.webpro.board.entity.Board;
import server.api.webpro.board.repository.BoardRepository;
import server.api.webpro.user.entity.User;
import server.api.webpro.user.repository.UserRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class BoardService {

    private BoardRepository boardRepository;
    private UserRepository userRepository;

    public void createBoard(BoardRequest boardRequest) {
        User writer = userRepository.getReferenceById(boardRequest.getUserId());
        boardRepository.save(Board.of(boardRequest, writer));
    }

    public List<BoardRetrieveResponse> retrieveAllBoard() {
        return boardRepository.findAll()
                .stream()
                .map(BoardRetrieveResponse::of)
                .collect(Collectors.toList());
    }

    public void updateBoard(BoardRequest boardRequest) {
        Board targetBoard = boardRepository.getReferenceById(boardRequest.getBoardId());
        targetBoard.setTitle(boardRequest.getTitle());
        targetBoard.setDate(LocalDate.now());
        targetBoard.setContent(boardRequest.getContent());
        targetBoard.setPicture(boardRequest.getPicture());
        boardRepository.save(targetBoard);
    }
}
