package com.yedy.chat_app.controller;

import com.yedy.chat_app.consts.BCrypto;
import com.yedy.chat_app.entity.Profile;
import com.yedy.chat_app.entity.Role;
import com.yedy.chat_app.entity.User;
import com.yedy.chat_app.entity.UserRole;
import com.yedy.chat_app.enums.Gender;
import com.yedy.chat_app.enums.Roles;
import com.yedy.chat_app.repository.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/test")
public class TestController {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;
    private final AuthsRepository authsRepository;
    private final ProfileRepository profileRepository;

    public TestController(UserRepository userRepository, RoleRepository roleRepository, UserRoleRepository userRoleRepository, AuthsRepository authsRepository, ProfileRepository profileRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userRoleRepository = userRoleRepository;
        this.authsRepository = authsRepository;
        this.profileRepository = profileRepository;
    }


    @Transactional
    @GetMapping(value = "test")
    public String test() {
        userRoleRepository.deleteAll();
        roleRepository.deleteAll();
        ;
        authsRepository.deleteAll();
        userRepository.deleteAll();
        List<User> users = new ArrayList<>();
        User yusuf = userRepository.save(createUser("yusuf", "yusuf123@gmail.com"));
        User ethem = userRepository.save(createUser("ethem", "ethem123@gmail.com"));
        User yasin = userRepository.save(createUser("yasin", "yasin123@gmail.com"));
        User dogu = userRepository.save(createUser("dogu", "dogu123@gmail.com"));
        User test = userRepository.save(createUser("test", "test123@gmail.com"));

        Role adminRole = roleRepository.save(createRole(Roles.ADMIN, "Admin"));
        Role user = roleRepository.save(createRole(Roles.USER, "Kullanıcı"));

        List<Profile> profiles = new ArrayList<>();
        profiles.add(createProfile(yusuf, "yusuf", 29, Gender.MAN, "test", ""));
        profiles.add(createProfile(ethem, "ethem", 29, Gender.MAN, "test", ""));
        profiles.add(createProfile(yasin, "yasin", 29, Gender.MAN, "test", ""));
        profiles.add(createProfile(dogu, "dogu", 29, Gender.MAN, "test", ""));
        profiles.add(createProfile(test, "test", 29, Gender.MAN, "test", ""));

        profileRepository.saveAll(profiles);

        List<UserRole> roles = new ArrayList<>();
        roles.add(createUserRole(yasin, user));
        roles.add(createUserRole(yusuf, user));
        roles.add(createUserRole(ethem, user));
        roles.add(createUserRole(dogu, user));
        roles.add(createUserRole(test, user));
        userRoleRepository.saveAll(roles);

        return "başarılı";
    }

    private User createUser(String userName, String email) {
        User user = new User();
        user.setUsername(userName);
        user.setPhoneNumber("05555555555");
        user.setEmail(email);
        user.setPassword(BCrypto.getEncoder().encode("123"));
        return user;
    }

    private Role createRole(Roles code, String name) {
        Role role = new Role();
        role.setCode(code);
        role.setName(name);
        return role;
    }

    private UserRole createUserRole(User user, Role role) {
        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);
        return userRole;
    }

    private Profile createProfile(User user, String name, int age, Gender gender, String bio, String ppUrl) {
        Profile profile = new Profile();
        profile.setUserId(user.getId());
        profile.setName(name);
        profile.setAge(age);
        profile.setGender(gender);
        profile.setBio(bio);
        profile.setProfilePhotoUrl(ppUrl);
        profile.setInterests(new ArrayList<>());
        profile.setPhotos(new ArrayList<>());
        return profile;
    }
}
