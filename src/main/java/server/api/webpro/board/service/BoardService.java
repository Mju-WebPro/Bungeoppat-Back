package server.api.webpro.board.service;

import com.amazonaws.services.kms.model.NotFoundException;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import server.api.webpro.board.dto.BoardDto;
import server.api.webpro.board.dto.BoardRequest;
import server.api.webpro.board.dto.BoardRetrieveResponse;
import server.api.webpro.board.dto.BoardUpdateRequest;
import server.api.webpro.board.entity.Board;
import server.api.webpro.board.repository.BoardRepository;
import server.api.webpro.user.entity.User;
import server.api.webpro.user.service.UserService;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserService userService;
    private final S3UploadService s3UploadService;

    @Transactional
    public void createBoard(BoardRequest boardRequest, MultipartFile multipartFile) throws IOException {
        checkDuplicateBoard(boardRequest);
        String imageUrl = s3UploadService.saveFile(multipartFile);
        User boardUser = userService.getUserById(boardRequest.getUserId());
        boardRepository.save(Board.of(boardRequest, multipartFile, imageUrl, boardUser));
    }

    public List<BoardRetrieveResponse> retrieveBoard() {
        return boardRepository.findAll()
                .stream().map(BoardRetrieveResponse::of).collect(Collectors.toList());
    }

    public void deleteBoard(Long id) {
        checkBoardExist(id);
        Board targetBoard = boardRepository.getReferenceById(id);
        s3UploadService.deleteImage(targetBoard.getFileName());
        boardRepository.delete(targetBoard);
    }

    public BoardDto retrieveBoardById(Long id) {
        checkBoardExist(id);
        Board targetBoard = boardRepository.getReferenceById(id);
        return BoardDto.of(targetBoard);
    }

    public void updateBoard(Long id, BoardUpdateRequest boardUpdateRequest) throws IOException {
        checkBoardExist(id);
        Board targetBoard = boardRepository.getReferenceById(id);
        targetBoard.update(boardUpdateRequest.getTitle(), boardUpdateRequest.getContent());
        if(targetBoard.getFileName() != boardUpdateRequest.getMultipartFile().getOriginalFilename()) {
            s3UploadService.deleteImage(targetBoard.getFileName());
            String imageUrl = s3UploadService.saveFile(boardUpdateRequest.getMultipartFile());
            targetBoard.updateImage(imageUrl);
        }
    }

    private void checkDuplicateBoard(BoardRequest boardRequest) {
//        if(boardRepository.existsBy)
    }

    private void checkBoardExist(Long id) {
        boardRepository.findById(id).orElseThrow(() -> new NotFoundException("Board Not Found!"));
    }

}
