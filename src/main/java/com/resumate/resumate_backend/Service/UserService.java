package com.resumate.resumate_backend.Service;

import com.resumate.resumate_backend.Model.User;
import com.resumate.resumate_backend.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User createUser(User user) {
        return userRepo.save(user);
    }

    public User getUserById(String id) {
        return userRepo.findById(id).orElse(null);
    }
}
