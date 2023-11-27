package server.api.webpro.board.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import server.api.webpro.board.repository.BoardRepository;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class BoardService {

    private BoardRepository boardRepository;

}
