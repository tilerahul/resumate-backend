package com.resumate.resumate_backend.Service;

import com.resumate.resumate_backend.Model.User;
import com.resumate.resumate_backend.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    public List<User> getAllUsers() {
        return userRepo.findAll();
    }


    public User getUserById(String id) {
        return userRepo.findById(id).orElse(null);
    }


    public String register(User request) {
        if (request.getFirstName() == null || request.getLastName() == null ||
                request.getEmail() == null || request.getPhone() == null ||
                request.getPassword() == null || request.getCpassword() == null) {
            return "All fields are mandatory";
        }

        if (userRepo.findByEmail(request.getEmail()).isPresent()) {
            return "Email already exists";
        }

        if (!request.getPassword().equals(request.getCpassword())) {
            return "Password does not match";
        }

        String hashedPassword = passwordEncoder.encode(request.getPassword());
        String imgUrl = "https://ui-avatars.com/api/?name=" + request.getFirstName() + " +" + request.getLastName();
        request.setPassword(hashedPassword);
        request.setImgUrl(imgUrl);

        userRepo.save(request);
        System.out.println("User Register");

        return "User registered successfully!";
    }

    public String deleteUser(String id) {
        Optional<User> user = userRepo.findById(id);
        if(user.isPresent()) {
            userRepo.deleteById(id);
            return "User deleted successfully";
        }
        return "User Not found";
    }
}
