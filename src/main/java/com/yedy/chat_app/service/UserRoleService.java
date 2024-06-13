package com.yedy.chat_app.service;

import com.yedy.chat_app.entity.User;
import com.yedy.chat_app.entity.UserRole;
import com.yedy.chat_app.enums.ErrorMessages;
import com.yedy.chat_app.exception.Assert;
import com.yedy.chat_app.repository.UserRoleRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.apache.catalina.realm.UserDatabaseRealm.getRoles;

@Service
public class UserRoleService {

    private final UserRoleRepository userRoleRepository;

    public UserRoleService(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    public List<UserRole> findByUser(User user) {
        return userRoleRepository.findByUserAndDeletedFalse(user);
    }

    public List<String> getRoles(String userId) {
        return userRoleRepository.getRoles(userId);
    }

    public Set<GrantedAuthority> getGrantedRoles(User user) {
        Set<GrantedAuthority> grantedAuths = new HashSet<>();
        List<String> roles = getRoles(user.getId());
        for (String r : roles)
            grantedAuths.add(new SimpleGrantedAuthority("ROLE_" + r));
        return grantedAuths;
    }

    public UserRole save(UserRole userRole) {
        Assert.isNull(userRole.getUser(), ErrorMessages.ROLE_NAME_NULL);
        Assert.isNull(userRole.getRole(), ErrorMessages.ROLE_CODE_NULL);
        return userRoleRepository.save(userRole);
    }
}
