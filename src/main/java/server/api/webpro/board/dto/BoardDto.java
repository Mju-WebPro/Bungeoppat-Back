package server.api.webpro.board.dto;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardDto {

    private String title;
    private MultipartFile multipartFile;
    private String content;
}
