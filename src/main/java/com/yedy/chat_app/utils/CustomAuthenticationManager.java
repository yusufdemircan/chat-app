package com.yedy.chat_app.utils;

import com.yedy.chat_app.dto.YedyTokenDto;
import com.yedy.chat_app.entity.User;
import com.yedy.chat_app.enums.ErrorMessages;
import com.yedy.chat_app.exception.Assert;
import com.yedy.chat_app.service.TokenService;
import com.yedy.chat_app.service.UserRoleService;
import com.yedy.chat_app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationManager implements AuthenticationManager {
    private final UserService userService;
    private final TokenService tokenService;
    private final UserRoleService userRoleService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (authentication instanceof BearerTokenAuthenticationToken bearer) {
            YedyTokenDto t = tokenService.validateToken(bearer.getToken());
            User user = userService.findById(t.getUserId());
            Assert.isNull(user, ErrorMessages.USER_NOT_FOUND);
            Set<GrantedAuthority> gRoles = userRoleService.getGrantedRoles(user);
            return new UsernamePasswordAuthenticationToken(user.getId(), null, gRoles);
        }
        return null;
    }
}