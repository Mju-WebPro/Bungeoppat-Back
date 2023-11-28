package server.api.webpro.common.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import server.api.webpro.common.exception.BizException;

import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static server.api.webpro.common.security.state.JwtException.*;
@Slf4j
@RequiredArgsConstructor
public class AuthFilter extends OncePerRequestFilter {
    private final String BEARER_PREFIX = "Bearer ";
    private final TokenProvider tokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws BizException, ServletException, IOException {
        String jwt = resolveToken(request);
        String requestURI = request.getRequestURI();
        try {
            if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
                boolean isAdditionalInfoProvided = tokenProvider.getAdditionalInfoProvided(jwt);
                if (isAdditionalInfoProvided) {
                    Authentication authentication = tokenProvider.getAuthentication(jwt);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    log.debug("Security Context에 '{}' 인증 정보를 저장했습니다, uri: {}", authentication.getName(), requestURI);
                } else {
                    throw new BizException(ADDITIONAL_REQUIRED_TOKEN);
                }
            }

        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            request.setAttribute("exception", MAL_FORMED_TOKEN.getCode());
        } catch( BizException e ){
            if (e.getBaseExceptionType() == ILLEGAL_TOKEN) {
                request.setAttribute("exception", ILLEGAL_TOKEN.getCode());}
            request.setAttribute("exception", ADDITIONAL_REQUIRED_TOKEN.getCode());
        } catch (ExpiredJwtException e ) {
            request.setAttribute("exception", EXPIRED_TOKEN.getCode());
        } catch (UnsupportedJwtException e ) {
            request.setAttribute("exception", UNSUPPORTED_TOKEN.getCode());
        } catch (Exception e) {
            // 에러 처리 로직 추가
            log.error("================================================");
            log.error("JwtFilter - doFilterInternal() 오류발생");
            log.error("token : {}", jwt);
            log.error("Exception Message : {}", e.getMessage());
            log.error("Exception StackTrace : {");
            e.printStackTrace();
            log.error("}");
            log.error("================================================");
            request.setAttribute("exception", UNKNOWN_ERROR.getCode());
        }
        filterChain.doFilter(request, response);
    }

    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION);
        log.debug(bearerToken);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {
            return bearerToken.substring(BEARER_PREFIX.length());
        }
        return null;
    }
}
