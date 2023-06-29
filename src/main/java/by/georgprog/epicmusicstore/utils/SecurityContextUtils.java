package by.georgprog.epicmusicstore.utils;

import by.georgprog.epicmusicstore.config.security.UserDetailsImpl;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.context.SecurityContextHolder;

@UtilityClass
public class SecurityContextUtils {

    public UserDetailsImpl getUserFromSecurityContext() {
        return (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
