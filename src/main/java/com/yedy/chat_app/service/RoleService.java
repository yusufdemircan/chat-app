package com.yedy.chat_app.service;

import com.yedy.chat_app.entity.Role;
import com.yedy.chat_app.enums.ErrorMessages;
import com.yedy.chat_app.enums.Roles;
import com.yedy.chat_app.exception.Assert;
import com.yedy.chat_app.exception.YedyException;
import com.yedy.chat_app.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role findByCode(Roles code) {
        Assert.isNull(code, ErrorMessages.ROLE_CODE_NULL);
        return roleRepository.findByCodeAndDeletedFalse(code);
    }

    public Role save(Role role) {
        Assert.isEmpty(role.getName(), ErrorMessages.ROLE_NAME_NULL);
        Assert.isNull(role.getCode(), ErrorMessages.ROLE_CODE_NULL);
        if (role.getId() == null)
            Assert.notNull(findByCode(role.getCode()), ErrorMessages.SAME_ROLE_CODE);
        else {
            Role r = roleRepository.findById(role.getId()).orElseThrow(()->new YedyException("role bulunamadı"));
            if (!r.getCode().equals(role.getCode()))
                Assert.notNull(findByCode(role.getCode()), ErrorMessages.SAME_ROLE_CODE);
        }
        return roleRepository.save(role);
    }
}
