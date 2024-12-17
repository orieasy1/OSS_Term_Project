package com.team3.ossproject.global.util;

import com.team3.ossproject.auth.exception.AuthErrorCode;
import com.team3.ossproject.auth.exception.AuthException;
import com.team3.ossproject.user.domain.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityUtil {

    private SecurityUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static UserDetails getCurrentUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            return (UserDetails)authentication.getPrincipal();
        }
        throw new AuthException(AuthErrorCode.INVALID_TOKEN);
    }

    public static Long getCurrentUserId() {
        UserDetails userDetails = getCurrentUserDetails();
        if (userDetails instanceof User) {  // Assuming SecurityUser extends UserDetails
            return ((User)userDetails).getId();
        }
        throw new AuthException(AuthErrorCode.INVALID_TOKEN);
    }
}