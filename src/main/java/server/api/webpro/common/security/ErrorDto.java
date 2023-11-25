package server.api.webpro.common.security;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.net.URI;

@Setter
@Getter
@Builder
public class ErrorDto {
    private URI type;
    private String title;
    private String point;
    private int status;
    private String detail;
    private URI instance;
}
