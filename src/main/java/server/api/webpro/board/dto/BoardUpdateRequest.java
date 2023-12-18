package server.api.webpro.board.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@Builder
public class BoardUpdateRequest {

    private Long userId;
    private String title;
    private String content;
    private MultipartFile multipartFile;
}
