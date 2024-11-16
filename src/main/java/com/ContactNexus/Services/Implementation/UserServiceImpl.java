package com.ContactNexus.Services.Implementation;

import com.ContactNexus.Entities.User;
import com.ContactNexus.Helper.AppConstants;
import com.ContactNexus.Helper.ResourceNotFoundException;
import com.ContactNexus.Repositories.UserRepositories;
import com.ContactNexus.Services.UserService;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepositories userRepositories;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public User saveUser(User user) {

        String userId = UUID.randomUUID().toString();
        user.setUserId(userId);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoleList(List.of(AppConstants.ROLE_USER));
        return userRepositories.save(user);
    }

    @Override
    public Optional<User> getUserById(String id) {
        return userRepositories.findById(id);
    }

    @Override
    public Optional<User> updateUser(User user) {
      User currentUser=  userRepositories.findById(user.getUserId()).orElseThrow(() -> new ResourceNotFoundException("user not found"));
      currentUser.setName(user.getName());
        currentUser.setName(user.getName());
        currentUser.setEmail(user.getEmail());
        currentUser.setAbout(user.getAbout());
        currentUser.setPhoneNumber(user.getPhoneNumber());
        currentUser.setProfilePic(user.getProfilePic());

        currentUser.setEmailVerified(user.isEmailVerified());
        currentUser.setPhoneVerified(user.isPhoneVerified());
        currentUser.setProvider(user.getProvider());
        currentUser.setProviderUserId(user.getProviderUserId());
        // save the user in database
        User save = userRepositories.save(currentUser);
        return Optional.ofNullable(save);
    }

    @Override
    public void deleteUser(String id) {

    }
    @Override
    public boolean isUserExist(String userId) {
        return false;
    }

    @Override
    public boolean isUserExistByEmail(String email) {
        return false;
    }

    @Override
    public List<User> getAllUsers() {
        return List.of();
    }
}
