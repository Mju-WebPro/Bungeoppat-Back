package server.api.webpro.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import server.api.webpro.board.dto.BoardDto;
import server.api.webpro.board.entity.Board;
import server.api.webpro.board.repository.BoardRepository;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final S3UploadService s3UploadService;

    public void createBoard(BoardDto boardDto) throws IOException {

        String imageUrl = s3UploadService.saveFile(boardDto.getMultipartFile());

        boardRepository.save(Board.of(boardDto, imageUrl));
    }
}
