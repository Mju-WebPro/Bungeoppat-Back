package server.api.webpro.common.security.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import server.api.webpro.common.exception.BizException;
import server.api.webpro.common.security.service.CustomUserDetails;
import server.api.webpro.user.entity.User;

import java.util.Objects;

import static server.api.webpro.common.security.exception.SecurityExceptionList.REQUIRED_LOGGED_IN;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Transactional
public class SecurityUtils {

    public static User getLoggedInUser() {
        try {
            return
                    ((CustomUserDetails) Objects.requireNonNull(SecurityContextHolder.getContext().getAuthentication()).getPrincipal()).getUser();
        } catch (NullPointerException e) {
            throw new BizException(REQUIRED_LOGGED_IN);
        }
    }

}